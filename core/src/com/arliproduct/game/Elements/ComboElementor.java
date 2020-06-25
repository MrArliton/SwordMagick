package com.arliproduct.game.Elements;

import com.arliproduct.game.Actor.Character;
import com.arliproduct.game.StateManager;
import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayDeque;


public class ComboElementor {
    boolean attack = false;
    boolean two = false;
    boolean bonus;
    ArrayDeque<Integer> combo;
   public ComboRender comboRender;
  public  Character character;
    Pole pole;
  public  StateManager manager;
    int x;
    int y;
    public ComboElementor(int x, int y, Pole pole, StateManager manager,String obj){
        combo = new ArrayDeque<Integer>();
        this.manager  = manager;
        this.pole = pole;
        System.out.println("Combo Player x:"+x+" y:"+y);
        character = new Character(x,y,pole,this);
        this.x = character.x;
        this.y = character.y;
        comboRender = new ComboRender(this,pole,character,obj);
    }
    public ComboElementor(int x, int y, Pole pole, StateManager manager,String obj,boolean off){
        combo = new ArrayDeque<Integer>();
        this.manager  = manager;
        this.pole = pole;
        character = new Character(x,y,pole,this);
        this.x = character.x;
        this.y = character.y;
        comboRender = new ComboRender(this,pole,character,obj,true);
    }
    public void comboButton(String button){
        if(!comboRender.start){
        if(!button.equalsIgnoreCase("activate")) {
            if (button.equalsIgnoreCase("up")) {
                System.out.println("UP");
                if(!attack){
                pole.addYellowColor(x, y += 1);}else attack = false;
                combo.addLast(8);
            } else if (button.equalsIgnoreCase("down")) {
                if(!attack){
                pole.addYellowColor(x, y -= 1);}else attack = false;
                combo.addLast(2);
            } else if (button.equalsIgnoreCase("left")) {
                if(!attack){
                pole.addYellowColor(x -= 1, y);}else attack = false;
                combo.addLast(4);
            } else if (button.equalsIgnoreCase("right")) {
                if(!attack){
                pole.addYellowColor(x += 1, y);}else attack = false;
                combo.addLast(6);
            } else if (button.equalsIgnoreCase("attack")) {
                combo.addLast(7);
                if(!two){
                attack = true;two = true;}else{two = false;}
            }else if(button.equalsIgnoreCase("shield")){
                combo.addLast(9);
                if(!two){
                    attack = true;two = true;}else{two = false;}
            }else if(button.equalsIgnoreCase("help")){
                combo.addLast(1);
            }else if(button.equalsIgnoreCase("power")){
                combo.addLast(3);
            }
        } else {
            System.out.println("Activate");
                comboRender.active();
                x = character.x;
                y = character.y;
                attack = false;
                two = false;
        }}
        if (button.equalsIgnoreCase("clear")) {System.out.println("clear");
            comboRender.clearAttack(false);
            comboRender.clearShield(false);
            combo.clear();
            pole.clearColors();
            x = character.x;
            y = character.y;
        }}
    public void render(SpriteBatch sb,float delta){
        comboRender.update(delta);
        comboRender.render(sb,delta);
    }
    public void dispose(){
        comboRender.dispose();
        pole.dispose();
        combo.clear();
        character.dispose();
    }
}
