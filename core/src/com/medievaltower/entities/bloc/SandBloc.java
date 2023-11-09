package com.medievaltower.entities.bloc;

import com.medievaltower.entities.Personnage;

public class SandBloc extends Bloc {

    public SandBloc(int x, int y) {
        super(x, y);
    }

    @Override
    public void effect() {
        // Make the player slow
        Personnage.getInstance().setSlow(true);
    }
}
