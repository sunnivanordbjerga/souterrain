package no.uib.inf101.model.game;

import no.uib.inf101.model.game.node.Node;

import java.util.Objects;

/**
 * Represents a choice connecting two {@link Node}s.
 *
 * @param text     the text describing this choice; cannot be blank
 * @param nextNode the resulting node from choosing this choice
 * @param action   an optional Runnable action to execute on this choice
 * @param type     the {@link ChoiceType}, representing system behavior;
 *                 default {@code NORMAL}
 */
public record Choice(String text, Node nextNode, Runnable action, ChoiceType type) {

    /**
     * Creates a {@link Choice} with {@link ChoiceType} {@code NORMAL} and no action.
     *
     * @param text     the text describing this choice; cannot be blank
     * @param nextNode the resulting node from choosing this choice
     * @throws NullPointerException     if text is null
     * @throws IllegalArgumentException if text is blank
     *                                  or if type is NORMAL and nextNode is null
     */
    public Choice(String text, Node nextNode) {
        this(text, nextNode, null, ChoiceType.NORMAL);
    }

    /**
     * Creates a {@link Choice}.
     *
     * @param text     the text describing this choice; cannot be blank
     * @param nextNode the resulting node from choosing this choice
     * @param action   an optional Runnable action to execute on this choice
     * @param type     the {@link ChoiceType}, defining its system behavior
     * @throws NullPointerException     if text or type is null
     * @throws IllegalArgumentException if text is blank
     *                                  or if type is NORMAL and nextNode is null
     */
    public Choice {
        Objects.requireNonNull(text, "Text cannot be null");
        Objects.requireNonNull(type, "ChoiceType cannot be null");
        if (text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be blank");
        }
        if (type == ChoiceType.NORMAL && nextNode == null) {
            throw new IllegalArgumentException("NORMAL choices must have a nextNode");
        }
    }

    /**
     * Executes this {@link Choice}'s action if one exists.
     */
    public void execute() {
        if (action != null) {
            action.run();
        }
    }
}
