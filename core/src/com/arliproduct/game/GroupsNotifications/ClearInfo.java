package com.arliproduct.game.GroupsNotifications;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.Player.Character;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ClearInfo extends Group {
    Sprite fone = new Sprite(new Texture("StartMenu/items/notification.png"));
    Sprite[] shops;
    BitmapFont texter;
    OrthographicCamera camera;
    Button yes;
    Button no;
    int result = 0;
    public ClearInfo(Sprite[] shops, BitmapFont texter, OrthographicCamera camera) {
        this.shops = shops;
        this.texter = texter;
        this.camera = camera;
        yes = new Button(Constants.WIDTH/2-250,Constants.HEIGTH/2-110,190,50,new Texture("StartMenu/items/button.png"),new Texture("StartMenu/items/buttonDown.png"),true,camera);
        yes.setFast(false);
        yes.setDuration(0.2f);
        yes.setText(-15,15,Constants.lang.getText("yes"),1.5f,1.5f,texter);
        no = new Button(Constants.WIDTH/2+70,Constants.HEIGTH/2-110,190,50,new Texture("StartMenu/items/button.png"),new Texture("StartMenu/items/buttonDown.png"),true,camera);
        no.setFast(false);
        no.setDuration(0.2f);
        no.setText(-10,10,Constants.lang.getText("no"),1.3f,1.3f,texter);
    }
    public String getInfo(int i){
        switch (i) {
            case 0:
                return Character.bay+"";
            case 1:
                return Character.bsy+"";
            case 2:
                return Character.bey+"";
            case 3:
                return Character.bhsy+"";
            case 4:
                return Character.bsay+"";
        }
        return "0";
    }
    @Override
    public void render(SpriteBatch sb, float delta) {
        fone.setBounds(Constants.WIDTH/2-300,Constants.HEIGTH/2-150,600,300);
        fone.draw(sb);
        texter.setColor(Color.BLACK);
        texter.getData().setScale(1);
            texter.draw(sb, Constants.lang.getText1("CLRE"), Constants.WIDTH / 2-235, Constants.HEIGTH / 2 + 130);
            for(int i=0;i<5;i++){
                shops[i].setBounds(Constants.WIDTH/2-210+90*i,Constants.HEIGTH/2+25,50,50);
                shops[i].draw(sb);
                    texter.draw(sb, getInfo(i), Constants.WIDTH/2-210+90*i+60, Constants.HEIGTH/2+60);}
            yes.draw(sb,delta);
            no.draw(sb,delta);
    }


    public int getResult(){
        int buff = result;
        result = 0;
        return buff;
    }
    @Override
    public void update(float delta) {
        if(yes.onClicked()){
            result = 1;
            clearEffects();
        }
        if(no.onClicked()){
            result = 2;
        }
    }
    private void clearEffects(){
        Character.bhsy = 0;
        Character.bay =0;
        Character.bey = 0;
        Character.bsy = 0;
        Character.besy =0;
        Character.bsay = 0;
    }

    @Override
    public void dispose() {
        fone.getTexture().dispose();

    }
}
