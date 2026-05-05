package no.uib.inf101.model.entity.enemy;

import no.uib.inf101.model.entity.Enemy;
import no.uib.inf101.model.entity.Player;
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
        Random random = new Random(1);
        this.enemy = new Enemy("Enemy", 10, 2, 8, random);
    }

    @Test
    void throwsOnNegativeMaxHp() {
        assertThrows(IllegalArgumentException.class, () -> new Enemy("Illegal", -3, 2, 5, new Random(1)));
    }

    @Test
    void throwsOnBlankDisplayName() {
        assertThrows(IllegalArgumentException.class, () -> new Enemy(" ", 3, 2, 5, new Random(1)));
    }

    @Test
    void attack_reducesTargetHp() {
        Enemy grunt = new Enemy("Grunt", 10, 2, 5, new Random(2));

        int before = grunt.getHp();

        int result = enemy.attack(grunt);

        assertEquals(before - result, grunt.getHp());
    }

    @Test
    void attack_returnsDamageWithinAttackRange() {
        Player hero = new Player(new Random(2));

        int result = enemy.attack(hero);

        assertTrue(result >= 2);
        assertTrue(result <= 8);
    }
}