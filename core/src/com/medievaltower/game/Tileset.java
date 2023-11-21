package com.medievaltower.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Tileset class
 * <p>
 * This class is used to create a tileset.
 * It contains the textures of the tileset.
 * </p>
 */
public class Tileset {
    private TextureRegion[][] Textures;

    /**
     * Tileset constructor
     * <p>
     * This constructor is used to create a tileset.
     * It takes the path of the tileset, the size of the block in the tileset as parameters.
     * It is used to create a tileset.
     * </p>
     *
     * @param path : the path of the tileset
     * @param blockSizeX : the size of the block in the tileset
     * @param blockSizeY : the size of the block in the tileset
     */
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

    /**
     * Get the textures of the tileset
     * @return the textures of the tileset
     */
    public TextureRegion[][] getTextures() {
        return Textures.clone();
    }

    /**
     * Get a texture of the tileset
     * @param row
     * @param col
     * @return the texture of the tileset
     */
    public TextureRegion getTexture(int row, int col) {
        return Textures.clone()[row][col];
    }

    /**
     * Get a texture of the tileset
     * @param id
     * @return the texture of the tileset
     */
    public TextureRegion getTexture(int id) {
        int row = id / Textures[0].length;
        int col = id % Textures[0].length;
        return Textures.clone()[row][col];
    }

    /**
     * Get the number of textures of the tileset
     * @return the number of textures of the tileset
     */
    public int getNbTexture() {
        return Textures.length * Textures[0].length;
    }
}

