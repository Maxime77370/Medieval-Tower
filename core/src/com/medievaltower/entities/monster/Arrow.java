package com.medievaltower.entities.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.medievaltower.core.Entity;
import com.medievaltower.core.EntityManager;
public class Arrow extends Entity {
    private float direction;
    private float speed = 14;
    private float speed_x;
    private float speed_y;
    private float gravity = 0.2f;
    private double angle = Math.PI/4;

    public Arrow(int x, int y) {
        super(x, y, 100, 100, new Texture("arrow.png"));

        speed_x = speed * (float) Math.cos(angle);
        speed_y = speed * (float) Math.sin(angle);
    }
    @Override
    public void update() {
        // TODO
        // 1. Initialiser le lancement de la flèche avec l'angle.
        // 2. Donner une vitesse de départ à la flèche
        // 3
        speed_y -= gravity;

        this.x += speed_x;
        this.y += speed_y;
    }
    // Creer fleche si null
    // Lancer la fleche avec formule (position de l'archer):
    // Recupérer l'instance singleton du Personnage.
    // Lancer la méthode pour atteindre la target Personnage
    // DEtecter si la collision est faite avec le personnage

    // couldo

}