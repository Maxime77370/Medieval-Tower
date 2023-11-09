package com.medievaltower.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.medievaltower.entities.Personnage;

public class Camera {
    // Déclarez la caméra
    private OrthographicCamera camera;
    private Personnage personnage;

    public Camera() {
        // Initialisez la caméra
        camera = new OrthographicCamera();

        // Définissez la taille de la vue en fonction de la largeur et la hauteur de l'écran
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Positionnez la caméra au centre de la scène (ou à l'endroit que vous préférez)
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        // Obtenir le personnage
        this.personnage = Personnage.getInstance();

        // Mettez à jour la caméra
        update();
    }

    public void update() {



        // Mettez à jour la caméra
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
