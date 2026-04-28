package no.uib.inf101.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests {@link AbstractEntity} and {@link Entity} by extension.
 */
public class EntityTest {
    TestEntity entity;

    @BeforeEach
    void setup() {
        this.entity = new TestEntity();
    }

    @Test
    void entitySetsDisplayName() {
        assertEquals("TestEntity", entity.getDisplayName());
    }

    @Test
    void entityHp_StartsAtMax() {
        assertEquals(entity.getMaxHp(), entity.getHp());
    }

    @Test
    void takeDamage_stopsAtHpZero() {
        entity.takeDamage(entity.getMaxHp() + 1);
        assertEquals(0, entity.getHp());
    }

    @Test
    void isAlive_returnsTrueOnPositiveHP() {
        assertTrue(entity.isAlive());
        entity.takeDamage(1);
        assertTrue(entity.isAlive());
    }

    @Test
    void isAlive_returnsFalseOnZeroHP() {
        entity.takeDamage(entity.getHp());
        assertEquals(0, entity.getHp());
        assertFalse(entity.isAlive());
    }
}
