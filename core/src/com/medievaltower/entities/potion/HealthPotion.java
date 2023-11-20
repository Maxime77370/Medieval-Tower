package com.medievaltower.entities.potion;

import com.badlogic.gdx.graphics.Texture;

public class HealthPotion extends Potion {
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
     */
    protected HealthPotion(int x, int y) {
        super(x, y, 20, 20, new Texture("Texture/Potion/healing_potion.png"));
    }

    @Override
    public void collect() {
        super.collect();
    }

    @Override
    public void update() {
        super.update();
    }

}
