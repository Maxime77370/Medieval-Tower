package com.medievaltower.entities.monster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie extends Monstre {
    public Zombie(int x, int y) {
        super(x, y);

        // Créez une texture 1x1 de couleur verte
        Pixmap pixmap = new Pixmap(this.getWidth(), this.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.YELLOW);
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
    }

    @Override
    public void attack() {

    }

    @Override
    public void receiveDamage(int damage) {

    }

    @Override
    public void update() {

    }
}
