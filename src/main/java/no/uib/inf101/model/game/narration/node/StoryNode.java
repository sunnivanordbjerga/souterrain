package no.uib.inf101.model.game.narration.node;

import no.uib.inf101.model.game.narration.Choice;

/**
 * Represents a simple {@link Node}, containing text
 * and a list of {@link Choice}s.
 */
public class StoryNode extends AbstractNode {

    /**
     * Creates a {@link StoryNode} with the given text
     * and empty list of {@link Choice}s.
     *
     * @param text the text corresponding to this node
     */
    public StoryNode(String text) {
        super(text);
    }
}
