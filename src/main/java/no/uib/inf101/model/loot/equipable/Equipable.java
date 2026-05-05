package no.uib.inf101.model.loot.equipable;

import no.uib.inf101.model.entity.Player;
import no.uib.inf101.model.loot.Loot;

/**
 * Defines functionality for items equipable by the {@link Player}, like a piece of armor.
 */
public interface Equipable extends Loot {

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
}
