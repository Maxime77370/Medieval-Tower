package com.medievaltower.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
public final class Assets {

    private static AssetManager assetManager;

    private Assets() {
        // Private constructor to prevent external instantiation
    }

    public static AssetManager getInstance() {
        if (assetManager == null) {
            assetManager = new AssetManager();
        }
        return assetManager;
    }

    public void loadTexture(String name){
        assetManager.load(name, Texture.class);
    }

}
