package view;

import model.game.Game;
import model.game.node.Node;

import java.util.List;

/**
 * Defines {@link Game} methods usable by the {@link GameView}
 */
public interface ViewableGame {
    /**
     * Returns the currently active {@link Node}.
     *
     * @return the current node
     */
    Node getCurrentNode();

    /**
     * Returns the {@link Game} log, containing the game event history.
     *
     * @return a list of strings, representing the game log
     */
    List<String> getLog();

    /**
     * Returns the player's current health points.
     *
     * @return the player's current hp
     */
    int getPlayerHp();

    /**
     * Returns the player's max health points.
     *
     * @return the player's max hp
     */
    int getPlayerMaxHp();
}
