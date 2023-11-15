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
    // Declare the camera
    private OrthographicCamera camera;
    private Personnage personnage;

    private static Camera instance;
    private float zoomInitial = 0.5f;  // Adjust this value as needed
    private float zoomSpeed = 0.1f; // Adjust the zoom speed as needed
    private float minZoom = 0.1f;   // Adjust the minimum zoom level
    private float maxZoom = 2.0f;   // Adjust the maximum zoom level

    /**
     * Camera constructor
     * Initialize the camera
     */
    public Camera() {
        // Initialize the singleton
        instance = this;

        // Initialize the camera
        camera = new OrthographicCamera();

        // Set the size of the view based on the width and height of the screen
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Set the zoom
        camera.zoom = zoomInitial;

        System.out.println("Initial Zoom: " + camera.zoom);

        // Get the character
        this.personnage = Personnage.getInstance();

        // Update the camera
        update();
    }

    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }
        return instance;
    }
    public void update() {
        float range = 0.8f; // 80% of the screen width
        float cameraSpeed = 2.0f;

        // Prevent the camera from going out of the screen area
        float xDelta = personnage.getX() - camera.position.x;
        if (xDelta < -camera.viewportWidth / 2 * range) {
            camera.position.x = personnage.getX() + camera.viewportWidth / 2 * range;
        } else if (xDelta + personnage.getWidth() > camera.viewportWidth / 2 * range) {
            camera.position.x = personnage.getX() - camera.viewportWidth / 2 * range + personnage.getWidth();
        }

        float yDelta = personnage.getY() - camera.position.y;
        if (yDelta < -camera.viewportHeight / 2) {
            camera.position.y = personnage.getY() + camera.viewportHeight / 2;
        } else if (yDelta > camera.viewportHeight / 2) {
            camera.position.y = personnage.getY() - camera.viewportHeight / 2 + personnage.getHeight();
        }

        // Smooth the camera (linear interpolation)
        float targetX = personnage.getX() + personnage.getWidth() / 2;
        float targetY = personnage.getY() + personnage.getHeight() / 2;

        float lerpedX = MathUtils.lerp(camera.position.x, targetX, cameraSpeed * Gdx.graphics.getDeltaTime());
        float lerpedY = MathUtils.lerp(camera.position.y, targetY, cameraSpeed * Gdx.graphics.getDeltaTime());

        camera.position.x = lerpedX;
        camera.position.y = lerpedY;

        // Adjust zoom based on character's speed
        float characterSpeed = Math.abs(personnage.getxVelocity()) + Math.abs(personnage.getyVelocity());
        float zoomFactor = 0.001f; // Adjust this value based on your preference
        float newZoom = 1.0f + characterSpeed * zoomFactor;

        // Apply zoom constraints
        camera.zoom = zoomInitial;

        // Update the camera
        camera.update();
    }

    // Zoom in
    public void zoomIn() {
        camera.zoom += zoomSpeed;
        if (camera.zoom > maxZoom) {
            camera.zoom = maxZoom;
        }
    }

    // Zoom out
    public void zoomOut() {
        camera.zoom -= zoomSpeed;
        if (camera.zoom < minZoom) {
            camera.zoom = minZoom;
        }
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
