package no.uib.inf101.model.loot.consumable;

import no.uib.inf101.model.entity.player.Player;

/**
 * Defines functionality for items consumed by the {@link Player}, like a potion.
 */
public interface Consumable {

    /**
     * Consumes the item, applying an effect to the {@link Player}.
     *
     * @param player the player to use the item on
     */
    void use(Player player);

    /**
     * Returns the display name of the item, used for logging.
     *
     * @return the display name
     */
    String getDisplayName();
}
