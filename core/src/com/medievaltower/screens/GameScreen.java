package com.medievaltower.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.medievaltower.core.EntityManager;
import com.medievaltower.entities.Personnage;
import com.medievaltower.game.Camera;
import com.medievaltower.game.MedievalTower;
import com.medievaltower.levels.Chronometre;
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

    private static GameScreen Instance;
    private final SpriteBatch batch;
    private final Personnage personnage;
    private final Camera camera;
    private final EntityManager entityManager;
    private final Label fpsLabel; // Label for FPS
    private final Stage stage; // Stage for HUD
    private final Table table; // Table to organize HUD elements
    private final BitmapFont font; // BitmapFont for Label
    private final ProgressBar expBar; // Progress bar for experience points
    private final Label levelLabel; // Label for player level
    private final Label expLabel; // Label for experience points
    private final Array<Image> heartImages = new Array<>(); // Liste pour gérer les images de cœur
    private final Table heartTable; // Table pour organiser les images de cœur
    private final MedievalTower game;
    private final Map map;
    private final Label monsterCountLabel; // Label for monster count
    private final Label keyLabel; // Label for key
    private final Label potionCountLabel; // Label for potion
    private final Chronometre chronometre = new Chronometre();
    private final Label endLabel; // Label for end game
    private final Label countDeathLabel;

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
        map = new Map(2);

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

        expBar = new ProgressBar(0, personnage.getExpRequiredForNextLevel(), 1, false, progressBarStyle);
        expBar.setPosition(10, 10);
        expBar.setSize(150, expBar.getPrefHeight()); // Set a constant width for the progress bar

        // Label to show the player's level
        levelLabel = new Label("Niv. " + personnage.getLevel(), new Label.LabelStyle(font, Color.WHITE));

        // Label to show the experience points progress
        expLabel = new Label("Exp restante  : " + personnage.getExpRequiredForNextLevel(), new Label.LabelStyle(font, Color.WHITE));

        // Update the progress bar
        expBar.setValue(personnage.getExp());
        expBar.setSize(290, expBar.getPrefHeight());

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

        Texture potionTexture = new Texture("potion.png"); // Replace with your potion icon
        Texture monsterTexture = new Texture("monster.png"); // Replace with your monster icon
        Texture keyTexture = new Texture("Texture/Key/key.png"); // Replace with your key icon
        Texture death = new Texture("death.png"); // Replace with your key icon


        // Add a separator
        table.row().padTop(5);

        // display key icon
        Image keyImage = new Image(keyTexture);
        table.add(keyImage).pad(5).width(50).height(50);

        // Display Key
        if (Personnage.getInstance().isKeyEquipped()) {
            keyLabel = new Label("FOUND", new Label.LabelStyle(font, Color.GREEN));
            table.add(keyLabel).pad(10);
        } else {
            keyLabel = new Label("NOT FOUND", new Label.LabelStyle(font, Color.RED));
            table.add(keyLabel).pad(10);
        }

        // Add another separator
        table.row().padTop(10);

        // Display potion icon
        Image potionImage = new Image(potionTexture);
        table.add(potionImage).pad(5).width(50).height(50);

        // Display potion count (replace with the actual count)
        potionCountLabel = new Label("x" + EntityManager.getInstance().getNumberOfPotions(), new Label.LabelStyle(font, Color.WHITE));
        table.add(potionCountLabel).pad(5);

        // Add information about monsters (replace with the actual count)
        table.row().padTop(10);

        Image monsterImage = new Image(monsterTexture);
        table.add(monsterImage).pad(5).width(50).height(50);
        monsterCountLabel = new Label("x3" + entityManager.getNumberOfMonsters(), new Label.LabelStyle(font, Color.WHITE));
        table.add(monsterCountLabel).pad(5);

        table.row().padTop(10);

        Image deathImage = new Image(death);
        table.add(deathImage).pad(5).width(50).height(50);
        countDeathLabel = new Label("x" + personnage.getDeathCount(), new Label.LabelStyle(font, Color.RED));
        table.add(countDeathLabel).pad(5);


        // display end game message on the bloc end game
        if (Personnage.getInstance().isKeyEquipped()) {
            endLabel = new Label("Press F to continue", new Label.LabelStyle(font, Color.WHITE));
        } else {
            endLabel = new Label("You need to find the key...", new Label.LabelStyle(font, Color.WHITE));
        }

        if (Map.getInstance().getIdMap() == 2) {
            MapObjects text_jump = Map.getInstance().getTutoJump();
            MapObjects text_attack = Map.getInstance().getTutoAttack();
            MapObjects text_slide = Map.getInstance().getTutoSlide();

            float x_jump = text_jump.get(0).getProperties().get("x", Float.class);
            float y_jump = text_jump.get(0).getProperties().get("y", Float.class);

            float x_attack = text_attack.get(0).getProperties().get("x", Float.class);
            float y_attack = text_attack.get(0).getProperties().get("y", Float.class);

            float x_slide = text_slide.get(0).getProperties().get("x", Float.class);
            float y_slide = text_slide.get(0).getProperties().get("y", Float.class);


            batch.begin();
            font.draw(batch, "Press Z or ↑ to jump", x_jump, y_jump);
            font.draw(batch, "Press Space to attack", x_attack, y_attack);
            font.draw(batch, "Press and keep <- or -> and press ↓", x_slide, y_slide);
            batch.end();
        }

        // Set position to the end bloc
        MapObjects enblocs = Map.getInstance().getEndCollision();

        // get the end blocs position
        float x = enblocs.get(0).getProperties().get("x", Float.class);
        float y = enblocs.get(0).getProperties().get("y", Float.class);

        endLabel.setPosition(x, y);

        stage.addActor(endLabel);

        Gdx.input.setInputProcessor(stage);
    }

    public static GameScreen getInstance() {
        return Instance;
    }

    @Override
    public void show() {
        // Initialization code (if any) when the screen is shown
        chronometre.start();
    }

    /**
     * Render the game screen
     *
     * @param delta
     */
    @Override
    public void render(float delta) {

        // if delta time to long don't run the script to fix bug collide
        if (Gdx.graphics.getDeltaTime() > 0.2f) {
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

        // Set position to the end bloc
        MapObjects enblocs = Map.getInstance().getEndCollision();

        float x = enblocs.get(0).getProperties().get("x", Float.class) - 100;
        float y = enblocs.get(0).getProperties().get("y", Float.class) + 50; // Par exemple, 50 pixels au-dessus du bloc

        String message;

        if (Personnage.getInstance().isKeyEquipped()) {
            message = "COME TO THE NEXT LEVEL";
        } else {
            message = "YOU NEED TO FIND THE KEY";
        }

        if (Map.getInstance().getIdMap() == 2) {
            MapObjects text_jump = Map.getInstance().getTutoJump();
            MapObjects text_attack = Map.getInstance().getTutoAttack();
            MapObjects text_slide = Map.getInstance().getTutoSlide();

            float x_jump = text_jump.get(0).getProperties().get("x", Float.class);
            float y_jump = text_jump.get(0).getProperties().get("y", Float.class);

            float x_attack = text_attack.get(0).getProperties().get("x", Float.class);
            float y_attack = text_attack.get(0).getProperties().get("y", Float.class);

            float x_slide = text_slide.get(0).getProperties().get("x", Float.class);
            float y_slide = text_slide.get(0).getProperties().get("y", Float.class);

            String message_jump = "Press Z Q S D to move.";
            String message_attack = "Press Space to attack monsters.";
            String message_slide = "Press and keep Q or D and press S to slide.";
            batch.begin();
            font.draw(batch, message_jump, x_jump - 80, y_jump + 30);
            font.draw(batch, message_attack, x_attack, y_attack + 30);
            font.draw(batch, message_slide, x_slide - 90, y_slide + 20);
            batch.end();
        }

        batch.begin();
        font.draw(batch, message, x, y);
        batch.end();

        stage.addActor(endLabel);


        // Draw the debug entities
        // entityManager.drawDebug(batch);

        // Draw entities
        entityManager.draw(batch);

        // stop the game
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        // if player is dead
        if (personnage.isDead()) {
            chronometre.stop();
            ((Game) Gdx.app.getApplicationListener()).setScreen(new DeathScreen(game));
        }

        // if player finish a level
        if (personnage.isFinish()) {
            chronometre.stop();
            ((Game) Gdx.app.getApplicationListener()).setScreen(new EndGameScreen(game));
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
        monsterCountLabel.setText("x" + entityManager.getNumberOfMonsters());

        // Check if the player leveled up
        if (personnage.levelUp()) {
            // If the player leveled up, update the progress bar for the new level
            expBar.setRange(0, personnage.getExpRequiredForNextLevel());
            expBar.setValue(0);
            expBar.setSize(150, expBar.getPrefHeight());
        } else {
            expBar.setRange(0, personnage.getExpRequiredForNextLevel());
            expBar.setValue(personnage.getExp());
            // Maintenez la taille constante de la barre de progression
            expBar.setSize(150, expBar.getPrefHeight());
        }

        // Update other HUD elements
        expLabel.setText("Exp restante  : " + (personnage.getExpRequiredForNextLevel()));
        if (personnage.getCurrentLevel() >= personnage.getMaxLevel()) {
            levelLabel.setText("Niveau Max");
        } else {
            levelLabel.setText("Niv. " + personnage.getCurrentLevel());
        }

        // Update the personnage level
        personnage.levelUp();

        // Mettre à jour le chronomètre
        long elapsedTimeInSeconds = chronometre.getElapsedTime() / 1000; // Convertir en secondes
        int hours = (int) (elapsedTimeInSeconds / 3600);
        int minutes = (int) (elapsedTimeInSeconds % 3600) / 60;
        int seconds = (int) elapsedTimeInSeconds % 60;

        // Formatter et afficher le temps écoulé
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond() + " | " + time);


        // Update the key
        if (Personnage.getInstance().isKeyEquipped()) {
            keyLabel.setText("FOUND");
            keyLabel.setStyle(new Label.LabelStyle(font, Color.GREEN));
        } else {
            keyLabel.setText("NOT FOUND");
            keyLabel.setStyle(new Label.LabelStyle(font, Color.RED));
        }

        // Update the death count
        countDeathLabel.setText("Death count : x" + personnage.getDeathCount());

        // Update the heart
        updateHearts();

        // Display potion count (replace with the actual count)
        potionCountLabel.setText("x" + EntityManager.getInstance().getNumberOfPotions());

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
        personnage.reset();
        map.loadMap(map.getIdMap());
        map.createEntities();
    }
}
