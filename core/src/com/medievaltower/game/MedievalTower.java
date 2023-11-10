package com.medievaltower.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.medievaltower.core.EntityManager;
import com.medievaltower.entities.Personnage;
import com.medievaltower.entities.monster.Archer;
import com.medievaltower.entities.monster.Bat;
import com.medievaltower.entities.monster.Zombie;


public class MedievalTower extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	// Déclarez la caméra
	private Camera camera;
	private Personnage personnage;
	private Zombie zombie;
	private Bat bat;
	private Archer archer;

	private EntityManager entityManager;
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		entityManager = EntityManager.getInstance();

		// Créez un personnage avec une position initiale de (50, 50)
		personnage = new Personnage(0, 0);
		entityManager.newEntity(personnage);

		// Créez un zombie avec une position initiale de (100, 100)
		zombie = new Zombie(50, 50);
		entityManager.newEntity(zombie);

		// Créez une chauve-souris avec une position initiale de (200, 200)
		bat = new Bat(200, 200);
		entityManager.newEntity(bat);

		archer = new Archer(500, 50);
		entityManager.newEntity(archer);

		camera = new Camera();

	}

	@Override
	public void render () {
		// Effacez l'écran
		ScreenUtils.clear(1, 0, 0, 1);

		// Mettez à jour les entités
		entityManager.update();

		// Appelez camera.update() pour mettre à jour la matrice de projection de la caméra
		camera.update();

		// Passez la matrice de projection de la caméra au SpriteBatch
		batch.setProjectionMatrix(camera.getCamera().combined);

		// Commencez le dessin
		batch.begin();

		batch.draw(personnage.getSprite(), personnage.getX(), personnage.getY());
		batch.draw(zombie.getSprite(), zombie.getX(), zombie.getY());
		batch.draw(bat.getSprite(), bat.getX(), bat.getY());
		batch.draw(archer.getSprite(), archer.getX(), archer.getY());

		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		// N'oubliez pas de disposer de la texture du personnage lorsque vous en avez fini avec elle.
		personnage.getSprite().getTexture().dispose();
		zombie.getSprite().getTexture().dispose();
		bat.getSprite().getTexture().dispose();
		archer.getSprite().getTexture().dispose();
	}
}
