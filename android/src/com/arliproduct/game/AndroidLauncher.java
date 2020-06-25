package com.arliproduct.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.arliproduct.game.Main;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) { // Запуск андроид
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.hideStatusBar = true;
		config.useImmersiveMode = true;
		config.useAccelerometer = Constants.useAccelerometr;
		config.useCompass = Constants.useCompass;
		initialize(new Main(), config);
	}
}
