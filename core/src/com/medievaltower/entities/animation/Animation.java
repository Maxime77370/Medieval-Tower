package com.medievaltower.entities.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Animation {

    protected String state;
    protected String lastState;
    protected String forceState;
    protected boolean inverse;
    protected int frame;
    protected float frameDuration = 0.1f; // Adjust this value to control the speed of the animation

    protected float frameTimer;

    protected TextureRegion sprite;


    public Animation() {
        this.state = "Breath";
        this.lastState = "Breath";
        this.frame = 0;
        this.inverse = false;
    }
    public void setStateLocal(String state, boolean... inverse) {

        if (forceState == null) {
            if (!this.state.equals(state)) {
                this.state = state;
            }
        }

        if (inverse.length > 0) {
            if (this.inverse != inverse[0]) {
                this.inverse = inverse[0];
            }
        }
    }

    public void setStateGlobal(){
        if (forceState != null) {
            if (this.lastState != this.state){
                frame = 0;
                frameTimer = 0;
                lastState = this.state;
            }
        }
    }

    public void setStateGlobal(String stats){
        if (forceState != null) {
            if (this.lastState != this.state){
                frame = 0;
                frameTimer = 0;
                lastState = this.state;
            }
        }
        this.state = stats;
    }

    public void ifInverseSprit(TextureRegion originalSprite) {
        TextureRegion flippedSprite = new TextureRegion(originalSprite);
        if (inverse) {
            flippedSprite.flip(true, false);
        }
        this.sprite = flippedSprite;
    }

    public abstract TextureRegion update();
}
