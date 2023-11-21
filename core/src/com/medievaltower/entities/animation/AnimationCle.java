package com.medievaltower.entities.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

/**
 * Class that manages the animations of Key
 * <p>
 * This class is used to create an animation.
 * Create the sprite of the animation.
 * It extends the Animation class.
 * It contains the update method.
 * </p>
 *
 * @see Animation
 * @see TextureRegion
 * @see Tileset
 * @see Gdx
 * @see TextureRegion
 */
public class AnimationCle extends Animation {

    private final Tileset KeyTile = new Tileset("Key/key.png", 320, 320);

    /**
     * AnimationCle constructor
     * <p>
     *     This constructor is used to create an animation.
     *     Create the sprite of the animation.
     *     It extends the Animation class.
     * </p>
     */
    public AnimationCle() {
        super();
        sprite = KeyTile.getTexture(0);
    }

    /**
     * Override the update method
     * @return the sprite of the animation
     */
    @Override
    public TextureRegion update() {
        float delta = Gdx.graphics.getDeltaTime();
        this.frameTimer += delta;

        setStateGlobal();

        // Adjust the frame duration to control the speed of the animation
        if (frameTimer > frameDuration) {
            frameTimer -= frameDuration;
            sprite = new TextureRegion(animateFly());
        }
        ifInverseSprit(sprite);
        return sprite;
    }

    /**
     * Animate the fly of the key
     * @return the sprite of the animation
     */
    private TextureRegion animateFly() {
        if (frame >= KeyTile.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return KeyTile.getTexture(frame - 1);
    }
}
