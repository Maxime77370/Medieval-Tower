package com.medievaltower.entities.bloc;

import com.medievaltower.entities.Personnage;

/**
 * Class for the ice blocs
 * @see Bloc
 */
public class IceBloc extends Bloc {

    /**
     * Constructor
     * @param x : the x position of the bloc
     * @param y : the y position of the bloc
     */
    public IceBloc(int x, int y) {
        super(x, y);
    }

    /**
     * Effect of the bloc
     * Make the player slide
     * @see Personnage#setSliding(boolean)
     */
    @Override
    public void effect() {
        // Make the player slide
        Personnage.getInstance().setSliding();
    }
}
