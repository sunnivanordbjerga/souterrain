package no.uib.inf101.controller;

import no.uib.inf101.model.game.Game;

/**
 * Defines {@link Game} methods usable by the {@link GameController}
 */
public interface ControllableGame {

    /**
     * Returns whether the game is over.
     *
     * @return {@code true} if the game is over
     */
    boolean gameOver();

    //TODO: Implement ControllableGame
}
