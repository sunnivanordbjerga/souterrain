package no.uib.inf101.model.entity.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests {@link Player}.
 */
class PlayerTest {
    private Player player;

    @BeforeEach
    void setup() {
        this.player = new Player();
    }

    @Test
    void playerDisplayNameIsYou() {
        assertEquals("You", player.getDisplayName());
    }

    @Test
    void takeDamage_doesMinimumOneDamage() {
        player.takeDamage(player.getDefense());
        assertEquals(player.getMaxHp() - 1, player.getHp());
    }

    @Test
    void takeDamage_decreasesByDefenceRating() {
        player.takeDamage(10);
        assertEquals(player.getMaxHp() - 10 + player.getDefense(), player.getHp());
    }

    @Test
    void heal_capsAtMaxHp() {
        player.heal(1);
        assertEquals(player.getMaxHp(), player.getHp());
    }

    @Test
    void modifyDefenseBonus_increasesDefence() {
        player.modifyEquipmentBonus(+5);
        player.takeDamage(10);
        assertEquals(player.getMaxHp() - 1, player.getHp());
    }

    @Test
    void modifyDefenseBonus_decreasesDefence() {
        player.modifyEquipmentBonus(+5);
        assertEquals(10, player.getDefense());

        player.modifyEquipmentBonus(-3);
        assertEquals(7, player.getDefense());

        player.takeDamage(7);
        assertEquals(player.getMaxHp() - 1, player.getHp());
    }

    //TODO: test modifyAttackRange

    @Test
    void modifyAttackRange_capsAtZero(){

    }

    @Test
    void modifyAttackRange_increasesDamage() {

    }

    @Test
    void modifyAttackRange_decreasesDamage() {

    }

    @Test
    void modifyAttackRange_capsAtBase(){

    }

    //TODO: test attack
}