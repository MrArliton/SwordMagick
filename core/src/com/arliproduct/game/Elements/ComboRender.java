package com.arliproduct.game.Elements;

import com.arliproduct.game.Actor.Character;
import com.arliproduct.game.Actor.Object;
import com.arliproduct.game.Actor.ObjectClass;
import com.arliproduct.game.Constants;
import com.arliproduct.game.Objects.BotObject;
import com.arliproduct.game.Objects.DeathObject;
import com.arliproduct.game.Objects.GunObject;
import com.arliproduct.game.Objects.TrapObject;
import com.arliproduct.game.States.Game;
import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.arliproduct.game.States.Groups.GroupsGame.StateBoard;
import com.arliproduct.game.States.StartMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

import java.util.NoSuchElementException;


public class ComboRender {
    ComboElementor elementor;
    Pole pole;
    Character player;
    StateBoard state;
   public boolean start = false;
        //Элементы для воссозданяи анимаций
        float buffer=0;
        int x;
        int y;
        // атака
        boolean attack = false;
        Array<Integer> attacks;
        boolean one;
        int p=0;
        int o=0;
        boolean runAttack = false;
        int directionA;
        float power = 1;
        boolean yesAttack;
        boolean ActiveAttack = false;
       public Array<ObjectClass> objectsA;
    Array<Integer> bufferAttackerClear = new Array<Integer>();
    Array<Integer> bufferSpecialCombo = new Array<Integer>();
        int xA=-1;
        int yA=-1;
        boolean created = false;
        public boolean stop = false;
        //
        //защита
    boolean ActiveShield = false;
    boolean runShield = false;
    boolean yesShield = false;
    int directionS;
    boolean shield = false;
    boolean level = false;
    boolean oneS = false;
    Array<Integer> shields;
            //
    boolean active = false;
    int go = 0;
    public ComboRender(ComboElementor elementor, Pole pole, Character player,String obj) {
        this.pole = pole;
        this.elementor = elementor;
        this.player = player;
        x = player.x;
        objectsA = new Array<ObjectClass>();
        y = player.y;
        attacks = new Array<Integer>();
        shields = new Array<Integer>();
        state = new StateBoard(power,player.powermax,player.health,player.speed,player.attack,player.superPower,player);
        power = player.speedPowerRegeneration;
        createObjects(obj,false);
    }    public ComboRender(ComboElementor elementor, Pole pole, Character player,String obj,boolean off) {
        this.pole = pole;
        level = true;
        this.elementor = elementor;
        this.player = player;
        x = player.x;
        objectsA = new Array<ObjectClass>();
        y = player.y;
        attacks = new Array<Integer>();
        shields = new Array<Integer>();
        state = new StateBoard(power,player.powermax,player.health,player.speed,player.attack,player.superPower,player);
        power = player.speedPowerRegeneration;
        createObjects(obj,true);
    }
    public void createObjects(String obj,boolean off){
        if(off||!Gdx.files.external("Game/MapLevels/"+obj).exists()) {
            String[] buffer1;
            if(!off) {
                FileHandle fileMap = Gdx.files.internal("Game/MapLevels/" + obj);
                buffer1 = fileMap.readString().split(",");
            }else{
                buffer1 = obj.split(",");
            }
            for(int i=0;i<buffer1.length;i++){
            if(buffer1[i].split(";")[0].equalsIgnoreCase("Trap")){
            int[][] a = new int[Integer.parseInt(buffer1[i].split(";")[1])/2][2];
            int l = 0;
            for(int s = 1;s<Integer.parseInt(buffer1[i].split(";")[1]);s+=2){
                    a[l][0]=Integer.parseInt(buffer1[i].split(";")[s+1]);
                    a[l][1]=Integer.parseInt(buffer1[i].split(";")[s+2]);
                    l++;
            }
            l = Integer.parseInt(buffer1[i].split(";")[1])+1;
                float attack = Float.parseFloat(buffer1[i].split(";")[l+1]);
                String path1 = "Game/MapLevels/Textures/"+buffer1[i].split(";")[l+2];
                String path2 = "Game/MapLevels/Textures/"+buffer1[i].split(";")[l+3];
                float duration = Float.parseFloat(buffer1[i].split(";")[l+4]);
                objectsA.add(new TrapObject(a,attack,new Sprite(new Texture(path1)),new Sprite(new Texture(path2)),duration,pole));
            }else
            if(buffer1[i].split(";")[0].equalsIgnoreCase("Death")){
                int[][] a = new int[Integer.parseInt(buffer1[i].split(";")[1])/2][2];
                int l = 0;
                for(int s = 1;s<Integer.parseInt(buffer1[i].split(";")[1]);s+=2){
                    a[l][0]=Integer.parseInt(buffer1[i].split(";")[s+1]);
                    a[l][1]=Integer.parseInt(buffer1[i].split(";")[s+2]);
                    l++;
                }
                l = Integer.parseInt(buffer1[i].split(";")[1])+1;
                int w = Integer.parseInt(buffer1[i].split(";")[l+1]);
                int h = Integer.parseInt(buffer1[i].split(";")[l+2]);
                float duration = Float.parseFloat(buffer1[i].split(";")[l+3]);
                TextureRegion textureRegion = new TextureRegion(new Texture("Game/MapLevels/Textures/"+buffer1[i].split(";")[l+4]));
                objectsA.add(new DeathObject(textureRegion,w,h,duration,a,pole));

            }else
            if(buffer1[i].split(";")[0].equalsIgnoreCase("Bot")){ // Доделать потом

            }else
            if(buffer1[i].split(";")[0].equalsIgnoreCase("Gun")){
               TextureRegion tx = new TextureRegion(new Texture("Game/MapLevels/Textures/"+buffer1[i].split(";")[1]));
               float durationFrame =  Float.parseFloat(buffer1[i].split(";")[2]);
               float duration =  Float.parseFloat(buffer1[i].split(";")[3]);
               int direction = Integer.parseInt(buffer1[i].split(";")[4]);
               int x = Integer.parseInt(buffer1[i].split(";")[5]);
               int y = Integer.parseInt(buffer1[i].split(";")[6]);
               float health = Float.parseFloat(buffer1[i].split(";")[7]);
               int dead = Integer.parseInt(buffer1[i].split(";")[8]);
               float attackP = Float.parseFloat(buffer1[i].split(";")[9]);
               int w = Integer.parseInt(buffer1[i].split(";")[10]);
               int h = Integer.parseInt(buffer1[i].split(";")[11]);
               Sprite[] sp = new Sprite[tx.split(w,h)[0].length];
               for(int r=0;r<tx.split(w,h)[0].length;r++){
                   sp[r]=new Sprite(tx.split(w,h)[0][r]);
               }
               objectsA.add(new GunObject(sp,durationFrame,duration,direction,x,y,health,dead,pole,attackP));
            }else
            if(buffer1[i].split(";")[0].equalsIgnoreCase("Object")){
                int x = Integer.parseInt(buffer1[i].split(";")[1]);
                int y = Integer.parseInt(buffer1[i].split(";")[2]);
                float shield = Float.parseFloat(buffer1[i].split(";")[3]);
                float health = Float.parseFloat(buffer1[i].split(";")[4]);
                boolean dead = false;
                if(Integer.parseInt(buffer1[i].split(";")[5])==1){
                    dead = true;
                }
                String path = buffer1[i].split(";")[6];
                objectsA.add(new Object(new Vector2(x,y),shield,health,dead,new Sprite(new Texture("Game/MapLevels/Textures/"+path)),pole));
            }else
            if(buffer1[i].split(";")[0].equalsIgnoreCase("botObj")){ System.out.println("YES BOT Create");
                String pathF = buffer1[i].split(";")[3];
                String pathB = buffer1[i].split(";")[4];
                String pathR = buffer1[i].split(";")[5];
                String pathL = buffer1[i].split(";")[6];
                TextureRegion[] fT = new TextureRegion(new Texture("Game/"+pathF)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                TextureRegion[] bT = new TextureRegion(new Texture("Game/"+pathB)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                TextureRegion[] rT = new TextureRegion(new Texture("Game/"+pathR)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                TextureRegion[] lT = new TextureRegion(new Texture("Game/"+pathL)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                Sprite[] f = new Sprite[fT.length];
                Sprite[] b = new Sprite[bT.length];
                Sprite[] r = new Sprite[rT.length];
                Sprite[] l = new Sprite[lT.length];
                for(int a=0;a<fT.length;a++){
                    f[a] = new Sprite(fT[a]);
                }
                for(int a=0;a<bT.length;a++){
                    b[a] = new Sprite(bT[a]);
                }
                for(int a=0;a<rT.length;a++){
                    r[a] = new Sprite(rT[a]);
                }
                for(int a=0;a<lT.length;a++){
                    l[a] = new Sprite(lT[a]);
                }
                //
                 pathF = buffer1[i].split(";")[7];
                 pathB = buffer1[i].split(";")[8];
                 pathR = buffer1[i].split(";")[9];
                 pathL = buffer1[i].split(";")[10];
                TextureRegion[] fTS = new TextureRegion(new Texture("Game/"+pathF)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                TextureRegion[] bTS = new TextureRegion(new Texture("Game/"+pathB)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                TextureRegion[] rTS = new TextureRegion(new Texture("Game/"+pathR)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                TextureRegion[] lTS = new TextureRegion(new Texture("Game/"+pathL)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                Sprite[] fS = new Sprite[fTS.length];
                Sprite[] bS = new Sprite[bTS.length];
                Sprite[] rS = new Sprite[rTS.length];
                Sprite[] lS = new Sprite[lTS.length];
                for(int a=0;a<fTS.length;a++){
                    fS[a] = new Sprite(fTS[a]);
                }
                for(int a=0;a<bTS.length;a++){
                    bS[a] = new Sprite(bTS[a]);
                }
                for(int a=0;a<rTS.length;a++){
                    rS[a] = new Sprite(rTS[a]);
                }
                for(int a=0;a<lTS.length;a++){
                    lS[a] = new Sprite(lTS[a]);
                }
                //
                pathF = buffer1[i].split(";")[11];
                pathB = buffer1[i].split(";")[12];
                pathR = buffer1[i].split(";")[13];
                pathL = buffer1[i].split(";")[14];
                TextureRegion[] fTI = new TextureRegion(new Texture("Game/"+pathF)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                TextureRegion[] bTI = new TextureRegion(new Texture("Game/"+pathB)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                TextureRegion[] rTI = new TextureRegion(new Texture("Game/"+pathR)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                TextureRegion[] lTI = new TextureRegion(new Texture("Game/"+pathL)).split(Integer.parseInt(buffer1[i].split(";")[1]),Integer.parseInt(buffer1[i].split(";")[2]))[0];
                Sprite[] fI = new Sprite[fTS.length];
                Sprite[] bI = new Sprite[bTS.length];
                Sprite[] rI = new Sprite[rTS.length];
                Sprite[] lI = new Sprite[lTS.length];
                for(int a=0;a<fTI.length;a++){
                    fI[a] = new Sprite(fTI[a]);
                }
                for(int a=0;a<bTI.length;a++){
                    bI[a] = new Sprite(bTI[a]);
                }
                for(int a=0;a<rTI.length;a++){
                    rI[a] = new Sprite(rTI[a]);
                }
                for(int a=0;a<lTI.length;a++){
                    lI[a] = new Sprite(lTI[a]);
                }
                boolean biffer = false;
                if(buffer1[i].split(";")[25].equalsIgnoreCase("1")){
                    biffer = true;
                }
                objectsA.add(new BotObject(f,b,l,r,fS,bS,rS,lS,fI,bI,rI,lI,new Sprite(new Texture("Game/"+buffer1[i].split(";")[15])),new Sprite(new Texture("Game/"+buffer1[i].split(";")[16])),new Sprite(new Texture("Game/"+buffer1[i].split(";")[17])),Integer.parseInt(buffer1[i].split(";")[18]),Integer.parseInt(buffer1[i].split(";")[19]),Integer.parseInt(buffer1[i].split(";")[20]),pole,Float.parseFloat(buffer1[i].split(";")[21]),Float.parseFloat(buffer1[i].split(";")[22]),Float.parseFloat(buffer1[i].split(";")[23]),Float.parseFloat(buffer1[i].split(";")[24]),biffer));
                System.out.println("Complete");
            }
            }
            System.out.println("Created");
            // Тестовая часть
         /*   Sprite[] f = new Sprite[com.arliproduct.game.Player.Character.animationForward.length];
            Sprite[] b = new Sprite[com.arliproduct.game.Player.Character.animationBack.length];
            Sprite[] r = new Sprite[com.arliproduct.game.Player.Character.animationR.length];
            Sprite[] l = new Sprite[com.arliproduct.game.Player.Character.animationL.length];
            for(int i=0;i<com.arliproduct.game.Player.Character.animationForward.length;i++){
                f[i] = new Sprite(com.arliproduct.game.Player.Character.animationForward[i]);
            }
            for(int i=0;i<com.arliproduct.game.Player.Character.animationBack.length;i++){
                b[i] = new Sprite(com.arliproduct.game.Player.Character.animationBack[i]);
            }
            for(int i=0;i<com.arliproduct.game.Player.Character.animationR.length;i++){
                r[i] = new Sprite(com.arliproduct.game.Player.Character.animationR[i]);
            }
            for(int i=0;i<com.arliproduct.game.Player.Character.animationL.length;i++){
                l[i] = new Sprite(com.arliproduct.game.Player.Character.animationL[i]);
            }
            objectsA.add(new BotObject(f,b,l,r,new Sprite(com.arliproduct.game.Player.Character.character[2]),new Sprite(com.arliproduct.game.Player.Character.character[0]),1,1,10,pole,20,20,3,0.5f,true));
*/
            //
        }else{elementor.manager.set(new StartMenu(elementor.manager));}
    }

    public void active() {
        start = true;
        int b = 0;
        try {
            b = elementor.combo.peek();
        }catch(NullPointerException e){
            start = false;
            return;
        }
            if(b==8||b==6||b==2||b==4||b==1){
                if(player.power<1.5){
                    start = false;
                }
                if(b==7||b==9){
                    if(player.power<4){
                        start = false;
                    }
                }
                if(b==3){
                    if(player.power<15){
                        start=false;
                    }
                }
            }
    }
    private boolean forvard(float delta){
        if(isNoRun(player.x,player.y+1)){
            player.clearActions();
            clearDvig();
            return true;
        }
        if(!active|ActiveAttack|ActiveShield){
            player.minusEnergy(1);
            ActiveAttack = false;
            ActiveShield = false;
            active = true;
            x = player.x;
            y = player.y;
            if(player.direction!=8){
                player.addAction(Actions.rotateTo(0,5/player.speed));
                player.direction = 8;
            }
            player.addAction(Actions.moveBy(0,pole.getCoordinates(x,y+1)[3],5/player.speed));
            Constants.PassedCells++;
        }
        if(!player.hasActions()){ System.out.println("ur");
            player.y++;
            go = 0;
            isEnergy();
            return true;
        }
        return false;
    }
    private boolean back(float delta){
        if(isNoRun(player.x,player.y-1)){
            player.clearActions();
            clearDvig();
            return true;
        }
        if(!active|ActiveAttack|ActiveShield){
            player.minusEnergy(1);
            ActiveAttack = false;
            ActiveShield = false;
            active = true;
            x = player.x;
            y = player.y;
            if(player.direction!=2){
                player.addAction(Actions.rotateTo(180,5/player.speed));
                player.direction = 2;
            }
            player.addAction(Actions.moveTo(pole.getCoordinates(x,y-1)[0],pole.getCoordinates(x,y-1)[1],5/player.speed));
            Constants.PassedCells++;
        }
        if(!player.hasActions()){ System.out.println("ur");
            player.y--;
            go = 0;
            isEnergy();
            return true;
        }
        return false;
                }
    private boolean left(float delta){
        if(isNoRun(player.x-1,player.y)){
            player.clearActions();
            clearDvig();
            return true;
        }
        if(!active|ActiveAttack|ActiveShield){
            player.minusEnergy(1);
            ActiveAttack = false;
            active = true;
            ActiveShield = false;
            x = player.x;
            y = player.y;
            if(player.direction!=4){
                player.addAction(Actions.rotateTo(90,5/player.speed));
                player.direction = 4;
            }
            player.addAction(Actions.moveTo(pole.getCoordinates(x-1,y)[0],pole.getCoordinates(x-1,y)[1],5/player.speed));
            Constants.PassedCells++;
        }
        if(!player.hasActions()){ System.out.println("ur");
            player.x--;
            go = 0;
            isEnergy();
            return true;
        }
        return false;
    }
    private boolean right(float delta){
        if(isNoRun(player.x+1,player.y)){
            player.clearActions();
            clearDvig();
            go = 0;
            return true;
        }
        if(!active|ActiveAttack|ActiveShield){
            player.minusEnergy(1);
            ActiveAttack = false;
            ActiveShield = false;
            active = true;
            x = player.x;
            y = player.y;
            if(player.direction!=6){
                player.addAction(Actions.rotateTo(-90,5/player.speed));
                player.direction = 6;
            }
            player.addAction(Actions.moveTo(pole.getCoordinates(x+1,y)[0],pole.getCoordinates(x+1,y)[1],5/player.speed));
            Constants.PassedCells++;
        }
        if(!player.hasActions()){ System.out.println("ur");
            player.x++;
            go = 0;
            isEnergy();
            return true;
        }
        return false;
    }
    private void clearDvig(){
        try{clearAttack(false);}catch(Exception e){}
        try{clearShield(false);}catch(Exception e){}
        elementor.combo.clear();
    }
    public void update(float delta) {
        player.act(delta);
        state.updateCharacteristics(player.power,player.powermax,player.health,player.speed,player.attack,player.superPower);
        state.update(delta);
        if(level) {
            short a = 0;
            for (int i = 0; i < objectsA.size; i++) {
                objectsA.get(i).update(delta);
                if (objectsA.get(i).index == 9) {
                    if (objectsA.get(i).dead) {
                        if (a != 1) {
                            a = 2;
                            objectsA.get(i).dispose();
                            objectsA.removeIndex(i);
                        }
                    } else {
                        a = 1;
                    }
                }
            }
            if (a == 2) { // Открываем проход
                for (int i = 0; i < objectsA.size; i++) {
                    if (objectsA.get(i).index == 20) {
                        if (objectsA.get(i).isMe(9, 0) || objectsA.get(i).isMe(10, 0)) {
                            objectsA.get(i).interupt(9, 0, 8, 2000);
                            objectsA.get(i).interupt(10, 0, 8, 2000);
                        }
                    }
                }
            }
            if (player.x == 9 & player.y == 0 || player.x == 10 & player.y == 0) { // Переходим на новый уровень
                Constants.exp += 15 * (Constants.level + 1);
                if (Constants.level % 5 == 0) {
                    Constants.money += 1 * Constants.level / 5;
                }
                Constants.level += 1;
                pole.game.manager.LevelsC.reCreateLevel(Constants.level);
                pole.game.manager.set(pole.game.stateManager.LevelsC.getGame(pole.game.stateManager));
                Constants.levels++;
            }
        }
        if(stop||start){
            state.setTimeout(true);
        }else{state.setTimeout(false);}

        if(!stop) { // Общее условие Всё ниже выполняет передвижения и анимацию
            if (!elementor.combo.isEmpty() & !active & start) {
                pole.clearColors();
                go = elementor.combo.pop();
            }
            if (!attack&&!shield) {
                if (go == 8) { // Вверх
                    if (forvard(delta)) {
                        active = false;
                    }
                } else if (go == 2) { // внищ
                    if (back(delta)) {
                        active = false;
                    }
                } else if (go == 4) { // лево
                    if (left(delta)) {
                        active = false;
                    }
                } else if (go == 6) { // право
                    if (right(delta)) {
                        active = false;
                    }
                } else if (go == 7) { // Кнопка атаки
                    attack = true;
                    active = true;
                    player.activeAttack();
                    attack(delta);
                }else if(go == 9){
                    shield = true;
                    active = true;
                    player.activeAttack();
                    shield(delta);
                }else if(go == 3){
                    if(player.superPowerMode){
                        player.superPowerMode = false;
                    }else{player.superPowerMode = true;
                    player.minusEnergy(10);}
                }
            } else {
                if(attack){ System.out.println("attack");
                attack(delta);}
                if(shield){System.out.println("shield");
                    shield(delta);
                }
            }

            if (elementor.combo.isEmpty() & !active) {
                start = false;
                player.setSpeedPowerRegeneration(power);
                go = 0;
                elementor.x = player.x;
                elementor.y = player.y;
                bufferSpecialCombo.clear();
                player.superPowerMode = false;
                clearAttack(false);
               clearShield(false);
                player.addAction(Actions.moveTo(pole.getCoordinates(player.x,player.y)[0],pole.getCoordinates(player.x,player.y)[1],5/player.speed));
            }
        }
    }
    private void isEnergy(){
        if(!player.isEnergy()){
            System.out.println("NoEnergy");
            start = false;
            player.setSpeedPowerRegeneration(power);
            go = 0;
            elementor.x = player.x;
            elementor.y = player.y;
            bufferSpecialCombo.clear();
            clearDvig();
        }
    }
    public void shield(float delta){
        if(!elementor.combo.isEmpty()&!oneS&!runShield&shield){
            go = elementor.combo.pop();
        }
        if(!runShield) {
            if (!oneS) {
                shields.add(go);
                if (!elementor.combo.isEmpty()) {
                    go = elementor.combo.pop();
                    if (go == 9) {
                        oneS = true;System.out.println("shield2");
                    } else {
                        directionS = shields.get(0);
                        shields.removeIndex(0);
                        shields.add(go);
                        ActiveShield = true;
                        runShield = true;System.out.println("shield3");
                    }
                } else {
                    clearShield(true);
                }
            } else {
                if (shields.get(0) == 8) {
                    p = 1;
                }
                if (shields.get(0) == 2) {
                    p = -1;
                }
                if (shields.get(0) == 4) {
                    o = -1;
                }
                if (shields.get(0) == 6) {
                    o = 1;
                }
                player.shieldb = true;
                player.directionS = shields.get(0);
                player.setSpeedPowerRegeneration(0);
                if (player.isAttackMeS()) {
                    player.setSpeedPowerRegeneration(power);
                    player.shieldb = false;
                    System.out.println("yes");
                    player.minusEnergy(2);
                    player.startAnimationS(shields.get(0));
                    clearShield(false);
                }

            }
        }else {
            if(!yesShield){
                player.shieldb = true;
                player.directionS = directionS;
                if(player.isAttackMeS()){
                    yesShield = true;}
            }
            if (shields.get(0) == 8) { // Вверх
                if (forvard(delta)) {
                    if(yesShield){System.out.println("yes1");player.minusEnergy(2);
                        player.startAnimationS(directionS);
                        clearShield(true);
                    activateCombo(bufferSpecialCombo);}
                    else
                        updateShield();
                }
            } else if (shields.get(0) == 2) { // внищ
                if (back(delta)) {
                    if(yesShield){System.out.println("yes1");player.minusEnergy(2);
                        player.startAnimationS(directionS);
                        clearShield(true);
                    activateCombo(bufferSpecialCombo);}
                    else
                        updateShield();
                }
            } else if (shields.get(0) == 4) { // лево
                if (left(delta)) {
                    if(yesShield){System.out.println("yes1");player.minusEnergy(2);
                        player.startAnimationS(directionS);
                        clearShield(true);
                    activateCombo(bufferSpecialCombo);}
                    else
                        updateShield();
                }
            } else if (shields.get(0) == 6) { // право
                if (right(delta)) {
                    if(yesShield){System.out.println("yes");player.minusEnergy(2);
                        player.startAnimationS(directionS);
                        clearShield(true);
                        activateCombo(bufferSpecialCombo);
                    }
                    else
                        updateShield();
                }
            }else if (shields.get(0)==9){
                clearShield(false);
            }else if(shields.get(0)==1){player.minusEnergy(0.5f);
                createSpecialCombo();
                updateShield();
            }else if(shields.get(0)==3){
                if(player.superPowerMode){
                    player.superPowerMode = false;
                }else{player.superPowerMode = true;
                    player.minusEnergy(10);}
                    updateShield();
            }
            }
        }


    private void updateShield(){
        if(shield&!elementor.combo.isEmpty()){
            shields.removeIndex(0);
            ActiveShield = true;
            shields.add(elementor.combo.pop());
        }else{clearShield(false);}
    }
    public void clearShield(boolean clearing){created = false;
        boolean biffer = clearing;
        player.deactiveAttack();
        int biffer1;
        int intbuff = 0;
        try {
            while (biffer && clearing) {
                if (elementor.combo.size() - intbuff != 0) {
                    biffer1 = elementor.combo.pop();
                    if (biffer1 == 9) {
                        biffer = false;
                        break;
                    } else {
                        bufferAttackerClear.add(biffer1);
                        intbuff++;
                    }
                } else {
                    biffer = false;
                }
            }
            if (clearing) {
                for (int i = 0; i < bufferAttackerClear.size; i++) {
                    elementor.combo.addFirst(bufferAttackerClear.get(bufferAttackerClear.size - 1 - i));
                }
            }
        }catch(NoSuchElementException e){}
        player.isAttackMe = false;
        player.shieldb  = false;
        bufferAttackerClear.clear();
        shields.clear();
        shield=false;
        active = false;
        o=0;
        p=0;
        oneS=false;
        runShield =false;
        yesShield = false;
        directionS = 0;
        xA = -1;
        yA = -1;
    }
    public void attack(float delta){
        System.out.println(go+" go");
        if(!elementor.combo.isEmpty()&!one&!runAttack&attack){
            go = elementor.combo.pop();
        }
        if(!runAttack) {
            if (!one) {
                attacks.add(go);
                if (!elementor.combo.isEmpty()) {
                    go = elementor.combo.pop();
                    if (go == 7) {
                        one = true;
                    } else {
                        directionA = attacks.get(0);
                        attacks.removeIndex(0);
                        attacks.add(go);
                        ActiveAttack = true;
                        runAttack = true;
                    }
                } else {
                    clearAttack(true);
                }
            } else {
                if (attacks.get(0) == 8) {
                    p = 1;
                }
                if (attacks.get(0) == 2) {
                    p = -1;
                }
                if (attacks.get(0) == 4) {
                    o = -1;
                }
                if (attacks.get(0) == 6) {
                    o = 1;
                }
                boolean offer = true;
                if(attacks.get(0)==6 && player.direction==4){
                    offer = false;
                }else if(attacks.get(0)==4 && player.direction==6){
                    offer = false;
                }else if(attacks.get(0)==2 && player.direction==8){
                    offer = false;
                }else if(attacks.get(0)==8 && player.direction==2){
                    offer = false;
                }
                player.setSpeedPowerRegeneration(0);
                if(offer) {
                    if (pole.objectInterupt(player.x + o, player.y + p, attacks.get(0), player.attack)) {
                        Constants.causeDamage+=player.attack;
                        player.minusEnergy(2);
                        System.out.println("yes");
                        player.startAnimaion(attacks.get(0));
                        clearAttack(false);
                    }
                }else{                        player.minusEnergy(2);
                    System.out.println("yes");
                    player.startAnimaion(attacks.get(0));
                    clearAttack(false);}
            }
        }else {
            if(!yesAttack){
                if(getDirectionObject()){
                    Constants.causeDamage+=player.attack;
                    yesAttack = true;}
            }
            if (attacks.get(0) == 8) { // Вверх
                System.out.println("8");
                if (forvard(delta)) {
                    if(yesAttack){System.out.println("yes1");player.minusEnergy(3);
                        player.startAnimaion(directionA);
                    clearAttack(true);
                        activateSpecial();}
                    else
                    updateAttack();
                }
            } else if (attacks.get(0) == 2) { // внищ
                System.out.println("2");
                if (back(delta)) {
                    if(yesAttack){System.out.println("yes1");player.minusEnergy(3);
                    player.startAnimaion(directionA);
                    clearAttack(true);
                        activateSpecial();}
                    else
                        updateAttack();
                }
            } else if (attacks.get(0) == 4) { // лево
                System.out.println("4");
                if (left(delta)) {
                    if(yesAttack){System.out.println("yes1");player.minusEnergy(3);
                        player.startAnimaion(directionA);
                    clearAttack(true);
                        activateSpecial();}
                    else
                    updateAttack();
                }
            } else if (attacks.get(0) == 6) { // право
                System.out.println("6");
                if (right(delta)) {
                    if(yesAttack){System.out.println("yes");player.minusEnergy(3);
                        player.startAnimaion(directionA);
                    clearAttack(true);
                    activateSpecial();
                    }
                    else
                    updateAttack();
                }
            }else if (attacks.get(0)==7){ System.out.println("Cleared");
            created = false;
                clearAttack(false);
            }else if(attacks.get(0)==1){
                createSpecialCombo();
                updateAttack();
            }else if(attacks.get(0)==3){
                if(player.superPowerMode){
                    player.superPowerMode = false;
                }else{player.superPowerMode = true;
                    player.minusEnergy(10);}
            }
        }
    }
    private void createSpecialCombo(){
        if(!created){
            bufferSpecialCombo.clear();
            boolean bif = true;
            created = true;
            int buffer = 0;
            while (bif) {
                if (!elementor.combo.isEmpty()) {
                    buffer = elementor.combo.pop();
                    if (buffer == 1) {
                        bif = false;
                        break;
                    } else {
                        bufferSpecialCombo.add(buffer);
                    }
                } else {
                    bif = false;
                    break;
                }
        } System.out.println("Created");
    }}
    private void activateSpecial(){
        if(!bufferSpecialCombo.isEmpty()) {
            activateCombo(bufferSpecialCombo);
            bufferSpecialCombo.clear();
        }
        System.out.println("activated");
    }
    public void activateCombo(Array<Integer> array){
        elementor.combo.clear();
        clearShield(false);
        clearAttack(false);
        go = 0;
        elementor.x = player.x;
        elementor.y = player.y;
        while(!array.isEmpty()){
            elementor.combo.addLast(array.get(0));
            array.removeIndex(0);
        }
        start = true;
    }
    private boolean getDirectionObject() {
        int x = 0;
        int y = 0;

        if (attacks.get(0) == 8) { // Вверх
            y++;
        } else if (attacks.get(0) == 2) { // внищ
            y--;
        } else if (attacks.get(0) == 4) { // лево
            x--;
        } else if (attacks.get(0) == 6) { // право
            x++;
        }
        if (directionA == 8) { // Вверх
            y++;
        } else if (directionA == 2) { // внищ
            y--;
        } else if (directionA == 4) { // лево
            x--;
        } else if (directionA == 6) { // право
            x++;
        }
        if(directionA==6 && attacks.get(0)==4){
            return true;
        }else if(directionA==4 && attacks.get(0)==6){
            return true;
        }else if(directionA==2 && attacks.get(0)==8){
            return true;
        }else if(directionA==8 && attacks.get(0)==2){
            return true;
        }
        return pole.objectInterupt(player.x+x,player.y+y,directionA,player.attack);
    }
    private void updateAttack(){
        if(attack&!elementor.combo.isEmpty()){
            attacks.removeIndex(0);
            ActiveAttack = true;
            attacks.add(elementor.combo.pop());
        }else{clearAttack(false);}
    }
    public void clearAttack(boolean clearing){created = false;
        boolean biffer = clearing;
        player.deactiveAttack();
        int biffer1;
        int intbuff = 0;
        try {
            while (biffer && clearing) {
                if (elementor.combo.size() - intbuff != 0) {
                    biffer1 = elementor.combo.pop();
                    if (biffer1 == 7) {
                        biffer = false;
                        break;
                    } else {
                        bufferAttackerClear.add(biffer1);
                        intbuff++;
                    }
                } else {
                    biffer = false;
                }
            }
            if (clearing) {
                for (int i = 0; i < bufferAttackerClear.size; i++) {
                    elementor.combo.addFirst(bufferAttackerClear.get(bufferAttackerClear.size - 1 - i));
                }
            }
        }catch(NoSuchElementException e){}
        bufferAttackerClear.clear();
        attacks.clear();
        attack=false;
        active = false;
        o=0;
        p=0;
        one=false;
        runAttack =false;
        yesAttack = false;
        directionA = 0;
        xA = -1;
        yA = -1;
    }
    public void upfateFunc(){

    }
    private boolean isNoRun(int x ,int y){
        if(x<20&&y<20&&x>-1&&y>-1){
            if(pole.objectInterupt(x,y,-1,0)){
                return true;}else{return false;}
        }else{return true;}
    }
    public void render(SpriteBatch sb, float delta) {
        for(int i = 0;i<objectsA.size;i++){
          if(objectsA.get(i).index!=7) {
              objectsA.get(i).render(sb, delta);
          }
        }
        for(int i = 0;i<objectsA.size;i++){
         if(objectsA.get(i).index==7) {
             objectsA.get(i).render(sb, delta);
         }
        }
        player.draw(sb,1);
        state.render(sb,delta);
    }
    public void dispose(){
        state.dispose();
        for(int i = 0;i<objectsA.size;i++){
            objectsA.get(0).dispose();
            objectsA.removeIndex(0);
        }
    }
}