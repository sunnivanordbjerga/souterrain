package no.uib.inf101.model.loot.consumable;

import no.uib.inf101.model.entity.Player;

import java.util.Objects;

public class HealthPotion implements Consumable {
    private final int healAmount;
    private final String displayName;

    public enum PotionStrength {
        PUNY(10, "Puny Health Potion"),
        MINOR(25, "Minor Health Potion"),
        MAJOR(40, "Major Health Potion");

        private final int healAmount;
        private final String displayName;

        PotionStrength(int healAmount, String displayName) {
            this.healAmount = healAmount;
            this.displayName = displayName;
        }
    }

    public HealthPotion(PotionStrength strength) {
        Objects.requireNonNull(strength, "Strength cannot be null");
        this.healAmount = strength.healAmount;
        this.displayName = strength.displayName;
    }

    @Override
    public void use(Player player) {
        Objects.requireNonNull(player, "Player cannot be null");
        player.heal(healAmount);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
