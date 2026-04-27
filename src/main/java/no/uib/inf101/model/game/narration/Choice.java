package no.uib.inf101.model.game.narration;

import no.uib.inf101.model.game.narration.node.Node;

import java.util.Objects;

/**
 * Represents a choice connecting two {@link Node}s.
 *
 * @param text     the text describing this choice
 * @param nextNode the resulting node from choosing this choice
 */
public record Choice(String text, Node nextNode) {
    public Choice {
        Objects.requireNonNull(text, "Text cannot be null");
        Objects.requireNonNull(nextNode, "NextNode cannot be null");
        if (text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be blank");
        }
    }
}
