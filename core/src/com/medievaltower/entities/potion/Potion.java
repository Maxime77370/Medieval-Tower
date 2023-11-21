package com.medievaltower.entities.potion;

import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.CollectableEntity;
import com.medievaltower.core.Entity;

/**
 * Potion class
 * <p>
 * This class is the parent class of all potions in the game.
 * It is abstract because it is not supposed to be instantiated.
 * It extends the CollectableEntity class.
 * </p>
 *
 * @see CollectableEntity
 */
public abstract class Potion extends Entity implements CollectableEntity {

    /**
     * Potion constructor
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

    /**
     * Collide with the floor
     * <p>
     * This method is used to collide with the floor.
     * It is called when the entity collide with the floor.
     * </p>
     */
    @Override
    public void collide_floor() {

    }

    /**
     * Update the potion
     */
    @Override
    public void update() {
        super.update();
    }

    /**
     * Collect the potion
     */
    @Override
    public void collect() {

    }

    /**
     * Collide with an entity
     * @param entity : the entity that collide with the potion
     */
    @Override
    public void collide(Entity entity) {

    }
}
