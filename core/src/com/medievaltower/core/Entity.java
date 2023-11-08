package com.medievaltower.core;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Sprite sprite;

    protected Entity(int x, int y, int width, int height, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }


}
