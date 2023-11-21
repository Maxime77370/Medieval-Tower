package com.medievaltower.entities.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

/**
 * Class that manages the animations of Archer
 */
public class AnimationArcher extends Animation{

    private Tileset RunAndIdleTile = new Tileset("Archery/Idle and running.png", 64, 64);
    private Tileset NormalAttackTile = new Tileset("Archery/Normal Attack.png", 128, 64);
    private Tileset HighAttackTile = new Tileset("Archery/High Attack.png", 128, 64);
    private Tileset LowAttackTile = new Tileset("Archery/Low Attack.png", 128, 64);
    private Tileset JumpTile = new Tileset("Archery/Jumping.png", 128, 64);
    private Tileset DeathTile = new Tileset("Archery/death.png", 128, 64);
    private Tileset DashTile = new Tileset("Archery/Dash.png", 128, 64);

    /**
     * AnimationArcher constructor
     * <p>
     *     This constructor is used to create an animation.
     *     Create the sprite of the animation.
     * </p>
     */
    public AnimationArcher() {
        super();
        sprite = RunAndIdleTile.getTexture(0);
    }

    /**
     * Override the update method
     * Affect the sprite of the animation
     * @return
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
     * Take the animation of the archer when breath
     * @return
     */
    private TextureRegion animationBreath() {
        if (frame >= 2) {
            frame = 0;
        }
        frame++;
        return RunAndIdleTile.getTexture(frame - 1);
    }

    /**
     * Take the animation of the archer when run
     * @return the texture of the animation
     */
    private TextureRegion animationRun() {
        if (frame >= 8) {
            frame = 0;
        }
        frame++;
        return RunAndIdleTile.getTexture(frame + 7);
    }
}
