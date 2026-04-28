package no.uib.inf101.model.loot;

import no.uib.inf101.model.game.Game;
import no.uib.inf101.model.loot.consumable.Consumable;
import no.uib.inf101.model.loot.consumable.HealthPotion;
import no.uib.inf101.model.loot.equipable.Equipable;
import no.uib.inf101.model.loot.equipable.EquipmentType;
import no.uib.inf101.model.loot.equipable.HollowHelm;
import no.uib.inf101.model.loot.equipable.WolfsTooth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates {@link Consumable}s and {@link Equipable}s.
 */
public class LootFactory {
    private final Random random;
    private final List<Consumable> consumables;

    /**
     * Creates an {@link LootFactory}, holding a list of items for random selection.
     *
     * @param game the game, holding the shared random instance
     */
    public LootFactory(Game game) {
        this.random = game.getRandom();
        this.consumables = new ArrayList<>();
        initConsumables();
    }

    /**
     * Returns a random {@link Consumable}
     *
     * @return a random consumable
     */
    public Consumable getRandomConsumable() {
        int index = random.nextInt(consumables.size());
        return consumables.get(index);
    }

    /**
     * Returns a specific {@link Equipable} matching the provided {@link EquipmentType}.
     *
     * @param item the desired equipment type
     */
    public Equipable getSpecificEquipable(EquipmentType item) {
        return switch (item) {
            case HOLLOW_HELM -> new HollowHelm();
            case WOLFS_TOOTH -> new WolfsTooth();
        };
    }

    private void initConsumables() {
        consumables.add(new HealthPotion(HealthPotion.PotionStrength.PUNY));
        consumables.add(new HealthPotion(HealthPotion.PotionStrength.MINOR));
        consumables.add(new HealthPotion(HealthPotion.PotionStrength.MAJOR));
    }
}
