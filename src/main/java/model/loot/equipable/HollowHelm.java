package model.loot.equipable;

import model.entity.Player;

import java.util.Objects;

/**
 * Represents the helmet dropped by the undead guardian,
 * providing a defense rating buff to the {@link Player} while equipped.
 */
public class HollowHelm implements Equipable {
    private static final String DISPLAY_NAME = "Hollow Helm";
    private static final int DEFENSE_BUFF = 5;

    @Override
    public Player.Slot getSlot() {
        return Player.Slot.ARMOR;
    }

    @Override
    public void onEquip(Player player) {
        Objects.requireNonNull(player, "Player cannot be null");
        player.modifyEquipmentBonus(DEFENSE_BUFF);
    }

    @Override
    public void onUnequip(Player player) {
        Objects.requireNonNull(player, "Player cannot be null");
        player.modifyEquipmentBonus(-DEFENSE_BUFF);
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
