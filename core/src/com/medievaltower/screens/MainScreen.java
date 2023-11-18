package com.medievaltower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.medievaltower.game.MedievalTower;

import java.util.Locale;

public class MainScreen implements Screen {

    private final MedievalTower game;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    private boolean isLaunched = false;
    private TextButton playButton;
    private TextButton optionButton;
    private TextButton exitButton;

    public MainScreen(MedievalTower game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        batch = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixel.ttf"));

        // Create the BitmapFont using FreeTypeFontGenerator
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64; // Increase font size for a larger title
        font = generator.generateFont(parameter);

        stage = new Stage(viewport, batch);

        // Load background image
        Texture backgroundTexture = new Texture("medievaltower.jpg");
        Image background = new Image(backgroundTexture);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(background);

        createUI();

        // Dispose of the generator after creating the font
        generator.dispose();
    }

    private void createUI() {
        Skin skin = new Skin();
        TextButtonStyle style = new TextButtonStyle();
        style.font = font;
        skin.add("default", style);

        // Create a Label for the game title
        TextButton titleLabel = new TextButton("Medieval Tower".toUpperCase(Locale.ROOT), skin, "default");
        titleLabel.getLabel().setWrap(true);
        titleLabel.getLabel().setAlignment(Align.center);
        titleLabel.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.8f, Align.center);

        // Create buttons with a larger font size for better visibility
        this.playButton = new TextButton("Play", skin);
        this.optionButton = new TextButton("Options", skin);
        this.exitButton = new TextButton("Exit", skin);

        playButton.getLabel().setAlignment(Align.center);
        optionButton.getLabel().setAlignment(Align.center);
        exitButton.getLabel().setAlignment(Align.center);

        float buttonSpacing = 5f; // Adjust this value as needed
        float buttonY = Gdx.graphics.getHeight() * 0.6f;

        playButton.setPosition(Gdx.graphics.getWidth() / 2f, buttonY, Align.center);
        optionButton.setPosition(Gdx.graphics.getWidth() / 2f, buttonY - playButton.getHeight() - buttonSpacing, Align.center);
        exitButton.setPosition(Gdx.graphics.getWidth() / 2f, buttonY - 2 * (playButton.getHeight() + buttonSpacing), Align.center);

        playButton.addListener(new com.badlogic.gdx.scenes.scene2d.InputListener() {
            @Override
            public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                if (!isLaunched) {
                    isLaunched = true;
                    game.setScreen(new GameScreen());
                }
            }

            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        optionButton.addListener(new com.badlogic.gdx.scenes.scene2d.InputListener() {
            @Override
            public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                if (!isLaunched) {
                    isLaunched = true;
                    game.setScreen(new OptionScreen());
                }
            }

            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        exitButton.addListener(new com.badlogic.gdx.scenes.scene2d.InputListener() {
            @Override
            public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }

            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(titleLabel);
        stage.addActor(playButton);
        stage.addActor(optionButton);
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {
        // Initialization code (if any) when the screen is shown
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (this.playButton.isOver()) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
            this.playButton.getLabel().setFontScale(1.2f);
            this.optionButton.getLabel().setFontScale(1f);
            this.exitButton.getLabel().setFontScale(1f);
        } else if (this.optionButton.isOver()) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
            this.optionButton.getLabel().setFontScale(1.2f);
            this.playButton.getLabel().setFontScale(1f);
            this.exitButton.getLabel().setFontScale(1f);
        } else if (this.exitButton.isOver()) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
            this.exitButton.getLabel().setFontScale(1.2f);
            this.playButton.getLabel().setFontScale(1f);
            this.optionButton.getLabel().setFontScale(1f);
        } else {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
            this.playButton.getLabel().setFontScale(1f);
            this.optionButton.getLabel().setFontScale(1f);
            this.exitButton.getLabel().setFontScale(1f);
        }

        // Draw UI
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.update();
    }

    @Override
    public void pause() {
        // Pause logic (if any) when the app is paused
    }

    @Override
    public void resume() {
        // Resume logic (if any) when the app is resumed
    }

    @Override
    public void hide() {
        // Hide logic (if any) when the screen is hidden
    }

    @Override
    public void dispose() {
        // Dispose of resources when the screen is disposed
        stage.dispose();
        batch.dispose();
        font.dispose();
    }
}
