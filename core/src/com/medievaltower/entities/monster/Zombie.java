package com.medievaltower.entities.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.Entity;
import com.medievaltower.entities.animation.AnimationZombie;

/**
 * Class Zombie
 * <p>
 *     This class is a subclass class of monster in the game.
 *     It contains the position, the size and the texture of the monster.
 *     It also contains the update method.
 *     This entity move horizontally.
 *     It extends the Monstre class.
 *     It contains the AI of the zombie.
 *     It contains the attack method of the zombie.
 *     It contains the receiveDamage method of the zombie.
 *     It contains the update method of the zombie.
 * </p>
 * @see Monstre
 */
public class Zombie extends Monstre {

    /**
     * Zombie constructor
     * @param x : the x position of the monster
     * @param y : the y position of the monster
     */
    private AnimationZombie animation = new AnimationZombie();
    public Zombie(int x, int y) {
        super(x, y, 75, 75, new Texture("paix.jpg"));
    }

    /**
     * Move the zombie
     * This method contains the horizontal move of the zombie
     */
    @Override
    public void move() {

        this.xLast = this.x;
        this.yLast = this.y;

        this.yVelocity -= GRAVITY * Gdx.graphics.getDeltaTime();

        animation.setStateLocal("Run");
        this.x += speed;
        if (this.x >= 100) {
            speed = -speed;
        } else if (this.x <= 0) {
            speed = Math.abs(speed);
        }

        super.move();
    }

    /**
     * Move the zombie with the AI
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
     * Update the zombie
     */
    @Override
    public void update() {
        updateTexture(animation);
    }

}
