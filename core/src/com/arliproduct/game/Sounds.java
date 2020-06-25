package com.arliproduct.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
   public static Sound soundA = Gdx.audio.newSound(Gdx.files.internal("Sound/attack.mp3"));
   public static Sound soundS = Gdx.audio.newSound(Gdx.files.internal("Sound/shield.mp3"));
   public static Sound soundB = Gdx.audio.newSound(Gdx.files.internal("Sound/Button.mp3"));
}
