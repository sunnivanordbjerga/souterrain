package controller;

import model.game.Choice;
import model.game.Game;
import model.game.node.Node;

/**
 * Defines {@link Game} methods usable by the {@link GameController}
 */
public interface ControllableGame {
    /**
     * Chooses one of the available {@link Choice}s from
     * the current {@link Node}.
     *
     * @param choice selected choice
     * @throws IllegalArgumentException if the choice is not available from the current {@link Node}
     *                                  or if the {@link Choice} type is not NORMAL
     */
    void choose(Choice choice);

    /**
     * Resets the {@link Game} to it's initial state.
     */
    void reset();
}
