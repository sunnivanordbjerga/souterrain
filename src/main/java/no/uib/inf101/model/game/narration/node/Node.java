package no.uib.inf101.model.game.narration.node;

import no.uib.inf101.model.game.Game;
import no.uib.inf101.model.game.narration.Choice;

import java.util.List;

/**
 * Defines functionality for game event nodes
 * like a combat encounter or story progression.
 */
public interface Node {

    /**
     * Returns the text belonging to this {@link Node}
     *
     * @return the node text
     */
    String getText();

    /**
     * Returns the {@link Choice}s available from this {@link Node}.
     *
     * @return the list of the choices belonging to this node
     */
    List<Choice> getChoices();

    /**
     * Adds an option to the list of {@link Choice}s.
     * Ignores duplicates.
     *
     * @param choice the choice to add
     * @throws NullPointerException if choice is null
     */
    void addChoice(Choice choice);

    /**
     * Calls on the {@link Game} to execute any logic belonging to this {@link Node},
     * like starting a combat encounter or performing a skill check.
     *
     * @param game the game model to use
     */
    void onEnter(Game game);
}
