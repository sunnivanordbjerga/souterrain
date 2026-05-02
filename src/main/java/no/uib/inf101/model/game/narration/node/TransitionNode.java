package no.uib.inf101.model.game.narration.node;

import java.util.Objects;

/**
 * A {@link Node} containing text, automatically proceeding to the next node on enter.
 */
public class TransitionNode extends AbstractNode {
    private final Node nextNode;

    /**
     * Creates a {@link TransitionNode} with the given text and linked nextNode.
     *
     * @param text     the text corresponding to this node; cannot be blank
     * @param nextNode the {@link Node} to proceed to
     * @throws NullPointerException     if text  or nextNode is null
     * @throws IllegalArgumentException if text is blank
     */
    public TransitionNode(String text, Node nextNode) {
        super(text);
        this.nextNode = Objects.requireNonNull(nextNode, "NextNode cannot be null");
    }
}
