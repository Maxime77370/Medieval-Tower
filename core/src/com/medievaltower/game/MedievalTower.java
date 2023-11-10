package com.medievaltower.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.medievaltower.screens.MainScreen;

public class MedievalTower extends Game {

    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }
}
