package com.medievaltower.entities.bloc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.core.Entity;

public abstract class Bloc extends Entity {

    protected Bloc(int x, int y)
    {
        super(x, y, 50, 50, new Texture("paix.jpg"));
    }

    public void update() {
    }

    public abstract void effect();
}
