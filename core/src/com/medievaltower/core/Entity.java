package com.medievaltower.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.entities.annimation.Animation;


/**
 * Entity class
 * <p>
 * This class is the parent class of all entities in the game.
 * It contains the position, the size and the texture of the entity.
 * It also contains the update method.
 * It is abstract because it is not supposed to be instantiated.
 * It extends the Sprite class from libGDX.
 * </p>
 *
 * @see Sprite
 * @see Texture
 * @see EntityManager
 */
public abstract class Entity extends Sprite {


    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected Sprite sprite;

    /**
     * Entity constructor
     * <p>
     *     This constructor is used to create an entity.
     *     It takes the position, the size and the texture of the entity as parameters.
     *     It also adds the entity to the entity manager.
     *     It is protected because it is not supposed to be used outside of the package.
     *     It is used by the child classes.
     *     It extends the Sprite class from libGDX.
     * </p>
     * @param x : the x position of the entity
     * @param y : the y position of the entity
     * @param width : the width of the entity
     * @param height : the height of the entity
     * @param texture : the texture of the entity
     */
    protected Entity(int x, int y, int width, int height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = new Sprite(texture);

        EntityManager entityManager = EntityManager.getInstance();
    }

    /**
     * Get the x position of the entity
     * @return the x position of the entity
     */
    public float getX() {
        return this.x;
    }

    /**
     * Get the y position of the entity
     * @return the y position of the entity
     */
    public float getY() {
        return this.y;
    }

    /**
     * Get the width of the entity
     * @return the width of the entity
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Get the height of the entity
     * @return the height of the entity
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Get the sprite of the entity
     * @return the sprite of the entity
     */
    public Sprite getSprite() {
        return this;
    }

    public void updateTexture(Animation animation) {
        this.sprite.setRegion(animation.update());
    }

    public void draw(Batch batch) {
        batch.draw(this.sprite, this.x, this.y, this.width, this.height);
    }

    /**
     * Update the entity
     * <p>
     *     This method is used to update the entity.
     *     It is abstract because it is not supposed to be used outside of the package.
     *     It is used by the child classes.
     * </p>
     */
    public abstract void update();
}
