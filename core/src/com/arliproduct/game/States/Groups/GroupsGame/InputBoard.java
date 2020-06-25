package com.arliproduct.game.States.Groups.GroupsGame;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InputBoard extends Group {
    Button up;
    Button down;
    Button left;
    Button rigth;
    Button attack;
    Button power;
    Button shield;
    Button help;
    Button activate;
    Button clear;
    String clicked = "nothing";
    public InputBoard(int mode,Camera camera,BitmapFont texter){
        createButtons(camera,texter);
    }
    private void createButtons(Camera camera, BitmapFont texter){

        up = new Button(Constants.WIDTH/2+170,296,150,162,new Texture("Game/GameMenu/up.png"),new Texture("Game/GameMenu/upDown.png"),false,camera);
        up.sound = false;
        down = new Button(Constants.WIDTH/2+170,10,150,162,new Texture("Game/GameMenu/down.png"),new Texture("Game/GameMenu/downDown.png"),false,camera);
        down.sound = false;
        rigth =new Button(Constants.WIDTH/2+253,170,200,128,new Texture("Game/GameMenu/right.png"),new Texture("Game/GameMenu/rightDown.png"),false,camera);
        rigth.sound = false;
        left =new Button(Constants.WIDTH/2+40,170,200,128,new Texture("Game/GameMenu/left.png"),new Texture("Game/GameMenu/leftDown.png"),false,camera);
        left.sound = false;
        attack =new Button(Constants.WIDTH/2+10,298,150,150,new Texture("Game/GameMenu/attack.png"),new Texture("Game/GameMenu/attackDown.png"),false,camera);
        attack.sound = false;
        shield =new Button(Constants.WIDTH/2+180+150,298,150,150,new Texture("Game/GameMenu/shield.png"),new Texture("Game/GameMenu/shieldDown.png"),false,camera);
        shield.sound = false;
        power =new Button(Constants.WIDTH/2+180+150,20,150,150,new Texture("Game/GameMenu/power.png"),new Texture("Game/GameMenu/powerDown.png"),false,camera);
        power.sound = false;
        help =new Button(Constants.WIDTH/2+10,20,150,150,new Texture("Game/GameMenu/special.png"),new Texture("Game/GameMenu/specialDown.png"),false,camera);
        help.sound = false;
        activate = new Button(Constants.WIDTH-160,298,150,150,new Texture("Game/GameMenu/other.png"),new Texture("Game/GameMenu/otherDown.png"),true,camera);
        activate.sound = false;
        activate.setText(-5,0,Constants.lang.getText("activate"),0.8f,0.8f,texter);
        clear = new Button(Constants.WIDTH-160,20,150,150,new Texture("Game/GameMenu/other.png"),new Texture("Game/GameMenu/otherDown.png"),true,camera);
        clear.setText(3,0,Constants.lang.getText("clear"),0.8f,0.8f,texter);
    }
    private void drawButton(SpriteBatch sb,float delta){
        up.draw(sb,delta);
        down.draw(sb,delta);
        left.draw(sb,delta);
        rigth.draw(sb,delta);
        attack.draw(sb,delta);
        shield.draw(sb,delta);
        help.draw(sb,delta);
        power.draw(sb,delta);
        activate.draw(sb,delta);
        clear.draw(sb,delta);
    }
    public String isClicked(){
        return clicked;
    }
    private void updateButton(float delta){
        if(up.onClicked()){
        clicked = "up";
        }else

        if(down.onClicked()){
            clicked = "down";
        }else

        if(left.onClicked()){
            clicked = "left";
        }else

        if(rigth.onClicked()){
            clicked = "right";
        }else

        if(attack.onClicked()){
            clicked = "attack";
        }else

        if(help.onClicked()){
            clicked = "help";
        }else

        if(power.onClicked()){
            clicked = "power";
        }else

        if(shield.onClicked()){
            clicked = "shield";
        }else
            if(activate.onClicked()){
                clicked = "activate";
            }
            else
                if(clear.onClicked()){
                clicked = "clear";
                }
                else
        {clicked = "Nothing";}
    }
    @Override
    public void render(SpriteBatch sb, float delta) {
        drawButton(sb,delta);
    }

    @Override
    public void update(float delta) {
            updateButton(delta);
    }

    @Override
    public void dispose() {
        up.dispose();
        down.dispose();
        rigth.dispose();
        left.dispose();
        activate.dispose();
        attack.dispose();
        clear.dispose();
        shield.dispose();
        help.dispose();
        power.dispose();
    }
}
