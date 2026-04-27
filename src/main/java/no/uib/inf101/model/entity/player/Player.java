package no.uib.inf101.model.entity.player;

import no.uib.inf101.model.entity.AbstractEntity;
import no.uib.inf101.model.entity.Attacker;
import no.uib.inf101.model.entity.Entity;
import no.uib.inf101.model.game.AttackResult;

/**
 * Represents the player controlled character.
 */
public class Player extends AbstractEntity implements Attacker {
    private int defenseRating;

    /**
     * Creates a new {@link Player}
     */
    public Player() {
        super("You", 50);
        this.defenseRating = 5;
    }

    @Override
    public void takeDamage(int damage) {
        int takenDamage = Math.max(0, damage - defenseRating);
        hp = Math.max(0, hp - takenDamage);
    }

    @Override
    public AttackResult attack(Entity target) {
        return new AttackResult(0);
    }

    /**
     * Increases the {@link Player}'s health points
     * by the given amount, capping at the maximum.
     *
     * @param amount the amount to increase the health points by
     */
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    /**
     * Changes the {@link Player}'s defense rating by the given amount
     *
     * @param changeAmount the amount to increase or decrease the defense rating by
     */
    public void setDefenseRating(int changeAmount) {
        defenseRating += changeAmount;
    }
}
