package com.medievaltower.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.medievaltower.core.EntityManager;
import com.medievaltower.entities.Personnage;
import com.medievaltower.game.Camera;
import com.medievaltower.game.MedievalTower;
import com.medievaltower.levels.Map;

/**
 * GameScreen class
 * <p>
 * This class is used to create the game screen.
 * It contains the render method.
 * It is used to render the game screen.
 * </p>
 */
public class GameScreen implements Screen {

    private MedievalTower game;
    private final SpriteBatch batch;
    private final Personnage personnage;
    private final Camera camera;
    private final EntityManager entityManager;
    private Map map;
    private final Label fpsLabel; // Label for FPS
    private final Stage stage; // Stage for HUD
    private final Table table; // Table to organize HUD elements
    private final BitmapFont font; // BitmapFont for Label
    private final ProgressBar expBar; // Progress bar for experience points
    private final Label levelLabel; // Label for player level
    private final Label expLabel; // Label for experience points
    private final Array<Image> heartImages = new Array<>(); // Liste pour gérer les images de cœur
    private final Table heartTable; // Table pour organiser les images de cœur
    private Label monsterCountLabel; // Label for monster count
    private Label keyLabel; // Label for key
    private Label potionCountLabel; // Label for potion
    private Label inventoryCountLabel; // Label for inventory
    private static GameScreen Instance;

    /**
     * GameScreen constructor
     * <p>
     * This constructor is used to create the game screen.
     * It takes the game as parameter.
     * It is used to create the game screen.
     * </p>
     *
     * @param game : the game
     */
    public GameScreen(MedievalTower game) {

        this.game = game;
        Instance = this;

        batch = new SpriteBatch();

        entityManager = EntityManager.getInstance();
        map = new Map(4);

        // create entities in the map
        map.createEntities();

        System.out.println(EntityManager.getInstance().getEntities());

        // get the personnage
        this.personnage = Personnage.getInstance();

        // New camera
        camera = new Camera();

        // Initialize the HUD
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        table.top().left();
        stage.addActor(table);

        font = new BitmapFont();

        // Create the heart table
        heartTable = new Table();
        heartTable.top().right();
        heartTable.setFillParent(true);
        stage.addActor(heartTable);

        // FPS Label (Top right corner)
        Table fpsTable = new Table();
        fpsTable.bottom().left();
        fpsTable.setFillParent(true);
        fpsLabel = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), new Label.LabelStyle(font, Color.WHITE));
        fpsTable.add(fpsLabel).pad(10);
        stage.addActor(fpsTable);

        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        skin.add("green", new Texture(pixmap));

        // Customize the progress bar style
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.knob = skin.getDrawable("white"); // Set knob to white color
        progressBarStyle.knobBefore = skin.getDrawable("green"); // Set knobBefore to green color

        // Create a background drawable for the progress bar
        ProgressBar.ProgressBarStyle backgroundStyle = new ProgressBar.ProgressBarStyle();
        backgroundStyle.knobBefore = skin.getDrawable("white");

        // Set the background style to the progress bar
        progressBarStyle.background = skin.getDrawable("white");

        expBar = new ProgressBar(0, 325, 1, false, progressBarStyle);
        expBar.setPosition(10, 10);
        expBar.setSize(290, expBar.getPrefHeight());
        expBar.setValue(personnage.getExp());

        // Label to show the player's level
        levelLabel = new Label("Niv. " + personnage.getLevel(), new Label.LabelStyle(font, Color.WHITE));

        // Label to show the experience points progress
        expLabel = new Label("Exp restante  : " + (325 - personnage.getExp()), new Label.LabelStyle(font, Color.WHITE));

        // Arrange the widgets in a table
        Table expTable = new Table();
        expTable.center().top();
        expTable.setFillParent(true);
        expTable.add(expBar).pad(10); // Add the exp bar to the expTable
        expTable.row(); // Move to the next row
        expTable.add(levelLabel).padTop(5); // Add the level label
        expTable.row(); // Move to the next row
        expTable.add(expLabel).padTop(5); // Add the exp label

        stage.addActor(expTable);


        // Load textures for HUD elements
        Texture inventoryTexture = new Texture("inventory.png"); // Replace with your inventory icon
        Texture potionTexture = new Texture("potion.png"); // Replace with your potion icon
        Texture monsterTexture = new Texture("monster.png"); // Replace with your monster icon
        Texture keyTexture = new Texture("Texture/Key/key.png"); // Replace with your key icon


        // Add a separator
        table.row().padTop(5);

        // Display inventory icon
        Image inventoryImage = new Image(inventoryTexture);
        table.add(inventoryImage).pad(5).width(50).height(50);

        // Display inventory count (replace with the actual count)
        inventoryCountLabel = new Label("x" + personnage.getWeaponInventory().size(), new Label.LabelStyle(font, Color.WHITE));
        table.add(inventoryCountLabel).pad(5);

        // Add another separator
        table.row().padTop(10);

        // Display potion icon
        Image potionImage = new Image(potionTexture);
        table.add(potionImage).pad(5).width(50).height(50);

        // Display potion count (replace with the actual count)
        potionCountLabel = new Label("x" + map.countPotions(), new Label.LabelStyle(font, Color.WHITE));
        table.add(potionCountLabel).pad(5);

        // Add information about monsters (replace with the actual count)
        table.row().padTop(10);

        Image monsterImage = new Image(monsterTexture);
        table.add(monsterImage).pad(5).width(50).height(50);
        monsterCountLabel = new Label("x3" + entityManager.getNumberOfMonsters(), new Label.LabelStyle(font, Color.WHITE));
        table.add(monsterCountLabel).pad(5);

        table.row().padTop(10);

        // display key icon
        Image keyImage = new Image(keyTexture);
        table.add(keyImage).pad(5).width(50).height(50);


        if (Personnage.getInstance().isKeyEquipped()) {
            keyLabel = new Label("FOUND", new Label.LabelStyle(font, Color.GREEN));
            table.add(keyLabel).pad(10);
        } else {
            keyLabel = new Label("NOT FOUND", new Label.LabelStyle(font, Color.RED));
            table.add(keyLabel).pad(10);
        }

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {
        // Initialization code (if any) when the screen is shown
    }

    /**
     * Render the game screen
     * @param delta
     */
    @Override
    public void render(float delta) {

        // if delta time to long don't run the script to fix bug collide
        if (Gdx.graphics.getDeltaTime() > 0.2f){
            return;
        }
        // Update entities
        entityManager.update();

        float red = 31f / 255f;
        float green = 28f / 255f;
        float blue = 18f / 255f;

        // Clear the screen
        ScreenUtils.clear(red, green, blue, 1);

        // Update camera matrix
        camera.update();

        // Set the projection matrix to the SpriteBatch
        batch.setProjectionMatrix(camera.getCamera().combined);

        // Draw map
        map.render(camera.getCamera()); // Pass the camera to the render method

        // Update the HUD
        updateHUD();

        // Draw the debug entities
        entityManager.drawDebug(batch);

        // Draw entities
        entityManager.draw(batch);

        // stop the game
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        // if player is dead
        if (personnage.isDead()) {
            dispose();
            ((Game) Gdx.app.getApplicationListener()).setScreen(new DeathScreen(game));
        }


        // Display square with debug
        //stage.setDebugAll(true);

        stage.draw();
    }

    /**
     * Update the HUD
     */
    private void updateHUD() {
        // Update the HUD
        fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        monsterCountLabel.setText("x" + entityManager.getNumberOfMonsters());

        // Update the progress bar
        expBar.setValue(personnage.getExp());

        // Update the exp restant
        expLabel.setText("Exp restante  : " + (325 - personnage.getExp()));

        // Update the level
        levelLabel.setText("Niv. " + personnage.getLevel());

        // Update the key
        if (Personnage.getInstance().isKeyEquipped()) {
            keyLabel.setText("FOUND");
            keyLabel.setStyle(new Label.LabelStyle(font, Color.GREEN));
        } else {
            keyLabel.setText("NOT FOUND");
            keyLabel.setStyle(new Label.LabelStyle(font, Color.RED));
        }

        // Update the heart
        updateHearts();

        // Display inventory count (replace with the actual count)
        inventoryCountLabel.setText("x" + personnage.getWeaponInventory().size());

        // Display potion count (replace with the actual count)
        potionCountLabel.setText("x" + map.countPotions());

        // Update the monster
        monsterCountLabel.setText("x" + entityManager.getNumberOfMonsters());

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    }

    /**
     * Update the hearts in the screen
     */
    private void updateHearts() {
        int currentHealth = personnage.getHealth();

        // Supprimer les cœurs en trop
        if (heartImages.size > currentHealth) {
            for (int i = heartImages.size - 1; i >= currentHealth; i--) {
                heartImages.get(i).remove(); // Retirer l'image de la scène
                heartImages.removeIndex(i); // Retirer de la liste
            }
        }

        // Ajouter des cœurs si nécessaire
        while (heartImages.size < currentHealth) {
            Image heartImage = new Image(new Texture("heart.png")); // Utilisez une texture chargée une seule fois si possible
            heartImages.add(heartImage);
            heartTable.add(heartImage).pad(5).width(50).height(50); // Ajouter à la table des cœurs
        }

        // Réorganiser les cœurs dans la table
        heartTable.clear();
        for (Image heartImage : heartImages) {
            heartTable.add(heartImage).pad(5).width(50).height(50);
        }
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

        // Dispose of element of entities
        entityManager.dispose();

        // Dispose of the HUD
        stage.dispose();

        // Dispose of the font
        font.dispose();
    }

    public void restartGame() {
        // Réinitialiser toutes les entités, états et variables nécessaires
        entityManager.reset();
        map.createEntities();
    }

    public static GameScreen getInstance() {
        return Instance;
    }
}
