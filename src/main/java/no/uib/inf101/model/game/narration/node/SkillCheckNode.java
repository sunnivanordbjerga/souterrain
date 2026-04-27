package no.uib.inf101.model.game.narration.node;

import no.uib.inf101.model.game.Game;

/**
 * A {@link Node} with a success/failure outcome.
 * <p>
 * The resulting text and next node is determined based on a random
 * "dice roll" and successChance between 0 (guaranteed failure)
 * and 100 (guaranteed success).
 */
public class SkillCheckNode extends AbstractNode {
    private final int successChance;
    private final String successText;
    private final String failureText;
    private final Node successNode;
    private final Node failureNode;

    /**
     * Creates a {@link SkillCheckNode}.
     *
     * @param baseText      the text to show before both outcomes
     * @param successText   the text to show on success
     * @param failureText   the text to show on failure
     * @param successNode   the {@link Node} to proceed to on success
     * @param failureNode   the {@link Node} to proceed to on failure
     * @param successChance the chance of success (0-100)
     */
    public SkillCheckNode(
            String baseText,
            String successText,
            String failureText,
            Node successNode,
            Node failureNode,
            int successChance) {

        super(baseText);
        this.successText = successText;
        this.failureText = failureText;
        this.successNode = successNode;
        this.failureNode = failureNode;
        this.successChance = successChance;
    }

    @Override
    public void onEnter(Game game) {
        super.onEnter(game);

        boolean success = game.getRandom().nextInt(100) < successChance;

        if (success) {
            game.log(successText);
            game.setCurrentNode(successNode);
        } else {
            game.log(failureText);
            game.setCurrentNode(failureNode);
        }
    }
}
