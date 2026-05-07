package model.loot.consumable;

import model.Player;
import model.loot.Loot;

/**
 * Defines functionality for items consumed by the {@link Player}, like a potion.
 */
public interface Consumable extends Loot {

    /**
     * Consumes the item, applying an effect to the {@link Player}.
     *
     * @param player the player to use the item on
     */
    void use(Player player);
}
