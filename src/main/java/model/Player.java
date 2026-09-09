package model;

import model.loot.Loot;
import model.loot.equipable.Equipable;

import java.util.*;

/**
 * Represents the player controlled character.
 */
public class Player extends AbstractEntity implements Attacker {
    private final Random random;
    private static final int BASE_DEFENSE = 5;
    private static final int BASE_MIN_DAMAGE = 4;
    private static final int BASE_MAX_DAMAGE = 7;
    private int defenseBonus;
    private int damageBonus;

    public enum Slot {
        ARMOR,
        WEAPON
    }

    private final Map<Slot, Equipable> equipped = new EnumMap<>(Slot.class);
    private final List<Loot> inventory;

    /**
     * Creates a new {@link Player}
     */
    public Player(Random random) {
        super("You", 50);
        this.random = random;
        this.defenseBonus = 0;
        this.damageBonus = 0;
        this.inventory = new ArrayList<>();
    }

    @Override
    public void takeDamage(int damage) {
        int takenDamage = Math.max(1, damage - getDefense());
        hp = Math.max(0, hp - takenDamage);
    }

    @Override
    public int attack(Entity target) {
        int minDamage = BASE_MIN_DAMAGE + damageBonus;
        int maxDamage = BASE_MAX_DAMAGE + damageBonus;

        int damage = random.nextInt(minDamage, maxDamage + 1);
        target.takeDamage(damage);

        return damage;
    }

    /**
     * Increases the {@link Player}'s health points
     * by the given amount, capping at the maximum.
     *
     * @param amount the amount to increase the health points by
     */
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    /**
     * Returns the sum of the {@link Player}'s base defense and any equipment bonuses.
     *
     * @return the player's total defense
     */
    public int getDefense() {
        return BASE_DEFENSE + defenseBonus;
    }

    /**
     * Changes the {@link Player}'s equipment bonus by the given amount
     *
     * @param changeAmount the amount to increase or decrease the defense rating by
     */
    public void modifyEquipmentBonus(int changeAmount) {
        defenseBonus = Math.max(0, defenseBonus + changeAmount);
    }

    /**
     * Changes the {@link Player}'s attack range by the given range,
     * capping at the base damage.
     *
     * @param changeAmount the amount to increase or decrease to the min and max damage by
     */
    public void modifyAttackRange(int changeAmount) {
        damageBonus = Math.max(0, damageBonus + changeAmount);
    }

    /**
     * Returns a copy of the {@link Player}'s inventory.
     *
     * @return the player inventory
     */
    public List<Loot> getInventory() {
        return List.copyOf(inventory);
    }

    /**
     * Adds the given {@link Loot} item to the {@link Player}'s inventory.
     *
     * @param item the loot item to add
     * @throws NullPointerException if item is null
     */
    public void addItem(Loot item) {
        inventory.add(Objects.requireNonNull(item, "Item cannot be null."));
    }

    /**
     * Removes the given {@link Loot} item to the {@link Player}'s inventory.
     *
     * @param item the loot item to remove
     * @throws NullPointerException if item is null
     */
    public void removeItem(Loot item) {
        inventory.remove(Objects.requireNonNull(item, "Item cannot be null."));
    }

    /**
     * Equips an {@link Equipable} item to it's corresponding {@link Slot},
     * removing it from the inventory and applying its effect to the {@link Player}.
     * <p>
     * If the slot is occupied, the currently equipped item is unequipped,
     * returned to the inventory, and its effect removed.
     * <p>
     * If the item is already equipped, no changes are made.
     *
     * @param item the item to equip
     * @throws NullPointerException     if item is null
     * @throws IllegalArgumentException if the item is not in the inventory
     */
    public void equip(Equipable item) {
        Objects.requireNonNull(item, "Item cannot be null.");

        if (!inventory.contains(item)) {
            throw new IllegalArgumentException("The " + item.getDisplayName() + " is not in the inventory.");
        }

        Slot slot = item.getSlot();

        if (equipped.get(slot) == item) {
            return;
        }

        unequipOld(slot);

        inventory.remove(item);
        equipped.put(slot, item);
        item.onEquip(this);
    }

    /**
     * Resets the {@link Player} to it's initial state.
     */
    public void reset() {
        this.maxHp = 50;
        this.hp = maxHp;
        for (Slot slot : Slot.values()) {
            equipped.put(slot, null);
        }
        inventory.clear();
        this.defenseBonus = 0;
        this.damageBonus = 0;
    }

    private void unequipOld(Slot slot) {
        Equipable oldEquip = equipped.get(slot);
        if (oldEquip != null) {
            oldEquip.onUnequip(this);
            addItem(oldEquip);
        }
    }
}
