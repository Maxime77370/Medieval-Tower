package com.medievaltower.entities.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

/**
 * Class that manages the animations of Bat
 */
public class AnimationBat extends Animation {

    private Tileset BatTile = new Tileset("Bat/bat.png", 32, 32);

    /**
     * AnimationBat constructor
     * <p>
     *     This constructor is used to create an animation.
     *     Create the sprite of the animation.
     * </p>
     */
    public AnimationBat() {
        super();
        sprite = BatTile.getTexture(0);
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
     * Animate the fly of the bat
     * @return the sprite of the animation
     */
    private TextureRegion animateFly() {
        if (frame >= 2) {
            frame = 0;
        }
        frame++;
        return BatTile.getTexture(frame - 1);
    }
}
