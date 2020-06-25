package com.arliproduct.game.States.Groups.GroupsStartMenu;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.Player.Character;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item extends Group { // Класс для работы магизина предмет для проджи
    Sprite item;
    Sprite lineM = new Sprite(new Texture("StartMenu/items/Money.png"));
    Sprite kol = new Sprite(new Texture("StartMenu/items/pok.png"));
    Button left;
    Button rigth;
    Shop shop;
int number;
int size = 0;
float effect;
int x;
int y;
int width = 150;
int heigth = 150;
int money;
BitmapFont texter;
    public Item(Sprite item, Shop shop, int x, int y, int number, float effect, int money, OrthographicCamera camera, BitmapFont texter) {
        this.item = item;
        this.shop = shop;
        this.number = number;
    this.x =x;
    this.y = y;
    this.effect = effect;
    this.money = money;
    this.texter = texter;
    createButton(camera);
    }
    public void createButton(OrthographicCamera camera){
        left = new Button(x+width/2-75,y-52,50,50,new Texture("All/left1.png"),new Texture("All/left1Down.png"),false,camera);
        rigth = new Button(x+width/2+25,y-52,50,50,new Texture("All/right1.png"),new Texture("All/right1Down.png"),false,camera);
    }
    public void getWidth(){

    }
    public void getHeidth(){

    }
    public void addX(int x){
        this.x+=x;
    }
    @Override
    public void render(SpriteBatch sb, float delta) {
    item.setBounds(x,y,width,heigth);
    item.draw(sb);
    lineM.setBounds(x+(width/3)/2,y+heigth,width-width/3,width/3);
    lineM.draw(sb);
    kol.setBounds(x+width/2-25,y-54,50,50);
    kol.draw(sb);
    texter.setColor(Color.BLACK);
    texter.getData().setScale(1.3f);
    texter.draw(sb,money+"",x+width/2,y+heigth+width/4); // Вывод количества денег
        texter.draw(sb,size+"",x+width/2-10,y-18); // Вывод количества для покупки
    left.draw(sb,delta);
    rigth.draw(sb,delta);
    }
    public String getInfo(){
        return money+","+size+","+number+","+effect;
    }
    @Override
    public void update(float delta) {
        if(left.onClicked()){
            if(size-1>-1){
                size-=1;
            }
        }
        if(rigth.onClicked()){
            if(size+1<10){
                size+=1;
            }
        }

    }

    @Override
    public void dispose() {
        item.getTexture().dispose();
        left.dispose();
        lineM.getTexture().dispose();
        rigth.dispose();
        kol.getTexture().dispose();
    }
}
