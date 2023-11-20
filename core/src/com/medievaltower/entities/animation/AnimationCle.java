package com.medievaltower.entities.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

public class AnimationCle extends Animation {

    private Tileset KeyTile = new Tileset("Key/key.png", 320, 320);

    public AnimationCle() {
        super();
        sprite = KeyTile.getTexture(0);
    }

    /**
     * @return
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

    private TextureRegion animateFly() {
        if (frame >= KeyTile.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return KeyTile.getTexture(frame - 1);
    }
}
