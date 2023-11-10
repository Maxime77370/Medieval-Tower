package com.medievaltower.entities.bloc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.core.Entity;

/**
 * Entity class
 * <p>
 *     This class is the parent class of all bloc in the game.
 *     It contains the position, the size and the texture of the bloc.
 *     It also contains the update method.
 *     It is abstract because it is not supposed to be instantiated.
 *     It extends the Entity class.
 * </p>
 * @see Entity
 * @see Texture
 */
public abstract class Bloc extends Entity {

    /**
     * Bloc constructor
     * @param x : the x position of the bloc
     * @param y : the y position of the bloc
     */
    protected Bloc(int x, int y)
    {
        super(x, y, 50, 50, new Texture("paix.jpg"));
    }

    /**
     * Update the bloc
     * called in the subclass
     */
    public void update() {
    }

    /**
     * Effect of the bloc
     * defined in the subclass
     */
    public abstract void effect();
}
