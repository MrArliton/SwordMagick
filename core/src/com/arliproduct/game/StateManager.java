package com.arliproduct.game;

import com.arliproduct.game.Elements.LevelCreator;
import com.arliproduct.game.Player.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Stack;

public class StateManager { // Управление экранами
    private Stack<State> states;
    public Viewport view;
    public InputController input;
    public LevelCreator LevelsC = new LevelCreator();
    public TextInputController inputText;
    public Save save;
    public StateManager(Viewport view){
        states = new Stack<State>();
        this.view = view;
        save = new Save();
        inputText = new TextInputController();
        input = new InputController();
        input.updateCoordinate(view); // Обновление x y при нажатии по экрану
    }

    public void push(State state){ // Помещаем в стек
        states.push(state);
        view.setCamera(states.peek().camera);
    }
    public void pop(){ // Удаляем из стека
        states.pop().dispose();
    }
    public void  set(State state){ // Устанавлиаем стек
        states.pop().dispose();
        push(state);
    }
    public void update(float delta){ // Обновление логики стека
        Character.update();
        input.updateCoordinate();
        states.peek().update(delta);
    }
    public void render(SpriteBatch sb){ // обновление графики стека
        input.updateCoordinate();
        states.peek().render(sb);
    }
    public State getState(){
        return states.peek();
    }
    public void dispose(){
        if(!states.isEmpty()){
        pop();}
    }
}
