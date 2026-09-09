package model.game.node;

import model.Player;
import model.game.Choice;
import model.game.ChoiceType;
import model.game.Game;
import model.loot.Loot;
import model.loot.consumable.Consumable;
import model.loot.equipable.Equipable;

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
            super.onEnter(game);
            game.log("Nothing but empty pockets.");
            addChoice(new Choice("Return", game.getPreviousNode()));
        } else {
            for (Loot item : inventory) {
                if (item instanceof Consumable c) {
                    addChoice(new Choice(item.getDisplayName(), null, () -> handleUse(player, c, game), ChoiceType.USE));
                } else {
                    addChoice(new Choice(item.getDisplayName(), null, () -> handleEquip(player, item, game), ChoiceType.EQUIP));
                }
            }
            addChoice(new Choice("Return", game.getPreviousNode()));
        }
    }

    private void handleEquip(Player player, Loot item, Game game) {
        player.equip((Equipable) item);
        this.onEnter(game);
    }

    private void handleUse(Player player, Consumable item, Game game) {
        item.use(player);
        this.onEnter(game);
    }
}
