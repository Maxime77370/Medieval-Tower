package com.medievaltower.entities.monster;

import com.medievaltower.core.AttackableEntity;
import com.medievaltower.core.MovableEntity;
import com.medievaltower.entities.Personnage;

public abstract class Monstre extends Personnage implements AttackableEntity, MovableEntity {
    public Monstre(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
    }
}
