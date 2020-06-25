package com.arliproduct.game.Animations;

import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animator {
    Sprite[] framesF;
    Sprite[] framesL;
    Sprite[] framesR;
    Sprite[] framesB;
    int frame = 0;
    float buffer = 0;
    float duration = 1;
    int x;
    int y;
    int heigth;
    int width;
    float rotation = 0;
    int directionP = 0;
    boolean stop = false;
    public Animator(int direction,int directionPlayer,float duration, int x, int y,Pole pole,Sprite[] spritesF,Sprite[] spritesL,Sprite[] spritesR,Sprite[] spritesB) {
        this.duration = duration;
        directionP = direction;
        framesF = spritesF;
        framesB = spritesB;
        framesL  = spritesL;
        framesR = spritesR;
        //
        if(direction==directionPlayer){// удар вперёд
            directionP = direction;
            anim(directionPlayer,pole,x,y);
        }
        if (direction == 2&&directionPlayer == 8 ) {// удар назад
            directionP = 2;
            anim(directionPlayer,pole,x,y);

        } else if (direction == 8&&direction==2) { // удар вниз
            directionP = 2;
            anim(directionPlayer,pole,x,y);
        } else if (direction == 6 &&direction == 4) { // Удар в вниз
            directionP = 2;
            anim(directionPlayer,pole,x,y);
        }else if (direction == 4&& direction==6) {// удар назад
            directionP = 2;
            anim(directionPlayer,pole,x,y);
        } else if(directionPlayer == 8||directionPlayer == 2) {
            if (direction - directionPlayer == 4 || direction - directionPlayer == -4) { // удар в лево
                directionP = 4;
                anim(directionPlayer,pole,x,y);
            } else if (direction - directionPlayer == 2 || direction - directionPlayer == -2) { // Удар в право
                directionP = 6;
                anim(directionPlayer,pole,x,y);
            }
        } else{
            if (direction - directionPlayer == 4 || direction - directionPlayer == -4) { // удар в право
                directionP = 4;
                anim(directionPlayer,pole,x,y);
            } else if (direction - directionPlayer == 2 || direction - directionPlayer == -2) { // Удар в лево
                directionP = 6;
                anim(directionPlayer,pole,x,y);
            }
        }
        //


    }
    public void anim(int directionPlayer,Pole pole,int x,int y){
        if(directionPlayer==8) {
            this.x = pole.getCoordinates(x-1, y)[0];
            this.y = pole.getCoordinates(x-1, y)[1];
            this.heigth = pole.getCoordinates(x+1, y+1)[2]*2;
            this.width = pole.getCoordinates(x+1, y+1)[3]*3;
            rotation =0;
        }else
        if(directionPlayer==2){
            this.x = pole.getCoordinates(x+2, y+1)[0];
            this.y = pole.getCoordinates(x+2, y+1)[1];
            this.heigth = pole.getCoordinates(x-1, y-1)[2]*2;
            this.width = pole.getCoordinates(x-1, y-1)[3]*3;
            rotation = 180;
        }else
        if(directionPlayer==4){
            this.x = pole.getCoordinates(x+1, y-1)[0];
            this.y = pole.getCoordinates(x+1,y-1)[1];
            this.heigth = pole.getCoordinates(x-1, y+1)[2]*2;
            this.width = pole.getCoordinates(x-1, y+1)[3]*3;
            rotation = 90;
        }else
        if(directionPlayer == 6){
            this.x = pole.getCoordinates(x, y+2)[0];
            this.y = pole.getCoordinates(x, y+2)[1];
            this.heigth = pole.getCoordinates(x+1, y+2)[2]*2;
            this.width = pole.getCoordinates(x+1, y+2)[3]*3;
            rotation = -90;
        }
    }
    public void restartAnim(int direction,int directionPlayer,Pole pole,int x,int y,float duration){
        clear();
        if(direction==directionPlayer){// удар вперёд
            directionP = 8;
            anim(directionPlayer,pole,x,y);
        }
        if (direction == 2&&directionPlayer == 8 ) {// удар назад
            directionP = 2;
            anim(directionPlayer,pole,x,y);

        } else if (direction == 8&&directionPlayer==2) { // удар вниз
            directionP = 2;
            anim(directionPlayer,pole,x,y);
        } else if (direction == 6 &&directionPlayer == 4) { // Удар в вниз
            directionP = 2;
            anim(directionPlayer,pole,x,y);
        }else if (direction == 4&& directionPlayer==6) {// удар назад
            directionP = 2;
            anim(directionPlayer,pole,x,y);
        } else if(directionPlayer == 8||directionPlayer == 2) {
            if (direction - directionPlayer == 4 || direction - directionPlayer == -4) { // удар в лево
                directionP = 4;
                anim(directionPlayer,pole,x,y);
            } else if (direction - directionPlayer == 2 || direction - directionPlayer == -2) { // Удар в право
                directionP = 6;
                anim(directionPlayer,pole,x,y);
            }
        } else{
            if (direction - directionPlayer == 4 || direction - directionPlayer == -4) { // удар в право
                directionP = 6;
                anim(directionPlayer,pole,x,y);
            } else if (direction - directionPlayer == 2 || direction - directionPlayer == -2) { // Удар в лево
                directionP = 4;
                anim(directionPlayer,pole,x,y);
            }
        }
    }
    public boolean isAnimateAttack(){
        if(buffer>duration){
            return false;
        }else{return true;}
    }
    public void update(float delta){System.out.println("update");
        if(directionP == 8) {
            if (buffer > duration / framesF.length * (frame + 1)) {
                frame++;
            } else {
                buffer += delta;
            }
        }else  if(directionP == 2) {
            if (buffer > duration / framesB.length * (frame + 1)) {
                frame++;
            } else {
                buffer += delta;
            }
        }else        if(directionP == 4) {
            if (buffer > duration / framesL.length * (frame + 1)) {
                frame++;
            } else {
                buffer += delta;
            }
        }else  if(directionP == 6) {
            if (buffer > duration / framesR.length * (frame + 1)) {
                frame++;
            } else {
                buffer += delta;
            }
        }}

    public void draw(Batch sb){
        if(directionP == 8) {System.out.println("DR8");
            framesF[frame].setOrigin(0, 0);
            framesF[frame].setRotation(rotation);
            framesF[frame].setBounds(x, y, width, heigth);
            framesF[frame].draw(sb);
        }else if(directionP == 2) {System.out.println("DR2");
            framesB[frame].setOrigin(0, 0);
            framesB[frame].setRotation(rotation);
            framesB[frame].setBounds(x, y, width, heigth);
            framesB[frame].draw(sb);
        }else if(directionP == 4) {System.out.println("DR4");
            framesL[frame].setOrigin(0, 0);
            framesL[frame].setRotation(rotation);
            framesL[frame].setBounds(x, y, width, heigth);
            framesL[frame].draw(sb);
        }else  if(directionP == 6) {System.out.println("DR6");
            framesR[frame].setOrigin(0, 0);
            framesR[frame].setRotation(rotation);
            framesR[frame].setBounds(x, y, width, heigth);
            framesR[frame].draw(sb);
        }
    }
    public void clear(){
        buffer = 0;
        frame = 0;
    }
    public void dispose(){
        for(Sprite s:framesF){
            s.getTexture().dispose();
        }
        for(Sprite s:framesB){
            s.getTexture().dispose();
        }
        for(Sprite s:framesL){
            s.getTexture().dispose();
        }
        for(Sprite s:framesR){
            s.getTexture().dispose();
        }
    }
}
