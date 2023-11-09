package com.medievaltower.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.medievaltower.levels.Map;

import com.medievaltower.game.Screen;

import

public class MedievalTower extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Map mapManager;
	
	@Override
	public void create () {
		Screen screen = new Screen();
		// Create the MapManager instance
		mapManager = new Map();
		mapManager.test();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
