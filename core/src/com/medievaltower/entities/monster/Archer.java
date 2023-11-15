package com.medievaltower.entities.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.entities.animation.AnimationArcher;
import com.medievaltower.core.Entity;
import com.medievaltower.core.EntityManager;
import com.medievaltower.entities.Personnage;

import java.util.Date;

/**
 * Class Archer
 * <p>
 *     This class is a subclass class of monster in the game.
 *     It contains the position, the size and the texture of the monster.
 *     It also contains the update method.
 *     This entity move horizontally.
 *     It extends the Monstre class.
 * </p>
 * @see Monstre
 */
public class Archer extends Monstre {

    private float timeSinceLastShot = 0;
    private Arrow currentArrow;
    private static final float SHOOT_INTERVAL = 5; // Intervalle de tir en secondes
    private EntityManager entityManager;
    private Date timer;


    /**
     * Archer constructor
     * @param x : the x position of the monster
     * @param y : the y position of the monster
     */
    private AnimationArcher animation = new AnimationArcher();

    public Archer(int x, int y){
        super(x, y, 64, 64, new Texture("paix.jpg"));
        this.entityManager = EntityManager.getInstance();
    }

    /**
     * Move the monster
     * This method contains the horizontal move of the monster
     */
    @Override
    public void move() {
        this.xLast = this.x;
        this.yLast = this.y;

        this.yVelocity -= GRAVITY * Gdx.graphics.getDeltaTime();

        this.x += speed;
        if (speed > 0) {
            animation.setStateLocal("Run", false);
        } else if (speed < 0){
            animation.setStateLocal("Run", true);
        }
        else{
            animation.setStateLocal("Breath");
        }
        if (this.x >= 800) {
            speed = -speed;
        } else if (this.x <= 0) {
            speed = Math.abs(speed);
        }

        super.move();
    }

    /**
     * Move the monster with the AI
     */
    public void ai() {
        // TODO: Implémentez l'IA du zombie
    }

    /**
     * Attack the player
     */
    @Override
    public void attack() {
        currentArrow = new Arrow((int) getX(), (int) getY());
        entityManager.newEntity(currentArrow);
        timer = new Date();
    }

    @Override
    public void receiveDamage(int damage) {

    }
// Je regarde si ça fait 5 secondes que j'ai tiré (objet Date).
    // Si j'ai pas tiré, j'appelle la fonction attaque qui crée une flèche.

    public Arrow getCurrentArrow() {
        return currentArrow;
    }

    @Override
    public void update() {

        /*
        // Vérifie si la flèche actuelle est nulle
        if (currentArrow == null) {
            attack();
        }
        else if (new Date().getTime() - timer.getTime() > SHOOT_INTERVAL * 1000) {
            entityManager.removeEntity(currentArrow);
            System.out.println("delete");
            attack();
        }
        */

        // Tir la flèche si la condition est remplie

        updateTexture(animation);
    }

    public void collide(Entity entity){

    }
}
