package no.uib.inf101.model.game.node;


import no.uib.inf101.model.entity.Player;
import no.uib.inf101.model.game.Choice;
import no.uib.inf101.model.game.ChoiceType;
import no.uib.inf101.model.game.Game;
import no.uib.inf101.model.loot.Loot;
import no.uib.inf101.model.loot.consumable.Consumable;
import no.uib.inf101.model.loot.equipable.Equipable;

import java.util.List;

public class InventoryNode extends AbstractNode {

    /**
     * Creates an {@link Node} with predefined {@link Choice}s for
     * using {@link Loot} in the player's inventory.
     */
    public InventoryNode() {
        super("You check your belongings:");
    }

    @Override
    public void onEnter(Game game) {
        Player player = game.getPlayer();
        List<Loot> inventory = player.getInventory();

        clearChoices();

        if (inventory.isEmpty()) {
            game.log("Nothing but empty pockets.");
            addChoice(new Choice("Return", null, null, ChoiceType.BACK));
        } else {
            for (Loot item : inventory) {
                if (item instanceof Consumable c) {
                    addChoice(new Choice(item.getDisplayName(), null, () -> c.use(player), ChoiceType.USE));
                } else {
                    addChoice(new Choice(item.getDisplayName(), null, () -> player.equip((Equipable) item), ChoiceType.EQUIP));
                }
            }
            addChoice(new Choice("Return", null, null, ChoiceType.BACK));
        }
    }
}
