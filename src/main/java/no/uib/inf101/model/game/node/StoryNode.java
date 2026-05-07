package no.uib.inf101.model.game.node;

import no.uib.inf101.model.game.Choice;
import no.uib.inf101.model.game.Game;

import java.util.Objects;

/**
 * A {@link Node} containing text and a list of {@link Choice}s.
 */
public class StoryNode extends AbstractNode {
    private final String returnText;
    private boolean visited;

    /**
     * Creates a {@link StoryNode} with the given text, default returnText,
     * and empty list of {@link Choice}s.
     * <p>
     * This constructor sets a default returnText.
     *
     * @param text the text shown when first entering this node; cannot be blank
     * @throws NullPointerException     if text is null
     * @throws IllegalArgumentException if text is blank
     */
    public StoryNode(String text) {
        this(text, "Nothing seems to have changed.");
    }

    /**
     * Creates a {@link StoryNode} with the given text
     * and empty list of {@link Choice}s.
     *
     * @param text       the text shown when first entering this node; cannot be blank
     * @param returnText the text shown when reentering this node; cannot be blank
     * @throws NullPointerException     if text or returnText is null
     * @throws IllegalArgumentException if text or returnText is blank
     */
    public StoryNode(String text, String returnText) {
        super(text);
        this.returnText = Objects.requireNonNull(returnText, "ReturnText cannot be null");
        if (text.isBlank()) {
            throw new IllegalArgumentException("ReturnText cannot be blank");
        }
    }

    @Override
    public void onEnter(Game game) {
        if (!visited) {
            super.onEnter(game);
            visited = true;
        } else {
            game.log(returnText);
        }
    }
}
