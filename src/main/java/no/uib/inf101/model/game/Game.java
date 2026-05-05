package no.uib.inf101.model.game;

import no.uib.inf101.controller.ControllableGame;
import no.uib.inf101.model.entity.Enemy;
import no.uib.inf101.model.entity.Player;
import no.uib.inf101.model.game.node.Node;
import no.uib.inf101.model.loot.Loot;
import no.uib.inf101.model.loot.LootFactory;
import no.uib.inf101.view.ViewableGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Responsible for the main game logic and holding the game state.
 */
public class Game implements ControllableGame, ViewableGame {
    private final Random random;
    private final Player player;
    private final List<String> log;
    private final LootFactory lootFactory;
    private Node currentNode;
    private boolean gameOver;

    /**
     * Creates a {@link Game}.
     */
    public Game() {
        this.random = new Random();
        this.log = new ArrayList<>();
        this.lootFactory = new LootFactory(random);
        this.player = new Player(random);
        StoryBuilder storyBuilder = new StoryBuilder(random);

        setCurrentNode(storyBuilder.buildStory());
    }

    @Override
    public Node getCurrentNode() {
        return currentNode;
    }

    @Override
    public void choose(int index) {
        if (gameOver) {
            return;
        }

        List<Choice> choices = currentNode.getChoices();

        if (index < 0 || index >= choices.size()) {
            throw new IllegalArgumentException("Index " + index + " out of bounds for choices of size " + choices.size());
        }

        Choice choice = choices.get(index);
        log("> " + choice.text());

        setCurrentNode(choice.nextNode());
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Sets gameOver to true
     */
    public void setGameOver() {
        gameOver = true;
    }

    /**
     * Sets the current {@link Node} to the one provided.
     *
     * @param node the node to change to
     */
    public void setCurrentNode(Node node) {
        this.currentNode = Objects.requireNonNull(node, "Node cannot be null");
        currentNode.onEnter(this);
    }

    /**
     * Adds a message to the {@link Game} log.
     *
     * @param text the text to log
     */
    public void log(String text) {
        Objects.requireNonNull(text, "Text cannot be null");
        if (text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be blank");
        }
        log.add(text);
    }

    /**
     * Simulates a probability roll, returning whether it succeeded based on a given successChance.
     *
     * @param successChance a number between 0 (guaranteed failure) and 1 (guaranteed success)
     * @return true if the roll is successful
     * @throws IllegalArgumentException if successChance is not between 0-1
     */
    public boolean roll(double successChance) {
        if (successChance < 0 || successChance > 1) {
            throw new IllegalArgumentException("SuccessChance must be between 0 and 1");
        }
        return random.nextDouble() < successChance;
    }

    /**
     * Auto-resolves a combat between the {@link Player} and the given {@link Enemy},
     * returning whether the player survives or dies to the relevant {@link CombatNode}.
     *
     * @param enemy the enemy to fight
     * @return {@code true} if the player wins, or {@code false} if they die
     */
    public boolean resolveCombat(Enemy enemy) {
        while (player.isAlive() && enemy.isAlive()) {

            int playerDamage = player.attack(enemy);
            log(randomPlayerAttackText(enemy, playerDamage));

            if (!enemy.isAlive()) {
                handleLoot(enemy);
                return true;
            }

            int enemyDamage = enemy.attack(player);
            log(randomEnemyAttackText(enemy, enemyDamage));

            if (!player.isAlive()) {
                return false;
            }
        }
        return player.isAlive();
    }

    private String randomPlayerAttackText(Enemy enemy, int damage) {
        String enemyName = enemy.getDisplayName();
        double enemyHpRatio = (double) enemy.getHp() / enemy.getMaxHp();

        List<String> variants;

        if (enemyHpRatio <= 0.2) {
            variants = List.of(
                    "The " + enemyName + " is at the brink of collapse as you hit it for " + damage + ".",
                    "You land a devastating blow on the " + enemyName +
                            ", dealing " + damage + " damage.",
                    "The " + enemyName + " sways where it stands. You hit it for " + damage + ".",
                    "The " + enemyName + " reels as you land a devastating blow for " + damage + "."

            );
        } else if (enemyHpRatio <= 0.5) {
            variants = List.of(
                    "You hit the " + enemyName + " for " + damage + ". It staggers for a moment.",
                    "You find an opening in the " + enemyName + "'s defenses, hitting it for " + damage + ".",
                    "Your weapon lands a solid hit on the " + enemyName + " for " + damage + "."
            );
        } else {
            variants = List.of(
                    "You swing your weapon at the " + enemyName + ", hitting it for " + damage + ".",
                    "You strike the " + enemyName + ", dealing " + damage + " damage.",
                    "You land a blow on the " + enemyName + ", hitting it for " + damage + "."
            );

        }
        return variants.get(random.nextInt(variants.size()));
    }

    private String randomEnemyAttackText(Enemy enemy, int damage) {
        String enemyName = enemy.getDisplayName();
        double playerHpRatio = (double) player.getHp() / player.getMaxHp();

        List<String> variants;

        if (playerHpRatio <= 0.2) {
            variants = List.of(
                    "The " + enemyName + " lunges at you, dealing " + damage + " damage. Your vision falters for a brief moment.",
                    "You barely hold onto consciousness as the " + enemyName + " strikes you for " + damage + ".",
                    "Panic sets in as the " + enemyName + " hits you for " + damage + ". You are barely standing.",
                    "Searing pain spreads through your body as you take " + damage + " damage."
            );
        } else if (playerHpRatio <= 0.5) {
            variants = List.of(
                    "You barely manage to maintain your stance as the " + enemyName + " hits you for " + damage + ".",
                    "You narrowly evade a fatal blow, redirecting the " + enemyName + "'s attack to a less critical point for " + damage + ".",
                    "The " + enemyName + " lands a solid hit on you for " + damage + "."
            );
        } else {
            variants = List.of(
                    "The " + enemyName + " swings at you, hitting you for " + damage + ".",
                    "The " + enemyName + " strikes you, dealing " + damage + " damage.",
                    "The " + enemyName + " lands a blow on you, hitting you for " + damage + "."
            );
        }
        return variants.get(random.nextInt(variants.size()));
    }

    private void handleLoot(Enemy enemy) {
        List<Loot> drops = enemy.dropLoot(lootFactory);
        for (Loot loot : drops) {
            player.addItem(loot);
            log("You find: " + loot.getDisplayName());
        }
    }
}
