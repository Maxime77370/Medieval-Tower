package com.medievaltower.entities;

import com.medievaltower.core.AttackableEntity;
import com.medievaltower.core.MovableEntity;

public abstract class Monstre extends Personnage implements AttackableEntity, MovableEntity {
    public Monstre(int x, int y) {
        super(x, y);
    }
}
