package com.medievaltower.entities.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.entities.animation.AnimationBat;

/**
 * Bat class
 * <p>
 *     This class is a subclass class of monster in the game.
 *     It contains the position, the size and the texture of the monster.
 *     It also contains the update method.
 *     This entity move sinusoidally.
 *     It extends the Monstre class.
 * </p>
 * @see Monstre
 */
public class Bat extends Monstre {
    protected float amplitude = 10f;
    protected float frequency = 1f;
    private float timeElapsed = 0;
    private AnimationBat animation = new AnimationBat();

    /**
     * Bat constructor
     * @param x : the x position of the monster
     * @param y : the y position of the monster
     */
    public Bat(int x, int y) {
        super(x, y, 30, 30, new Texture("paix.jpg"));
    }

    /**
     * Move the monster
     * This method contains the sinusoidal move of the monster
     */
    @Override
    public void move() {
        this.sinusoidalMove();
        updateTexture(animation);
    }

    /**
     * Sinusoidal move of the monster
     */
    private void sinusoidalMove() {
        // Utilisez la fonction sinus pour moduler la position y en fonction du temps
        float deltaTime = Gdx.graphics.getDeltaTime();

        float newY = (float) ((getY()) + amplitude * Math.sin(2 * Math.PI * frequency * timeElapsed));

        // Définissez la nouvelle position y
        this.y = (int) newY;

        this.x += speed;
        if (this.x >= 400) {
            speed = -speed;
        } else if (this.x <= 0) {
            speed = Math.abs(speed);
        }
        // Mettez à jour le temps écoulé
        timeElapsed += deltaTime;
    }

    /**
     * Move the bat with the AI
     */
    public void ai() {
        // TODO: Implémentez l'IA du zombie
    }

    /**
     * Attack the personnage
     */
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

