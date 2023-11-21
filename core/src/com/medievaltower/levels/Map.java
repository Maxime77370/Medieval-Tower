package com.medievaltower.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.medievaltower.core.EntityManager;
import com.medievaltower.entities.Cle;
import com.medievaltower.entities.Personnage;
import com.medievaltower.entities.monster.Archer;
import com.medievaltower.entities.monster.Bat;
import com.medievaltower.entities.monster.Zombie;

import java.util.HashMap;

/**
 * Map class
 * <p>
 * This class is a singleton that contains the map.
 * </p>
 */
public class Map {
    private static Map instance;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private MapObjects collisionPlateforme;
    private MapObjects collisionMonstre;
    private MapObjects spawnZombie;
    private MapObjects spawnArcher;
    private MapObjects spawnBat;
    private MapObjects spawnPlayer;
    private MapObjects spawnKey;
    private MapObjects collisionMort;

    /**
     * Map constructor
     * Load the map
     *
     * @param mapId : the id of the map to load
     */
    public Map(int mapId) {
        instance = this;
        loadMap(mapId);
    }

    /**
     * Get the instance of map
     *
     * @return the map
     */
    public static Map getInstance() {
        return instance;
    }
    public void render(OrthographicCamera camera) {
        // Set the renderer's view to the camera's combined matrix
        tiledMapRenderer.setView(camera);

        // Render the map
        tiledMapRenderer.render();
    }

    /**
     * Resize the map
     * @param width
     * @param height
     */
    public void resize(int width, int height) {
        // Update the viewport when the screen is resized
        tiledMapRenderer.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    /**
     * Dispose of the map when it is no longer needed
     */
    public void dispose() {
        // Dispose of the map when it is no longer needed
        tiledMap.dispose();
        tiledMapRenderer.dispose();
    }

    /**
     * Load the map with all the elements that been created in Tiled
     * @param mapId : the id of the map to load
     */
    public void loadMap(int mapId) {
        // Your existing code to load the map
        TmxMapLoader mapLoader = new TmxMapLoader();
        FileHandle mapFileHandle = Gdx.files.internal("Maps/map_" + mapId + ".tmx");
        this.tiledMap = mapLoader.load(mapFileHandle.path());
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        this.collisionPlateforme = tiledMap.getLayers().get("Collisions_plateforme").getObjects();
        this.collisionMonstre = tiledMap.getLayers().get("Collisions_monstres").getObjects();
        this.spawnZombie = tiledMap.getLayers().get("Zombie").getObjects();
        this.spawnArcher = tiledMap.getLayers().get("Archer").getObjects();
        this.spawnBat = tiledMap.getLayers().get("Bat").getObjects();
        this.spawnPlayer = tiledMap.getLayers().get("Spawn_personnage").getObjects();
        this.spawnKey = tiledMap.getLayers().get("Key").getObjects();
        this.collisionMort = tiledMap.getLayers().get("Collisions_mort").getObjects();
    }

    /**
     * Get the collision blocks of the map
     * @return the map
     */
    public MapObjects getPlatforms() {
        return collisionPlateforme;
    }

    /**
     * Get all the elements that been created in Tiled and need to be created in the game
     * @return the elements that been created in Tiled
     */
    public HashMap<MapObjects, String> getAllElementsToCreate() {
        HashMap<MapObjects, String> elementsToCreate = new HashMap<>();
        elementsToCreate.put(spawnZombie, "Zombie");
        elementsToCreate.put(spawnArcher, "Archer");
        elementsToCreate.put(spawnBat, "Bat");
        elementsToCreate.put(spawnPlayer, "Personnage");
        elementsToCreate.put(spawnKey, "Key");
        return elementsToCreate;
    }

    /**
     * Get the collision death of the map
     * @return the map
     */
    public MapObjects getDeathCollision() {
        return this.collisionMort;
    }

    /**
     * Get the collision monsters of the map to make them move between the platforms
     * @return the map
     */
    public MapObjects getMonstersCollision() {
        return collisionMonstre;
    }

    /**
     * Create all instances of the elements with their position
     */
    public void createEntities() {
        HashMap<MapObjects, String> elementsToCreate = this.getAllElementsToCreate();

        EntityManager entityManager = EntityManager.getInstance();

        // Loop through all the elements to create
        for (MapObjects mapObjects : elementsToCreate.keySet()) {
            // Loop through all the objects of the current element
            for (MapObject mapObject : mapObjects) {
                // Get the type of the current element
                String type = elementsToCreate.get(mapObjects);

                // Get the position of the current element
                int x = (int) ((RectangleMapObject) mapObject).getRectangle().x;
                int y = (int) ((RectangleMapObject) mapObject).getRectangle().y;

                // Create the element
                switch (type) {
                    case "Zombie":
                        entityManager.newEntity(new Zombie((int) x, (int) y));
                        break;
                    case "Archer":
                        entityManager.newEntity(new Archer((int) x, (int) y));
                        break;
                    case "Bat":
                        entityManager.newEntity(new Bat((int) x, (int) y));
                        break;
                    case "Personnage":
                        entityManager.newEntity(new Personnage((int) x, (int) y));
                        break;
                    case "Key":
                        entityManager.newEntity(new Cle((int) x, (int) y));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Get the instance of the map
     * @param mapId : the id of the map to load
     * @return the map
     */
    public static Map getInstance(int... mapId) {
        return instance;
    }
}