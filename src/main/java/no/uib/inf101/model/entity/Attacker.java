package no.uib.inf101.model.entity;

import no.uib.inf101.model.game.AttackResult;

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
     * @return an {@link AttackResult}, containing details about the attack
     */
    AttackResult attack(Entity target, Random random);
}
