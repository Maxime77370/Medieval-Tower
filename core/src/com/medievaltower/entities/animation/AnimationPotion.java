package com.medievaltower.entities.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.game.Tileset;

public class AnimationPotion extends Animation {

    private final Tileset potionSpeed = new Tileset("Potion/speed_potion.png", 2058, 2400);
    private final Tileset potionHealing = new Tileset("Potion/healing_potion.png", 384, 384);
    private final Tileset potionExp = new Tileset("Potion/exp_potion.png", 400, 400);
    private Tileset potionTile;

    public AnimationPotion(int type) {
        super();
        if (type == 1) {
            sprite = potionHealing.getTexture(0);
            potionTile = potionHealing;
        }
        else if (type == 2) {
            sprite = potionExp.getTexture(0);
            potionTile = potionExp;
        }
        else if (type == 3) {
            sprite = potionSpeed.getTexture(0);
            potionTile = potionSpeed;
        } else {
            sprite = potionHealing.getTexture(0);
            potionTile = potionHealing;
        }

    }

    /**
     * Update the animation
     */
    @Override
    public TextureRegion update() {
        float delta = Gdx.graphics.getDeltaTime();
        this.frameTimer += delta;

        setStateGlobal();

        // Adjust the frame duration to control the speed of the animation
        if (frameTimer > frameDuration) {
            frameTimer -= frameDuration;
            sprite = new TextureRegion(animatePotion());
        }
        ifInverseSprit(sprite);
        return sprite;
    }

    private TextureRegion animatePotion() {
        if (frame >= potionTile.getNbTexture()) {
            frame = 0;
        }
        frame++;
        return potionTile.getTexture(frame - 1);
    }
}
