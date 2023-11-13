package com.medievaltower.game;

public abstract class Screen {

    private int width;
    private int height;
    private String type;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
    }

    public boolean loadParameters(){
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
}