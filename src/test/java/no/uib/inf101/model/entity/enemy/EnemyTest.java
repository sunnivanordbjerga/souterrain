package no.uib.inf101.model.entity.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests {@link Enemy}.
 */
class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    void setup(){
        this.enemy = new Enemy("Enemy", 10, 2, 8);
    }
    @Test
    void throwsOnNegativeMaxHp(){
        assertThrows(IllegalArgumentException.class, () -> new Enemy("Illegal",-3,2,5));
    }

    @Test
    void throwsOnBlankDisplayName(){
        assertThrows(IllegalArgumentException.class, () -> new Enemy(" ",3,2,5));
    }

}