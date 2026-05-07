package model;

/**
 * Represents a general game entity, like a player, object or NPC.
 */
public interface Entity {

    /**
     * Returns the entity's name to display in the story log.
     *
     * @return the entity's name
     */
    String getDisplayName();

    /**
     * Returns the entity's current health points.
     *
     * @return the entity's current health points
     */
    int getHp();

    /**
     * Returns the entity's max health points.
     *
     * @return the entity's max health points
     */
    int getMaxHp();

    /**
     * Reduces the entity's current health points, capping at 0.
     *
     * @param damage the amount to reduce the entity's health points by
     */
    void takeDamage(int damage);

    /**
     * Returns true if the entity's health points are above 0.
     *
     * @return true if the entity's health points are above 0
     */
    boolean isAlive();
}
