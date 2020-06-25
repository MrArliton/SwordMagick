package com.arliproduct.game;

import com.arliproduct.game.Player.Character;
import com.arliproduct.game.States.LanguageChose;
import com.arliproduct.game.States.LoadingGame;
import com.arliproduct.game.States.StartMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends Game {
	private SpriteBatch batch;
	private StateManager stateManager;
	private Viewport view;
	float durationSave = 0;
	final int saveDuration = 10;
	@Override
	public void create () { // Начальный метод

			batch = new SpriteBatch();
			view = new FitViewport(Constants.WIDTH, Constants.HEIGTH);
			stateManager = new StateManager(view);
			stateManager.save.loadSettings();
			Gdx.gl.glClearColor(0, 0, 0, 1);
			// Создаём окно загрузки
			Constants.lang.language = 0;
			stateManager.push(new LoadingGame(stateManager));
        }
	@Override
	public void render () { // Обновляем логику игры
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         // Основной код прорисовки всех элементов.
		batch.begin();
		stateManager.update(Gdx.graphics.getDeltaTime());
        stateManager.render(batch);
		batch.end();
		if(durationSave>saveDuration){
			stateManager.save.saveSettings();
		}else{durationSave+=Gdx.graphics.getDeltaTime();}
	}
	@Override
	public void resume(){

	}
	@Override
	public void pause(){

	}
	@Override
    public void resize(int width,int heigth){
        view.update(width,heigth);

    }
	@Override
	public void dispose () { // Очищаем ресурсы
		batch.dispose();
		stateManager.dispose();
	}
}
