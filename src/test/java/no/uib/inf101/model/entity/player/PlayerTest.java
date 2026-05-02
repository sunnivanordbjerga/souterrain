package no.uib.inf101.model.entity.player;

import no.uib.inf101.model.entity.enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        int before = player.getHp();
        player.takeDamage(10);
        int after = before - player.getHp();

        int expected = Math.max(1, 10 - player.getDefense());

        assertEquals(expected, after);
    }

    @Test
    void attack_reducesTargetHp() {
        Random random = new Random(1);
        Enemy grunt = new Enemy("Grunt", 10, 2, 5);

        int before = grunt.getHp();

        int result = player.attack(grunt, random);

        assertEquals(before - result, grunt.getHp());
    }

    @Test
    void attack_returnsDamageWithinAttackRange() {
        Random random = new Random(1);
        Enemy grunt = new Enemy("Grunt", 10, 2, 5);

        int result = player.attack(grunt, random);
        assertTrue(result >= 4 && result <= 7);
    }

    @Test
    void heal_capsAtMaxHp() {
        assertEquals(player.getMaxHp(), player.getHp());
        player.heal(1);
        assertEquals(player.getMaxHp(), player.getHp());
    }

    @Test
    void modifyDefenseBonus_increasesDefence() {
        int before = player.getDefense();
        player.modifyEquipmentBonus(+5);
        assertEquals(before + 5, player.getDefense());
    }

    @Test
    void modifyDefenseBonus_decreasesDefence() {
        int before = player.getDefense();

        player.modifyEquipmentBonus(+5);
        player.modifyEquipmentBonus(-3);

        assertEquals(before + 5 - 3, player.getDefense());
    }

    @Test
    void modifyAttackRange_increasesDamage() {
        Enemy grunt = new Enemy("Grunt", 10, 2, 5);

        Random random = new Random(1);
        int before = player.attack(grunt, random);

        player.modifyAttackRange(2);

        random = new Random(1);
        int after = player.attack(grunt, random);
        assertEquals(before + 2, after);
    }

    @Test
    void modifyAttackRange_decreasesDamage() {
        Enemy grunt = new Enemy("Grunt", 10, 2, 5);

        Random random = new Random(1);
        int before = player.attack(grunt, random);

        player.modifyAttackRange(5);
        player.modifyAttackRange(-2);

        random = new Random(1);
        int after = player.attack(grunt, random);
        assertEquals(before + 5 - 2, after);
    }

    @Test
    void modifyAttackRange_capsAtZero() {
        Enemy grunt = new Enemy("Grunt", 10, 2, 5);

        Random random = new Random(1);
        int before = player.attack(grunt, random);

        player.modifyAttackRange(-2);

        random = new Random(1);
        int after = player.attack(grunt, random);

        assertEquals(before, after);
    }
}