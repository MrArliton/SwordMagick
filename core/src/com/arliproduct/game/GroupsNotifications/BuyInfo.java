package com.arliproduct.game.GroupsNotifications;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class BuyInfo extends Group {
    Sprite fone;
    Sprite coin = new Sprite(new Texture("StartMenu/items/coin.png"));
    int money;
    Button yes;
    Button no;
    String text;
    boolean on=false;
    BitmapFont texter;
    int result = 0;
    public BuyInfo(int money, String text, BitmapFont texter,OrthographicCamera camera) { // Класс отвечающий за предупреждение о покупке
        this.money = money;
        this.text = text;
        this.texter = texter;
        createButtons(camera);
        fone = new Sprite(new Texture("StartMenu/items/notification.png"));
    }
    public BuyInfo(String text,OrthographicCamera camera,BitmapFont texter) { // Класс отвечающий за предупреждение о покупке
        this.text =text;
        this.texter  = texter;
        on = true;
        fone = new Sprite(new Texture("StartMenu/items/notificationNo.png"));
        createButtons(camera);
    }
    public void createButtons(OrthographicCamera camera){
        if(on){
         yes = new Button(Constants.WIDTH/2-103,Constants.HEIGTH/2-110,198,50,new Texture("StartMenu/items/button.png"),new Texture("StartMenu/items/buttonDown.png"),true,camera);
         yes.setFast(false);
         yes.setDuration(0.2f);
         yes.setText(-50,15,Constants.lang.getText("okey"),1.5f,1.5f,texter);
        }else{
            yes = new Button(Constants.WIDTH/2-250,Constants.HEIGTH/2-110,190,50,new Texture("StartMenu/items/button.png"),new Texture("StartMenu/items/buttonDown.png"),true,camera);
            yes.setFast(false);
            yes.setDuration(0.2f);
            yes.setText(-15,15,Constants.lang.getText("yes"),1.5f,1.5f,texter);
    no = new Button(Constants.WIDTH/2+70,Constants.HEIGTH/2-110,190,50,new Texture("StartMenu/items/button.png"),new Texture("StartMenu/items/buttonDown.png"),true,camera);
            no.setFast(false);
            no.setDuration(0.2f);
            no.setText(-10,10,Constants.lang.getText("no"),1.3f,1.3f,texter);
}
    }
    public int getResult(){
        return result;
    }
    @Override
    public void render(SpriteBatch sb, float delta) {
        fone.setBounds(Constants.WIDTH/2-300,Constants.HEIGTH/2-150,600,300);
        fone.draw(sb);
        texter.getData().setScale(1.2f);
        texter.draw(sb,text,Constants.WIDTH/2-260,Constants.HEIGTH/2+115);
        if(!on) {
            coin.setBounds(Constants.WIDTH / 2 - 60, Constants.HEIGTH / 2 - 25, 50, 60);
            coin.draw(sb);
            texter.draw(sb,money+"",Constants.WIDTH/2,Constants.HEIGTH/2+20);
        }
        yes.draw(sb,delta);
        if(!on)
        no.draw(sb,delta);
    }

    @Override
    public void update(float delta) {
        if(yes.onClicked()){
        result = 1;
        }
        if(!on) {
            if (no.onClicked()) {
                result = 2;
            }
        }
    }

    @Override
    public void dispose() {
        yes.dispose();
        coin.getTexture().dispose();
        fone.getTexture().dispose();
        if(!on) {
            no.dispose();
        }
    }
}
