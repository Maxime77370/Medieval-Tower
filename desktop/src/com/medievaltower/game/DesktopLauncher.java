package com.medievaltower.game;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.medievaltower.game.MedievalTower;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	private static int width;
	private static int height;
	private static Graphics.DisplayMode fullscreen;
	public static void main (String[] arg) {

		loadParameters();

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle("MedievalTower");
		config.setWindowedMode(width, height);

		config.setForegroundFPS(60);
		config.setTitle("MedievalTower");
		new Lwjgl3Application(new MedievalTower(), config);
	}

	public static boolean loadParameters(){
		width = 800;
		height = 600;
		fullscreen = ;

		return true;
	}
}
