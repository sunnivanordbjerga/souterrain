package no.uib.inf101.model.entity;

import no.uib.inf101.model.game.AttackResult;

/**
 * Defines methods for {@link Entity}s with the ability to attack, like the player or enemies.
 */
public interface Attacker {

    /**
     * Applies damage to the target {@link Entity}.
     *
     * @param target the entity to attack.
     * @return an {@link AttackResult}, containing information like damage dealt
     */
    AttackResult attack(Entity target);
}
