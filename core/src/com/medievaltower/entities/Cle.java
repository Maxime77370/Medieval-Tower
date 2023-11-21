package com.medievaltower.entities;

import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.CollectableEntity;
import com.medievaltower.core.Entity;
import com.medievaltower.entities.animation.AnimationCle;

/**
 * Cle class
 * <p>
 * It contains the position, the size and the texture of the cle.
 * It also contains the update method.
 * It extends the Entity class.
 * </p>
 * @see Entity
 * @see Texture
 */
public class Cle extends Entity implements CollectableEntity {

    private AnimationCle animation = new AnimationCle();

    /**
     * Cle constructor
     * @param x : the x position of the cle
     * @param y : the y position of the cle
     */
    public Cle(int x, int y) {
        super(x, y, 20, 20, new Texture("Texture/Key/key.png"));
    }

    /**
     * Update the cle when it is collected by the player
     */
    @Override
    public void collect() {
        if (Personnage.getInstance().getCleEquipped() != null) {
            Personnage.getInstance().setCleEquipped(this);
        }
    }

    /**
     * Move the cle but here it is not used
     */
    @Override
    public void move() {

    }

    /**
     * Update the cle
     */
    @Override
    public void update() {
        updateTexture(animation);
        setBoundingBox();
    }

    /**
     * Collide with an entity
     */
    @Override
    public void collide_floor() {

    }

    /**
     * Collide with an entity
     * @param entity : the entity that collide with the cle
     */
    @Override
    public void collide(Entity entity) {

    }

}
