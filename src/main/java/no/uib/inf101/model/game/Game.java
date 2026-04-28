package no.uib.inf101.model.game;

import no.uib.inf101.controller.ControllableGame;
import no.uib.inf101.model.entity.enemy.Enemy;
import no.uib.inf101.model.entity.player.Player;
import no.uib.inf101.model.game.narration.StoryBuilder;
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
        this.random = new Random();
        this.player = new Player();
        this.storyBuilder = new StoryBuilder();
        this.currentNode = storyBuilder.buildStory();
        this.log = new ArrayList<>();
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public Node getCurrentNode() {
        return currentNode;
    }

    @Override
    public boolean gameOver() {
        return gameOver;
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
     * Sets the current {@link Node} to the given one.
     *
     * @param node the node to change to
     */
    public void setCurrentNode(Node node) {
        this.currentNode = node;
    }

    public boolean resolveCombat(Enemy enemy){
        return false;
        //TODO
    }

    /**
     * Sets gameOver to true
     */
    public void setGameOver() {
        gameOver = true;
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
