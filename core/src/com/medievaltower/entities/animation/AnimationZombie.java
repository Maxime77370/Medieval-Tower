package com.medievaltower.entities.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

/**
 * Class that manages the animations of Zombie
 */
public class AnimationZombie extends Animation{

    private Tileset ZombieTile = new Tileset("Zombie/Zombie.png", 32, 32);

    /**
     * AnimationZombie constructor
     * <p>
     *     This constructor is used to create an animation.
     *     Create the sprite of the animation.
     * </p>
     */
    public AnimationZombie() {
        super();
        sprite = ZombieTile.getTexture(0);
    }

    /**
     * Override the update method
     * @return the sprite of the animation
     */
    public TextureRegion update() {
        float delta = Gdx.graphics.getDeltaTime();
        this.frameTimer += delta;

        setStateGlobal();

        // Adjust the frame duration to control the speed of the animation
        if (frameTimer > frameDuration) {
            frameTimer -= frameDuration;
            TextureRegion sprite = null;
            switch (this.state) {
                case "Breath":
                    sprite = new TextureRegion(animationBreath());
                    break;
                case "Run":
                    sprite = new TextureRegion(animationRun());
                    break;

            }
            ifInverseSprit(sprite);
        }
        return sprite;
    }

    /**
     * Animate the breath of the zombie
     * @return the sprite of the animation
     */
    private TextureRegion animationBreath() {
        if (frame >= 8) {
            frame = 0;
        }
        frame++;
        return ZombieTile.getTexture(frame - 1);
    }

    /**
     * Animate the run of the zombie
     * @return the sprite of the animation
     */
    private TextureRegion animationRun() {
        if (frame >= 8) {
            frame = 0;
        }
        frame++;
        return ZombieTile.getTexture(frame + 25);
    }
}
