package no.uib.inf101.model.game.narration.node;

import no.uib.inf101.model.game.Game;
import no.uib.inf101.model.game.narration.Choice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A base implementation of {@link Node}, providing common functionality
 * like getting the text description and list of {@link Choice}s
 */
public abstract class AbstractNode implements Node {
    private final String text;
    private final List<Choice> choices;

    /**
     * Creates an {@link AbstractNode} with the given text
     * and an empty list of {@link Choice}s.
     *
     * @param text the text corresponding to this node; cannot be blank
     * @throws NullPointerException if text is null
     * @throws IllegalArgumentException if text is blank
     */
    public AbstractNode(String text) {
        this.text = Objects.requireNonNull(text, "Text cannot be null");
        if(text.isBlank()){
            throw new IllegalArgumentException("Text cannot be blank");
        }

        this.choices = new ArrayList<>();
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<Choice> getChoices() {
        return List.copyOf(choices);
    }

    @Override
    public void addChoice(Choice choice) {
        Objects.requireNonNull(choice, "Choice cannot be null");
        if (!choices.contains(choice)) {
            choices.add(choice);
        }
    }

    @Override
    public void onEnter(Game game) {
        Objects.requireNonNull(game, "Game cannot be null");
        game.log(text);
    }
}
