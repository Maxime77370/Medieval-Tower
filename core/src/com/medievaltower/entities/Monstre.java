package com.medievaltower.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.core.AttackableEntity;
import com.medievaltower.core.Entity;
import com.medievaltower.core.MovableEntity;

public abstract class Monstre extends Entity implements AttackableEntity, MovableEntity {
    private int speed = 1;
    public Monstre(int x, int y) {
        super(x, y, 50, 50, new Sprite());
    }

    public void update() {
    }

    @Override
    public void move() {
        this.x += 5 + speed;
        if (this.x >= 100) {
            speed = -speed;
        } else if

        }
    }
}
