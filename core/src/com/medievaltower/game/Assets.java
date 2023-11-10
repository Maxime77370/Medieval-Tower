package com.medievaltower.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Assets class
 * <p>
 * This class is a singleton that contains the AssetManager.
 * </p>
 * @see AssetManager
 */
public final class Assets {

    private static AssetManager assetManager;

    private Assets() {
        // Private constructor to prevent external instantiation
    }

    /**
     * Get the AssetManager
     * @return the AssetManager
     */
    public static AssetManager getInstance() {
        if (assetManager == null) {
            assetManager = new AssetManager();
        }
        return assetManager;
    }

    /**
     * Load a texture
     * @param name : the name of the texture
     */
    public void loadTexture(String name){
        assetManager.load(name, Texture.class);
    }

}
