package no.uib.inf101.model.game;

import no.uib.inf101.controller.ControllableGame;
import no.uib.inf101.model.entity.enemy.Enemy;
import no.uib.inf101.model.entity.player.Player;
import no.uib.inf101.model.game.narration.Choice;
import no.uib.inf101.model.game.narration.StoryBuilder;
import no.uib.inf101.model.game.narration.node.CombatNode;
import no.uib.inf101.model.game.narration.node.Node;
import no.uib.inf101.view.ViewableGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Manages and directs the main game logic.
 */
public class Game implements ControllableGame, ViewableGame {
    private final Random random;
    private final Player player;
    private static final StoryBuilder STORYBUILDER = new StoryBuilder();
    private Node currentNode;
    private final List<String> log;
    private boolean gameOver;

    /**
     * Creates a {@link Game}.
     */
    public Game() {
        this.log = new ArrayList<>();
        this.player = new Player();
        setCurrentNode(STORYBUILDER.buildStory());
        this.random = new Random();
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
     * Auto-resolves a combat between the {@link Player} and the given {@link Enemy},
     * returning whether the player survives or dies to the relevant {@link CombatNode}.
     *
     * @param enemy the enemy to fight
     * @return {@code true} if the player wins, or {@code false} if they die
     */
    public boolean resolveCombat(Enemy enemy) {
        while (player.isAlive() && enemy.isAlive()) {

            int playerDamage = player.attack(enemy, random);
            log(randomPlayerAttackText(enemy, playerDamage));

            if (!enemy.isAlive()) {
                return true;
            }

            int enemyDamage = enemy.attack(player, random);
            log(randomEnemyAttackText(enemy, enemyDamage));

            if (!player.isAlive()) {
                return false;
            }
        }
        return player.isAlive();
    }

    /**
     * Returns a shared {@link Random} instance for this {@link Game}.
     * Used for randomized outcomes.
     *
     * @return the Random instance
     */
    public Random getRandom() {
        return random;
    }

    private String randomPlayerAttackText(Enemy enemy, int damage) {
        String enemyName = enemy.getDisplayName();
        float enemyHpRatio = (float) enemy.getHp() / enemy.getMaxHp();

        List<String> variants;

        if (enemyHpRatio <= 0.2f) {
            variants = List.of(
                    "The " + enemyName + " is at the brink of collapse as you hit it for " + damage + ".",
                    "You land a devastating blow on the " + enemyName +
                            ", dealing " + damage + " damage.",
                    "The " + enemyName + " sways where it stands. You hit it for " + damage + ".",
                    "The " + enemyName + " reels as you land a devastating blow for " + damage + "."

            );
        } else if (enemyHpRatio <= 0.5f) {
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
        float playerHpRatio = (float) player.getHp() / player.getMaxHp();

        List<String> variants;

        if (playerHpRatio <= 0.2f) {
            variants = List.of(
                    "The " + enemyName + " lunges at you, dealing " + damage + " damage. Your vision falters for a brief moment.",
                    "You barely hold onto consciousness as the " + enemyName + " strikes you for " + damage + ".",
                    "Panic sets in as the " + enemyName + " hits you for " + damage + ". You are barely standing.",
                    "Searing pain spreads through your body as you take " + damage + " damage."
            );
        } else if (playerHpRatio <= 0.5f) {
            variants = List.of(
                    "You barely manage to maintain your stance as the " + enemyName + " hits you for " + damage + ".",
                    "You barely evade a fatal blow, redirecting the " + enemyName + "'s attack to a less critical point for " + damage + ".",
                    "Your weapon lands a solid hit on the " + enemyName + " for " + damage + "."
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
}
