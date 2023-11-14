package com.medievaltower.entities.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

public class AnimationBat extends Animation {

    private Tileset BatTile = new Tileset("Bat/bat.png", 32, 32);

    public AnimationBat() {
        super();
        sprite = BatTile.getTexture(0);
    }

    /**
     * @return
     */
    @Override
    public TextureRegion update() {
        float delta = com.badlogic.gdx.Gdx.graphics.getDeltaTime();
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
        if (frame >= 2) {
            frame = 0;
        }
        frame++;
        return BatTile.getTexture(frame - 1);
    }
}
