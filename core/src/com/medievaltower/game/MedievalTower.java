package com.medievaltower.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.medievaltower.core.EntityManager;
import com.medievaltower.entities.Personnage;


public class MedievalTower extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	// Déclarez la caméra
	private Camera camera;
	private Personnage personnage;

	private EntityManager entityManager;
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		entityManager = EntityManager.getInstance();

		// Créez un personnage avec une position initiale de (50, 50)
		personnage = new Personnage(0, 0);
		entityManager.newEntitie(personnage);

		camera = new Camera();

	}

	@Override
	public void render () {
		// Effacez l'écran
		ScreenUtils.clear(1, 0, 0, 1);

		// Mettez à jour la position du personnage
		personnage.update(); // Assurez-vous que vous avez une méthode pour mettre à jour la position du personnage

		// Appelez camera.update() pour mettre à jour la matrice de projection de la caméra
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
