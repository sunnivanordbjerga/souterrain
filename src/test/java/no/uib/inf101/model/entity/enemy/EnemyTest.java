package no.uib.inf101.model.entity.enemy;

import no.uib.inf101.model.entity.player.Player;
import no.uib.inf101.model.game.AttackResult;
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

        AttackResult result = enemy.attack(grunt, random);

        assertEquals(before - result.damageDealt(), grunt.getHp());
    }

    @Test
    void attack_returnsDamageWithinAttackRange() {
        Random random = new Random(1);
        Player hero = new Player();

        AttackResult result = enemy.attack(hero, random);

        assertTrue(result.damageDealt() >= 2);
        assertTrue(result.damageDealt() <= 8);
    }
}