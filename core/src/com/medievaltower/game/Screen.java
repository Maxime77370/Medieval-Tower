package com.medievaltower.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Screen extends ScreenAdapter {

    private int width;
    private int height;
    private boolean fullscreen;

    public Screen(){
        loadParameters();

        // Obtenir la configuration actuelle
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Mon jeu";
        config.width = width;
        config.height = height;

        // Modifier la configuration (par exemple, passer en mode plein écran)
        config.fullscreen = fullscreen; // ou false pour basculer entre plein écran et fenêtré

        // Recréer l'application avec la nouvelle configuration
        new LwjglApplication(new MyGame(), config);
    }

    public boolean loadParameters(){
        this.width = 800;
        this.height = 600;
        this.fullscreen = false;

        return true;
    }

    public void setScreenType(String type){
        this.type = type;
    }

    public String getScreenType(){
        return type;
    }

    public int getWidth(){
        return width;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void render(){
        
    }
}


