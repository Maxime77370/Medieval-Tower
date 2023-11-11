package com.medievaltower.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tileset {
    private TextureRegion[][] Textures;

    public Tileset(String path, int blockSizeX, int blockSizeY) {
        Texture texture = new Texture("Texture/" + path);
        int cols = texture.getWidth() / blockSizeX;
        int rows = texture.getHeight() / blockSizeY;

        Textures = new TextureRegion[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Textures[row][col] = new TextureRegion(texture, col * blockSizeX, row * blockSizeY, blockSizeX, blockSizeY);
            }
        }
    }


    public TextureRegion[][] getTextures() {
        return Textures.clone();
    }

    public TextureRegion getTexture(int row, int col) {
        return Textures.clone()[row][col];
    }

    public TextureRegion getTexture(int id) {
        int row = id / Textures[0].length;
        int col = id % Textures[0].length;
        return Textures.clone()[row][col];
    }

    public int getNbTexture() {
        return Textures.length * Textures[0].length;
    }
}

