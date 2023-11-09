package com.medievaltower.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.medievaltower.core.CollectableEntity;
import com.medievaltower.core.Entity;

public class Cle extends Entity implements CollectableEntity {

    public Cle(int x, int y) {
        super(x, y, 1, 1, new Sprite());
    }

    @Override
    public void collect() {
        if (Personnage.getInstance().getCleEquipped() != null) {
            Personnage.getInstance().setCleEquipped(this);
        }
    }

}
