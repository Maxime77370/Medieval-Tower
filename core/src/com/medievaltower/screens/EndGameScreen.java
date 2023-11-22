package com.medievaltower.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
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
        this.startTime = TimeUtils.millis();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixel.ttf"));

        // Create the BitmapFont using FreeTypeFontGenerator
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64; // Increase font size for a larger title
        font = generator.generateFont(parameter);
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

        // Afficher les histoires
        int mapId = Map.getInstance().getIdMap();
        showStory(mapId-1);
  

        batch.end();

        // Vérifier si 2-3 secondes se sont écoulées
        if (TimeUtils.timeSinceMillis(startTime) > 2000) { // 2000 millisecondes pour 2 secondes
            GameScreen gameScreen = GameScreen.getInstance();
            gameScreen.restartGame();
            game.setScreen(gameScreen);
        }
    }

    private void showStory(int mapId) {
    if (Gdx.files.internal("Story/" + mapId + ".txt").exists()) {
        String story = Gdx.files.internal("Story/" + mapId + ".txt").readString();

        // Utiliser GlyphLayout pour mesurer la taille du texte
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, story, Color.WHITE, Gdx.graphics.getWidth(), Align.center, true);

        // Calculer la position Y centrée, en tenant compte des marges si nécessaire
        float textY = (Gdx.graphics.getHeight() + layout.height) / 2f; // Centré verticalement, ajuster si nécessaire

        // Dessiner le texte centré horizontalement
        font.draw(batch, layout, 0, textY);
    } else {
        System.out.println("le fichier n'existe pas : " + mapId);
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
