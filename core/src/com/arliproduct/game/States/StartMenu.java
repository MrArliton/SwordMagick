package com.arliproduct.game.States;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.TextView;
import com.arliproduct.game.InputController;
import com.arliproduct.game.Player.Character;
import com.arliproduct.game.State;
import com.arliproduct.game.StateManager;
import com.arliproduct.game.States.Groups.GroupsStartMenu.GroupPlayerCha;
import com.arliproduct.game.States.Groups.GroupsStartMenu.PlayerMenu;
import com.arliproduct.game.States.Groups.GroupsStartMenu.SettingWindow;
import com.arliproduct.game.States.Groups.GroupsStartMenu.Shop;
import com.arliproduct.game.States.Groups.GroupsStartMenu.UpgradeView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.CopyOnWriteArrayList;

public class StartMenu extends State {
    private Texture fone;
    private float foneStart = 0;
    private Sprite foneS;
    Sprite infopanel = new Sprite(new Texture("StartMenu/Money.png"));
    private Button startGame;
    private Button Creators;
    private Button editInventory;
    private Button setting;
    private Button shopWindow;
    private SettingWindow settingsWindow;
    public int activeWindow = 0;
    private int next;
    boolean end = false;
    private float delta;
    UpgradeView upgrade;
    TextView textPro;
    GroupPlayerCha chara;
    Shop shop = new Shop(camera,this,texter);
    PlayerMenu menu = new PlayerMenu(camera,this,texter);
    // Особые элементы для окон
    private Button exit;

    public StartMenu(StateManager manager) { // Объявление элементов и их работа
        super(manager);
        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0,0,0,1);
        fone = new Texture("All/Fone.png");
        createButtons();
        infopanel.setBounds(Constants.WIDTH/2+250,Constants.HEIGTH-80,170,80);
        settingsWindow = new SettingWindow(camera,texter,manager);
        chara = new GroupPlayerCha(texter,camera,this);
        upgrade = new UpgradeView(texter,camera,manager);
        foneS =new Sprite(fone);
        textPro = new TextView(Constants.WIDTH/2-400, Constants.HEIGTH/2-300,800,600,1f,1f,new Texture("StartMenu/SettingWindow.png"),texter);
        textPro.setCoordinateText(30,-40);
        textPro.print(Constants.lang.getText("ForCreator"));
    }


    @Override
    public void update(float delta) { // Обновление логики окна
        if(!activeWindow()) {
            chara.update(delta);
            if (startGame.onClicked()) { // При нажатии на кнопку старт
                end = true;
                next = 1;
            }
            if (Creators.onClicked()) { // При нажатии на кнопку о разработчиках
                        activeWindow = 3;
            }
            if (setting.onClicked()) {
            activeWindow = 1;
            }
            if(editInventory.onClicked()){
                activeWindow = 2;
            }
            if(shopWindow.onClicked()){
                activeWindow = 5;
            }
        }
    }
    public boolean activeWindow(){
        if(activeWindow == 0){;
            return false;
        }else if(activeWindow == 1){
            settingsWindow.update(delta);
            if(settingsWindow.isClose(true)){activeWindow = 0; }
        }else if(activeWindow == 2){
            upgrade.update(delta);
            if(upgrade.isClose(true)){activeWindow = 0; }
        }else if(activeWindow == 3){
            if(exit.onClicked()){
                activeWindow = 0;
            }
        }else if(activeWindow == 4){
            menu.update(delta);
            if(exit.onClicked()){
                activeWindow = 0;
            }
        }else if(activeWindow == 5){
            shop.update(delta);
        }
        return true;
    }
    public void renderWindow(SpriteBatch sb){
        if(activeWindow == 0){
            return;
        } else if (activeWindow == 1) {
            settingsWindow.render(sb,delta);
        }else if(activeWindow == 2){
            upgrade.render(sb,delta);
        }else if(activeWindow == 3){
            textPro.draw(sb);
            exit.draw(sb,delta);
        }else if(activeWindow == 4){
            menu.render(sb,delta);
        }else if(activeWindow==5){
            shop.render(sb,delta);
        }
    }
    @Override
    public void render(SpriteBatch sb) { // Отрисовка
        delta = Gdx.graphics.getDeltaTime();
        sb.setProjectionMatrix(camera.combined);
        camera.update();
        if(!end&&StartFone(sb)) {
                sb.draw(foneS,0,0,Constants.WIDTH,Constants.HEIGTH);
                startGame.draw(sb, delta);
                Creators.draw(sb, delta);
                setting.draw(sb,delta);
                editInventory.draw(sb,delta);
                chara.render(sb,delta);
                shopWindow.draw(sb,delta);
                // Панель информации
                infopanel.draw(sb);
                if((Character.money+"").length()>2){
                    texter.getData().setScale(1.5f);
                }else {
                    texter.getData().setScale(2f);
                }
                texter.setColor(Color.BLACK);
                texter.draw(sb, Character.money+"",Constants.WIDTH/2+330,Constants.HEIGTH-18);
                //
                renderWindow(sb);
        }else if(end){
            if(EndFone(sb)){
                if(next == 1){manager.set(new LevelsGet(manager));}//new Game(manager,"TEST.txt","TESTObjects.txt")
            }
        }
    }

    @Override
    public void dispose() { // Высвобождение ресурсов
        startGame.dispose();
        Creators.dispose();
        texter.dispose();
        setting.dispose();
        settingsWindow.dispose();
        fone.dispose();
        exit.dispose();
        textPro.dispose();
        upgrade.dispose();
        shopWindow.dispose();
        menu.dispose();
        infopanel.getTexture().dispose();
    }
    public void createButtons(){        //Кнопка начать
        startGame = new Button(Constants.WIDTH/2+300-228,Constants.HEIGTH/5,455,250,new Texture("StartMenu/Start.png"),new Texture("StartMenu/StartDown.png"),true,camera);
        startGame.setFast(false);
        startGame.setDuration(0.2f);
        startGame.setText(0,15,Constants.lang.getText("start"),0.85f,0.85f,texter);
        // Кнопка о разработчиках
        Creators = new Button(Constants.WIDTH-400,Constants.HEIGTH/20,350,80,new Texture("StartMenu/Creators.png"),new Texture("StartMenu/CreatorsDown.png"),true,camera);
        Creators.setFast(false);
        Creators.setDuration(0.2f);
        Creators.setText(-5,8,Constants.lang.getText("developers"),0.85f,0.85f,texter);
        // Кнопка инвентаря
        editInventory = new Button(Constants.WIDTH/2-500,Constants.HEIGTH/5+50,400,150,new Texture("All/Button2.png"),new Texture("All/Button2Down.png"),true,camera);
        editInventory.setFast(false);
        editInventory.setDuration(0.2f);
        editInventory.setText(-10,8,Constants.lang.getText("advancement"),0.85f,0.85f,texter);
        //Кнопка настройки
        setting = new Button(Constants.WIDTH-94-15,Constants.HEIGTH-94-20,94,94,new Texture("StartMenu/Setting.png"),new Texture("StartMenu/SettingDown.png"),false,camera);
        setting.setFast(false);
        setting.setDuration(0.2f);
        setting.setCoordinateActive(-10,-10,5,5);
        // Кнопка выхода для окна о разработчках
        exit = new Button(Constants.WIDTH/2+310,Constants.HEIGTH/2+200,60,60,new Texture("All/exitButton.png"),new Texture("All/exitButtonDown.png"),false,camera);
        exit.setFast(false);
        exit.setDuration(0.2f);
        //Кнопка магазина
        shopWindow = new Button(100,100,80,80,new Texture("StartMenu/items/ButtonShop.png"),new Texture("StartMenu/items/ButtonShopDown.png"),false,camera);
        shopWindow.setFast(false);
        shopWindow.setDuration(0.25f);
    }



    private boolean StartFone(SpriteBatch sb){ // Стартуем фон
        foneS.setAlpha(foneStart);
        foneS.setBounds(0,0,Constants.WIDTH,Constants.HEIGTH);
        if(foneStart>=0.95){
            return true;
        }
        foneStart+=Gdx.graphics.getDeltaTime();
        foneS.draw(sb);
        return false;
    }
    private boolean EndFone(SpriteBatch sb){ // Уходит фон
        foneS.setAlpha(foneStart);
        foneS.setBounds(0,0,Constants.WIDTH,Constants.HEIGTH);
        if(foneStart<0.1f){
           return true;
        }
        foneStart = foneStart-delta*2;
        System.out.println(foneStart);
        foneS.draw(sb);
        return false;
    }
}
