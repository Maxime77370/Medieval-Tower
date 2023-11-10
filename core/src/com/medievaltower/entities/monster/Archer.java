package com.medievaltower.entities.monster;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class Archer
 * <p>
 *     This class is a subclass class of monster in the game.
 *     It contains the position, the size and the texture of the monster.
 *     It also contains the update method.
 *     This entity move horizontally.
 *     It extends the Monstre class.
 * </p>
 * @see Monstre
 */
public class Archer extends Monstre {

    /**
     * Archer constructor
     * @param x : the x position of the monster
     * @param y : the y position of the monster
     */
    public Archer(int x, int y) {
        super(x, y, 50, 50, new Texture("paix.jpg"));
    }

    /**
     * Move the monster
     * This method contains the horizontal move of the monster
     */
    @Override
    public void move() {
        this.x += speed;
        if (this.x >= 800) {
            speed = -speed;
        } else if (this.x <= 0) {
            speed = Math.abs(speed);
        }
    }

    /**
     * Move the monster with the AI
     */
    public void ai() {
        // TODO: Implémentez l'IA du zombie
    }

    /**
     * Attack the player
     */
    @Override
    public void attack() {

    }

    /**
     * Receive damage
     * @param damage : the damage received
     */
    @Override
    public void receiveDamage(int damage) {

    }

    /**
     * Update the archer
     */
    @Override
    public void update() {
        this.move();
    }
}
