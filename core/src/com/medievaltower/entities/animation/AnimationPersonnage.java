package com.medievaltower.entities.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

/**
 * Class that manages the animations of the character
 */
public class AnimationPersonnage extends Animation {

    private final Tileset RunTile = new Tileset("2D_SL_Knight_v1.0/Run.png", 128, 64);
    private final Tileset AttackTile = new Tileset("2D_SL_Knight_v1.0/Attacks.png", 128, 64);
    private final Tileset JumpTile = new Tileset("2D_SL_Knight_v1.0/Jump.png", 128, 64);
    private final Tileset SlideTile = new Tileset("2D_SL_Knight_v1.0/Slide.png", 128, 64);
    private final Tileset DeathTile = new Tileset("2D_SL_Knight_v1.0/Death.png", 128, 64);
    private final Tileset HealthTile = new Tileset("2D_SL_Knight_v1.0/Health.png", 128, 64);
    private final Tileset IdleTile = new Tileset("2D_SL_Knight_v1.0/Idle.png", 128, 64);
    private final Tileset AttackFromAirTile = new Tileset("2D_SL_Knight_v1.0/attack_from_air.png", 128, 64);
    private final Tileset HurtTile = new Tileset("2D_SL_Knight_v1.0/Hurt.png", 128, 64);
    private final Tileset Hanging = new Tileset("2D_SL_Knight_v1.0/Hanging.png", 128, 64);

    /**
     * AnimationPersonnage constructor
     * <p>
     * This constructor is used to create an animation.
     * Create the sprite of the animation.
     * It extends the Animation class.
     * </p>
     */
    public AnimationPersonnage() {
        super();
        sprite = IdleTile.getTexture(0);
    }

    /**
     * Override the update method
     * Affect the sprite of the animation
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
                case "Run":
                    sprite = new TextureRegion(animationRun());
                    break;
                case "Breath":
                    sprite = new TextureRegion(animationBreath());
                    break;
                case "StartJump":
                    sprite = new TextureRegion(animationStartJump());
                    break;
                case "InJump":
                    sprite = new TextureRegion(animationInJump());
                    break;
                case "EndJump":
                    sprite = new TextureRegion(animationEndJump());
                    break;
                case "AttackFromAir":
                    sprite = new TextureRegion(animationAttackFromAir());
                    break;
                case "EndAttackFromAir":
                    sprite = new TextureRegion(animationEndAttackFromAir());
                    break;
                case "Attack":
                    sprite = new TextureRegion(animationAttack());
                    break;
                case "Slide":
                    sprite = new TextureRegion(animationSlide());
                    break;
                case "Death":
                    sprite = new TextureRegion(animationDeath());
                    break;
                case "Hurt":
                    sprite = new TextureRegion(animationHurt());
                    break;
                case "Hanging":
                    sprite = new TextureRegion(animationHanging());
                    break;

            }
            ifInverseSprit(sprite);
        }
        return sprite;
    }

    /**
     * Take the animation of the character when run
     * @return the texture of the animation
     */
    public TextureRegion animationRun() {
        if (frame >= RunTile.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return RunTile.getTexture(frame - 1);
    }

    /**
     * Take the animation of the character when breath
     * @return the texture of the animation
     */
    public TextureRegion animationBreath() {
        if (frame >= IdleTile.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return IdleTile.getTexture(frame - 1);
    }

    /**
     * Take the animation of the character when attack
     * @return the texture of the animation
     */
    public TextureRegion animationAttack() {
        forceState = "Attack";
        if (frame >= AttackTile.getNbTexture() / 2) {
            frame = 0;
            forceState = null;
        }
        frame++;
        return AttackTile.getTexture(frame - 1);
    }

    /**
     * Take the animation of the character when jump
     * @return the texture of the animation
     */
    public TextureRegion animationStartJump() {
        forceState = "Jump";
        if (frame >= 3) {
            frame = 0;
            forceState = null;
        }
        frame++;
        return JumpTile.getTexture(frame - 1);
    }

    /**
     * Take the animation of the character when jump
     * @return the texture of the animation
     */
    public TextureRegion animationInJump() {
        if (frame >= 2) {
            frame = 0;
        }
        frame++;
        return JumpTile.getTexture(frame + 2);
    }

    /**
     * Take the animation of the character when jump
     * @return the texture of the animation
     */
    public TextureRegion animationEndJump() {
        if (frame >= 3) {
            frame = 0;
        }
        frame++;
        return JumpTile.getTexture(frame + 3);
    }

    /**
     * Take the animation of the character when attack from air
     * @return the texture of the animation
     */
    public TextureRegion animationStartAttackFromAir() {
        forceState = "AttackFromAir";
        frame++;
        return AttackFromAirTile.getTexture(frame - 1);
    }

    /**
     * Take the animation of the character when attack from air
     * @return the texture of the animation
     */
    public TextureRegion animationAttackFromAir() {
        if (frame <= 2) {
            return animationStartAttackFromAir();
        }
        forceState = null;
        frame++;
        return AttackFromAirTile.getTexture(frame % 2);
    }

    /**
     * Take the animation of the character when attack from air
     * @return the texture of the animation
     */
    public TextureRegion animationEndAttackFromAir() {
        if (frame >= 4) {
            frame = 0;
        }
        frame++;
        return AttackFromAirTile.getTexture(frame + 2);
    }

    /**
     * Take the animation of the character when slide
     * @return the texture of the animation
     */
    public TextureRegion animationStartSlide() {
        forceState = "Slide";
        if (frame >= 3) {
            frame = 0;
            forceState = null;
        }
        frame++;
        return SlideTile.getTexture(frame - 1);
    }

    /**
     * Take the animation of the character when slide
     * @return the texture of the animation
     */
    public TextureRegion animationSlide() {
        if (frame <= 2) {
            return animationStartSlide();
        }
        forceState = null;
        frame++;
        return SlideTile.getTexture(frame % 6 + 2);
    }

    /**
     * Take the animation of the character when death
     * @return the texture of the animation
     */
    public TextureRegion animationDeath() {
        if (frame >= DeathTile.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return DeathTile.getTexture(frame - 1);
    }

    /**
     * Take the animation of the character when hurt
     * @return the texture of the animation
     */
    public TextureRegion animationHurt() {
        forceState = "Hurt";
        if (frame >= HurtTile.getNbTexture()) {
            frame = 0;
            forceState = null;
        }
        frame++;
        return HurtTile.getTexture(frame - 1);
    }

    /**
     * Take the animation of the character when hanging
     * @return the texture of the animation
     */
    public TextureRegion animationHanging() {
        if (frame >= Hanging.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return Hanging.getTexture(frame - 1);
    }

    /**
     * Take the current state
     * @return the current state
     */
    public Object getState() {
        return state;
    }
}
