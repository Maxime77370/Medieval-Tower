package com.medievaltower.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	private static int width;
	private static int height;
	public static void main (String[] arg) {

		loadParameters();

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle("MedievalTower");
		config.setWindowedMode(width, height);
		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.setForegroundFPS(60);
		config.setTitle("MedievalTower");
		config.useVsync(true);
		new Lwjgl3Application(new MedievalTower(), config);
	}

	public static boolean loadParameters(){
		width = 800;
		height = 600;

		return true;
	}
}
