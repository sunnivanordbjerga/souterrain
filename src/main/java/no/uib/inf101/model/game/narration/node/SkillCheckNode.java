package no.uib.inf101.model.game.narration.node;

import no.uib.inf101.model.game.Game;

import java.util.Objects;

/**
 * A {@link Node} with a success/failure outcome.
 * <p>
 * The resulting text and next node is determined based on a random
 * "dice roll" and successChance between 0 (guaranteed failure)
 * and 100 (guaranteed success).
 */
public class SkillCheckNode extends AbstractNode {
    private static final int MAX_SUCCESS_CHANCE = 100;

    private final int successChance;
    private final String successText;
    private final String failureText;
    private final Node successNode;
    private final Node failureNode;

    /**
     * Creates a {@link SkillCheckNode}.
     *
     * @param baseText      the text to show before both outcomes
     * @param successText   the text to show on success; cannot be blank
     * @param failureText   the text to show on failure; cannot be blank
     * @param successNode   the {@link Node} to proceed to on success
     * @param failureNode   the {@link Node} to proceed to on failure
     * @param successChance the chance of success (0-100)
     * @throws NullPointerException     if any arguments are null
     * @throws IllegalArgumentException if successChance is not between 0-100
     * @throws IllegalArgumentException if any text arguments are blank
     */
    public SkillCheckNode(
            String baseText,
            String successText,
            String failureText,
            Node successNode,
            Node failureNode,
            int successChance) {

        super(baseText);
        this.successText = Objects.requireNonNull(successText, "SuccessText cannot be null");
        this.failureText = Objects.requireNonNull(failureText, "FailureText cannot be null");
        this.successNode = Objects.requireNonNull(successNode, "SuccessNode cannot be null");
        this.failureNode = Objects.requireNonNull(failureNode, "FailureNode cannot be null");
        this.successChance = successChance;

        if (successText.isBlank()) {
            throw new IllegalArgumentException("SuccessText cannot be blank");
        }
        if (failureText.isBlank()) {
            throw new IllegalArgumentException("FailureText cannot be blank");
        }
        if (successChance < 0 || successChance > 100) {
            throw new IllegalArgumentException("SuccessChance must be between 0 and 100");
        }
    }

    @Override
    public void onEnter(Game game) {
        super.onEnter(game);

        boolean success = game.getRandom().nextInt(MAX_SUCCESS_CHANCE) < successChance;

        if (success) {
            game.log(successText);
            game.setCurrentNode(successNode);
        } else {
            game.log(failureText);
            game.setCurrentNode(failureNode);
        }
    }
}
