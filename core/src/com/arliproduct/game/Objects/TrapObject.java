package com.arliproduct.game.Objects;

import com.arliproduct.game.Actor.ObjectClass;
import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TrapObject extends ObjectClass {
    int[][] trapers;
    Sprite attack;
    Sprite sleep;
    int attacksprite;
    float buffer = 0;
    float duration;
    Pole pole;
    boolean oneAttack = false;
    float attackP;
    public TrapObject(int[][] trapers,float attackP, Sprite sleep, Sprite attack, float durationFrame, Pole pole) {
        this.trapers = trapers;
        this.attack = attack;
        this.sleep = sleep;
        this.attackP = attackP;
        this.attack = attack;
        this.sleep = sleep;
        index = 6;
        duration = durationFrame;
        this.pole = pole;
    }
    @Override
    public boolean isMe(int x, int y) {
        for(int i = 0;i<trapers.length;i++) {
            if(trapers[i][0]==x&&trapers[i][1]==y){
                return true;
            }
        }

        return false;
    }
    @Override
    public void render(Batch sb, float delta) {
        for(int i = 0;i<trapers.length;i++){
            if(i==attacksprite){
                attack.setBounds(pole.getCoordinates(trapers[i][0],trapers[i][1])[0],pole.getCoordinates(trapers[i][0],trapers[i][1])[1],pole.getCoordinates(trapers[i][0],trapers[i][1])[2],pole.getCoordinates(trapers[i][0],trapers[i][1])[3]);
                attack.draw(sb);
            }else{
                sleep.setBounds(pole.getCoordinates(trapers[i][0],trapers[i][1])[0],pole.getCoordinates(trapers[i][0],trapers[i][1])[1],pole.getCoordinates(trapers[i][0],trapers[i][1])[2],pole.getCoordinates(trapers[i][0],trapers[i][1])[3]);
                sleep.draw(sb);
            }
        }
    }
    @Override
    public void update(float delta) {
        if(buffer>duration){
            if(attacksprite+1<trapers.length){
                attacksprite++;
                buffer=0;
                oneAttack = false;
            }else{attacksprite=0;buffer=0;oneAttack = false;}
        }else{buffer+=delta;}
        if(!oneAttack) {
            if (pole.objectInterupt(trapers[attacksprite][0], trapers[attacksprite][1], 0, attackP)) {
                oneAttack = true;
            }
        }
        }

    @Override
    public void dispose() {
  sleep.getTexture().dispose();
   attack.getTexture().dispose();
    }

    @Override
    public boolean interupt(int x, int y, int direction, float attack) {
        return false;
    }
}
