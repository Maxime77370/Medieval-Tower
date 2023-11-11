package com.medievaltower.entities.annimation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

public class AnimationPersonnage extends Animation{

    private Tileset RunTile = new Tileset("2D_SL_Knight_v1.0/Run.png", 128, 64);
    private Tileset AttackTile = new Tileset("2D_SL_Knight_v1.0/Attacks.png", 128, 64);
    private Tileset JumpTile = new Tileset("2D_SL_Knight_v1.0/Jump.png", 128, 64);
    private Tileset SlideTile = new Tileset("2D_SL_Knight_v1.0/Slide.png", 128, 64);
    private Tileset DeathTile = new Tileset("2D_SL_Knight_v1.0/Death.png", 128, 64);
    private Tileset HealthTile = new Tileset("2D_SL_Knight_v1.0/Health.png", 128, 64);

    private Tileset IdleTile = new Tileset("2D_SL_Knight_v1.0/Idle.png", 128, 64);

    private Tileset AttackFromAirTile = new Tileset("2D_SL_Knight_v1.0/attack_from_air.png", 128, 64);
    public AnimationPersonnage() {
        super();
        sprite = IdleTile.getTexture(0);
    }


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

            }
            ifInverseSprit(sprite);
        }
        return sprite;
    }

    public TextureRegion animationRun() {
        if (frame >= RunTile.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return RunTile.getTexture(frame - 1);
    }

    public TextureRegion animationBreath() {
        if (frame >= IdleTile.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return IdleTile.getTexture(frame - 1);
    }

    public TextureRegion animationAttack() {
        if (frame >= AttackTile.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return AttackTile.getTexture(frame - 1);
    }

    public TextureRegion animationStartJump() {
        forceState = "Jump";
        if (frame >= 3) {
            frame = 0;
            forceState = null;
        }
        frame++;
        return JumpTile.getTexture(frame - 1);
    }

    public TextureRegion animationInJump(){
        if (frame >= 2){
            frame = 0;
        }
        frame++;
        return JumpTile.getTexture(frame + 2);
    }

    public TextureRegion animationEndJump() {
        if (frame >= 3) {
            frame = 0;
        }
        frame++;
        return JumpTile.getTexture(frame + 3);
    }

    public TextureRegion animationStartAttackFromAir() {
        forceState = "AttackFromAir";
        if (frame >= 3) {
            frame = 0;
            forceState = null;
        }
        frame++;
        return AttackFromAirTile.getTexture(frame - 1);
    }

    public TextureRegion animationAttackFromAir(){
        if (frame == 0){
            animationStartAttackFromAir();
        }
        frame++;
        return AttackFromAirTile.getTexture(frame%2);
    }

    public TextureRegion animationEndAttackFromAir() {
        if (frame >= 4) {
            frame = 0;
        }
        frame++;
        return AttackFromAirTile.getTexture(frame + 2);
    }
}
