package com.medievaltower.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.core.Entity;

public abstract class Bloc extends Entity {
    protected Bloc(int x, int y, int width, int height, Sprite sprite) {
        super(x, y, width, height, sprite);
    }
}
