package no.uib.inf101.model.entity;

import java.util.Objects;

/**
 * Base implementation of {@link Entity}, defining common behavior,
 * like managing health points.
 */
public abstract class AbstractEntity implements Entity {
    protected String displayName;
    protected int maxHp;
    protected int hp;

    /**
     * Creates an {@link AbstractEntity}.
     *
     * @param displayName the entity's display name, shown in related log messages; cannot be blank
     * @param maxHp       the entity's maximum health points; must be positive
     * @throws NullPointerException     if displayName is null
     * @throws IllegalArgumentException if displayName is blank
     * @throws IllegalArgumentException if maxHp <= 0
     */
    public AbstractEntity(String displayName, int maxHp) {
        this.displayName = Objects.requireNonNull(displayName, "DisplayName cannot be null");
        this.maxHp = maxHp;

        if (displayName.isBlank()) {
            throw new IllegalArgumentException("DisplayName cannot be blank");
        }
        if (maxHp <= 0) {
            throw new IllegalArgumentException("MaxHP must be a positive number");
        }
        this.hp = maxHp;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
}
