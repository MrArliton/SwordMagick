package com.arliproduct.game.Objects;

import com.arliproduct.game.Actor.ObjectClass;
import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static java.lang.Math.random;

public class DeathObject extends ObjectClass {
    Sprite[] anim;
    float durationFrame = 0;
    int[][] zones;
    int sprite[];
    Pole pole;
    int x;
    int y;
    float buffer = 0;
    boolean random = true;
    public DeathObject(TextureRegion anim,int w,int h, float durationFrame, int[][] zones, Pole pole) {
        Sprite[] ar = new Sprite[anim.split(w,h)[0].length];
        index = 8;
        for(int i=0;i<ar.length;i++){
            ar[i] = new Sprite(anim.split(w,h)[0][i]);
        }
        sprite = new int[zones.length];
        this.anim = ar;
        this.durationFrame = durationFrame;
        this.zones = zones;
        this.pole = pole;
    }
    @Override
    public boolean isMe(int x, int y) {
      for(int i = 0;i<zones.length;i++) {
          if(zones[i][0]==x&&zones[i][1]==y){
              return true;
          }
      }

        return false;
    }
    @Override
    public void render(Batch sb, float delta) {
        for(int i=0 ; i<zones.length;i++){
           anim[sprite[i]].setBounds(pole.getCoordinates(zones[i][0], zones[i][1])[0], pole.getCoordinates(zones[i][0], zones[i][1])[1], pole.getCoordinates(zones[i][0], zones[i][1])[2], pole.getCoordinates(zones[i][0], zones[i][1])[3]);
           anim[sprite[i]].draw(sb);
        }
    }

    @Override
    public void update(float delta) {
        if(buffer>durationFrame){
            for(int i=0;i<zones.length;i++){
                sprite[i] = 0 + (int) (random() * (anim.length));
            }buffer=0;
        }else{buffer+=delta;}
        for(int i=0;i<zones.length;i++){
            pole.objectInterupt(zones[i][0], zones[i][1],0,150);
        }
    }

    @Override
    public void dispose() {
        for(int i=0;i<anim.length;i++){
            anim[i].getTexture().dispose();
        }
    }

    @Override
    public boolean interupt(int x, int y, int direction, float attack) {
        return false;
    }
}
