package com.arliproduct.game.Objects;

import com.arliproduct.game.Actor.Bullet;
import com.arliproduct.game.Actor.ObjectClass;
import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class GunObject extends ObjectClass {
    Sprite[] gun; // Последний элемент должен быть текстурой пули
    float FrameDuration;
    float duration;
    int direction;
    int x;
    int y;
    float health = 10;
    int xP = x;
    int yP = y;
    int frame = 0;
    Pole pole;
    float attackP;
    float buffer;
    boolean attack = false;
    boolean bullet = false;
    boolean dead = false;
    boolean deadOn = false;
    boolean dBullet = false;
    float buffer1;
    Bullet bu;

    public GunObject(Sprite[] gun, float frameDuration,float duration, int direction, int x, int y, float health,int dead,Pole pole, float attackP) {
        this.gun = gun;
        FrameDuration = frameDuration;
        this.direction = direction;
        this.duration = duration;
        for(int i = 0;i<gun.length-1;i++){
            gun[i].setOrigin(gun[i].getWidth(),gun[i].getHeight());
            gun[i].setRotation(getCRotation());
        }
        index = 7;
        this.x = x;
        this.y = y;
        this.health = health;
        this.pole = pole;
        this.attackP = attackP;
        if(dead == 1){
           this.deadOn = true;
        }else{deadOn = false;}
    }
    @Override
    public boolean isMe(int x, int y) {
        if(this.x == x&&this.y ==y ){
            return true;
        }else{return false;}
    }
    @Override
    public void render(Batch sb, float delta) {
        if(!dead) {
            gun[frame].setBounds(pole.getCoordinates(x, y)[0], pole.getCoordinates(x, y)[1], pole.getCoordinates(x, y)[2], pole.getCoordinates(x, y)[3]);
            gun[frame].draw(sb);
            if (bullet) {
                int rotation = 0;
                if(direction == 6 || direction == 4){
                    rotation = 90;
                }
                bu = new Bullet(gun[gun.length - 1], xP, yP, pole, rotation);
                bu.setBounds(pole.getCoordinates(x, y)[0], pole.getCoordinates(x, y)[1], pole.getCoordinates(x, y)[2], pole.getCoordinates(x, y)[3]);
                bullet = false;
                dBullet = true;
                xP = x;
                yP = y;
            }
            if (dBullet) {
                bu.draw(sb, delta);
            }
            if (dBullet && !bu.hasActions()) {
                xP += getCX();
                yP += getCY();
                bu.addAction(Actions.moveTo(pole.getCoordinates(xP + getCX(), yP + getCY())[0], pole.getCoordinates(xP + getCX(), yP + getCY())[1],duration));
                if (xP >= 19 || yP >= 19) {
                    dBullet = false;
                    bullet = false;
                    frame = 0;
                    xP = x;
                    yP = y;
                }
            }
        }
    }
    private float getCRotation(){
        if(direction == 8){
            return 0;
        }else if(direction == 2){
            return 180;
        }else if(direction==4){
            return 90;
        }else if(direction==6){
            return -90;
        }
        return 0;
    }
    private int getCX(){
        if(direction == 8){
            return 0;
        }else if(direction == 2){
            return 0;
        }else if(direction==4){
            return -1;
        }else if(direction==6){
            return 1;
        }
        return 0;
    }
    private int getCY(){
        if(direction == 8){
            return 1;
        }else if(direction == 2){
            return -1;
        }else if(direction==4){
            return 0;
        }else if(direction==6){
            return 0;
        }
        return 0;
    }
    @Override
    public void update(float delta) {
        if(!dead) {
            if (attack) {
                if (buffer > FrameDuration) {
                    if (frame + 1 < gun.length - 1) {
                        frame++;
                    } else {
                        attack = false;
                        bullet = true;
                    }
                    buffer = 0;
                } else {
                    buffer += delta;
                }
            }
            if (dBullet) {
                bu.act(delta);
                int direction = 0;
                if(this.direction == 8){
                    direction = 2;
                }else if(this.direction == 2){
                    direction = 8;
                }else if(this.direction == 6){
                    direction = 4;
                }else if(this.direction == 4){
                    direction = 6;
                }
                if(pole.objectInterupt(xP, yP, direction, attackP)){
                    dBullet = false;
                    bullet = false;
                    frame = 0;
                    xP = x;
                    yP = y;
                    bu.setBounds(pole.getCoordinates(x, y)[0], pole.getCoordinates(x, y)[1], pole.getCoordinates(x, y)[2], pole.getCoordinates(x, y)[3]);
                }
            }
            if(!dBullet&&!bullet&&!attack){
                if(buffer1>duration){
                    attack = true;
                }else{buffer1+=delta;}
            }
            if(health<0){
                if(deadOn) {
                    dead = true;
                }
            }

        }
        }

    @Override
    public void dispose() {
        for(int i=0;i<gun.length;i++){
            gun[i].getTexture().dispose();
        }
    }

    @Override
    public boolean interupt(int x, int y, int direction, float attack) {
        if(this.x==x&&this.y==y) {
            if (!dead) {
                health -= attack;
                return true;
            } else {
                return false;
            }
        }else{return false;}
    }
}
