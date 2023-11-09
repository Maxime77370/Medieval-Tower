package com.medievaltower.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.medievaltower.entities.Personnage;

import com.badlogic.gdx.Gdx;

public class MedievalTower extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	// Déclarez la caméra
	private Camera camera;
	private Personnage personnage;
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		// Créez un personnage avec une position initiale de (50, 50)
		personnage = new Personnage(0, 0);

		// Créez une caméra orthographique
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(personnage.getX() + personnage.getWidth() / 2, personnage.getY() + personnage.getHeight() / 2, 0);
		camera.update();
	}

		@Override
		public void render () {
			// Effacez l'écran
			ScreenUtils.clear(1, 0, 0, 1);

			// Mettez à jour la position de la caméra pour suivre le personnage
			camera.position.set(personnage.getX() + personnage.getWidth() / 2, personnage.getY() + personnage.getHeight() / 2, 0);
			camera.update();

			// Passez la matrice de projection de la caméra au SpriteBatch
			batch.setProjectionMatrix(camera.combined);

			// Commencez le dessin
			batch.begin();
			batch.draw(personnage.getSprite(), personnage.getX(), personnage.getY());
			batch.end();
		}

		@Override
		public void dispose () {
			batch.dispose();
			img.dispose();
			// N'oubliez pas de disposer de la texture du personnage lorsque vous en avez fini avec elle.
			personnage.getSprite().getTexture().dispose();
		}
	}