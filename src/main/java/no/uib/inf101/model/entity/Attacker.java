package no.uib.inf101.model.entity;

import java.util.Random;

/**
 * Defines methods for {@link Entity}s with the ability to attack, like the player or enemies.
 */
public interface Attacker {

    /**
     * Performs an attack on the target {@link Entity}, dealing a random amount of
     * damage within the {@link Attacker}'s attack range.
     *
     * @param target the entity to attack.
     * @param random a random instance, used to generate damage within the attack range
     * @return the amount of damage dealt
     */
    int attack(Entity target, Random random);
}
