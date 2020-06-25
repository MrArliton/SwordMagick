package com.arliproduct.game.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character { // тут прописываются параметры самого игрока
    // Основные текстуры
    public static TextureRegion[] character = new TextureRegion(new Texture("Game/Character/Character1Atlas.png")).split(27,27)[0];
    public static TextureRegion[] animationForward = new TextureRegion(new Texture("Game/Character/Animation/ForwardAttackAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationBack = new TextureRegion(new Texture("Game/Character/Animation/BackAttackAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationL = new TextureRegion(new Texture("Game/Character/Animation/LAttackAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationR = new TextureRegion(new Texture("Game/Character/Animation/RAttackAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationForwardS = new TextureRegion(new Texture("Game/Character/Animation/FShieldAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationBackS = new TextureRegion(new Texture("Game/Character/Animation/BShieldAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationLS = new TextureRegion(new Texture("Game/Character/Animation/LShieldAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationRS = new TextureRegion(new Texture("Game/Character/Animation/RShieldAtlas.png")).split(81,54)[0];
    public static TextureRegion[] PicturesPlayerMenu = new TextureRegion(new Texture("All/icons.png")).split(32,32)[0];
    public static TextureRegion[] animationForwardI = new TextureRegion(new Texture("Game/BotsSprites/FIAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationBackI = new TextureRegion(new Texture("Game/BotsSprites/BIAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationLI = new TextureRegion(new Texture("Game/BotsSprites/LIAtlas.png")).split(81,54)[0];
    public static TextureRegion[] animationRI = new TextureRegion(new Texture("Game/BotsSprites/RIAtlas.png")).split(81,54)[0];
    //
    public static int image = 0;
    public static int Expirience = 0;
    public static int level = 0;
    public static int MaxExpirience = 100+50*level;
    public static int SkillPoint = 0;
    public static int SkillSw = 0;
    public static int SkillSh = 0;
    public static int SkillEn = 0;
    public static int SkillHpS = 0;
    public static int SkillEnS = 0;
    public static float speed=20f+level;
    public static float powermax = 31+SkillEnS;
    public static float superPower = 1.5f;
    public static float health = 10+SkillHpS;
    public static int sword = 1+SkillSw;
    public static float attack=2+sword;
    public static float SpeedRegeneration = 0.35f+SkillEn*0.15f;
    public static float shield = 1+SkillSh;
    public static int money = 0;
    // Bonus
   public static float bonusHealthSize = 0;
    public static int bhsy = 0;
    public static float bonusAttack = 0;
    public  static int bay =0;
    public static float bonusEnergy = 0;
    public static int bey = 0;
    public static float bonusShield = 0;
    public static int bsy = 0;
    public static float bonusEnergySize = 0;
    public  static int besy =0;
    public  static float bonusSuper = 0;
    public  static int bsay= 0;
    //

    public static void dispose(){ // Производим сохранение данных а также диспосим изображения
        for(TextureRegion t:character){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationBack){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationForward){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationBackS){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationForwardS){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationL){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationLS){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationR){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationRS){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationBackI){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationForwardI){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationLI){
            t.getTexture().dispose();
        }
        for(TextureRegion t:animationRI){
            t.getTexture().dispose();
        }
    }
    public static void activeMinus(){
        if(bsy>0){
            bsy-=1;
        }
        if(bay>0){
            bay-=1;
        }
        if(bhsy>0){
          bhsy-=1;
        }
        if(bey>0){
        bey-=1;
        }
        if(besy>0){
         besy -=1;
        }
        if(bsay>0){
          bsay -=1;
        }
    }
    public static void update(){
        if(Expirience>=MaxExpirience){
            level++;
            Expirience -= MaxExpirience;
            SkillPoint++;
        }
        // Обновление переменных для полноценной работы
        MaxExpirience = 100+50*level;
         speed=20f;
         powermax = 31+SkillEnS+bonusEnergySize;
         superPower = 1.5f+bonusSuper;
         health = 20+SkillHpS+bonusHealthSize;
        sword = 1+SkillSw;
         attack=2+sword+bonusAttack;
        SpeedRegeneration = 0.25f+SkillEn*0.1f+bonusEnergy;
         shield = 1+SkillSh+bonusShield;
         if(bsy<1){
             bonusShield = 0;
         }
        if(bay<1){
            bonusAttack = 0;
        }
        if(bhsy<1){
            bonusHealthSize = 0;
        }
        if(bey<1){
            bonusEnergy = 0;
        }
        if(besy<1){
            bonusEnergySize = 0;
        }
        if(bsay<1){
            bonusSuper = 0;
        }

         //
    }
}
