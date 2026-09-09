package model.entity;

import model.Enemy;
import model.Player;
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
        this.player = new Player(new Random(1));
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
        Enemy grunt = new Enemy("Grunt", 10, 2, 5, new Random(2));

        int before = grunt.getHp();

        int result = player.attack(grunt);

        assertEquals(before - result, grunt.getHp());
    }

    @Test
    void attack_returnsDamageWithinAttackRange() {
        Enemy grunt = new Enemy("Grunt", 10, 2, 5, new Random(2));

        int result = player.attack(grunt);
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
        Player player2 = new Player(new Random(1));
        Enemy grunt1 = new Enemy("Grunt", 10, 2, 5, new Random(2));
        Enemy grunt2 = new Enemy("Grunt", 10, 2, 5, new Random(2));

        int before = player.attack(grunt1);

        player2.modifyAttackRange(2);

        int after = player2.attack(grunt2);
        assertEquals(before + 2, after);
    }

    @Test
    void modifyAttackRange_decreasesDamage() {
        Player player2 = new Player(new Random(1));
        Enemy grunt1 = new Enemy("Grunt", 10, 2, 5, new Random(2));
        Enemy grunt2 = new Enemy("Grunt", 10, 2, 5, new Random(2));

        int before = player.attack(grunt1);

        player2.modifyAttackRange(5);
        player2.modifyAttackRange(-2);


        int after = player2.attack(grunt2);
        assertEquals(before + 5 - 2, after);
    }

    @Test
    void modifyAttackRange_capsAtZero() {
        Player player2 = new Player(new Random(1));
        Enemy grunt1 = new Enemy("Grunt", 10, 2, 5, new Random(2));
        Enemy grunt2 = new Enemy("Grunt", 10, 2, 5, new Random(2));

        int before = player.attack(grunt1);

        player2.modifyAttackRange(-2);

        int after = player2.attack(grunt2);

        assertEquals(before, after);
    }
}
