package com.arliproduct.game.States.Groups.GroupsGame;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.Player.Character;
import com.arliproduct.game.States.Game;
import com.arliproduct.game.States.StartMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lose extends Group { // Если игрок умер
    Sprite fone = new Sprite(new Texture("Game/GameMenu/Lose-Fone.png"));
    Button button;
    Game game;
    BitmapFont texter;

    public Lose(Game game, OrthographicCamera camera, BitmapFont texter) {
        this.game = game;
        button = new Button(Constants.WIDTH/2-200,Constants.HEIGTH/2-220,400,120,new Texture("Game\\GameMenu\\Button.png"),new Texture("Game/GameMenu/ButtonDown.png"),true,camera);
        button.setFast(false);
        button.setDuration(0.2f);
        this.texter = texter;
        button.setText(-40,10,Constants.lang.getText1("go menu"),1f,1f,texter);
    }

    public void addProgress(){
        Character.money+= Constants.money;
        Character.Expirience+=Constants.exp;
        if(Constants.maxLevel<Constants.level){
            Constants.maxLevel = Constants.level;
        }
        if(Constants.maxExp<Constants.exp){
            Constants.maxExp = Constants.exp;
        }
        if(Constants.maxMoney<Constants.money){
            Constants.maxMoney = Constants.money;
        }
        Constants.money = 0;
        Constants.exp = 0;
        Constants.level = 0;
        game.stateManager.save.saveSettings();

    }
    @Override
    public void render(SpriteBatch sb, float delta) {
        fone.setBounds(Constants.WIDTH/2-400,Constants.HEIGTH/2-250,800,500);
        fone.draw(sb);
        button.draw(sb,delta);
        texter.getData().setScale(2.5f);
        texter.setColor(Color.BLACK);
        texter.draw(sb,Constants.lang.getText1("dead"),Constants.WIDTH/2-300,Constants.HEIGTH/2+180);
        texter.getData().setScale(5.3f);
        texter.draw(sb,Constants.money+"",Constants.WIDTH/2-230,Constants.HEIGTH/2+50);
        texter.draw(sb,Constants.exp+"",Constants.WIDTH/2+205,Constants.HEIGTH/2+50);
    }

    @Override
    public void update(float delta) {
        if(button.onClicked()){
            game.manager.set(new StartMenu(game.stateManager));
            addProgress();
        }
    }

    @Override
    public void dispose() {
        button.dispose();
        fone.getTexture().dispose();
    }
}
