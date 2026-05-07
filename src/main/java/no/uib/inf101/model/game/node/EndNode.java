package no.uib.inf101.model.game.node;

import no.uib.inf101.model.game.Choice;
import no.uib.inf101.model.game.ChoiceType;

import java.util.List;

/**
 * A {@link Node} that ends the game. Used on game over or game win events.
 * Choices are predefined.
 */
public class EndNode extends AbstractNode {
    private final EndType endType;

    /**
     * Defines the ending type.
     */
    public enum EndType {
        GAME_OVER,
        WIN
    }

    /**
     * Creates an {@link EndNode} of the given {@link EndType}.
     *
     * @param text    the text corresponding to this node; cannot be blank
     * @param endType the type of ending this node signifies
     */
    public EndNode(String text, EndType endType) {
        super(text);
        this.endType = endType;
    }


    @Override
    public List<Choice> getChoices() {
        Choice retry = new Choice("Brave the darkness again", null, null, ChoiceType.RESTART);

        return switch (endType) {
            case GAME_OVER -> List.of(
                    retry,
                    new Choice("Admit defeat", null, null, ChoiceType.QUIT)
            );
            case WIN -> List.of(
                    retry,
                    new Choice("Accept freedom", null, null, ChoiceType.QUIT)
            );
        };
    }

    @Override
    public void addChoice(Choice choice) {
        throw new UnsupportedOperationException("Choices for EndNodes are pre-defined");
    }
}
