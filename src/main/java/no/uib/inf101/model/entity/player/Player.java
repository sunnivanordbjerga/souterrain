package no.uib.inf101.model.entity.player;

import no.uib.inf101.model.entity.AbstractEntity;
import no.uib.inf101.model.entity.Attacker;
import no.uib.inf101.model.entity.Entity;

import java.util.Random;

/**
 * Represents the player controlled character.
 */
public class Player extends AbstractEntity implements Attacker {
    private final Random random;
    private static final int BASE_DEFENSE = 5;
    private static final int BASE_MIN_DAMAGE = 4;
    private static final int BASE_MAX_DAMAGE = 7;
    private int defenseBonus;
    private int damageBonus;


    /**
     * Creates a new {@link Player}
     */
    public Player(Random random) {
        super("You", 50);
        this.random = random;
        this.defenseBonus = 0;
        this.damageBonus = 0;
    }

    @Override
    public void takeDamage(int damage) {
        int takenDamage = Math.max(1, damage - getDefense());
        hp = Math.max(0, hp - takenDamage);
    }

    @Override
    public int attack(Entity target) {
        int minDamage = BASE_MIN_DAMAGE + damageBonus;
        int maxDamage = BASE_MAX_DAMAGE + damageBonus;

        int damageDealt = random.nextInt(minDamage, maxDamage + 1);
        target.takeDamage(damageDealt);

        return damageDealt;
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
     * Returns the sum of the {@link Player}s base defense and any equipment bonuses.
     *
     * @return the player's total defense
     */
    public int getDefense() {
        return BASE_DEFENSE + defenseBonus;
    }

    /**
     * Changes the {@link Player}'s equipment bonus by the given amount
     *
     * @param changeAmount the amount to increase or decrease the defense rating by
     */
    public void modifyEquipmentBonus(int changeAmount) {
        defenseBonus = Math.max(0, defenseBonus + changeAmount);
    }

    /**
     * Changes the {@link Player}'s attack range by the given range,
     * capping at the base damage.
     *
     * @param changeAmount the amount to increase or decrease to the min and max damage by
     */
    public void modifyAttackRange(int changeAmount) {
        damageBonus = Math.max(0, damageBonus + changeAmount);
    }
}
