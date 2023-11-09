package com.medievaltower.entities.bloc;

import com.medievaltower.entities.Personnage;

public class FireBloc extends Bloc {


    protected FireBloc(int x, int y) {
        super(x, y);
    }

    @Override
    public void effect() {
        // Add damage to the player
        Personnage.getInstance().receiveDamage(1);
    }
}
