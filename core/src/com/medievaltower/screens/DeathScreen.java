package com.medievaltower.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.medievaltower.game.MedievalTower;
import com.medievaltower.levels.Map;

public class DeathScreen implements Screen {

    private MedievalTower game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Texture backgroundTexture;
    private final Texture deathImage;
    private final Stage stage;
    private TextButton btnRestart;
    private TextButton btnQuit;

    public DeathScreen(MedievalTower game) {

        this.game = game;

        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("medievaltower.jpg"));
        deathImage = new Texture(Gdx.files.internal("death.png"));
        stage = new Stage(new ScreenViewport(), batch);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixel.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        font = generator.generateFont(parameter);
        generator.dispose();

        initializeButtons();
        Gdx.input.setInputProcessor(stage);
    }

    private void initializeButtons() {
        TextButtonStyle style = new TextButtonStyle();
        style.font = font;

        btnRestart = new TextButton("Restart", style);
        btnRestart.addListener(event -> {
            if (event.isHandled()) {
                GameScreen gameScreen = GameScreen.getInstance();
                Map.getInstance().loadMap(3);
                gameScreen.restartGame();
                game.setScreen(gameScreen);
            }
            return true;
        });

        btnQuit = new TextButton("Exit", style);
        btnQuit.addListener(event -> {
            if (event.isHandled()) {
                Gdx.app.exit();
            }
            return true;
        });

        float buttonSpacing = 5f;
        float buttonY = Gdx.graphics.getHeight() * 0.5f;

        btnRestart.setPosition(Gdx.graphics.getWidth() / 2f, buttonY, Align.center);
        btnQuit.setPosition(Gdx.graphics.getWidth() / 2f, buttonY - btnRestart.getHeight() - buttonSpacing, Align.center);

        stage.addActor(btnRestart);
        stage.addActor(btnQuit);
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
        batch.draw(deathImage, Gdx.graphics.getWidth() / 2 - deathImage.getWidth() / 2 - 120, Gdx.graphics.getHeight() / 2f + 40);
        font.draw(batch, "You died !", Gdx.graphics.getWidth() / 2f - 70 , Gdx.graphics.getHeight() / 2f + 150);
        batch.end();

        if (this.btnRestart.isOver()) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
            this.btnRestart.getLabel().setFontScale(1.2f);
            this.btnQuit.getLabel().setFontScale(1f);
        } else if (this.btnQuit.isOver()) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
            this.btnQuit.getLabel().setFontScale(1.2f);
            this.btnRestart.getLabel().setFontScale(1f);
        } else {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
            this.btnQuit.getLabel().setFontScale(1f);
            this.btnRestart.getLabel().setFontScale(1f);
        }

        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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

    // Implémentez les autres méthodes nécessaires de l'interface Screen

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        backgroundTexture.dispose();
        deathImage.dispose();
        stage.dispose();
    }
}
