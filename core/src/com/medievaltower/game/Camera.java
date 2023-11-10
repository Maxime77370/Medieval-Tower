package com.medievaltower.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.medievaltower.entities.Personnage;

/**
 * Camera class
 * <p>
 *     This class is a singleton that contains the camera.
 * </p>
 * @see OrthographicCamera
 */
public class Camera {
    // Déclarez la caméra
    private OrthographicCamera camera;
    private Personnage personnage;

    /**
     * Camera constructor
     * Initialize the camera
     */
    public Camera() {
        // Initialisez la caméra
        camera = new OrthographicCamera();

        // Définissez la taille de la vue en fonction de la largeur et la hauteur de l'écran
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Obtenir le personnage
        this.personnage = Personnage.getInstance();

        // Mettez à jour la caméra
        update();
    }


    public void update() {

        float range = 0.8f; // 80% de la largeur de l'écran
        float cameraSpeed = 2.0f;

        // Empêchez la caméra de sortir de la zone de l'écran
        float xDelta = personnage.getX() - camera.position.x;
        if (xDelta < -camera.viewportWidth / 2 * range) {
            camera.position.x = personnage.getX() + camera.viewportWidth / 2 * range;
        }
        else if (xDelta + personnage.getWidth() > camera.viewportWidth / 2 * range) {
            camera.position.x = personnage.getX() - camera.viewportWidth / 2 * range + personnage.getWidth();
        }

        float yDelta = personnage.getY() - camera.position.y;
        if (yDelta < -camera.viewportHeight / 2) {
            camera.position.y = personnage.getY() + camera.viewportHeight / 2;
        }
        else if (yDelta > camera.viewportHeight / 2) {
            camera.position.y = personnage.getY() - camera.viewportHeight / 2 + personnage.getHeight();
        }

        // Lissage de la caméra (interpolation linéaire)
        camera.position.x = MathUtils.lerp(camera.position.x, personnage.getX() + personnage.getWidth() / 2, cameraSpeed * Gdx.graphics.getDeltaTime());
        camera.position.y = MathUtils.lerp(camera.position.y, personnage.getY() + personnage.getHeight() / 2, cameraSpeed * Gdx.graphics.getDeltaTime());

        // Mettez à jour la caméra
        camera.update();
    }




    public OrthographicCamera getCamera() {
        return camera;
    }
}
