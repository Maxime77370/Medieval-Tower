package com.medievaltower.entities.monster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.core.AttackableEntity;
import com.medievaltower.core.Entity;
import com.medievaltower.core.MovableEntity;
import com.medievaltower.entities.Personnage;

public abstract class Monstre extends Entity implements AttackableEntity, MovableEntity {
    protected int speed = 2;
    public Monstre(int x, int y) {
        super(x, y, 50, 50, new Texture("paix.jpg"));
    }

    public void update() {
    }

    public void move(){ }


}
