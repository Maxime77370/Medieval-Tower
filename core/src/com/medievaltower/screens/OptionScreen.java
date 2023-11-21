package com.medievaltower.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.medievaltower.game.GameSettings;
import com.medievaltower.game.MedievalTower;

/**
 * OptionScreen class
 * <p>
 * This class is used to create the option screen.
 * It contains the render method.
 * It is used to render the option screen.
 * </p>
 */
public class OptionScreen implements Screen {

    private MedievalTower game;
    private final Stage stage;
    private final BitmapFont font;
    private final Texture backgroundTexture;
    private final Batch batch;
    private Slider speedSlider;
    private Slider gravitySlider;
    private Label speedLabel;
    private Label gravityLabel;

    /**
     * OptionScreen constructor
     * <p>
     * This constructor is used to create the option screen.
     * It takes the game as parameter.
     * It is used to create the option screen.
     * </p>
     *
     * @param game : the game
     */
    public OptionScreen(MedievalTower game) {

        this.game = game;
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("medievaltower.jpg"));
        stage = new Stage(new ScreenViewport(), batch);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixel.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        font = generator.generateFont(parameter);
        generator.dispose();

        initializeSliders();
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Initialize the sliders
     * <p>
     * This method is used to initialize the sliders.
     * It is used to initialize the sliders and change the value of the params.
     * </p>
     */
    private void initializeSliders() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, null);
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        // Position et style pour le slider de vitesse
        speedSlider = new Slider(0, 10, 0.1f, false, sliderStyle);
        speedSlider.setValue(GameSettings.speed);
        speedSlider.setPosition(100, 400, Align.center); // Nouvelle position
        speedLabel = new Label("Speed: " + speedSlider.getValue(), labelStyle);
        speedLabel.setPosition(Gdx.graphics.getWidth() / 2, 430, Align.center); // Nouvelle position

        // Position et style pour le slider de gravité
        gravitySlider = new Slider(0, 20, 0.1f, false, sliderStyle);
        gravitySlider.setValue(GameSettings.gravity);
        gravitySlider.setPosition(100, 250, Align.center); // Nouvelle position
        gravityLabel = new Label("Gravity: " + gravitySlider.getValue(), labelStyle);
        gravityLabel.setPosition(Gdx.graphics.getWidth() / 2, 350, Align.center); // Nouvelle position

        // Boutons Sauvegarder et Annuler
        TextButton saveButton = new TextButton("Save", buttonStyle);
        saveButton.setPosition(100, 100);

        saveButton.addListener(event -> {
            if (event.isHandled()) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen(game));
            }
            return true;
        });

        TextButton cancelButton = new TextButton("Cancel", buttonStyle);
        cancelButton.setPosition(saveButton.getWidth() + 150, 100);

        cancelButton.addListener(event -> {
            if (event.isHandled()) {
                game.setScreen(new MainScreen(game));
            }
            return true;
        });

        // Ajout des éléments au stage
        stage.addActor(saveButton);
        stage.addActor(cancelButton);
        stage.addActor(speedSlider);
        stage.addActor(speedLabel);
        stage.addActor(gravitySlider);
        stage.addActor(gravityLabel);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        speedLabel.setText("Speed: " + speedSlider.getValue());
        gravityLabel.setText("Gravity: " + gravitySlider.getValue());

        GameSettings.speed = speedSlider.getValue();
        GameSettings.gravity = gravitySlider.getValue();

        stage.act();
        stage.draw();
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    // Implémentation des autres méthodes de l'interface Screen...

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        stage.dispose();
        font.dispose();
    }
}
