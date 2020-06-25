package com.arliproduct.game.States.Groups.GroupsStartMenu;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.Elements.TwoTS;
import com.arliproduct.game.Player.Character;
import com.arliproduct.game.StateManager;
import com.arliproduct.game.States.Groups.GroupsGame.WarningWindow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UpgradeView extends Group { // Распределние очков уровней
    Sprite Sw = new Sprite(new Texture("StartMenu/sword.png"));
    Sprite Sh = new Sprite(new Texture("StartMenu/shield.png"));
    Sprite En = new Sprite(new Texture("StartMenu/energy.png"));
    Sprite EnS = new Sprite(new Texture("StartMenu/energyS.png"));
    Sprite HpS = new Sprite(new Texture("StartMenu/HpS.png"));
    Sprite levelR = new Sprite(new Texture("StartMenu/paint1.png"));

    final float levelRMax = 660;
    Button exit;
    TwoTS[] upgradeSw;
    TwoTS[] upgradeSh;
    TwoTS[] upgradeEn;
    TwoTS[] upgradeEnS;
    TwoTS[] upgradeHpS;
    int skills = Character.SkillPoint;
    int en = Character.SkillEn;
    int ens = Character.SkillEnS;
    int hps = Character.SkillHpS;
    int sw = Character.SkillSw;
    int sh = Character.SkillSh;
    Button addSw;
    Button addSh;
    Button addEn;
    Button addEnS;
    Button addHpS;
    Button clear;
    Button apply;
    WarningWindow warning;
    boolean close = false;
    boolean warningW = false;
    float foneStart = 0;
    Sprite fone = new Sprite(new Texture("StartMenu/SettingWindow1.png"));
    OrthographicCamera camera;
    BitmapFont texter;
    // Временные спрайты
    Sprite one = new Sprite(new Texture("StartMenu/Button4.png"));
    Sprite two = new Sprite(new Texture("StartMenu/Button4Down.png"));
    StateManager manager;
    public UpgradeView(BitmapFont texter, OrthographicCamera camera, StateManager manager) {
        this.camera = camera;
        this.texter =texter;
        this.manager = manager;
        warning = new WarningWindow(Constants.lang.getText("SkillWarning"),Constants.lang.getText("yes"),Constants.lang.getText("no"),texter,camera);
        createElements();
    }
    private int getMoney(int i){
        return 0;
    }
    public void createElements(){
        exit = new Button(Constants.WIDTH-160,Constants.HEIGTH-160,100,100,new Texture("All/exitButton.png"),new Texture("All/exitButtonDown.png"),false,camera);
        exit.setFast(false);
        exit.setDuration(0.2f);
        // Создание кнопок
        addSw = new Button(887, Constants.HEIGTH - 178,120,130,new Texture("StartMenu/AddOff.png"),new Texture("StartMenu/AddOn.png"),false,camera);
        addSw.setFast(false);
        addSw.setDuration(0.3f);
        addSh = new Button(887,Constants.HEIGTH - 315,120,130,new Texture("StartMenu/AddOff.png"),new Texture("StartMenu/AddOn.png"),false,camera);
        addSh.setFast(false);
        addSh.setDuration(0.3f);
        addEn = new Button(887,Constants.HEIGTH - 440,120,120,new Texture("StartMenu/AddOff.png"),new Texture("StartMenu/AddOn.png"),false,camera);
        addEn.setFast(false);
        addEn.setDuration(0.3f);
        addEnS = new Button(887,Constants.HEIGTH - 565,120,120,new Texture("StartMenu/AddOff.png"),new Texture("StartMenu/AddOn.png"),false,camera);
        addEnS.setFast(false);
        addEnS.setDuration(0.3f);
        addHpS = new Button(887,Constants.HEIGTH - 680,120,110,new Texture("StartMenu/AddOff.png"),new Texture("StartMenu/AddOn.png"),false,camera);
        addHpS.setFast(false);
        addHpS.setDuration(0.3f);
        clear = new Button(Constants.WIDTH-150,Constants.HEIGTH/2+60,90,90,new Texture("StartMenu/ClearOff.png"),new Texture("StartMenu/ClearOn.png"),false,camera);
        apply = new Button(Constants.WIDTH-250,Constants.HEIGTH/2+60,90,90,new Texture("StartMenu/ApplyOff.png"),new Texture("StartMenu/ApplyOn.png"),false,camera);
        clear.setFast(false);
        apply.setFast(false);
        clear.setDuration(0.3f);
        apply.setDuration(0.3f);
        buttonAct();

        //
        upgradeSw = new TwoTS[9];
        upgradeEn = new TwoTS[9];
        upgradeSh = new TwoTS[9];
        upgradeEnS = new TwoTS[9];
        upgradeHpS = new TwoTS[9];
        // Работа с элементами прокачки
        for(int i=0;i<9;i++) {
            upgradeSw[i] = new TwoTS(one, two, 212+75*i, Constants.HEIGTH - 186, 75, 145);
        }
        for(int i=0;i<9;i++) {
            upgradeSh[i] = new TwoTS(one, two, 212+75*i, Constants.HEIGTH - 315, 75, 135);
        }
        for(int i=0;i<9;i++) {
                upgradeEn[i] = new TwoTS(one, two, 212+75*i, Constants.HEIGTH - 445, 75, 130);
            }
        for(int i=0;i<9;i++) {
            upgradeHpS[i] = new TwoTS(one, two, 212+75*i, Constants.HEIGTH - 689, 75, 119);
        }
        for(int i=0;i<9;i++) {
            upgradeEnS[i] = new TwoTS(one, two, 212+75*i, Constants.HEIGTH - 570, 75, 125);
        }
upgradeAct();
            }
            public void upgradeAct(){
                for(int i=0;i< sw;i++){
                    upgradeSw[i].set(true);
                }
                for(int i=0;i< sh;i++){
                    upgradeSh[i].set(true);
                }
                for(int i=0;i< en;i++){
                    upgradeEn[i].set(true);
                }
                for(int i=0;i< ens;i++){
                    upgradeEnS[i].set(true);
                }
                for(int i=0;i< hps;i++){
                    upgradeHpS[i].set(true);
                }
            }
            public void reUpdateAct(){
                for(int i=0;i< 9;i++){
                    upgradeSw[i].set(false);
                }
                for(int i=0;i< 9;i++){
                    upgradeSh[i].set(false);
                }
                for(int i=0;i< 9;i++){
                    upgradeEn[i].set(false);
                }
                for(int i=0;i< 9;i++){
                    upgradeEnS[i].set(false);
                }
                for(int i=0;i< 9;i++){
                    upgradeHpS[i].set(false);
                }
            }
            public void buttonAct(){
                if(Character.SkillPoint<1){
                    clear.setClickable(false);
                    apply.setClickable(false);
                    addHpS.setClickable(false);
                    addSw.setClickable(false);
                    addSh.setClickable(false);
                    addEnS.setClickable(false);
                    addEn.setClickable(false);
                }else
                {
                    clear.setClickable(true);
                    apply.setClickable(true);
                    addHpS.setClickable(true);
                    addSw.setClickable(true);
                    addSh.setClickable(true);
                    addEnS.setClickable(true);
                    addEn.setClickable(true);
                }
            }
    @Override
    public void render(SpriteBatch sb, float delta) {
            if (StartFone(sb)) {
                fone.setBounds(10, 10, 1270, 700);
                fone.draw(sb);
                exit.draw(sb, delta);
                Sw.setBounds(60, Constants.HEIGTH - 170, 120, 120);
                Sw.draw(sb);
                Sh.setBounds(60, Constants.HEIGTH - 310, 120, 120);
                Sh.draw(sb);
                En.setBounds(60, Constants.HEIGTH - 440, 120, 120);
                En.draw(sb);
                EnS.setBounds(60, Constants.HEIGTH - 567, 120, 120);
                EnS.draw(sb);
                HpS.setBounds(60, Constants.HEIGTH - 693, 120, 120);
                HpS.draw(sb);
                // Вывод нынешнего уровня
                for (int i = 0; i < 9; i++) {
                    upgradeSw[i].draw(sb);
                    upgradeSh[i].draw(sb);
                    upgradeEn[i].draw(sb);
                    upgradeHpS[i].draw(sb);
                    upgradeEnS[i].draw(sb);
                }
                // Вывод кол во скилов
                texter.setColor(Color.YELLOW);
                texter.getData().setScale(2.8f);
                texter.draw(sb, skills + "", 1035, 633);
                // Вывод кнопок
                addHpS.draw(sb, delta);
                addSh.draw(sb, delta);
                addSw.draw(sb, delta);
                addEnS.draw(sb, delta);
                addEn.draw(sb, delta);
                clear.draw(sb, delta);
                apply.draw(sb, delta);
                // Вывод полоски уровня
                levelR.setBounds(Constants.WIDTH / 2 - 420, Constants.HEIGTH - 40, levelRMax / 100 * (Character.Expirience * 100 / Character.MaxExpirience), 20);
                levelR.draw(sb);
                if(warningW){
                    warning.render(sb,delta);
                }
            }
        }

    @Override
    public void update(float delta) {
        if(!warningW) {
            buttonAct();
            upgradeAct();
            if (exit.onClicked()) {
                close = true;
            }
            if (addEn.onClicked()) {
                if (skills > 0) {
                    skills--;
                    en++;
                }
            }
            if (addEnS.onClicked()) {
                if (skills > 0) {
                    skills--;
                    ens++;
                }
            }
            if (addSw.onClicked()) {
                if (skills > 0) {
                    skills--;
                    sw++;
                }
            }
            if (addSh.onClicked()) {
                if (skills > 0) {
                    skills--;
                    sh++;
                }
            }
            if (addHpS.onClicked()) {
                if (skills > 0) {
                    skills--;
                    hps++;
                }
            }
            if (clear.onClicked()) {
                reUpdateAct();
                skills = Character.SkillPoint;
                en = Character.SkillEn;
                ens = Character.SkillEnS;
                hps = Character.SkillHpS;
                sw = Character.SkillSw;
                sh = Character.SkillSh;
            }
            if (apply.onClicked()) {
                if(skills!=Character.SkillPoint)
                warningW = true;
            }
        }else{warning.update(delta);
        if(warning.isClick()){
            if(warning.isClicableButton()==1){
                // Производим сохрание данных
                Character.SkillEnS = ens;
                Character.SkillEn = en;
                Character.SkillHpS = hps;
                Character.SkillSh = sh;
                Character.SkillSw = sw;
                Character.SkillPoint = skills;
                warningW = false;
                manager.save.saveSettings();
                //
            }else{
                warningW = false;
            }
        }
        }
    }
    public boolean isClose(boolean set){
        boolean otvet = false;
        if(set&&close){
            otvet = close;
            close = false;
            foneStart = 0;
        }
        return otvet;
    }
    @Override
    public void dispose() {
        Sw.getTexture().dispose();
        Sh.getTexture().dispose();
        En.getTexture().dispose();
        fone.getTexture().dispose();
        one.getTexture().dispose();
        two.getTexture().dispose();
        EnS.getTexture().dispose();
        HpS.getTexture().dispose();
        addEn.dispose();
        addEnS.dispose();
        addSh.dispose();
        addHpS.dispose();
        addSw.dispose();
        exit.dispose();
        clear.dispose();
        apply.dispose();
        levelR.getTexture().dispose();
    }
    private boolean StartFone(SpriteBatch sb){
        fone.setAlpha(foneStart);
        fone.setBounds(10,10,1270,700);
        if(foneStart>=0.90){
            fone.setAlpha(1);
            return true;
        }
        foneStart+= Gdx.graphics.getDeltaTime()*1.8f;
        fone.draw(sb);
        return false;
    }
}
