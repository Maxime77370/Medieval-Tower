package com.medievaltower.entities.bloc;

import com.medievaltower.entities.Personnage;

public class IceBloc extends Bloc {
    public IceBloc(int x, int y) {
        super(x, y);
    }

    @Override
    public void effect() {
        // Make the player slide
        Personnage.getInstance().setSliding(true);
    }
}
