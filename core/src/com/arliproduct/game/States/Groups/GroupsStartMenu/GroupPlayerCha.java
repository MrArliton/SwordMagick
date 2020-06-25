package com.arliproduct.game.States.Groups.GroupsStartMenu;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.Player.Character;
import com.arliproduct.game.States.StartMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GroupPlayerCha extends Group {
    Sprite fone = new Sprite(new Texture("StartMenu/LevelState.png"));
    Sprite line = new Sprite(new Texture("StartMenu/paint1.png"));
    Sprite image = new Sprite(Character.PicturesPlayerMenu[Character.image]);
    String name = Constants.name;
    final int maxLevel = 318 ;
    BitmapFont texter;
    boolean update = false;
    Button Player;
    StartMenu start;
    public GroupPlayerCha(BitmapFont texter, OrthographicCamera camera, StartMenu start) {
        this.texter = texter;
        Player = new Button(0, Constants.HEIGTH-115,450,115,false,fone.getTexture(),camera);
        Player.setFast(false);
        Player.setDuration(0.2f);
        this.start = start;
    }

    @Override
    public void render(SpriteBatch sb, float delta) {
        Player.draw(sb,delta);
        line.setBounds(115,Constants.HEIGTH-98,maxLevel / 100 * (Character.Expirience * 100 / Character.MaxExpirience),12);
        line.draw(sb);
        texter.setColor(Color.BLACK);
        texter.getData().setScale(0.7f);
        texter.draw(sb,name,120,Constants.HEIGTH-24);
        texter.draw(sb,Constants.lang.getText("level")+Character.level,308,Constants.HEIGTH-24);
        image.setBounds(17,Constants.HEIGTH-102,82,89);
        image.draw(sb);
    }

    @Override
    public void update(float delta) { //
        if(update){
            image = new Sprite(Character.PicturesPlayerMenu[Character.image]);
            name = Constants.name;
        }
    if(Player.onClicked()){
        start.activeWindow = 4;
        update = true;
    }
    }

    @Override
    public void dispose() {
    line.getTexture().dispose();
    Player.dispose();
}
}