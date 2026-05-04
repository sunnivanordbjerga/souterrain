package no.uib.inf101.model.game.narration.node;

import no.uib.inf101.model.game.Game;

import java.util.Objects;

/**
 * A {@link Node} with a success/failure outcome.
 * <p>
 * The resulting next node is determined based on a random
 * "dice roll" and successChance between 0 (guaranteed failure)
 * and 100 (guaranteed success).
 */
public class SkillCheckNode extends AbstractNode {
    private final double successChance;
    private final Node successNode;
    private final Node failureNode;

    /**
     * Creates a {@link SkillCheckNode}.
     *
     * @param baseText      the text to show before both outcomes
     * @param successNode   the {@link Node} to proceed to on success
     * @param failureNode   the {@link Node} to proceed to on failure
     * @param successChance the chance of success (0-100)
     * @throws NullPointerException     if text or nodes are null
     * @throws IllegalArgumentException if successChance is not between 0-100
     * @throws IllegalArgumentException if baseText is blank
     */
    public SkillCheckNode(
            String baseText,
            Node successNode,
            Node failureNode,
            double successChance) {

        super(baseText);
        this.successNode = Objects.requireNonNull(successNode, "SuccessNode cannot be null");
        this.failureNode = Objects.requireNonNull(failureNode, "FailureNode cannot be null");
        this.successChance = successChance;

        if (successChance < 0 || successChance > 1) {
            throw new IllegalArgumentException("SuccessChance must be between 0 and 100");
        }
    }

    @Override
    public void onEnter(Game game) {
        super.onEnter(game);

        boolean success = game.getRandom().nextInt(MAX_SUCCESS_RESULT) < successChance;

        game.setCurrentNode(success ? successNode : failureNode);
    }
}
