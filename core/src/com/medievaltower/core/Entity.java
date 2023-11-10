package com.medievaltower.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


/**
 * Entity class
 * <p>
 *     This class is the parent class of all entities in the game.
 *     It contains the position, the size and the texture of the entity.
 *     It also contains the update method.
 *     It is abstract because it is not supposed to be instantiated.
 *     It extends the Sprite class from libGDX.
 * </p>
 * @see Sprite
 * @see Texture
 * @see EntityManager
 *
 */
public abstract class Entity extends Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected Entity(int x, int y, int width, int height, Texture texture) {
        super(texture);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        EntityManager entityManager = EntityManager.getInstance();
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public Sprite getSprite() {
        return this;
    }

    public abstract void update();
}
