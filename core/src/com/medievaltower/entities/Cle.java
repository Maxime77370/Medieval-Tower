package com.medievaltower.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.core.CollectableEntity;
import com.medievaltower.core.Entity;

public class Cle extends Entity implements CollectableEntity {

    public Cle(int x, int y) {
        super(x, y, 50, 50, new Texture("paix.jpg"));
    }

    @Override
    public void collect() {
        if (Personnage.getInstance().getCleEquipped() != null) {
            Personnage.getInstance().setCleEquipped(this);
        }
    }

    public void update() {
    }

}
