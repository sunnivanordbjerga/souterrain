package model.loot;

import model.loot.consumable.HealthPotion;
import model.loot.equipable.Equipable;
import model.loot.equipable.EquipmentType;
import model.loot.equipable.HollowHelm;
import model.loot.equipable.WolfsTooth;

import java.util.List;
import java.util.Random;

/**
 * Generates {@link Loot}
 */
public class LootFactory {
    private final Random random;
    private final List<Loot> loot;

    /**
     * Creates a {@link LootFactory}.
     *
     * @param random the shared random instance, used for random drops
     */
    public LootFactory(Random random) {
        this.random = random;
        this.loot = initLootTable();
    }

    /**
     * Returns a random {@link Loot}
     *
     * @return a random loot item
     */
    public Loot getRandomLoot() {
        int index = random.nextInt(loot.size());
        return loot.get(index);
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

    private List<Loot> initLootTable() {
        return List.of(
                new HealthPotion(HealthPotion.PotionStrength.PUNY),
                new HealthPotion(HealthPotion.PotionStrength.MINOR),
                new HealthPotion(HealthPotion.PotionStrength.MAJOR)
        );
    }
}
