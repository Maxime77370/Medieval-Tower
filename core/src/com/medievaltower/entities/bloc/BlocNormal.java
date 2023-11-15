package com.medievaltower.entities.bloc;

import com.medievaltower.core.Entity;
import com.medievaltower.entities.Personnage;

/**
 * Class for the normal blocs
 * @see Bloc
 */
public class BlocNormal extends Bloc {

    /**
     * Constructor
     * @param x : the x position of the bloc
     * @param y : the y position of the bloc
     */
    public BlocNormal(int x, int y) {
        super(x, y);
    }

    /**
     * Effect of the bloc
     * Set the sliding and the slow of the player to false
     * @see Personnage#setSliding()
     * @see Personnage#setSlow()
     */
    @Override
    public void effect() {
        Personnage.getInstance().setSliding();
        Personnage.getInstance().setSlow();
    }

    @Override
    public void collide(Entity entity) {

    }
}
