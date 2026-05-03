package no.uib.inf101.model.entity.enemy;

import no.uib.inf101.model.entity.AbstractEntity;
import no.uib.inf101.model.entity.Attacker;
import no.uib.inf101.model.entity.Entity;
import no.uib.inf101.model.loot.equipable.EquipmentType;

import java.util.Random;

/**
 * Represents a basic enemy, with no additional abilities.
 */
public class Enemy extends AbstractEntity implements Attacker {
    private final int minDamage;
    private final int maxDamage;
    private final EquipmentType guaranteedDrop;
    private final Random random;

    /**
     * Creates a new {@link Enemy} with the given max HP and damage range.
     *
     * @param displayName this enemy's display name, shown in related log messages
     * @param maxHp       this enemy's maximum health points
     * @param minDamage   the minimum damage amount this enemy can deal
     * @param maxDamage   the maximum damage amount this enemy can deal
     */
    public Enemy(String displayName, int maxHp, int minDamage, int maxDamage, Random random) {
        this(displayName, maxHp, minDamage, maxDamage, null, random);
    }

    /**
     * Creates a new {@link Enemy} with the given max HP and damage range.
     *
     * @param displayName this enemy's display name, shown in related log messages
     * @param maxHp       this enemy's maximum health points
     * @param minDamage   the minimum damage amount this enemy can deal
     * @param maxDamage   the maximum damage amount this enemy can deal
     */
    public Enemy(String displayName, int maxHp, int minDamage, int maxDamage, EquipmentType guaranteedDrop, Random random) {
        super(displayName, maxHp);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.guaranteedDrop = guaranteedDrop;
        this.random = random;
    }

    @Override
    public int attack(Entity target) {
        int damage = random.nextInt(minDamage, maxDamage + 1);
        target.takeDamage(damage);

        return damage;
    }
}
