package com.arliproduct.game.States.Groups.GroupsGame;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarningWindow extends Group {
    int x = Constants.WIDTH/2-350;
    int y = Constants.HEIGTH/2-200;
    int heigth = 400;
    int width = 700;
    boolean click = false;
    int otvet;
    String text;
    Texture fone;
    Button yes;
    Button no;
    BitmapFont texter;
    public WarningWindow(String text, String yes, String no, BitmapFont texter, Camera camera){
        fone = new Texture("Game/GameMenu/FoneWarning.png");
        this.texter = texter;
        this.text =text;
        this.yes = new Button(x+80,y+50,100*2,40*2,new Texture("Game/GameMenu/Button.png"),new Texture("Game/GameMenu/ButtonDown.png"),true,camera);
        this.yes.setText(0,5,Constants.lang.getText("yes"),0.8f,0.8f,texter);
        this.yes.setFast(false);
        this.yes.setDuration(0.2f);
        this.no = new Button(x+width-width/2+80,y+50,100*2,40*2,new Texture("Game/GameMenu/Button.png"),new Texture("Game/GameMenu/ButtonDown.png"),true,camera);
        this.no.setText(0,5,Constants.lang.getText("no"),0.8f,0.8f,texter);
        this.no.setFast(false);
        this.no.setDuration(0.2f);
    }

    public void drawText(SpriteBatch sb,String text){
        texter.setColor(Color.BLACK);
        texter.getData().setScale(1.6f);
        int a = text.length();
        for(int i = 0;i<text.length();i++){
            if(text.charAt(i)=='\n'){
                a = i;
            }
        }
        texter.draw(sb,text,(2*x+width)/2-a*12-30,heigth+y-heigth/4);
    }
    public boolean isClick(){
        return click;
    }
    public int isClicableButton(){
        click = false;
        return otvet;
    }
    @Override
    public void render(SpriteBatch sb, float delta) {
        sb.draw(fone,x,y,width,heigth);
            yes.draw(sb, delta);
            no.draw(sb, delta);
            drawText(sb, text);

    }

    @Override
    public void update(float delta) {

            if (yes.onClicked()) {
                click = true;
                otvet = 1;
            }

            if (no.onClicked()) {
                click = true;
                otvet = 0;
            }

    }

    @Override
    public void dispose() {
            yes.dispose();
            no.dispose();
    }
}
