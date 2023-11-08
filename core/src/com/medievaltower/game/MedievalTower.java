package com.medievaltower.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.IOException;

public class MedievalTower extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	MapManager mapManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		// Create the MapManager instance
		mapManager = new MapManager();

		// Try to create the map
		try {
			mapManager.create();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
