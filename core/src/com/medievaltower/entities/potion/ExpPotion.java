package com.medievaltower.entities.potion;

import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.entities.animation.AnimationPotion;

/**
 * ExpPotion class
 * <p>
 *     This is an abstract class.
 *     It contains the position, the size and the texture of the potion.
 */
public class ExpPotion extends Potion {

    private AnimationPotion animation = new AnimationPotion(2);

    /**
     * ExpPotion constructor
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
    public ExpPotion(int x, int y) {
        super(x, y, 20, 20, new Texture("Texture/Potion/exp_potion.png"));
    }

    /**
     * Collect the potion
     * <p>
     * This method is used to collect the potion.
     * It is called when the player collide with the potion.
     * It is defined in the subclass.
     * </p>
     */
    @Override
    public void collect() {
        super.collect();
    }

    /**
     * Update the potion
     * <p>
     * This method is used to update the potion.
     * It is called in the update method of the entity manager.
     * It is defined in the subclass.
     * </p>
     */
    @Override
    public void update() {
        updateTexture(animation);
        setBoundingBox();
    }
}
