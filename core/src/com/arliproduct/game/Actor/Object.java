package com.arliproduct.game.Actor;

import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Object extends ObjectClass {
Vector2 vector;
float health;
boolean deadOn = false;
boolean dead = false;
float shield;
public float rotation = 0;
Sprite sprite;
Pole pole;
    public Object(Vector2 vector,float shield,float health, boolean dead, Sprite sprite, Pole pole) {
        this.vector = vector;
        this.shield = shield;
        this.dead = dead;
        this.health = health;
        this.sprite = sprite;
        this.pole = pole;
        index = 20;
        sprite = new Sprite(sprite);
        this.sprite.setBounds(pole.getCoordinates((int)vector.x,(int)vector.y)[0],pole.getCoordinates((int)vector.x,(int)vector.y)[1],pole.getCoordinates((int)vector.x,(int)vector.y)[2],pole.getCoordinates((int)vector.x,(int)vector.y)[3]);
    }
    float buffer = 0;
    boolean buffer1 = false;
    public void update(float delta){
        if(health<0&dead){
            deadOn = true;
        }
    }

    @Override
    public void dispose() {
    sprite.getTexture().dispose();
    }
        @Override
    public void render(Batch sb,float delta){
        if(!deadOn){
        sprite.setBounds(pole.getCoordinates((int)vector.x,(int)vector.y)[0],pole.getCoordinates((int)vector.x,(int)vector.y)[1],pole.getCoordinates((int)vector.x,(int)vector.y)[2],pole.getCoordinates((int)vector.x,(int)vector.y)[3]);
        sprite.setRotation(rotation);
        sprite.draw(sb);}else{ // При смерти
        }
    }
    public void animate(int direction){

    }
    public boolean interupt(int x,int y,int direction,float attack){
        if(vector.x == x&&vector.y ==y&!deadOn){
            animate(direction);
            health-=attack/(shield/2f);
            System.out.println("health "+health );
            return true;
        }else{return false;}
    }

    @Override
    public boolean isMe(int x, int y) {
        if(vector.x == x&&vector.y == y){
            return true;
        }
        return false;
    }
}
