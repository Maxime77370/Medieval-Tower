package com.medievaltower.entities.bloc;

import com.medievaltower.core.Entity;
import com.medievaltower.entities.Personnage;

/**
 * Class for the fire blocs
 * @see Bloc
 */
public class FireBloc extends Bloc {

    /**
     * Constructor
     * @param x : the x position of the bloc
     * @param y : the y position of the bloc
     */
    public FireBloc(int x, int y) {
        super(x, y);
    }

    /**
     * Effect of the bloc
     * Add damage to the player
     * @see Personnage#receiveDamage(int)
     */
    @Override
    public void effect() {
        // Add damage to the player
        Personnage.getInstance().receiveDamage(1);
    }

    @Override
    public void collide(Entity entity) {

    }
}
