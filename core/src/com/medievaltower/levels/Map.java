package com.medievaltower.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {
    private static Map instance;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private MapObjects collisionPlateforme;

    public Map(int mapId) {
        instance = this;
        loadMap(mapId);
    }

    public void render(OrthographicCamera camera) {
        // Set the renderer's view to the camera's combined matrix
        tiledMapRenderer.setView(camera);

        // Render the map
        tiledMapRenderer.render();
    }

    public void resize(int width, int height) {
        // Update the viewport when the screen is resized
        tiledMapRenderer.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    public void dispose() {
        // Dispose of the map when it is no longer needed
        tiledMap.dispose();
        tiledMapRenderer.dispose();
        instance = null;
    }

    private void loadMap(int mapId) {
        // Your existing code to load the map
        TmxMapLoader mapLoader = new TmxMapLoader();
        FileHandle mapFileHandle = Gdx.files.internal("Maps/map_" + mapId + ".tmx");
        this.tiledMap = mapLoader.load(mapFileHandle.path());
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        this.collisionPlateforme = tiledMap.getLayers().get("Collisions_plateforme").getObjects();
    }

    public MapObjects getPlatforms() {
        return collisionPlateforme;
    }

    public static Map getInstance(int... mapId) {
        if (instance == null && mapId.length == 1) {
            instance = new Map(mapId[0]);
        }
        return instance;
    }
}