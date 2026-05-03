package no.uib.inf101.model.loot;

/**
 * An item lootable by the player.
 */
public interface Loot {
    /**
     * Returns the display name of this {@link Loot}, used for logging.
     *
     * @return the display name
     */
    String getDisplayName();
}
