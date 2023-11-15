package com.medievaltower.entities.potion;

import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.CollectableEntity;
import com.medievaltower.core.Entity;

/**
 * Potion class
 * <p>
 *     This class is the parent class of all potions in the game.
 *     It is abstract because it is not supposed to be instantiated.
 *     It extends the CollectableEntity class.
 * </p>
 * @see CollectableEntity
 */
public class Potion extends Entity implements CollectableEntity {

    /**
     * Entity constructor
     * <p>
     * This constructor is used to create an entity.
     * It takes the position, the size and the texture of the entity as parameters.
     * It also adds the entity to the entity manager.
     * It is protected because it is not supposed to be used outside of the package.
     * It is used by the child classes.
     * It extends the Sprite class from libGDX.
     * </p>
     *
     * @param x       : the x position of the entity
     * @param y       : the y position of the entity
     * @param width   : the width of the entity
     * @param height  : the height of the entity
     * @param texture : the texture of the entity
     */
    protected Potion(int x, int y, int width, int height, Texture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public void collect() {

    }

    @Override
    public void collide(Entity entity) {

    }
}
