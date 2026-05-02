package no.uib.inf101.model.entity.enemy;

import no.uib.inf101.model.entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests {@link Enemy}.
 */
class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    void setup() {
        this.enemy = new Enemy("Enemy", 10, 2, 8);
    }

    @Test
    void throwsOnNegativeMaxHp() {
        assertThrows(IllegalArgumentException.class, () -> new Enemy("Illegal", -3, 2, 5));
    }

    @Test
    void throwsOnBlankDisplayName() {
        assertThrows(IllegalArgumentException.class, () -> new Enemy(" ", 3, 2, 5));
    }

    @Test
    void attack_reducesTargetHp() {
        Random random = new Random(1);
        Enemy grunt = new Enemy("Grunt", 10, 2, 5);

        int before = grunt.getHp();

        int result = enemy.attack(grunt, random);

        assertEquals(before - result, grunt.getHp());
    }

    @Test
    void attack_returnsDamageWithinAttackRange() {
        Random random = new Random(1);
        Player hero = new Player();

        int result = enemy.attack(hero, random);

        assertTrue(result >= 2);
        assertTrue(result <= 8);
    }
}