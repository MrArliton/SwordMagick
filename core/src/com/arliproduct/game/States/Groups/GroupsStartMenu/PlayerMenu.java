package com.arliproduct.game.States.Groups.GroupsStartMenu;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.Player.Character;
import com.arliproduct.game.States.StartMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerMenu extends Group {
    Sprite fone = new Sprite(new Texture("StartMenu/Player/Window.png"));
    Sprite image = new Sprite(Character.PicturesPlayerMenu[Character.image]);
    float foneStart = 0;
    Button exit;
    Button left;
    Button right;
    Button editName;
    StartMenu menu;
    BitmapFont texter;

    public PlayerMenu(OrthographicCamera camera, StartMenu menu, BitmapFont texter) { // Класс для рисования окна игрока
        exit = new Button(Constants.WIDTH-145,Constants.HEIGTH-145,100,100,new Texture("All/exitButton.png"),new Texture("All/exitButtonDown.png"),false,camera);
        exit.setFast(false);
        exit.setDuration(0.25f);
        left = new Button(50,Constants.HEIGTH-210,100,100,new Texture("StartMenu/Player/left.png"),new Texture("StartMenu/Player/leftDown.png"),false,camera);
        left.setDuration(0.2f);
        right = new Button(335,Constants.HEIGTH-205,100,100,new Texture("StartMenu/Player/rigth.png"),new Texture("StartMenu/Player/rigthDown.png"),false,camera);
        right.setDuration(0.2f);
        editName = new Button(Constants.WIDTH-302,Constants.HEIGTH-199,60,60,new Texture("StartMenu/Player/edit.png"),new Texture("StartMenu/Player/editDown.png"),false,camera);
        this.menu  = menu;
        this.texter = texter;
    }

    @Override
    public void render(SpriteBatch sb, float delta) {
        if(StartFone(sb)) {
            fone.setBounds(20, 20, Constants.WIDTH - 40, Constants.HEIGTH - 40);
            fone.draw(sb);
            exit.draw(sb,delta);
            left.draw(sb,delta);
            right.draw(sb,delta);
            editName.draw(sb,delta);
            texter.setColor(Color.BLACK);
            texter.getData().setScale(2f);
            texter.draw(sb,Constants.name, Constants.WIDTH/2-30,Constants.HEIGTH-125);
            texter.getData().setScale(1f);
            // Статистика
            // 1 Столб
            texter.draw(sb,Constants.lang.getText("levels")+": "+Constants.levels+"\n"+
                    Constants.lang.getText("causedamage")+": "+(int)Constants.causeDamage+"\n"+
                            Constants.lang.getText("recieveddamage")+": "+(int)Constants.recievedDamage+"\n"+
                            Constants.lang.getText("passedcells")+": "+Constants.PassedCells+"\n"+
                            Constants.lang.getText("Blockeddamage")+": "+(int)Constants.BlockedDamage+"\n"
                    ,110,395);
            // 2 столб
            texter.draw(sb,Constants.lang.getText("maxLevel")+": "+Constants.maxLevel+"\n"+
                            Constants.lang.getText("maxExp")+": "+(int)Constants.maxExp+"\n"+
                            Constants.lang.getText("maxMoney")+": "+(int)Constants.maxMoney+"\n"
                    ,Constants.WIDTH/2+10,395);
            //
            image.setBounds(171,Constants.HEIGTH-234,140,145);
            image.draw(sb);
        }
    }

    @Override
    public void update(float delta) {
        if(menu.manager.inputText.getUpdate()){
            String buffer = "";
            if(menu.manager.inputText.getResult()){
                buffer = menu.manager.inputText.getText();
                System.out.println("Buffer"+buffer);
                if(buffer.length()<9&&buffer.length()>2){
                    Constants.name = buffer;
                }
            }
        }
        if(exit.onClicked()){
            menu.activeWindow = 0;
            foneStart = 0;
            menu.manager.save.saveSettings();
        }
        if(left.onClicked()){
            if(Character.image-1>-1){
                Character.image-=1;
                image = new Sprite(Character.PicturesPlayerMenu[Character.image]);
            }
        }
        if(right.onClicked()){
            if(Character.image+1<Character.PicturesPlayerMenu.length){
                Character.image+=1;
                image = new Sprite(Character.PicturesPlayerMenu[Character.image]);
            }
        }
        if(editName.onClicked()){
            String buffer = "";
            Gdx.input.getTextInput(menu.manager.inputText,Constants.lang.getText("getName"),"",Constants.lang.getText("name"));

        }
    }

    @Override
    public void dispose() {
    fone.getTexture().dispose();
    left.dispose();
    right.dispose();
    editName.dispose();
    }
    private boolean StartFone(SpriteBatch sb){
        fone.setAlpha(foneStart);
        fone.setBounds(20,20,Constants.WIDTH - 40,Constants.HEIGTH - 40);
        if(foneStart>=0.90){
            fone.setAlpha(1);
            return true;
        }
        foneStart+= Gdx.graphics.getDeltaTime()*1.8f;
        fone.draw(sb);
        return false;
    }
}
