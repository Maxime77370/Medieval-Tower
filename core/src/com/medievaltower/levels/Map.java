package com.medievaltower.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.medievaltower.game.Camera;

public class Map {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private Camera camera;
    private Viewport viewport;

    private final int mapId;

    public Map(int id) {
        this.mapId = id;
        loadMap();
        camera = Camera.getInstance();
        createViewport();
    }

    private void createViewport() {
        // Utilisez FitViewport avec la taille de votre choix (par exemple, 800x600)
        viewport = new FitViewport(1920, 1080, camera.getCamera());
        viewport.apply();
    }

    private void loadMap() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        FileHandle mapFileHandle = Gdx.files.internal("Maps/map_" + mapId + ".tmx");
        this.tiledMap = mapLoader.load(mapFileHandle.path());
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void render() {
        // Mise à jour de la vue
        Gdx.app.log("Map", "Camera position: " + camera.getCamera().position);
        viewport.apply();

        // Rendu de la carte avec la caméra et le viewport
        tiledMapRenderer.setView(camera.getCamera());
        tiledMapRenderer.render();
    }

    public void resize(int width, int height) {
        // Mise à jour du viewport lors du redimensionnement de la fenêtre
        viewport.update(width, height);
    }

    public void dispose() {
        tiledMap.dispose();
        tiledMapRenderer.dispose();
    }
}
