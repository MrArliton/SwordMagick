package com.arliproduct.game.States.Groups.GroupsStartMenu;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.GroupsNotifications.BuyInfo;
import com.arliproduct.game.GroupsNotifications.ClearInfo;
import com.arliproduct.game.Player.Character;
import com.arliproduct.game.States.StartMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Shop extends Group {
    float foneStart = 0;
    Sprite fone = new Sprite(new Texture("StartMenu/ShopFone.png"));
    BitmapFont texter;
    Button exit;
    Button buy;
    Button clear;
    StartMenu start;
    Array<Item> items = new Array<Item>();
    BuyInfo buyOpt;
    BuyInfo buyNot;
    ClearInfo clearOpt;
    Sprite[] a;
    String error = "Error Game";
    int activeWindow = 0;
    OrthographicCamera camera;
    int buffer1=0;
    public Shop(OrthographicCamera camera, StartMenu start, BitmapFont texter) {
        exit = new Button(Constants.WIDTH-145,Constants.HEIGTH-145,100,100,new Texture("All/exitButton.png"),new Texture("All/exitButtonDown.png"),false,camera);
        exit.setFast(false);
        exit.setDuration(0.25f);
        this.texter = texter;
        this.start = start;
        this.camera = camera;
        Sprite[] buffer= new Sprite[10];
            buffer[0]=new Sprite(new Texture("StartMenu/items/Attack.png"));
        buffer[1]=new Sprite(new Texture("StartMenu/items/Shield.png"));
        buffer[2]=new Sprite(new Texture("StartMenu/items/Energy.png"));
        buffer[3]=new Sprite(new Texture("StartMenu/items/healing.png"));
        buffer[4]=new Sprite(new Texture("StartMenu/items/Super.png"));
        buffer[5]=new Sprite(new Texture("StartMenu/items/SAttack.png"));
        buffer[6]=new Sprite(new Texture("StartMenu/items/SShield.png"));
        buffer[7]=new Sprite(new Texture("StartMenu/items/SEnergy.png"));
        buffer[8]=new Sprite(new Texture("StartMenu/items/SHeal.png"));
        buffer[9]=new Sprite(new Texture("StartMenu/items/SSuper.png"));

        createShop(camera,buffer);
        buy = new Button(Constants.WIDTH/2+315,Constants.HEIGTH/2+50,200,100,new Texture("StartMenu/items/button.png"),new Texture("StartMenu/items/buttonDown.png"),true,camera);
        buy.setText(-20,+11,Constants.lang.getText("buy"),1.2f,1.2f,texter);
        clear = new Button(Constants.WIDTH/2+270,Constants.HEIGTH/2-100,300,100,new Texture("StartMenu/items/button.png"),new Texture("StartMenu/items/buttonDown.png"),true,camera);
        clear.setText(-35,+11,Constants.lang.getText("clearB"),1f,1f,texter);
        buyOpt = new BuyInfo(0,"",texter,camera);
        buyNot = new BuyInfo(error,camera,texter);
        clearOpt = new ClearInfo(buffer,texter,camera);
    }
    public void createShop(OrthographicCamera camera,Sprite[] a){// Элементы магазины
        items.add(new Item(a[0],this,100,Constants.HEIGTH/2+110,1,2f,1,camera,texter));
        items.add(new Item(a[1],this,260,Constants.HEIGTH/2+110,2,2f,1,camera,texter));
        items.add(new Item(a[2],this,420,Constants.HEIGTH/2+110,3,0.25f,1,camera,texter));
        items.add(new Item(a[3],this,580,Constants.HEIGTH/2+110,4,4f,1,camera,texter));
        items.add(new Item(a[4],this,740,Constants.HEIGTH/2+110,5,0.25f,1,camera,texter));
        items.add(new Item(a[5],this,100,Constants.HEIGTH/2-200,1,5f,3,camera,texter));
        items.add(new Item(a[6],this,260,Constants.HEIGTH/2-200,2,7f,3,camera,texter));
        items.add(new Item(a[7],this,420,Constants.HEIGTH/2-200,3,1f,3,camera,texter));
        items.add(new Item(a[8],this,580,Constants.HEIGTH/2-200,4,10f,3,camera,texter));
        items.add(new Item(a[9],this,740,Constants.HEIGTH/2-200,5,0.7f,3,camera,texter));
    }
    @Override
    public void render(SpriteBatch sb, float delta) {
            if(StartFone(sb)){
                    fone.draw(sb);
                    exit.draw(sb, delta);
                    buy.draw(sb, delta);
                    clear.draw(sb, delta);
                    for (int i = 0; i < items.size; i++) {
                        items.get(i).render(sb, delta);
                    }
                    setActiveWindow(sb,delta);
            }
    }
    public void setActiveWindow(SpriteBatch sb ,float delta){
        if(activeWindow == 1){
            buyOpt.render(sb,delta);
        }else if(activeWindow == 2){
            clearOpt.render(sb,delta);
        }else if(activeWindow == 3){
            buyNot.render(sb,delta);
        }
    }
    public void BuyItem(int item,float effect,int size){ // Добавление эффектов за покупки
     if(item == 1){
        if(size!=0) {
            Character.bay = size;
            Character.bonusAttack = effect;
        }
     }else if(item == 2){
         if(size!=0) {
             Character.bsy = size;
             Character.bonusShield = effect;
         }
     }else if(item == 3){
             if(size!=0) {
                 Character.bey = size;
                 Character.bonusEnergy = effect;
             }
     }else if(item == 4){
                 if(size!=0) {
                     Character.bhsy = size;
                     Character.bonusHealthSize = effect;
                 }
     }else if(item == 5){
                     if(size!=0) {
                         Character.bsay = size;
                         Character.bonusSuper = effect;
                     }
     }
        start.manager.save.saveSettings();
    }
    public boolean errorShop(int item){ //  ВЫчисляет можно ли совершить покупку
        if(item == 1){System.out.println("па");
            if(Character.bay==0){return true;}else{error = Constants.lang.getText("errorYesInv");return false;}

        }else if(item == 2){
            if(Character.bsy==0){return true;}else{error = Constants.lang.getText("errorYesInv");return false;}

        }else if(item == 3){
           if(Character.bey==0){return true;}else{ error = Constants.lang.getText("errorYesInv");return false;}

        }else if(item == 4){
            if(Character.bhsy==0){return true;}else{error = Constants.lang.getText("errorYesInv");return false;}

        }else if(item == 5){
            if(Character.bsay==0){return true;}else{error = Constants.lang.getText("errorYesInv");return false;}
        }else{return false;}
    }

    @Override
    public void update(float delta) { // Производим обновления за кадр
        if(activeWindow == 0) {
            for (int i = 0; i < items.size; i++) {
                items.get(i).update(delta);
            }
            if (exit.onClicked()) {
                start.activeWindow = 0;
                foneStart= 0;
                System.out.println(Character.health);
                System.out.println(Character.superPower);
                System.out.println(Character.attack);
            }
            if (buy.onClicked()) {
                buffer1 = 0;
                String buffer = "";
                boolean oner = false;
                boolean byes = false;
                for(int i=0;i<items.size;i++){
                   buffer = items.get(i).getInfo();
                    String[] buffer0 = buffer.split(",");
                    if(Integer.parseInt(buffer0[1])>0){
                        if(errorShop(Integer.parseInt(buffer0[2]))){
                            buffer1+=Integer.parseInt(buffer0[0])*Integer.parseInt(buffer0[1]);
                        }else{oner = true;}
                    }
                }
                if(Character.money<buffer1){
                    oner = true;
                    error = Constants.lang.getText("errorNoMoney");
                }

                for(int i = 0;i<items.size;i++){
                    if(Integer.parseInt(items.get(i).getInfo().split(",")[1])>0){
                        byes = true;
                    }
                }
                if(oner){activeWindow = 3;
                buyNot.dispose();
                buyNot = new BuyInfo(error,camera,texter);
                }else
                    {
                        if(byes) {
                            buyOpt.dispose();
                            buyOpt = new BuyInfo(buffer1, Constants.lang.getText("Buy?"), texter, camera);
                            activeWindow = 1;
                        }else{
                            activeWindow = 3;
                            buyNot.dispose();
                            error = Constants.lang.getText1("NoOptBuy");
                            buyNot = new BuyInfo(error,camera,texter);
                        }
                }

            }
            if (clear.onClicked()) {
            activeWindow = 2;
            }
        }else if(activeWindow == 1){ // При выборе покупать или нет
            buyOpt.update(delta);
            if(buyOpt.getResult()==1){
                String[] buffer;
                for(int i=0;i<items.size;i++) {
                    buffer = items.get(i).getInfo().split(",");
                    BuyItem(Integer.parseInt(buffer[2]), Float.parseFloat(buffer[3]), Integer.parseInt(buffer[1]));
                }
                Character.money-=buffer1;
                buffer1 = 0;
                activeWindow = 0;
            }else if(buyOpt.getResult()==2){
                activeWindow=0;
            }
        }else if(activeWindow == 2){ // При выборе стирании эфектов
            clearOpt.update(delta);
            if(clearOpt.getResult()!=0) {
                activeWindow = 0;
            }

        }else if(activeWindow == 3){ // При неудачной покупке
            buyNot.update(delta);
            if(buyNot.getResult()==1){
                activeWindow = 0;
            }
        }
    }

    @Override
    public void dispose() {
        fone.getTexture().dispose();
        buyOpt.dispose();
        clearOpt.dispose();
        for(int i=0;i<items.size;i++) {
            try {
                items.get(i).dispose();
            } catch (Exception e) {
            }
        }
            }
    private boolean StartFone(SpriteBatch sb){
        fone.setAlpha(foneStart);
        fone.setBounds(0,0, Constants.WIDTH ,Constants.HEIGTH );
        if(foneStart>=0.90){
            fone.setAlpha(1);
            return true;
        }
        foneStart+= Gdx.graphics.getDeltaTime()*1.8f;
        fone.draw(sb);
        return false;
    }
}
