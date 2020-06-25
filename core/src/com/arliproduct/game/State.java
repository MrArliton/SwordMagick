package com.arliproduct.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class State { // Экран
    protected OrthographicCamera camera = new OrthographicCamera(Constants.WIDTH,Constants.HEIGTH);
    protected Viewport viewport;
    protected Vector3 vector = new Vector3();
    public StateManager manager;
    protected BitmapFont texter = new BitmapFont(Gdx.files.internal("myFont.fnt"));
    public State(StateManager manager){
        this.manager = manager;
        camera.setToOrtho(false, Constants.WIDTH,Constants.HEIGTH);
        camera.update();
        viewport = manager.view;
    }

    public abstract void update(float delta);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();



}
