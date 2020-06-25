package com.arliproduct.game.desktop;

import com.arliproduct.game.Constants;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arliproduct.game.Main;

public class DesktopLauncher { // Запуск с дектопа
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGTH;
		System.setProperty("user.name","\\u0410\\u0434\\u043c\\u0438\\u043d");
		new LwjglApplication(new Main(), config);
	}
}
