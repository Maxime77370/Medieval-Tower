package com.medievaltower.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.medievaltower.entities.Personnage;

public class Camera {
    // Déclarez la caméra
    private OrthographicCamera camera;
    private float x;
    private float y;
    private Personnage personnage;

    public Camera() {

        // Obtenir le personnage
        this.personnage = Personnage.getInstance();

        // Initialisez la caméra
        camera = new OrthographicCamera();

        // Définissez la taille de la vue en fonction de la largeur et la hauteur de l'écran
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Positionnez la caméra au centre de la scène
        x = personnage.getX() + personnage.getWidth() / 2;
        y = personnage.getY() + personnage.getHeight() / 2;

        // Mettez à jour la caméra
        update();
    }

    public void update() {

        personnage.move();
        personnage.updatePosition();

        // Mettre a jour x (le personnage ne peux pas sortir de l'écran)
        if (personnage.getX() + personnage.getWidth() / 2 > Gdx.graphics.getWidth() / 2
                && personnage.getX() + personnage.getWidth() / 2 < 1000 - Gdx.graphics.getWidth() / 2) {
            x = personnage.getX() + personnage.getWidth() / 2;
        }

        System.out.println("x: " + x);

        // Mettre a jour y (le personnage ne peux pas sortir de l'écran)
        if (personnage.getY() + personnage.getHeight() / 2 > Gdx.graphics.getHeight() / 2
                && personnage.getY() + personnage.getHeight() / 2 < 1000 - Gdx.graphics.getHeight() / 2) {
            y = personnage.getY() + personnage.getHeight() / 2;
        }

        // Mettez à jour la position de la caméra pour suivre le personnage
        camera.position.set(x, y, 0);
        // Mettez à jour la caméra
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
