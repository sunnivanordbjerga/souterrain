package no.uib.inf101.model.game.node;

import no.uib.inf101.model.game.Choice;

import java.util.Objects;

/**
 * A {@link Node} containing text, automatically proceeding to the next node on enter.
 */
public class TransitionNode extends AbstractNode {

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
        Objects.requireNonNull(nextNode, "NextNode cannot be null");

        this.addChoice(new Choice("Continue", nextNode));
    }
}
