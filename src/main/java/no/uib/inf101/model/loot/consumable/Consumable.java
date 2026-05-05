package no.uib.inf101.model.loot.consumable;

import no.uib.inf101.model.entity.Player;
import no.uib.inf101.model.loot.Loot;

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
