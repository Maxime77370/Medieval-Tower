package com.medievaltower.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.medievaltower.game.MedievalTower;
import com.medievaltower.levels.Map;

public class EndGameScreen implements Screen {

    private final MedievalTower game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final long startTime;

    public EndGameScreen(MedievalTower game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.startTime = TimeUtils.millis();
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        batch.begin();

        // Fond noir
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Afficher "Loading"
        font.draw(batch, "Loading...", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        batch.end();

        // Vérifier si 2-3 secondes se sont écoulées
        if (TimeUtils.timeSinceMillis(startTime) > 2000) { // 2000 millisecondes pour 2 secondes
            GameScreen gameScreen = GameScreen.getInstance();
            gameScreen.restartGame();
            game.setScreen(gameScreen);
        }
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

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
