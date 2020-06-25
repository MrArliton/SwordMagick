package com.arliproduct.game.Actor;

import com.arliproduct.game.Animations.Animator;
import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.ComboElementor;
import com.arliproduct.game.Elements.ComboRender;
import com.arliproduct.game.Sounds;
import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Rectangle;

public class Character extends Actor  {
    public int x;
    public int y;
    Animator interupt;
    public float power = 36;
    public float health = com.arliproduct.game.Player.Character.health;
    public float superPower = com.arliproduct.game.Player.Character.superPower;
    public float attack = com.arliproduct.game.Player.Character.attack;
    public float speed = com.arliproduct.game.Player.Character.speed;
    public float powermax = com.arliproduct.game.Player.Character.powermax;
    public float shield = com.arliproduct.game.Player.Character.shield;
    private float bufferattack = attack;
    private float buffershield = shield;
    public int direction = 8;
    public boolean isAttackMe = false;
    public float speedPowerRegeneration = com.arliproduct.game.Player.Character.SpeedRegeneration; // Регенерация в секунду
    ComboElementor render;
    // для  работы регенерации силы
    float bufferPower = 0;
    //
    Animator animatorForward;
    Animator animatorShield;
    public Sprite character = new Sprite(com.arliproduct.game.Player.Character.character[2]);
    public Sprite characterAttack = new Sprite(com.arliproduct.game.Player.Character.character[0]);
    public Sprite characterNoWeapon = new Sprite(com.arliproduct.game.Player.Character.character[1]);
    public Pole pole;
    boolean powerNo = true;
    public boolean superPowerMode = false;
    public boolean bufferSuperpower = false;
    private float bufferspeed = speed;
    // Атака
    boolean attackb  = false;
    boolean attackyes = false;
    //Защита
    boolean isAttackMeS = false;
    public boolean shieldb = false;
    float shieldPlus = 1;
    boolean shieldyes = false;
    public int directionS = 0;
    //
    boolean Ctexture = true;
    //Для анимации
    boolean damage = false;
    float xAnim= 0;
    float yAnim = 0;

    public Character(int x, int y, Pole pole, ComboElementor render) {
        this.render =render;
        this.x = x;
        this.y  =y;
        System.out.println("Playr Spawn: x:"+x+" y:"+y);
        setX(pole.getCoordinates(x,y)[0]);
        setY(pole.getCoordinates(x,y)[1]);
        setHeight(pole.getCoordinates(x,y)[3]);
        setWidth(pole.getCoordinates(x,y)[2]);
        this.pole = pole;
        // Создание анимаций
        Sprite[] bufferS = new Sprite[com.arliproduct.game.Player.Character.animationForward.length];
        Sprite[] bufferB = new Sprite[com.arliproduct.game.Player.Character.animationBack.length];
        Sprite[] bufferL = new Sprite[com.arliproduct.game.Player.Character.animationL.length];
        Sprite[] bufferR = new Sprite[com.arliproduct.game.Player.Character.animationR.length];
        Sprite[] bufferSS = new Sprite[com.arliproduct.game.Player.Character.animationForward.length];
        Sprite[] bufferSB = new Sprite[com.arliproduct.game.Player.Character.animationBack.length];
        Sprite[] bufferSL = new Sprite[com.arliproduct.game.Player.Character.animationL.length];
        Sprite[] bufferSR = new Sprite[com.arliproduct.game.Player.Character.animationR.length];
        Sprite[] bufferIS = new Sprite[com.arliproduct.game.Player.Character.animationForwardI.length];
        Sprite[] bufferIB = new Sprite[com.arliproduct.game.Player.Character.animationBackI.length];
        Sprite[] bufferIL = new Sprite[com.arliproduct.game.Player.Character.animationLI.length];
        Sprite[] bufferIR = new Sprite[com.arliproduct.game.Player.Character.animationRI.length];
        int bufferI = 0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationForward){
                    bufferS[bufferI] = new Sprite(buffer);
                    bufferI++;
        }
        bufferI=0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationBack){
            bufferB[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationL){
            bufferL[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationR){
            bufferR[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        animatorForward = new Animator(8,direction,10/speed,x,y,pole,bufferS,bufferL,bufferR,bufferB);
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationForwardS){
            bufferSS[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationBackS){
            bufferSB[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationLS){
            bufferSL[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationRS){
            bufferSR[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        //
        bufferI = 0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationForwardI){
            bufferIS[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationBackI){
            bufferIB[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationLI){
            bufferIL[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        for(TextureRegion buffer:com.arliproduct.game.Player.Character.animationRI){
            bufferIR[bufferI] = new Sprite(buffer);
            bufferI++;
        }
        bufferI=0;
        animatorShield = new Animator(8,direction,15/speed,x,y,pole,bufferSS,bufferSL,bufferSR,bufferSB);
        //
        interupt = new Animator(8,direction,15/speed,x,y,pole,bufferIS,bufferIL,bufferIR,bufferIB);
        character.setBounds(pole.getCoordinates(x,y)[0],pole.getCoordinates(x,y)[1],pole.getCoordinates(x,y)[2],pole.getCoordinates(x,y)[3]);
        character.setOrigin(character.getWidth()/2,character.getHeight()/2);
    }
    public void setCharactirestics(){
        health = 30;
        shield = 1;
    }
    public boolean isEnergy(){
        boolean powerNo = this.powerNo;
        this.powerNo = true;
        return powerNo;
    }
    public boolean minusEnergy(float energy){
        if(power-energy>0){
            power -= energy;
        }else{powerNo =false;return true;}
        return false;
    }
    public boolean isDead(){
        if(health<1){return true;}
        return false;
    }
    public void setSpeedPowerRegeneration(float speedPowerRegeneration){
        this.speedPowerRegeneration = speedPowerRegeneration;
    }
    public boolean isAttackMe(){return isAttackMe;}
    public boolean isAttackMeS(){
        boolean a = isAttackMeS;
        isAttackMeS = false;
        return a;}
    public void activeAttack(){
        attackb = true;
    }
    public void deactiveAttack(){
        attackb = false;
    }
    public boolean isAttack(){
        return attackb;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(!attackb&!attackyes&!shieldb&!shieldyes) {
            character.setBounds(getX() + xAnim, getY() + yAnim, getWidth() + xAnim, getHeight() + yAnim);
            character.setRotation(getRotation());
            character.draw(batch);
        }else if(!attackyes&!shieldyes){System.out.println("SH1");
            characterAttack.setBounds(getX() + xAnim, getY() + yAnim, getWidth() + xAnim, getHeight() + yAnim);
            characterAttack.setRotation(getRotation());
            characterAttack.draw(batch);
        }else if(Ctexture){
            characterNoWeapon.setBounds(getX() + xAnim, getY() + yAnim, getWidth() + xAnim, getHeight() + yAnim);
            characterNoWeapon.setRotation(getRotation());
            characterNoWeapon.draw(batch);
            renderAnime(batch);
            }else if(!Ctexture){renderAnime(batch);}
            if(damage){
                interupt.draw(batch);
            }
    }
    private void renderAnime(Batch ba){
        if(attackyes){
            animatorForward.draw(ba);}
            else if(shieldyes) {
               animatorShield.draw(ba);
        }
        }
    @Override
    public void act(float delta) {
        super.act(delta);
        if(superPowerMode){
            superPower = com.arliproduct.game.Player.Character.superPower;
            speed = bufferspeed;
            attack = bufferattack;
            shield = buffershield;
        }else{superPower = 1;speed = bufferspeed;attack = bufferattack;shield = buffershield;}
            speed*=superPower;
        attack*=superPower;
        shield*=superPower;
        if (power < powermax) {
            if (bufferPower > 1) {
                power += speedPowerRegeneration;
                bufferPower = 0;
            } else {
                bufferPower += delta;
            }
        }

        if (attackyes||shieldyes) {
            updateAnim(delta);
        }
        if(damage){
            interupt.update(delta);
            if(!interupt.isAnimateAttack()){
                damage = false;
            }
        }

    }
    private void updateAnim(float delta){// Если атака перед собой
        if(attackyes) {
            animatorForward.update(delta);
            if (!animatorForward.isAnimateAttack()) {
                attackyes = false;
                render.comboRender.stop = false;
                animatorForward.clear();
                Ctexture = true;
            }
        }
        if(shieldyes){
        animatorShield.update(delta);
        if(!animatorShield.isAnimateAttack()){
            shieldyes = false;
            render.comboRender.stop = false;
            animatorShield.clear();
            Ctexture = true;
        }
        }
    }
    public void attackMe(int directionA,float attack){ // при атаке на игрока
            if(attack>0){
                Constants.recievedDamage+=attack;
                if(shieldb&directionA==directionS&attack-shield-shieldPlus>attack/(shield+shieldPlus)){
                    health -= attack/(shield+shieldPlus);System.out.println("hp1");
                    isAttackMeS = true;
                    Constants.BlockedDamage+=attack-(attack/(shield+shieldPlus));
                }else if(shieldb&directionA==directionS){
                    isAttackMeS = true;
                    if(attack-shieldPlus-shieldPlus-1>-1) {
                        health -= attack - shieldPlus - shieldPlus-1;
                        System.out.println("hp2");
                        Constants.BlockedDamage+=shieldPlus+shieldPlus+1;
                    }else{
                        health -= attack - shieldPlus - shieldPlus;
                        Constants.BlockedDamage+=shieldPlus+shieldPlus;
                    }
                }
                else
                    if(attack-shield-shieldPlus>0){
                        health -= attack-shieldPlus-shieldPlus;System.out.println("hp2");
                        int directionI = 0;
                        if(directionA == 8){
                            directionI = 2;
                        }else if(directionA==2){
                            directionI = 8;
                        }else if(directionA==6){
                            directionI = 4;
                        }else if(directionA==4){
                            directionI= 6;
                        }
                        interupt.restartAnim(directionI,direction,pole,x,y,10/speed);
                    damage = true;
                    }


            isAttackMe = true;
            startAnimationAttackme(directionA);

            }
    }
    public void dispose(){
    }
    public void startAnimationSh(int direction){
        if(direction==this.direction){// удар вперёд
            shieldyes = true;
            render.comboRender.stop = true;
            animatorShield.restartAnim(8,this.direction,pole,x,y,10/speed);
            System.out.println("vverx");
        }
        if(this.direction == 8||this.direction == 2) {
            if (direction - this.direction == 6 || direction - this.direction == -6) {// удар назад
                shieldyes = true;
                Ctexture = false;
                render.comboRender.stop = true;
                animatorShield.restartAnim(2, this.direction, pole, x, y, 10 / speed);
                System.out.println("vniz");
            } else if (direction - this.direction == 4 || direction - this.direction == -4) { // удар в лево
                shieldyes = true;
                render.comboRender.stop = true;
                Ctexture = false; // Для анимаций без основной тектуры
                animatorShield.restartAnim(4, this.direction, pole, x, y, 10 / speed);
                System.out.println("levo");
            } else if (direction - this.direction == 2 || direction - this.direction == -2) { // Удар в право
                shieldyes = true;
                render.comboRender.stop = true;
                Ctexture = false;// Для анимаций без основной тектуры
                animatorShield.restartAnim(6, this.direction, pole, x, y, 10 / speed);
                System.out.println("pravo");
            }
        }else{ System.out.println("eras");
            if (direction + this.direction == 10&&direction - this.direction == 2||direction + this.direction == -10&&direction - this.direction == -2) {// удар назад
                shieldyes = true;
                Ctexture = false;
                render.comboRender.stop = true;
                animatorShield.restartAnim(2, this.direction, pole, x, y, 10 / speed);
                System.out.println("vniz1");
            } else if (direction - this.direction == 4 || direction - this.direction == -4) { // удар в право
                shieldyes = true;
                render.comboRender.stop = true;
                Ctexture = false; // Для анимаций без основной тектуры
                animatorShield.restartAnim(6, this.direction, pole, x, y, 10 / speed);
                System.out.println("pravo1");
            } else if (direction - this.direction == 2 || direction - this.direction == -2) { // Удар в лево
                shieldyes = true;
                render.comboRender.stop = true;
                Ctexture = false;// Для анимаций без основной тектуры
                animatorShield.restartAnim(4, this.direction, pole, x, y, 10 / speed);
                System.out.println("levo1");
            }
        }
    }
    public void startAnimationS(int direction){
        if(direction==this.direction){// удар вперёд
            shieldyes = true;
            render.comboRender.stop = true;
            animatorShield.restartAnim(8,this.direction,pole,x,y,10/speed);
            System.out.println("vverx");
        }
        if(Constants.music)
        Sounds.soundS.play(1.5f);
            if (direction == 2&&this.direction == 8 ) {// удар назад
                shieldyes = true;
                Ctexture = false;
                render.comboRender.stop = true;
                animatorShield.restartAnim(2, 8, pole, x, y, 5 / speed);
                System.out.println("vniz");
            } else if (direction == 8&&this.direction==2) { // удар вниз
                shieldyes = true;
                render.comboRender.stop = true;
                Ctexture = false; // Для анимаций без основной тектуры
                animatorShield.restartAnim(8, 2, pole, x, y, 5 / speed);
                System.out.println("vniz");
            } else if (direction == 6 &&this.direction == 4) { // Удар в вниз
                shieldyes = true;
                render.comboRender.stop = true;
                Ctexture = false;// Для анимаций без основной тектуры
                animatorShield.restartAnim(6, 4, pole, x, y, 5 / speed);
                System.out.println("vniz");
            }else
            if (direction == 4&& this.direction==6) {// удар назад
                shieldyes = true;
                Ctexture = false;
                render.comboRender.stop = true;
                animatorShield.restartAnim(4, 6, pole, x, y, 5 / speed);
                System.out.println("vniz");
            } else if(this.direction == 8||this.direction == 2) {
                if (direction - this.direction == 4 || direction - this.direction == -4) { // удар в лево
                    shieldyes = true;
                    render.comboRender.stop = true;
                    Ctexture = false; // Для анимаций без основной тектуры
                    animatorShield.restartAnim(direction, this.direction, pole, x, y, 5 / speed);
                    System.out.println("levo");
                } else if (direction - this.direction == 2 || direction - this.direction == -2) { // Удар в право
                    shieldyes = true;
                    render.comboRender.stop = true;
                    Ctexture = false;// Для анимаций без основной тектуры
                    animatorShield.restartAnim(direction, this.direction, pole, x, y, 5 / speed);
                    System.out.println("pravo");
                }
            } else{
                if (direction - this.direction == 4 || direction - this.direction == -4) { // удар в право
                    shieldyes = true;
                    render.comboRender.stop = true;
                    Ctexture = false; // Для анимаций без основной тектуры
                    animatorShield.restartAnim(direction, this.direction, pole, x, y, 5 / speed);
                    System.out.println("pravo1");
                } else if (direction - this.direction == 2 || direction - this.direction == -2) { // Удар в лево
                    shieldyes = true;
                    render.comboRender.stop = true;
                    Ctexture = false;// Для анимаций без основной тектуры
                    animatorShield.restartAnim(direction, this.direction, pole, x, y, 5 / speed);
                    System.out.println("levo1");
                }
            }
    }
    public void startAnimationAttackme(int direction){

    }

public void startAnimaion(int direction){
        if(direction==this.direction){// удар вперёд
            attackyes = true;
            render.comboRender.stop = true;
            animatorForward.restartAnim(direction,this.direction,pole,x,y,10/speed);
            System.out.println("FOR");
        }
        if(Constants.music)
        Sounds.soundA.play(2f);
    if (direction == 2&&this.direction == 8 ) {// удар назад
        attackyes = true;
        render.comboRender.stop = true;
        animatorForward.restartAnim(direction, 8, pole, x, y, 10 / speed);
        System.out.println("vniz1");
    } else if (direction == 8&&this.direction==2) { // удар вниз
        attackyes = true;
        render.comboRender.stop = true;
         // Для анимаций без основной тектуры
        animatorForward.restartAnim(direction, 2, pole, x, y, 10 / speed);
        System.out.println("vniz2");
    } else if (direction == 6 &&this.direction == 4) { // Удар в вниз
        attackyes = true;
        render.comboRender.stop = true;
        // Для анимаций без основной тектуры
        animatorForward.restartAnim(direction, 4, pole, x, y, 10 / speed);
        System.out.println("vniz3");
    }else
    if (direction == 4&& this.direction==6) {// удар назад
        attackyes = true;
        render.comboRender.stop = true;
        animatorForward.restartAnim(direction, 6, pole, x, y, 10 / speed);
        System.out.println("vniz4");
    } else if(this.direction == 8||this.direction == 2) {
        if (direction - this.direction == 4 || direction - this.direction == -4) { // удар в лево
            attackyes = true;
            render.comboRender.stop = true;
            Ctexture = false; // Для анимаций без основной тектуры
            animatorForward.restartAnim(direction, this.direction, pole, x, y, 10 / speed);
            System.out.println("levo");
        } else if (direction - this.direction == 2 || direction - this.direction == -2) { // Удар в право
            attackyes = true;
            render.comboRender.stop = true;
            Ctexture = false;// Для анимаций без основной тектуры
            animatorForward.restartAnim(direction, this.direction, pole, x, y, 10 / speed);
            System.out.println("pravo");
        }
    } else{
        if (direction - this.direction == 4 || direction - this.direction == -4) { // удар в право
            attackyes = true;
            render.comboRender.stop = true;
            Ctexture = false; // Для анимаций без основной тектуры
            animatorForward.restartAnim(direction, this.direction, pole, x, y, 10 / speed);
            System.out.println("pravo1");
        } else if (direction - this.direction == 2 || direction - this.direction == -2) { // Удар в лево
            attackyes = true;
            render.comboRender.stop = true;
            Ctexture = false;// Для анимаций без основной тектуры
            animatorForward.restartAnim(direction, this.direction, pole, x, y, 10 / speed);
            System.out.println("levo1");
        }
    }}
}
