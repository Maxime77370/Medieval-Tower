package com.medievaltower.entities.potion;

import com.badlogic.gdx.graphics.Texture;

public class SpeedPotion extends Potion {
    /**
     * SpeedPotion constructor
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
    protected SpeedPotion(int x, int y, int width, int height, Texture texture) {
        super(x, y, 20, 20, new Texture("Texture/Potion/speed_potion.png"));
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
