package com.medievaltower.entities.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.Entity;
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
    protected float amplitude = 100f;
    protected float frequency = 1f;
    private float timeElapsed = 0;
    private int xOrigin;
    private int yOrigin;
    private AnimationBat animation = new AnimationBat();

    /**
     * Bat constructor
     * @param x : the x position of the monster
     * @param y : the y position of the monster
     */
    public Bat(int x, int y) {
        super(x, y, 30, 30, new Texture("paix.jpg"));
        this.xOrigin = x;
        this.yOrigin = y;
    }

    /**
     * Move the monster
     * This method contains the sinusoidal move of the monster
     */
    @Override
    public void move() {
        this.xLast = this.x;
        this.yLast = this.y;

        this.sinusoidalMove();

        xVelocity = 0;

        x += this.xVelocity * Gdx.graphics.getDeltaTime();
        setBoundingBox();
    }

    @Override
    public void update() {
        updateTexture(animation);
    }

    /**
     * Sinusoidal move of the monster
     */
    private void sinusoidalMove() {
        // Utilize the sine function to modulate the y position over time
        float deltaTime = Gdx.graphics.getDeltaTime();

        // Calculate the new y position using a sinusoidal function with the original y position as the reference
        float newY = (float) (yOrigin + amplitude * Math.sin(2 * Math.PI * frequency * timeElapsed));

        // Update the y position
        this.y = (int) newY;

        // Update the elapsed time
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

    public void collide(Entity entity){

    }
}

