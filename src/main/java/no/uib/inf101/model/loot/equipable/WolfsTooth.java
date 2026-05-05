package no.uib.inf101.model.loot.equipable;

import no.uib.inf101.model.entity.Player;

/**
 * Represents the sword dropped by the dire-wolf, providing a
 * damage buff to the {@link Player} while equipped.
 */
public class WolfsTooth implements Equipable {
    private static final String DISPLAY_NAME = "Wolf's Tooth";
    private static final int DAMAGE_BONUS = 2;


    @Override
    public void equip(Player player) {
        player.modifyAttackRange(DAMAGE_BONUS);
    }

    @Override
    public void unequip(Player player) {
        player.modifyAttackRange(-DAMAGE_BONUS);
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
