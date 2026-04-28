package no.uib.inf101.model.loot.equipable;

import no.uib.inf101.model.entity.player.Player;

/**
 * Defines functionality for items equipable by the {@link Player}, like a piece of armor.
 */
public interface Equipable {

    /**
     * Equips the item, applying its effect to the {@link Player}.
     *
     * @param player the player to equip the item on
     */
    void equip(Player player);

    /**
     * Unequips the item, removing its effect from the {@link Player}.
     *
     * @param player the player to unequip the item from
     */
    void unequip(Player player);

    /**
     * Returns the display name of the item, used for logging.
     *
     * @return the display name
     */
    String getDisplayName();
}
