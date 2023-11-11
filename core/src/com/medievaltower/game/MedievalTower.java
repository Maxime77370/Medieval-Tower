package com.medievaltower.game;

import com.badlogic.gdx.Game;
import com.medievaltower.screens.MainScreen;

public class MedievalTower extends Game {

    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }
}