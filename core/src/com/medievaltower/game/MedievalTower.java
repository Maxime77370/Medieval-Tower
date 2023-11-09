package com.medievaltower.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.medievaltower.entities.Personnage;
import com.medievaltower.core.EntityManager;

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

		// Crée le gestionnaire d'entité (EntityManager)
		EntityManager entityManager = EntityManager.getInstance();

		// Créez un personnage avec une position initiale de (50, 50)
		personnage = new Personnage(0, 0);

		// Créez une caméra orthographique
		camera = new Camera();
		camera.update();
	}

	@Override
	public void render () {
		// Effacez l'écran
		ScreenUtils.clear(1, 0, 0, 1);

		camera.update();

		// Passez la matrice de projection de la caméra au SpriteBatch
		batch.setProjectionMatrix(camera.getCamera().combined);

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
