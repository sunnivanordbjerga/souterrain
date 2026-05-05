package no.uib.inf101.model.game.node;

import no.uib.inf101.model.game.Game;

/**
 * A {@link Node} that ends the game. Used on game over or game win events.
 */
public class EndNode extends AbstractNode {
    public EndNode(String text) {
        super(text);
    }

    @Override
    public void onEnter(Game game) {
        super.onEnter(game);
        game.setGameOver();
    }
}
