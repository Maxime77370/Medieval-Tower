package com.medievaltower.core;

/**
 * Interface for the entities that can be attacked
 */
public interface AttackableEntity {

    /**
     * Method called when an entity attacks
     */
    public void attack();

    /**
     * Method called when an entity receives damage
     * @param damage the amount of damage received
     */
    public void receiveDamage(int damage);
}
