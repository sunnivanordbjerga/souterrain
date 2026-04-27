package no.uib.inf101.model.entity;

/**
 * A base implementation of {@link Entity}, common functionality
 * like managing health points.
 */
public abstract class AbstractEntity implements Entity {
    protected String displayName;
    protected int maxHp;
    protected int hp;

    /**
     * Creates an {@link AbstractEntity}.
     *
     * @param displayName the entity's display name, shown in related log messages
     * @param maxHp       the entity's maximum health points
     */
    public AbstractEntity(String displayName, int maxHp) {
        this.displayName = displayName;
        this.maxHp = maxHp;
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
