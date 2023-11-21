package com.medievaltower.entities.potion;

import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.entities.animation.AnimationPotion;

/**
 * HealthPotion class
 * <p>
 *     This class is a subclass class of potion in the game.
 *     It contains the position, the size and the texture of the potion.
 *     It also contains the update method.
 *     It extends the Potion class.
 *     It contains the collect method of the potion.
 *     It contains the update method of the potion.
 *     It is used to create a health potion.
 * </p>
 *      @see Potion
 *      @see Texture
 *
 */
public class HealthPotion extends Potion {
    private AnimationPotion animation = new AnimationPotion(1);
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
    public HealthPotion(int x, int y) {
        super(x, y, 30, 30, new Texture("Texture/Potion/healing_potion.png"));
    }

    @Override
    public void collect() {
        super.collect();
    }

    @Override
    public void update() {
        updateTexture(animation);
        setBoundingBox();
    }

}
