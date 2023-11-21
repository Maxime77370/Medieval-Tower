package com.medievaltower.entities.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class that manages the animations
 * <p>
 * This class is abstract because it is not supposed to be used directly.
 * It is used by the child classes.
 * It contains the state of the animation, the last state of the animation, the frame of the animation, the frame duration and the sprite.
 * It also contains the update method.
 * It extends the Sprite class from libGDX.
 * </p>
 *
 * @see TextureRegion
 */
public abstract class Animation {

    protected String state;
    protected String lastState;
    protected String forceState;
    protected boolean inverse;
    protected int frame;
    protected float frameDuration = 0.1f; // Adjust this value to control the speed of the animation
    protected float frameTimer;
    protected TextureRegion sprite;

    /**
     * Animation constructor
     * <p>
     * This constructor is used to create an animation.
     * </p>
     */
    public Animation() {
        this.state = "Breath";
        this.lastState = "Breath";
        this.frame = 0;
        this.inverse = false;
    }

    /**
     * Set state of the animation
     * <p>
     * This method is used to set the state of the animation.
     * It takes the state as parameter.
     * It also takes the inverse as parameter.
     * It is used to set the state of the animation.
     * </p>
     *
     * @param state
     * @param inverse
     */
    public void setStateLocal(String state, boolean... inverse) {

        if (forceState == null) {
            if (!this.state.equals(state)) {
                this.state = state;
            }
        }

        if (inverse.length > 0) {
            if (this.inverse != inverse[0]) {
                this.inverse = inverse[0];
            }
        }
    }

    /**
     * Set the global state of the animation
     */
    public void setStateGlobal() {
        if (forceState != null) {
            if (this.lastState != this.state) {
                frame = 0;
                frameTimer = 0;
                lastState = this.state;
            }
        }
    }

    /**
     * Inverse the sprite
     * @param originalSprite
     */
    public void ifInverseSprit(TextureRegion originalSprite) {
        TextureRegion flippedSprite = new TextureRegion(originalSprite);
        if (inverse) {
            flippedSprite.flip(true, false);
        }
        this.sprite = flippedSprite;
    }

    /**
     * Update the animation
     */
    public abstract TextureRegion update();
}
