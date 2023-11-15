package com.medievaltower.entities.monster;

import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.AttackableEntity;
import com.medievaltower.core.Entity;
import com.medievaltower.core.MovableEntity;

/**
 * Monstre class
 * <p>
 *     This class is the parent class of all monster in the game.
 *     It contains the position, the size and the texture of the monster.
 *     It also contains the update method.
 *     It is abstract because it is not supposed to be instantiated.
 *     It extends the Entity class.
 * </p>
 * @see Entity
 * @see Texture
 */
public abstract class Monstre extends Entity implements AttackableEntity, MovableEntity {
    protected int speed = 2;

    /**
     * Monstre constructor
     * @param x : the x position of the monster
     * @param y : the y position of the monster
     */
    public Monstre(int x, int y, int width, int height, Texture texture) {
        super(x, y, width, height, texture);
    }

    /**
     * Update the monster
     * called in the subclass
     */
    public void update() {
    }

    /**
     * Move the monster
     * defined in the subclass
     */
    public void move(){ }

    public void collide(Entity entity){

    }
}
