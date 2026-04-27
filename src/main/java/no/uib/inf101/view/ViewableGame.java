package no.uib.inf101.view;

import no.uib.inf101.model.game.Game;
import no.uib.inf101.model.game.narration.node.Node;

import java.util.List;

/**
 * Defines {@link Game} methods usable by the {@link GameView}
 */
public interface ViewableGame {

    /**
     * Returns the {@link Game} log, containing the game event history.
     *
     * @return a list of strings, representing the game log
     */
    List<String> getLog();

    /**
     * Returns the currently active {@link Node}.
     *
     * @return the current node
     */
    Node getCurrentNode();
}
