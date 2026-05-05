package no.uib.inf101.controller;

import no.uib.inf101.model.game.Game;
import no.uib.inf101.model.game.narration.Choice;
import no.uib.inf101.model.game.narration.node.Node;

/**
 * Defines {@link Game} methods usable by the {@link GameController}
 */
public interface ControllableGame {

    /**
     * Returns the currently active {@link Node}.
     *
     * @return the current node
     */
    Node getCurrentNode();

    /**
     * Chooses one of the available {@link Choice}s from
     * the current {@link Node} based on the given index.
     *
     * @param index the index of the chosen {@link Choice}
     * @throws IllegalArgumentException if the index is out of bounds
     */
    void choose(int index);

    /**
     * Returns whether the game is over.
     *
     * @return {@code true} if the game is over
     */
    boolean isGameOver();
}
