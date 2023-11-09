package com.medievaltower.entities.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bat extends Monstre {
    protected float amplitude = 10f; // Ajustez l'amplitude selon vos besoins
    protected float frequency = 1f; // Ajustez la fréquence selon vos besoins
    private float timeElapsed = 0;

    public Bat(int x, int y) {
        super(x, y);

        // Créez une texture 1x1 de couleur verte
        Pixmap pixmap = new Pixmap(this.getWidth(), this.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();

        // Créez une texture à partir du pixmap
        Texture texture = new Texture(pixmap);

        // Définissez la texture du sprite
        getSprite().setRegion(new TextureRegion(texture));

        // N'oubliez pas de disposer du pixmap
        pixmap.dispose();

    }

    @Override
    public void move() {
        this.sinusoidalMove();
    }

    private void sinusoidalMove() {
        // Utilisez la fonction sinus pour moduler la position y en fonction du temps
        float deltaTime = Gdx.graphics.getDeltaTime();

        float newY = (float) ((getY()) + amplitude * Math.sin(2 * Math.PI * frequency * timeElapsed));

        // Définissez la nouvelle position y
        this.y = (int) newY;

        // Mettez à jour le temps écoulé
        timeElapsed += deltaTime;
    }

    public void ai() {
        // TODO: Implémentez l'IA du zombie
    }

    @Override
    public void attack() {

    }

    @Override
    public void receiveDamage(int damage) {

    }

    @Override
    public void update() {
        this.move();
    }
}

