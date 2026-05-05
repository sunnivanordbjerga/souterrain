package no.uib.inf101.model.entity;

import no.uib.inf101.model.loot.Loot;
import no.uib.inf101.model.loot.LootFactory;
import no.uib.inf101.model.loot.equipable.EquipmentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a basic enemy, with no additional abilities.
 */
public class Enemy extends AbstractEntity implements Attacker {
    private final Random random;
    private final int minDamage;
    private final int maxDamage;
    private final EquipmentType guaranteedDrop;
    private static final double RANDOM_DROP_CHANCE = 0.5;

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

    /**
     * Returns this {@link Enemy}'s guaranteed drop, if any,
     * and optionally, a random {@link Loot} item based on a fixed drop chance.
     *
     * @param factory the {@link LootFactory} used to generate this enemy's drops
     * @return the list of dropped loot
     */
    public List<Loot> dropLoot(LootFactory factory) {
        Objects.requireNonNull(factory, "LootFactory cannot be null.");

        List<Loot> drops = new ArrayList<>();

        if (guaranteedDrop != null) {
            drops.add(factory.getSpecificEquipable(guaranteedDrop));
        }
        if (random.nextDouble() < RANDOM_DROP_CHANCE) {
            drops.add(factory.getRandomLoot());
        }
        return drops;
    }
}
