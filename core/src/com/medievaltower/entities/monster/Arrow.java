package com.medievaltower.entities.monster;

import com.badlogic.gdx.graphics.Texture;
import com.medievaltower.core.Entity;
import com.medievaltower.entities.Personnage;
import com.medievaltower.entities.animation.AnimationArrow;

public class Arrow extends Entity {
    private float speed = 12;
    private float speed_x;
    private float speed_y;
    private float gravity = 0.1f;
    private double angle = Math.PI/3;
    private float rotation = 0; // Ajout de l'attribut rotation
    private AnimationArrow animation = new AnimationArrow();

    /**
     * Arrow constructor
     * @param x
     * @param y
     */
    public Arrow(int x, int y) {
        super(x, y, 32, 32, new Texture("arrow.png"));

        speed_x = speed * (float) Math.cos(angle);
        speed_y = speed * (float) Math.sin(angle);
        rotation = (float) Math.toDegrees(angle); // Initialisation de la rotation

    }

    /**
     * Move the arrow with a formula
     */
    @Override
    public void move(){
        this.xLast = this.x;
        this.yLast = this.y;

        speed_y -= gravity;

        this.x += speed_x;
        this.y += speed_y;

        // Mise à jour de la rotation

        // Calculate the current rotation angle based on the arrow's velocity vector
        rotation = (float) Math.toDegrees(Math.atan2(-speed_y, speed_x));
        // Set the rotation of the arrow's sprite
        sprite.setRotation(rotation);

        super.move();
    }

    /**
     * Override the update method
     */
    @Override
    public void update() {
        super.update();
    }

    /**
     * Override the collide method
     * @param entity
     */
    public void collide(Entity entity){
        if (entity instanceof Personnage){
            Personnage personnage = (Personnage) entity;
            personnage.receiveDamage(1);
        }
    }
}