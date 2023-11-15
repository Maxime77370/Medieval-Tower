package com.medievaltower.entities;

import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.CollectableEntity;
import com.medievaltower.core.Entity;

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

    /**
     * Cle constructor
     * @param x : the x position of the cle
     * @param y : the y position of the cle
     */
    public Cle(int x, int y) {
        super(x, y, 50, 50, new Texture("paix.png"));
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
     * Update the cle
     */
    public void update() {
    }

    @Override
    public void collide_floor() {

    }

    @Override
    public void collide(Entity entity) {

    }

}
