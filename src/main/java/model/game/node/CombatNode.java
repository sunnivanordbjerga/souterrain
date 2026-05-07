package model.game.node;

import model.Enemy;
import model.Player;
import model.game.Game;

import java.util.Objects;

/**
 * A {@link Node} that starts a combat encounter between
 * the {@link Player} and an {@link Enemy}.
 * <p>
 * The resulting next node is determined by whether the player survives or dies.
 */
public class CombatNode extends AbstractNode {
    private final Enemy enemy;
    private final Node onVictory;
    private final Node onDefeat;
    private Boolean won = null;

    /**
     * Creates a {@link CombatNode}.
     *
     * @param text      the text to show upon entering the combat
     * @param enemy     the attacking enemy
     * @param onVictory the {@link Node} to proceed to on {@link Player} victory
     * @param onDefeat  the {@link Node} to proceed to on {@link Player} defeat
     * @throws NullPointerException     if text, enemy, onVictory or onDefeat is null
     * @throws IllegalArgumentException if text is blank
     */
    public CombatNode(String text, Enemy enemy, Node onVictory, Node onDefeat) {
        super(text);

        this.enemy = Objects.requireNonNull(enemy, "Enemy cannot be null");
        this.onVictory = Objects.requireNonNull(onVictory, "Victory node cannot be null");
        this.onDefeat = Objects.requireNonNull(onDefeat, "Defeat node cannot be null");
    }

    @Override
    public void onEnter(Game game) {
        if (won != null) {
            game.setCurrentNode(won ? onVictory : onDefeat);
            return;
        }

        super.onEnter(game);

        won = game.resolveCombat(enemy);
        game.setCurrentNode(won ? onVictory : onDefeat);
    }
}
