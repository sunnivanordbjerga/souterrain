package no.uib.inf101.model.game.narration;

import no.uib.inf101.model.game.narration.node.Node;

import java.util.Objects;

/**
 * Represents a choice connecting two {@link Node}s.
 *
 * @param text     the text describing this choice; cannot be blank
 * @param nextNode the resulting node from choosing this choice
 */
public record Choice(String text, Node nextNode) {

    /**
     * Creates a {@link Choice}.
     *
     * @param text     the text describing this choice; cannot be blank
     * @param nextNode the resulting node from choosing this choice
     * @throws NullPointerException     if text or nextNode is null
     * @throws IllegalArgumentException if text is blank
     */
    public Choice {
        Objects.requireNonNull(text, "Text cannot be null");
        Objects.requireNonNull(nextNode, "NextNode cannot be null");
        if (text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be blank");
        }
    }
}
