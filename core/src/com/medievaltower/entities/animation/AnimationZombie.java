package com.medievaltower.entities.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

public class AnimationZombie extends Animation{

    private Tileset ZombieTile = new Tileset("Zombie/Zombie.png", 32, 32);

    public AnimationZombie() {
        super();
        sprite = ZombieTile.getTexture(0);
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

    private TextureRegion animationBreath() {
        if (frame >= 8) {
            frame = 0;
        }
        frame++;
        return ZombieTile.getTexture(frame - 1);
    }

    private TextureRegion animationRun() {
        if (frame >= 8) {
            frame = 0;
        }
        frame++;
        return ZombieTile.getTexture(frame + 25);
    }
}
