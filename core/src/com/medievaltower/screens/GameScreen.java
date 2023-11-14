package com.medievaltower.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.medievaltower.core.EntityManager;
import com.medievaltower.entities.Personnage;
import com.medievaltower.entities.monster.Archer;
import com.medievaltower.entities.monster.Bat;
import com.medievaltower.entities.monster.Zombie;
import com.medievaltower.game.Camera;
import com.medievaltower.levels.Map;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Texture img;

    private Camera camera;
    private EntityManager entityManager;
    private Map map;
    private Personnage personnage;
    private Zombie zombie;
    private Bat bat;
    private Archer archer;

    public GameScreen() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        entityManager = EntityManager.getInstance();
        map = new Map(2);

        personnage = new Personnage(0, 0);
        entityManager.newEntity(personnage);

        zombie = new Zombie(50, 50);
        entityManager.newEntity(zombie);

        bat = new Bat(200, 200);
        entityManager.newEntity(bat);

        archer = new Archer(500, 50);
        entityManager.newEntity(archer);

        camera = new Camera();
    }

    @Override
    public void show() {
        // Initialization code (if any) when the screen is shown
    }

    @Override
    public void render(float delta) {

        // Update entities
        entityManager.update();

        // Clear the screen
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

        // Update camera matrix
        camera.update();

        // Set the projection matrix to the SpriteBatch
        batch.setProjectionMatrix(camera.getCamera().combined);

        // Draw map
        map.render(camera.getCamera()); // Pass the camera to the render method

        // Draw entities
        entityManager.draw(batch);
    }

    @Override
    public void resize(int width, int height) {
        // Resize logic
        map.resize(width, height); // Add map
    }

    @Override
    public void pause() {
        // Pause logic
    }

    @Override
    public void resume() {
        // Resume logic
    }

    @Override
    public void hide() {
        // Hide logic (if any) when the screen is hidden
    }

    @Override
    public void dispose() {
        // Dispose of resources when the screen is disposed
        batch.dispose();

        map.dispose();

        // Dispose of entity textures
        personnage.getSprite().getTexture().dispose();
        zombie.getSprite().getTexture().dispose();
        bat.getSprite().getTexture().dispose();
        archer.getSprite().getTexture().dispose();
        archer.getCurrentArrow().getSprite().getTexture().dispose();
    }
}
