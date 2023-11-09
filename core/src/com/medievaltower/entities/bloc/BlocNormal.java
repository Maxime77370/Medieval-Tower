package com.medievaltower.entities.bloc;

import com.medievaltower.entities.Personnage;

public class BlocNormal extends Bloc {

    protected BlocNormal(int x, int y) {
        super(x, y);
    }

    @Override
    public void effect() {
        Personnage.getInstance().setSliding(false);
        Personnage.getInstance().setSlow(false);
    }
}
