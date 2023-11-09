package com.medievaltower.entities.bloc;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.core.Entity;

public abstract class Bloc extends Entity {
    protected Bloc(int x, int y)
    {
        super(x, y, 50, 50, new Sprite());
    }

    public void update() {
    }

    public abstract void effect();
}
