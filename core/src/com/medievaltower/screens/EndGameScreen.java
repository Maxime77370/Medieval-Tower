package com.medievaltower.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.medievaltower.core.EntityManager;
import com.medievaltower.entities.Personnage;
import com.medievaltower.game.MedievalTower;
import com.medievaltower.levels.Map;

public class EndGameScreen implements Screen {

    private final MedievalTower game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final long startTime;
    private float continueTextAnimationTime = 0;

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
        showStory(mapId - 1, delta);


        batch.end();

        // Vérifier si 2-3 secondes se sont écoulées
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (Map.getInstance().getIdMap() == 5) {
                // Supprimer l'instance du personnage
                Personnage.getInstance().resetPersonnage();
                EntityManager.getInstance().reset();
                Map.getInstance().dispose();
                game.setScreen(new MainScreen(game));
                return;
            }
            GameScreen gameScreen = GameScreen.getInstance();
            gameScreen.restartGame();
            game.setScreen(gameScreen);
        }
    }

    private void showStory(int mapId, float delta) {
        if (Gdx.files.internal("Story/" + mapId + ".txt").exists()) {
            String story = Gdx.files.internal("Story/" + mapId + ".txt").readString();

            GlyphLayout layout = new GlyphLayout();
            layout.setText(font, story, Color.WHITE, Gdx.graphics.getWidth(), Align.center, true);
            float textY = (Gdx.graphics.getHeight() + layout.height) / 2f;

            // Dessiner le texte centré
            font.draw(batch, layout, 0, textY);

            // Animation pour "Press Escape to continue"
            continueTextAnimationTime += delta;
            float scale = 1 + 0.1f * MathUtils.sin(continueTextAnimationTime * 2); // Animation avec une oscillation sinusoïdale

            // Définir la taille de la police pour l'animation
            font.getData().setScale(scale);
            GlyphLayout continueLayout = new GlyphLayout(font, "Press Escape to continue");
            font.draw(batch, continueLayout, (Gdx.graphics.getWidth() - continueLayout.width) / 2, 150);

            // Restaurer la taille de la police pour le reste du texte
            font.getData().setScale(1);
        } else {
            System.out.println("Le fichier n'existe pas : " + mapId);
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
