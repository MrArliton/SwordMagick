package com.arliproduct.game.States;

import com.arliproduct.game.Actor.Object;
import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.ComboElementor;
import com.arliproduct.game.Elements.ComboRender;
import com.arliproduct.game.Player.Character;
import com.arliproduct.game.State;
import com.arliproduct.game.StateManager;
import com.arliproduct.game.States.Groups.GroupsGame.GameMenu;
import com.arliproduct.game.States.Groups.GroupsGame.InputBoard;
import com.arliproduct.game.States.Groups.GroupsGame.Lose;
import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.arliproduct.game.States.Groups.GroupsGame.WarningWindow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Game extends State {
    Pole pole;
    private float delta;
    Button menu;
    int activeWindow = 0;
    GameMenu gameMenu;
    WarningWindow warningQuit;
    public boolean onerS = false;
    InputBoard input;
    Lose lose = new Lose(this,camera,texter);
    public ComboElementor combo;
   public StateManager stateManager;
    public Game(StateManager manager,String pathMap,String obj,boolean minus) {
        super(manager);
        if(minus){
            Character.activeMinus();
        }
        stateManager = manager;
        int[][] pole = new int[20][20];
        this.pole = new Pole(camera,pole,0,pathMap,this);
        input = new InputBoard(0,camera,texter);
        delta = Gdx.graphics.getDeltaTime();
        combo = new ComboElementor(9,18,this.pole,manager,obj);
        Gdx.gl.glClearColor(0,0,0,1);
        menu = new Button(Constants.WIDTH-100,Constants.HEIGTH-100,80,80,new Texture("All/MenuButton.png"),new Texture("All/MenuButtonDown.png"),false,camera);
        menu.setFast(false);
        menu.setDuration(0.1f);
        menu.setCoordinateActive(-15,-15,5,5);
        gameMenu = new GameMenu(camera,texter,this);
        warningQuit = new WarningWindow(Constants.lang.getText("QuitWarning"),Constants.lang.getText("yes"),Constants.lang.getText("no"),texter,camera);
    }
    public Game(StateManager manager,String pathMap,String obj,String off,boolean minus) {
        super(manager);
        onerS = true;
        if(minus){
            Character.activeMinus();
        }
        stateManager = manager;
        int[][] pole = new int[20][20];
        this.pole = new Pole(camera,pole,0,pathMap,this);
        input = new InputBoard(0,camera,texter);
        delta = Gdx.graphics.getDeltaTime();
        combo = new ComboElementor(9,18,this.pole,manager,obj,true);
        Gdx.gl.glClearColor(0,0,0,1);
        menu = new Button(Constants.WIDTH-100,Constants.HEIGTH-100,80,80,new Texture("All/MenuButton.png"),new Texture("All/MenuButtonDown.png"),false,camera);
        menu.setFast(false);
        menu.setDuration(0.1f);
        menu.setCoordinateActive(-15,-15,5,5);
        gameMenu = new GameMenu(camera,texter,this);
        warningQuit = new WarningWindow(Constants.lang.getText("QuitWarning"),Constants.lang.getText("yes"),Constants.lang.getText("no"),texter,camera);
    }
    @Override
    public void update(float delta) {
        if(activeWindow!=3) {
            pole.update(delta);
            input.update(delta);
        }
        if(combo.character.health<0){
            activeWindow = 3;
        }
                            if(activeWindow==0){// Когда оконы закрыты
            pole.setAlpha(1f);
        if(menu.onClicked()){ // Кнопка меню
            activeWindow = 1;
        }
        if(!input.isClicked().equalsIgnoreCase("Nothing")){ // Ввод данных с кнопок комбинаторики
                combo.comboButton(input.isClicked());
        }
                            }else if(activeWindow == 1){ // окно меню
            gameMenu.update(delta);
            pole.setAlpha(0.4f);
            if(gameMenu.isClose()){
                pole.setAlpha(1);
                activeWindow = gameMenu.nextWindow;
                gameMenu.nextWindow = 0;
                gameMenu.end = false;
            }
                             }else if(activeWindow==2){
            warningQuit.update(delta);
            pole.setAlpha(0.4f);
            if(warningQuit.isClick()){
                pole.setAlpha(1);
                activeWindow = 0;
                if(warningQuit.isClicableButton()==1){
                    manager.set(new StartMenu(manager));
                }
            }
        }else if(activeWindow == 3){
                                lose.update(delta);
                            }
        if(combo.character.isDead()){ // Смерть персонажа

        }
    }
    public void renderWindow(SpriteBatch sb){
        if(activeWindow == 0){
            return;
        } else if (activeWindow == 1) {
            gameMenu.render(sb,delta);
        }else if(activeWindow == 2){
            warningQuit.render(sb,delta);
        }else if(activeWindow == 3){
            lose.render(sb,delta);
        }
    }
    @Override
    public void render(SpriteBatch sb) {
        delta = Gdx.graphics.getDeltaTime();
        sb.setProjectionMatrix(camera.combined);
        camera.update();
        pole.render(sb,delta);
        menu.draw(sb,delta);
        input.render(sb,delta);
        if(activeWindow!=3) {
            combo.render(sb, delta);
        }
        renderWindow(sb);
    }

    @Override
    public void dispose() {
        gameMenu.dispose();
        texter.dispose();
        lose.dispose();
        menu.dispose();
        pole.dispose();
        warningQuit.dispose();
        input.dispose();
    }
}
