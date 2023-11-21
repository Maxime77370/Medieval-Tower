package com.medievaltower.game;

import com.badlogic.gdx.Game;
import com.medievaltower.screens.MainScreen;

/**
 * MedievalTower class
 * <p>
 * This class is the main class of the game.
 * It extends the Game class from libGDX.
 * It contains the create method.
 * It is used to create the game.
 * </p>
 *
 * @see Game
 */
public class MedievalTower extends Game {

    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }
}