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
    private final StoryBuilder storyBuilder;
    private Node currentNode;
    private final List<String> log;
    private boolean gameOver;

    /**
     * Creates a {@link Game}.
     */
    public Game() {
        this.storyBuilder = new StoryBuilder();
        this.log = new ArrayList<>();
        this.player = new Player();
        setCurrentNode(storyBuilder.buildStory());
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
        while(player.isAlive() && enemy.isAlive()){
            player.attack(enemy, random);
            enemy.attack(player, random);
        }
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
}
