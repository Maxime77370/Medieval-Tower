package com.medievaltower.entities.bloc;

import com.medievaltower.entities.Personnage;

/**
 * Class for the sand blocs
 * @see Bloc
 */
public class SandBloc extends Bloc {

    /**
     * Constructor
     * @param x : the x position of the bloc
     * @param y : the y position of the bloc
     */
    public SandBloc(int x, int y) {
        super(x, y);
    }

    /**
     * Effect of the bloc
     * Make the player slow
     * @see Personnage#setSlow()
     */
    @Override
    public void effect() {
        // Make the player slow
        Personnage.getInstance().setSlow();
    }
}
