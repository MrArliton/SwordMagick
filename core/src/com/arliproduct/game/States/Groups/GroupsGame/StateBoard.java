package com.arliproduct.game.States.Groups.GroupsGame;

import com.arliproduct.game.Actor.Character;
import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Group;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ConcurrentModificationException;

public class StateBoard extends Group {
    float power;
    float powerMax;
    float health;
    float speed;
    float attack;
    float superPower;
    Character player;
    boolean timeout = false;
    int frame = 0;
    TextureRegion[][] textureRegions = new TextureRegion(new Texture("Game/States/AtlasAnimState.png")).split(64,36);
    Sprite[] fone;
    BitmapFont font = new BitmapFont();

    public StateBoard(float power, float powerMax, float health,float speed, float attack, float superPower, Character player) {
        this.power = power;
        this.powerMax = powerMax;
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.superPower = superPower;
        this.player = player;
        fone = new Sprite[textureRegions[0].length];
        for(int i = 0;i<textureRegions[0].length;i++){
            fone[i]=new Sprite(textureRegions[0][i]);
        }
    }
    public void setTimeout(boolean timeout){
        this.timeout = timeout;
    }
    public void updateCharacteristics(float power, float powerMax, float health,float speed, float attack,float superPower){
        this.power = power;
        this.powerMax = powerMax;
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.superPower = superPower;
    }
    @Override
    public void render(SpriteBatch sb, float delta) {
        fone[frame].setBounds(Constants.WIDTH/2+20,Constants.HEIGTH-260,400,225);
        fone[frame].draw(sb);
        font.getData().setScale(4f);
        font.draw(sb,(int)health+"",Constants.WIDTH/2+140,Constants.HEIGTH-65);
        font.getData().setScale(4f);
        font.draw(sb,(int)power+"",Constants.WIDTH/2+140,Constants.HEIGTH-180);
    }
    // buffer
    float buffer1= 0;
    //
    @Override
    public void update(float delta) {
        if(buffer1>0.2f){
            buffer1 = 0;
            if(frame!=fone.length-1) {
                frame++;
            }else{frame = 0;}
        }else{buffer1+=delta;}
    }

    @Override
    public void dispose() {
font.dispose();
    }
}
