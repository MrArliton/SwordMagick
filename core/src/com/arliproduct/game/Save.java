package com.arliproduct.game;

import com.arliproduct.game.Player.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.concurrent.CopyOnWriteArrayList;


public class Save {
    Preferences setting;
    public Save() {
        setting = Gdx.app.getPreferences("SaveGame1");
    }
    public void saveSettings(){
        try {
            setting.putBoolean("music", Constants.music);
            setting.putInteger("language", Constants.lang.language);
            setting.putInteger("ControllCharacterInterface", Constants.ControllCharacterInterface);
            // Игрок
            setting.putInteger("image", Character.image);
            setting.putInteger("exp",Character.Expirience);
            setting.putInteger("level",Character.level);
            setting.putInteger("money", Character.money);
            setting.putFloat("bonusHealthSize",Character.bonusHealthSize);
            setting.putInteger("bhsy",Character.bhsy);
            setting.putFloat("bonusAttack",Character.bonusAttack);
            setting.putInteger("bay",Character.bay);
            setting.putFloat("bonusEnergy",Character.bonusEnergy);
            setting.putInteger("bey",Character.bey);
            setting.putFloat("bonusShield",Character.bonusShield);
            setting.putInteger("bsy",Character.bsy);
            setting.putFloat("bonusEnergySize",Character.bonusEnergySize);
            setting.putInteger("besy",Character.besy);
            setting.putFloat("bonusSuper", Character.bonusSuper);
            setting.putInteger("bsay",Character.bsay);
            setting.putInteger("skills",Character.SkillPoint);
            setting.putString("name",Constants.name);
            setting.putInteger("levels",Constants.levels);
            setting.putInteger("maxLevel",Constants.maxLevel);
            setting.putInteger("passcells",Constants.PassedCells);
            setting.putInteger("BlockedDamage",Constants.BlockedDamage);
            setting.putFloat("causeDamage",Constants.causeDamage);
            setting.putFloat("receiveDamage",Constants.recievedDamage);
            setting.putFloat("maxExp",Constants.maxExp);
            setting.putFloat("maxMoney",Constants.maxMoney);
            //
            setting.putInteger("SkillSw",Character.SkillSw);
            setting.putInteger("SkillSh", Character.SkillSh);
            setting.putInteger ("SkillEn",Character.SkillEn);
            setting.putInteger ("SkillHpS",Character.SkillHpS);
            setting.putInteger ("SkillEnS",Character.SkillEnS);
            setting.flush();
        }catch(Exception e){
            System.out.println("Error with save.");
        }
        }
    public void loadSettings(){
        try {
            if(setting.getBoolean("start")) {
                Constants.music = setting.getBoolean("music");
                Constants.lang.language = setting.getInteger("language");
                Constants.ControllCharacterInterface = setting.getInteger("ControllCharacterInterface");
                Character.image = setting.getInteger("image");
                Character.Expirience = setting.getInteger("exp");
                Character.level = setting.getInteger("level");
                Character.money = setting.getInteger("money");
                Character.bonusHealthSize = setting.getFloat("bonusHealthSize");
                Character.bhsy = setting.getInteger("bhsy");
                Character.bonusAttack = setting.getFloat("bonusAttack");
                Character.bay = setting.getInteger("bay");
                Character.bonusEnergy = setting.getFloat("bonusEnergy");
                Character.bey = setting.getInteger("bey");
                Character.bonusShield = setting.getFloat("bonusShield");
                Character.bsy = setting.getInteger("bsy");
                Character.bonusEnergySize = setting.getFloat("bonusEnergySize");
                Character.besy = setting.getInteger("besy");
                Character.bonusSuper = setting.getFloat("bonusSuper");
                Character.bsay = setting.getInteger("bsay");
                Character.SkillPoint = setting.getInteger("skills");
                Constants.name = setting.getString("name");
                Constants.levels = setting.getInteger("levels");
                Constants.maxLevel = setting.getInteger("maxLevel");
                Constants.PassedCells = setting.getInteger("passcells");
                Constants.BlockedDamage = setting.getInteger("BlockedDamage");
                Constants.causeDamage = setting.getFloat("causeDamage");
                Constants.recievedDamage = setting.getFloat("receiveDamage");
                Constants.maxExp = setting.getFloat("maxExp");
                Constants.maxMoney = setting.getFloat("maxMoney");
                // Skills
                Character.SkillSw= setting.getInteger("SkillSw");
                Character.SkillSh=setting.getInteger("SkillSh");
                Character.SkillEn=setting.getInteger ("SkillEn");
                Character.SkillHpS=setting.getInteger ("SkillHpS");
                Character.SkillEnS=setting.getInteger ("SkillEnS");
            }else{
                setting.putBoolean("start",true);
                setting.flush();
                saveSettings();
            }
        }catch (Exception e){System.out.println("Error loading!");}
        }
}
