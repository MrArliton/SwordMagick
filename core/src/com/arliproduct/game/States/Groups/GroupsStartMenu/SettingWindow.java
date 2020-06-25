package com.arliproduct.game.States.Groups.GroupsStartMenu;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.Elements.LineActive;
import com.arliproduct.game.State;
import com.arliproduct.game.StateManager;
import com.arliproduct.game.States.StartMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SettingWindow extends Group {
    // Кнопки
    private Button exit;
    private Button info;
    private Button language;
    //
    //Line Active
    boolean restart = false;
    LineActive music;
    LineActive verticalI;
    //
    int langS = Constants.lang.language;
    private Texture fone;
    private Sprite foneS;
    private float foneStart = 0;
    int x = 20;
    int y = 20;
    public boolean close = false;
    int width = Constants.WIDTH - x;
    int heigth = Constants.HEIGTH - y;
    BitmapFont texter;
    StateManager manager;
    public SettingWindow(Camera camera, BitmapFont texter, StateManager manager) {
        fone = new Texture("StartMenu/SettingWindow.png");
        foneS = new Sprite(fone);
        createButton(camera,texter);
        createLineActive(texter);
        this.texter = texter;
        this.manager = manager;
    }

    @Override
    public void render(SpriteBatch sb, float delta) {
        if(StartFone(sb)) {
            foneS.draw(sb);
            exit.draw(sb, delta);
            info.draw(sb,delta);
            music.draw(sb,delta);
            verticalI.draw(sb,delta);
            language.draw(sb,delta);
        }
    }
    public boolean isClose(boolean set){
        boolean otvet = false;
        if(restart){
            manager.set(new StartMenu(manager));
        }
        if(set&&close){
            otvet = close;
            close = false;
            foneStart = 0;
        }
        return otvet;
    }
    @Override
    public void update(float delta) {
        Constants.music = music.down;
        if(exit.onClicked()){
            if(Constants.lang.language!=langS){
                restart = true;
            }else{
           close = true;}
        }else
        if(info.onClicked()){
            System.out.println("info");
        }else
        if(music.onClicked()){
        }
        if(language.onClicked()){
            if(Constants.lang.language+1<Constants.kolLang){
                Constants.lang.language++;
            }else{
                Constants.lang.language=0;
            }
            language.setText(-60,20,Constants.lang.getText1("language"),2.2f,2.2f,texter);
        }
        verticalI.onClicked();
    }
    @Override
    public void dispose() {
        fone.dispose();
        exit.dispose();
        info.dispose();
        music.dispose();
        language.dispose();
    }
    public void createButton(Camera camera, BitmapFont texter){
        exit = new Button(width-45-120,heigth-45-120,120,120,new Texture("All/exitButton.png"),new Texture("All/exitButtonDown.png"),false,camera);
        exit.setFast(false);
        exit.setCoordinateActive(-10,-10,10,10);
        exit.setDuration(0.2f);

        info = new Button(width-180,y+90,140,140,new Texture("StartMenu/Settings/faqButton.png"),new Texture("StartMenu/Settings/faqButtonDown.png"),false,camera);
        info.setFast(false);
        info.setDuration(0.4f);
        language = new Button(x+width/16,y+60,450,100,new Texture("All/Button1.png"),new Texture("All/Button1Down.png"),true,camera);
        language.setFast(false);
        language.setDuration(0.15f);
        language.setText(-60,20,Constants.lang.getText1("language"),2.5f,2.5f,texter);
    }
    public void createLineActive(BitmapFont texter){
        music = new LineActive(new Texture("StartMenu/Settings/ButtonOkey.png"),new Texture("StartMenu/Settings/ButtonOkeyActive.png"),x+width/16,y+heigth-heigth/4,450,90,texter);
        music.setText(-70,8,Constants.lang.getText("sound"),1.6f);
        music.setDowned(Constants.music);

        verticalI = new LineActive(new Texture("StartMenu/Settings/ButtonOkey.png"),new Texture("StartMenu/Settings/ButtonOkeyActive.png"),x+width/16,y+heigth-heigth/2,450,90,texter);
        verticalI.setText(-70,8,Constants.lang.getText("test"),1.4f);
        verticalI.setDowned(false);
    }
    private boolean StartFone(SpriteBatch sb){
        foneS.setAlpha(foneStart);
        foneS.setBounds(x,y,width,heigth);
        if(foneStart>=0.90){
            foneS.setAlpha(1);
            return true;
        }
        foneStart+= Gdx.graphics.getDeltaTime()*1.8f;
        foneS.draw(sb);
        return false;
    }
}
