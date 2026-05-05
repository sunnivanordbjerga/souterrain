package no.uib.inf101.model.game;

import no.uib.inf101.model.game.node.Node;

import java.util.Objects;

/**
 * Represents a choice connecting two {@link Node}s.
 *
 * @param text     the text describing this choice; cannot be blank
 * @param nextNode the resulting node from choosing this choice
 * @param type     the {@link ChoiceType}, representing system behavior;
 *                 default {@code PROGRESS_STORY}
 */
public record Choice(String text, Node nextNode, ChoiceType type) {

    /**
     * Creates a {@link Choice} with {@link ChoiceType} {@code PROGRESS_STORY}.
     *
     * @param text     the text describing this choice; cannot be blank
     * @param nextNode the resulting node from choosing this choice
     * @throws NullPointerException     if text or nextNode is null
     * @throws IllegalArgumentException if text is blank
     */
    public Choice(String text, Node nextNode) {
        this(text, nextNode, ChoiceType.PROGRESS_STORY);
    }

    /**
     * Creates a {@link Choice}.
     *
     * @param text     the text describing this choice; cannot be blank
     * @param nextNode the resulting node from choosing this choice
     * @param type     the {@link ChoiceType}, defining its system behavior
     * @throws NullPointerException     if text, nextNode or type is null
     * @throws IllegalArgumentException if text is blank
     */
    public Choice {
        Objects.requireNonNull(text, "Text cannot be null");
        Objects.requireNonNull(nextNode, "NextNode cannot be null");
        Objects.requireNonNull(type, "ChoiceType cannot be null");
        if (text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be blank");
        }
    }
}
