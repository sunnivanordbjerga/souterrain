package no.uib.inf101.model.loot.equipable;

import no.uib.inf101.model.entity.Player;
import no.uib.inf101.model.loot.Loot;

/**
 * Defines functionality for items equipable by the {@link Player}, like a piece of armor.
 */
public interface Equipable extends Loot {

    /**
     * Returns which {@link Player.Slot} this {@link Equipable} belongs to.
     *
     * @return this equippable's slot
     */
    Player.Slot getSlot();

    /**
     * Applies this {@link Equipable}'s effect to the {@link Player}.
     *
     * @param player the player to equip the item on
     */
    void onEquip(Player player);

    /**
     * Removes  this {@link Equipable}'s effect from the {@link Player}.
     *
     * @param player the player to unequip the item from
     */
    void onUnequip(Player player);
}
