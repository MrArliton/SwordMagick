package com.arliproduct.game.Objects;

import com.arliproduct.game.Actor.ObjectClass;
import com.arliproduct.game.Animations.Animator;
import com.arliproduct.game.Constants;
import com.arliproduct.game.Sounds;
import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class BotObject extends ObjectClass {
    Animator animation;
    Animator shieldMу; // Анимация защиты
     boolean shieldMyOn = false;
    Animator interuptMу; // Анимация ударо по боту
    boolean interuptMyOn = false;
    Sprite bot;
    Sprite botAttack;
    int x;
    int y;
    Pole pole;
    // параметры
    float health;
    float speed;
    float attack;
    boolean mode = false;
    boolean deadOn = true;
    float rotation = 0;
        boolean noAttaker = false;
    //

    boolean onAnimation = false;
    //Щит
    boolean shield = false;
    int naprShiled = 0;
    int napr = 8;
    // Части для работы алгоритма\
    float intelect = 0;
    float bufferIntelect = 0;
    int xBuffer = 0;
    int yBuffer = 0;
    int xB = 0;
    int yB = 0;
    boolean algo = false;
    boolean trap = false;
    boolean errorY = false;
    boolean errorX = false;
    float bufferShield = 0;
    Array<Integer> algoritm = new Array<Integer>();
    Array<Integer> algoritmA = new Array<Integer>();
    int directionA = 0;
    boolean run = false;
    float bufferoid = 0;
    boolean attackM = false;
    boolean shieldM = false;
    Sprite noAttack;
    boolean bufferRun = false;
    boolean extra = false;
    boolean ErrorMinDo = false;
    int radius = 6;
    //Все алгоритмы реализуются в массиве и отслежиую.т игрока прочими условиями и изменяют его по ходу
    // алгоритм приближения без препятсвий работает без массива
    public BotObject(Sprite[] f,Sprite[] b,Sprite[] l,Sprite[] r,Sprite[] fS,Sprite[] bS,Sprite[] lS,Sprite[] rS,Sprite[] fI,Sprite[] bI,Sprite[] lI,Sprite[] rI, Sprite bot,Sprite botAttack,Sprite noAttack, int x, int y,int radius, Pole pole, float health, float speed, float attack,float intelect, boolean deadOn) {
        animation = new Animator(8,8,10/speed,x,y,pole,f,l,r,b);
        shieldMу = new Animator(8,8,10/speed,x,y,pole,fS,lS,rS,bS);
        interuptMу = new Animator(8,8,10/speed,x,y,pole,fI,lI,rI,bI);
        this.bot = bot;
        index = 9;
        this.x = x;
        this.y = y;
        this.pole = pole;
        this.health = health;
        this.noAttack = noAttack;
        this.speed = speed;
        this.intelect = intelect;
        this.attack = attack;
        this.deadOn = deadOn;
        setX(pole.getCoordinates(x,y)[0]);
        setY(pole.getCoordinates(x,y)[1]);
        setWidth(pole.getCoordinates(x,y)[2]);
        setHeight(pole.getCoordinates(x,y)[3]);
        this.botAttack = botAttack;
        this.radius = radius;
        bot.setOrigin(getWidth()/2,getHeight()/2);
        botAttack.setOrigin(getWidth()/2,getHeight()/2);
    }
    @Override
    public void render(Batch sb, float delta) {
        if(!dead) {
            if (!onAnimation) {
                if (mode || shield) {
                    botAttack.setRotation(getRotation());
                    botAttack.setBounds(getX(), getY(), getWidth(), getHeight());
                    botAttack.draw(sb);
                    if(interuptMyOn) {
                        interuptMу.draw(sb);
                    }
                } else {
                    bot.setRotation(getRotation());
                    bot.setBounds(getX(), getY(), getWidth(), getHeight());
                    bot.draw(sb);
                    if(interuptMyOn) {
                        interuptMу.draw(sb);
                    }
                }
            } else {
                if(shieldMyOn) {
                shieldMу.draw(sb);
                }else {
                    if(noAttaker){
                        noAttack.setBounds(pole.getCoordinates(x,y)[0],pole.getCoordinates(x,y)[1],pole.getCoordinates(x,y)[2],pole.getCoordinates(x,y)[3]);
                        noAttack.setRotation(rotation);
                       noAttack.draw(sb);
                    }
                    animation.draw(sb);
                }
                }
        }
    }
    private int minGoWith(int a){ // Наход выходя для наибыстрейшего похода к игроку возвращает направление
        clearShield();
        clearAttack();
        run = false;
            trap = true;
        // Формируем алгоритм и записываем в массив
        if(a == 0){ // y>0
            int b = 0;
            if(pole.game.combo.character.x>x&&!ErrorMinDo){// если игрок правее
                ErrorMinDo = true;
                for(int x=this.x+1;x<20;x++) {
                    b = pole.getObjectIndex(x, y + 1);
                    if(b==6||b==8){
                        algoritm.add(6);
                    }else if(pole.objectInterupt(x,y+1,0,0)){
                        algoritm.add(6);
                    }else{
                        algoritm.add(6);
                        algoritm.add(8);
                        ErrorMinDo = false;
                        return 1;
                    }
                }
            }else{ // левее
                ErrorMinDo = false;
                for(int x=this.x-1;x>0;x--) {
                    b = pole.getObjectIndex(x, y + 1);
                    if(b==6||b==8){
                        algoritm.add(4);
                    }else if(pole.objectInterupt(x,y+1,0,0)){
                        algoritm.add(4);
                    }else{
                        algoritm.add(4);
                        algoritm.add(8);
                        return 1;
                    }
                }
            }
        }else if(a == 1){ //y<0
            int b = 0;
            if(pole.game.combo.character.x>x&&!ErrorMinDo){// если игрок правее
                ErrorMinDo = true;
                for(int x=this.x+1;x<20;x++) {
                    b = pole.getObjectIndex(x, y - 1);
                    if(b==6||b==8){
                        algoritm.add(6);
                    }else if(pole.objectInterupt(x,y-1,0,0)){
                        algoritm.add(6);
                    }else{
                        algoritm.add(6);
                        algoritm.add(8);
                        ErrorMinDo = false;
                        return 1;
                    }
                }
            }else { // левее
                ErrorMinDo = false;
                for (int x = this.x - 1; x > 0; x--) {
                    b = pole.getObjectIndex(x, y - 1);
                    if (b == 6 || b == 8) {
                        algoritm.add(4);
                    } else if (!pole.objectInterupt(x, y - 1, 0, 0)) {
                        algoritm.add(4);
                    } else {
                        algoritm.add(4);
                        algoritm.add(8);
                        return 1;
                    }
                }
            }
        }else if(a == 2){//x>0
            int b = 0;
            if(pole.game.combo.character.y>y&&!ErrorMinDo){// если игрок выше
                ErrorMinDo = true;
                for(int y=this.y+1;y<20;y++) {
                    b = pole.getObjectIndex(x+1, y);
                    if(b==6||b==8){
                        algoritm.add(8);
                    }else if(pole.objectInterupt(x+1,y,0,0)){
                        algoritm.add(8);
                    }else{
                        algoritm.add(8);
                        algoritm.add(6);
                        ErrorMinDo = false;
                        return 1;
                    }
                }
            }else { // ниже
                ErrorMinDo = false;
                for (int y = this.y - 1; y > 0; y--) {
                    b = pole.getObjectIndex(x+1, y);
                    if (b == 6 || b == 8) {
                        algoritm.add(2);
                    } else if (pole.objectInterupt(x+1, y, 0, 0)) {
                        algoritm.add(2);
                    } else {
                        algoritm.add(2);
                        algoritm.add(6);
                        return 1;
                    }
                }
            }
        }else if(a == 3){//x<0
            int b = 0;
            if(pole.game.combo.character.y>y&&!ErrorMinDo){// если игрок выше
                ErrorMinDo = true;
                for(int y=this.y+1;y<20;y++) {
                    b = pole.getObjectIndex(x-1, y);
                    if(b==6||b==8){
                        algoritm.add(8);
                    }else if(pole.objectInterupt(x-1,y,0,0)){
                        algoritm.add(8);
                    }else{
                        algoritm.add(8);
                        algoritm.add(4);
                        ErrorMinDo = false;
                        return 1;
                    }
                }
            }else { // ниже
                ErrorMinDo = false;
                for (int y = this.y - 1; y > 0; y--) {
                    b = pole.getObjectIndex(x-1, y);
                    if (b == 6 || b == 8) {
                        algoritm.add(2);
                    } else if (pole.objectInterupt(x-1, y, 0, 0)) {
                        algoritm.add(2);
                    } else {
                        algoritm.add(2);
                        algoritm.add(4);
                        return 1;
                    }
                }
            }
            if(ErrorMinDo){
                algoritm.clear();
                minGoWith(a);
            }
    }
        for(int i=0;i<algoritm.size;i++){
            System.out.print(algoritm.get(i)+" ");
        }
        return 0;
    }
    public void updateWithDoTrap(){ // Выполняем алгоритм с учётом ловушки
        boolean yS = false;

        if(!bufferRun) {
            for(int i=0;i<algoritm.size;i++){
                System.out.print(algoritm.get(i)+" ");
            }
            xB = 0;
            yB = 0;
            if (x == pole.game.combo.character.x) {// Проверяем на близость игрока
                if (y > pole.game.combo.character.y) {
                    shield = true;
                    yS = true;
                    naprShiled = 2;
                } else {
                    shield = true;
                    yS = true;
                    naprShiled = 8;
                }
            } else {
                shield = false;
            }
            if (y == pole.game.combo.character.y) {// Проверяем на близость игрока
                if (x > pole.game.combo.character.x) {
                    shield = true;
                    yS = true;
                    naprShiled = 4;
                } else {
                    shield = true;
                    yS = true;
                    naprShiled = 6;
                }
            } else {
                if (!yS) {
                    shield = false;
                }
            }
            int bufferAl = 0;
            if (!algoritm.isEmpty()) {
                bufferAl = algoritm.get(0);
                algoritm.removeIndex(0);
            } else {
                trap = false;
                algo = false;
                return;
            }
            if (bufferAl == 6) { // Право
                xB = 1;
                if (this.napr != 6) {
                    this.napr = 6;
                    rotation = 270;
                    addAction(Actions.rotateTo(rotation, 10 / speed));
                }bufferRun = true;
                addAction(Actions.moveTo(pole.getCoordinates(x + 1, y)[0], pole.getCoordinates(x + 1, y)[1], 10 / speed));
            } else if (bufferAl == 4) { // лево
                xB = -1;
                if (this.napr != 4) {
                    this.napr = 4;
                    rotation = 90;
                    addAction(Actions.rotateTo(rotation, 10 / speed));
                }bufferRun = true;
                addAction(Actions.moveTo(pole.getCoordinates(x - 1, y)[0], pole.getCoordinates(x - 1, y)[1], 10 / speed));
            } else if (bufferAl == 2) { // Внизщ
                yB = -1;
                if (this.napr != 2) {
                    this.napr = 2;
                    rotation = 180;
                    addAction(Actions.rotateTo(rotation, 10 / speed));
                }bufferRun = true;
                addAction(Actions.moveTo(pole.getCoordinates(x, y-1)[0], pole.getCoordinates(x, y-1)[1], 10 / speed));
            } else if (bufferAl == 8) { // Вверх
                yB = 1;
                if (this.napr != 8) {
                    this.napr = 8;
                    rotation = 0;
                    addAction(Actions.rotateTo(rotation, 10 / speed));
                }bufferRun = true;
                addAction(Actions.moveTo(pole.getCoordinates(x , y+1)[0], pole.getCoordinates(x , y+1)[1], 10 / speed));
            }
        }else{
            if(!hasActions()){
                bufferRun = false;
                x+=xB;
                y+=yB;
                xB = 0;
                yB = 0;
            }else if(pole.objectInterupt(x+xB,y+yB,0,0)){
                addAction(Actions.moveTo(pole.getCoordinates(x,y)[0],pole.getCoordinates(x,y)[1]));
                xB = 0;
                yB = 0;
                trap = false;
                algo = false;
                createAlgoritm();
            }
        }
        }

    private void algoritmRunP(){ // Добавление алгоритма приближения к игроку
        boolean buffer5 = false;
        run =false;
        int max = 21;
        int napr = (int)(Math.random()*2);
        if(!bufferRun) {
            int bufferA = 0;
            if (napr == 1 && pole.game.combo.character.y != y && !errorY) { // по y
                if (y < pole.game.combo.character.y) { // Если мы ниже противника
                    if (pole.objectInterupt(x, y + 1, 0, 0)&&y+1<19) {
                        errorY = true;
                        return;
                    }
                    bufferA = pole.getObjectIndex(x,y+1);
                    if(bufferA== 6||bufferA == 8){ // Обноружена ловушка
                        minGoWith(0);
                        return;
                    }

                    for (int y = this.y + 2; y < pole.maxx; y++) { // Ищим ловушки
                        bufferA = getObject(x, y);
                        if (bufferA == 7) {// Выставляем щит
                            if(max>y-this.y){
                                shield = true;
                                naprShiled = 8;
                                buffer5 = true;
                                max=y-this.y;
                            }

                        }
                    }
                    for (int x = this.x+1; x < pole.maxx; x++) { // Ищим ловушки
                        bufferA = getObject(x, y+1);
                        if (bufferA == 7) {// Выставляем щит
                            if(max>x-this.x){
                                shield = true;
                                naprShiled = 6;
                                buffer5 = true;
                                max=x-this.x;
                            }
                        }
                    }
                    for (int x = this.x-1; x > -1; x--) { // Ищим ловушки
                        bufferA = getObject(x, y+1);
                        if (bufferA == 7) {// Выставляем щит
                            if(max>this.x-x) {
                                shield = true;
                                naprShiled = 4;
                                buffer5 = true;
                            }
                        }
                    }
                    if(buffer5 == false){
                        shield = false;
                    }
                    errorX = false;
                    addAction(Actions.moveTo(pole.getCoordinates(x, y + 1)[0], pole.getCoordinates(x, y + 1)[1],10/speed));
                    if(this.napr != 8){
                        this.napr = 8;
                        rotation = 0;
                        addAction(Actions.rotateTo(rotation,10/speed));
                    }
                    bufferRun = true;
                    yB = 1;
                    xB = 0;
                }else { // Если игрок снизу
                    if (y != pole.game.combo.character.y) {
                        if (pole.objectInterupt(x, y - 1, 0, 0) && y - 1 > 1) {
                            errorY = true;
                            return;
                        }
                        bufferA = pole.getObjectIndex(x, y - 1);
                        if (bufferA == 6 || bufferA == 8) { // Обноружена ловушка
                            minGoWith(1);
                            return;
                        }
                        for (int y = this.y - 2; y > -1; y--) { // Ищим ловушки
                            bufferA = getObject(x, y);
                            if (bufferA == 7) {// Выставляем щит
                                if (max > this.y - y) {
                                    shield = true;
                                    naprShiled = 8;
                                    buffer5 = true;
                                    max = this.y - y;
                                }
                            }
                        }
                        for (int x = this.x + 1; x < pole.maxx; x++) { // Ищим ловушки
                            bufferA = getObject(x, y - 1);
                            if (bufferA == 7) {// Выставляем щит
                                if (max > x - this.x) {
                                    shield = true;
                                    naprShiled = 6;
                                    buffer5 = true;
                                    max = x - this.x;
                                }
                            }
                        }
                        for (int x = this.x - 1; x > -1; x--) { // Ищим ловушки
                            bufferA = getObject(x, y - 1);
                            if (bufferA == 7) {// Выставляем щит
                                if (max > this.x - x) {
                                    shield = true;
                                    naprShiled = 4;
                                    buffer5 = true;
                                }
                            }
                        }
                        if (buffer5 == false) {
                            shield = false;
                        }

                        errorX = false;
                        addAction(Actions.moveTo(pole.getCoordinates(x, y - 1)[0], pole.getCoordinates(x, y - 1)[1], 10 / speed));
                        bufferRun = true;
                        if (this.napr != 2) {
                            this.napr = 2;
                            rotation = 180;
                            addAction(Actions.rotateTo(rotation, 10 / speed));
                        }
                        yB = -1;
                        xB = 0;
                    }
                }
            } else { // по x
                if(!errorX){
                    if (x < pole.game.combo.character.x) { // Если мы левее противника
                        if (pole.objectInterupt(x+1, y , 0, 0)&&x+1<19) {
                            errorX = true;
                            return;
                        }      bufferA = pole.getObjectIndex(x+1,y);
                        if(bufferA== 6||bufferA == 8){ // Обноружена ловушка
                            minGoWith(2);
                            return;
                        }
                        for (int x = this.x + 2; x < pole.maxx; x++) { // Ищим ловушки
                            bufferA = getObject(x, y);
                            if (bufferA == 7) {// Выставляем щит
                                if(max>x-this.x){
                                shield = true;
                                naprShiled = 6;
                                buffer5 = true;
                                    max=x-this.x;
                                }
                            }
                        }
                        for (int y = this.y+1; y < pole.maxx; y++) { // Ищим ловушки
                            bufferA = getObject(x+1, y);
                            if (bufferA == 7) {// Выставляем щит
                                if(max>y-this.y){
                                shield = true;
                                naprShiled = 8;
                                buffer5 = true;
                                    max=y-this.y;
                                }
                            }
                        }
                        for (int y = this.y-1; y > -1; y--) { // Ищим ловушки
                            bufferA = getObject(x+1, y);
                            if (bufferA == 7) {// Выставляем щит
                                if(max>this.y-y) {
                                    shield = true;
                                    naprShiled = 2;
                                    buffer5 = true;
                                }
                            }
                        }
                        if(buffer5 == false){
                            shield = false;
                        }

                        errorY = false;
                        addAction(Actions.moveTo(pole.getCoordinates(x+1, y )[0], pole.getCoordinates(x+1, y )[1],10/speed));
                        bufferRun = true;
                        if(this.napr != 6){
                            this.napr = 6;
                            rotation = 270;
                            addAction(Actions.rotateTo(rotation,10/speed));
                        }
                        yB = 0;
                        xB = 1;
                    }else { // Если игрок снизу
                        if (x != pole.game.combo.character.x) {
                            if (pole.objectInterupt(x - 1, y, 0, 0) && x - 1 > 1) {
                                errorX = true;
                                return;
                            }
                            bufferA = pole.getObjectIndex(x, y - 1);
                            if (bufferA == 6 || bufferA == 8) { // Обноружена ловушка
                                minGoWith(3);
                                return;
                            }
                            for (int x = this.x - 2; x > -1; x--) { // Ищим ловушки
                                bufferA = getObject(x, y);
                                if (bufferA == 7) {// Выставляем щит
                                    if (max > this.x - x) {
                                        shield = true;
                                        naprShiled = 4;
                                        buffer5 = true;
                                        max = this.x - x;
                                    }
                                }
                            }
                            for (int y = this.y - 1; y < pole.maxx; y++) { // Ищим ловушки
                                bufferA = getObject(x - 1, y);
                                if (bufferA == 7) {// Выставляем щит
                                    if (max > y - this.y) {
                                        shield = true;
                                        naprShiled = 8;
                                        buffer5 = true;
                                        max = y - this.y;
                                    }
                                }
                            }
                            for (int y = this.y - 1; y > -1; y--) { // Ищим ловушки
                                bufferA = getObject(x - 1, y);
                                if (bufferA == 7) {// Выставляем щит
                                    if (max > this.y - y) {
                                        shield = true;
                                        naprShiled = 2;
                                        buffer5 = true;
                                    }
                                }
                            }
                            if (buffer5 == false) {
                                shield = false;
                            }
                            errorX = false;
                            addAction(Actions.moveTo(pole.getCoordinates(x - 1, y)[0], pole.getCoordinates(x - 1, y)[1], 10 / speed));
                            bufferRun = true;
                            if (this.napr != 4) {
                                this.napr = 4;
                                rotation = 90;
                                addAction(Actions.rotateTo(rotation, 10 / speed));
                            }
                            yB = 0;
                            xB = -1;
                        }
                    }
                }
            }
            if(errorX&&errorY){ // Экстренный режим ошибка прохода производим проход на несколько клеток назад
                minGoWith(napr);
            }
        }else{
            if(!hasActions()){ // Проверяет закончились ли все передвижения
                bufferRun = false;
                x+=xB;
                y+=yB;
                yB = 0;
                xB = 0;
            }else if(pole.objectInterupt(x+xB,y+yB,0,0)){
                clearActions();
                addAction(Actions.moveTo(pole.getCoordinates(x,y)[0],pole.getCoordinates(x,y)[1],15/speed));
                xB = 0;
                yB = 0;
                createAlgoritm();
            }
        }
    }
    private void algoritmShield(){ // Выставление защиты в нужном направлении
        if(!shieldM) { // Создание алгоритма
            // Отходим на 4 клетки от игрока за предел
            // Ожидаем около 2 секунд в режиме защиты после атакуем снова
            System.out.println("ShieldActive");
            clearAttack();
            shieldM = true;
            bufferRun = false;
            algo = true;
            extra = false;
            int xL = x;
            int yL = y;
            bufferShield = 0;
            shield = false;
            boolean uperror = false;
            boolean naerror = false;
            algoritm.clear();
            boolean up = false;
            boolean napravlen = false;
            if((int)(Math.random()*2)==1){ // Требуется при совподении одной из координат
                extra = true;
            }
            // Формируем нужные направления для отхода
            if(pole.game.combo.character.x==x){
                if(pole.game.combo.character.y>y){
                    napravlen = extra;
                    up = true;
                }else{
                    napravlen = extra;
                    up = false;
                }
            }else{
                if(pole.game.combo.character.y==y){
                    if(pole.game.combo.character.x>x){
                        napravlen = false;
                        up = extra;
                    }else{
                        napravlen = true;
                        up = extra;
                    }
                }else{ // Если ни одна из координат не ровна
                    if(pole.game.combo.character.x>x&&pole.game.combo.character.y>y){
                        napravlen = false;
                        up = false;
                    }else if(pole.game.combo.character.x<x&&pole.game.combo.character.y<y){
                        up = true;
                        napravlen = true;
                    }else if(pole.game.combo.character.x>x&&pole.game.combo.character.y<y){
                        up = true;
                        napravlen = false;
                    }else if(pole.game.combo.character.x<x&&pole.game.combo.character.y>y){
                        napravlen = true;
                        up = false;
                    }
                }
            }
            while(true){
                if(Math.abs(pole.game.combo.character.x-xL)+Math.abs(pole.game.combo.character.y-yL)<5){ // Тут можно отредактировать расстояние отхода
                 if(!up){ // Направление по вертикали
                    yL-=1;
                     if(pole.getObjectIndex(xL,yL)==6||pole.getObjectIndex(xL,yL)==7||pole.getObjectIndex(xL,yL)==8){
                     uperror = true;
                     }
                     if(pole.objectInterupt(xL,yL,0,0)){
                         uperror = true;
                     }
                     if(!uperror){
                         algoritm.add(2);

                         naerror = false;
                     }else{ yL+=1;}
                 }else{
                     yL+=1;
                     if(pole.getObjectIndex(xL,yL)==6||pole.getObjectIndex(xL,yL)==7||pole.getObjectIndex(xL,yL)==8){
                         uperror = true;
                     }
                     if(pole.objectInterupt(xL,yL,0,0)){
                         uperror = true;
                     }
                     if(!uperror){
                         algoritm.add(8);
                         naerror = false;
                     }else{
                         yL-=1;
                     }
                 }
                 if(!napravlen){ // направление по горизонтали
                     xL-=1;
                     if(pole.getObjectIndex(xL,yL)==6||pole.getObjectIndex(xL,yL)==7||pole.getObjectIndex(xL,yL)==8){
                         naerror = true;
                     }
                     if(pole.objectInterupt(xL,yL,0,0)){
                         naerror = true;
                     }
                     if(!naerror){
                         algoritm.add(4);
                         uperror = false;

                     }else{xL+=1;}
                 }else{
                     xL+=1;
                     if(pole.getObjectIndex(xL,yL)==6||pole.getObjectIndex(xL,yL)==7||pole.getObjectIndex(xL,yL)==8){
                         naerror = true;
                     }
                     if(pole.objectInterupt(xL,yL,0,0)){
                         naerror = true;
                     }
                     if(!naerror){
                         algoritm.add(4);

                         uperror = false;
                     }else{ xL-=1;}
                 }

                 if(uperror&&naerror){
                     break;
                 }
                }else{
                    break;
                }
            }
            // Выбор стороны защиты
            if(pole.game.combo.character.x==x+1&&pole.game.combo.character.y==y){
                naprShiled = 6;
            }else if(pole.game.combo.character.x==x-1&&pole.game.combo.character.y==y){
                naprShiled = 4;
            }else if(pole.game.combo.character.x==x&&pole.game.combo.character.y==y+1){
                naprShiled = 8;
            }else if(pole.game.combo.character.x==x&&pole.game.combo.character.y==y-1){
                naprShiled = 2;
            }
            algoritm.add(9);
            algoritm.add(naprShiled);
            //

        }else{// тут производим работу алгоритма
            if(!bufferRun){ // Продвигаем логику
                int buffer =0;
                xB = 0;
                yB=0;
                if(!algoritm.isEmpty()){
                     buffer = algoritm.get(0);
                     algoritm.removeIndex(0);
                }else{
                    if(shield){
                    bufferRun = true;}else{clearShield();run = true;}System.out.println("Cleared 2"); }
                if(buffer == 8){
                    if(napr!=8){
                        napr = 8;
                        rotation = 0;
                        addAction(Actions.rotateTo(rotation,10/speed));
                    }
                    addAction(Actions.moveTo(pole.getCoordinates(x,y+1)[0],pole.getCoordinates(x,y+1)[1],10/speed));
                    yB=1;
                    bufferRun = true;
                }else   if(buffer == 2){
                    if(napr!=2){
                        napr = 2;
                        rotation = 180;
                        addAction(Actions.rotateTo(rotation,10/speed));
                    }
                    addAction(Actions.moveTo(pole.getCoordinates(x,y-1)[0],pole.getCoordinates(x,y-1)[1],10/speed));
                    yB=-1;bufferRun = true;
                }else   if(buffer == 4){
                    if(napr!=4){
                        napr = 4;
                        rotation = 90;
                        addAction(Actions.rotateTo(rotation,10/speed));
                    }
                    addAction(Actions.moveTo(pole.getCoordinates(x-1,y)[0],pole.getCoordinates(x-1,y)[1],10/speed));
                    xB=-1;bufferRun = true;
                }else   if(buffer == 6){
                    if(napr!=6){
                        rotation = 270;
                        napr=6;
                        addAction(Actions.rotateTo(rotation,10/speed));
                    }
                    addAction(Actions.moveTo(pole.getCoordinates(x+1,y)[0],pole.getCoordinates(x+1,y)[1],10/speed));
                    xB=1;bufferRun = true;
                }else   if(buffer == 9){
                    if(!algoritmA.isEmpty()) {
                        buffer = algoritmA.get(0);
                        algoritmA.removeIndex(0);
                    }else{
                        if(pole.game.combo.character.x==x+1&&pole.game.combo.character.y==y){
                            buffer = 6;
                        }else if(pole.game.combo.character.x==x-1&&pole.game.combo.character.y==y){
                            buffer = 4;
                        }else if(pole.game.combo.character.x==x&&pole.game.combo.character.y==y+1){
                            buffer = 8;
                        }else if(pole.game.combo.character.x==x&&pole.game.combo.character.y==y-1){
                            buffer = 2;
                        }
                        shield = true;bufferRun = true;
                        naprShiled = buffer;
                    }
                }
            }else{ // Производим изменения
                if(!hasActions()){
                    if(!shield) {
                        bufferRun = false;
                        x += xB;
                        y += yB;
                        System.out.println("x =" +x+" y ="+y);
                        xB = 0;
                        yB = 0;
                    }else{
                        if(bufferShield>3){
                            shield = false;
                            clearShield();
                            if(Math.abs(pole.game.combo.character.x-x)<4&&Math.abs(pole.game.combo.character.y-y)<4){
                                algoritmAttack();
                            }else{
                                createAlgoritm();
                            }
                        }else{
                            bufferShield+=Gdx.graphics.getDeltaTime();
                        }
                    }
                }else if(pole.objectInterupt(x+xB,y+yB,0,0)){
                    clearActions();
                    addAction(Actions.moveTo(pole.getCoordinates(x,y)[0],pole.getCoordinates(x,y)[1],15/speed));
                        clearShield();
                        xB = 0;
                        yB = 0;
                        createAlgoritm();
                }
            }
        }

    }
    private void algoritmAttack() { // Атака на игрока
        if (!attackM) {
            int buffX = 0;
            clearShield();
            int buffY = 0;
            System.out.println("AttackActivate");
            algoritmA.clear();
            bufferRun = false;
            attackM = true;
            xBuffer = pole.game.combo.character.x;
            yBuffer = pole.game.combo.character.y;
            algo = true;
            if (Math.abs(x - pole.game.combo.character.x) > Math.abs(y - pole.game.combo.character.y)) { // игрок ближе по горизонтали
                boolean goY = true; System.out.println("Attack 1");
                int bu = 0;
                if(pole.game.combo.character.y>y) {
                    for (int i = y+1; i < pole.game.combo.character.y; i++) { // Проверяем мешает ли нам чтото сверху
                        if (pole.objectInterupt(x, i, 0, 0)) {
                            goY = false;
                            System.out.println("Interupt : "+ x+" "+i);
                        }
                        bu = pole.getObjectIndex(x, i);
                        if (bu == 8 || bu == 6 | bu == 7) {
                            goY = false;
                            System.out.println("index : "+ x+" "+i);
                        }
                        if (goY) {
                            algoritmA.add(8);
                        } else {
                            algoritmA.clear();
                            break;
                        }
                    }
                }else if(pole.game.combo.character.y<y) { System.out.println("Attack 1 2");
                    for (int i = y-1; i > pole.game.combo.character.y; i--) { // Проверяем мешает ли нам чтото сверху
                        if (pole.objectInterupt(x, i, 0, 0)) {
                            goY = false;
                            System.out.println("Interupt : "+ x+" "+i);
                        }
                        bu = pole.getObjectIndex(x, i);
                        if (bu == 8 || bu == 6 | bu == 7) {
                            goY = false;
                            System.out.println("index : "+ x+" "+i);
                        }
                        if (goY) {
                            algoritmA.add(2);
                        } else {
                            algoritmA.clear();
                            run = true;
                            break;
                        }
                    }
                }
                boolean goX = true;
                if (goY) { // Если вверх прошли то идём к игроку
                    if (pole.game.combo.character.x > x) {// Если игрок правее нас
                       System.out.println("Attack 2");
                        for (int i = x+1; i < pole.game.combo.character.x; i++) { // Проверяем мешает ли нам чтото справа
                            if (pole.objectInterupt(i, x, 0, 0)) {
                                goX = false;
                                System.out.println("Interupt X: "+ i+" "+y);
                            }
                            bu = pole.getObjectIndex(i, y);
                            if (bu == 8 || bu == 6 | bu == 7) {
                                System.out.println("index x : "+ i+" "+y);
                                goX = false;
                            }
                            if (goX) {
                                algoritmA.add(6);
                            } else {
                                algoritmA.clear();
                                break;
                            }
                        }
                    } else { // Если левее нас
                        System.out.println("Attack 2 1");
                        for (int i = x-1; i > pole.game.combo.character.x; i--) { // Проверяем мешает ли нам чтото слева
                            if (pole.objectInterupt(i, x, 0, 0)) {
                                goX = false;
                            }
                            bu = pole.getObjectIndex(i, y);
                            if (bu == 8 || bu == 6 | bu == 7) {
                                goX = false;
                            }
                            if (goX) {
                                algoritmA.add(4);
                            } else {
                                algoritmA.clear();
                                run = true;
                                break;

                            }
                        }
                    }
                }else{ //Если по вертикали не вышло
                    algoritmShield();
                }
                // Завершаем атаку ударом и выбором направления удара
                int naprav = (int) (Math.random());
                // Получаем изменение координат игрока
                buffX = x;
                buffY = y;
                for(int i = 0;i<algoritmA.size;i++){
                    if(algoritmA.get(i)==8){
                        y+=1;
                    }else  if(algoritmA.get(i)==2){
                        y-=1;
                    }else if(algoritmA.get(i)==6){
                        x+=1;
                    }else         if(algoritmA.get(i)==4){
                        x-=1;
                    }
                }
                //
                if (naprav == 0) {
                    if (pole.game.combo.character.y != y) { // Если не равен уходим
                        if(pole.game.combo.character.x!=x) {
                            if (pole.game.combo.character.y > y) {
                                algoritmA.add(8);
                                y+=1;
                            } else if (pole.game.combo.character.y < y) {
                                algoritmA.add(2);
                                y-=1;
                            }
                            if(pole.game.combo.character.x==x+1){
                                attackYes(6);
                            }else if(pole.game.combo.character.x==x-1){
                                attackYes(4);
                            }else{algoritmShield();}
                        }else{
                            if (pole.game.combo.character.y > y+1) {
                                algoritmA.add(8);
                                y+=1;
                            } else if (pole.game.combo.character.y < y-1) {
                                y-=1;
                                algoritmA.add(2);
                            }
                            if(pole.game.combo.character.y==y+1){
                                attackYes(8);
                            }else if(pole.game.combo.character.y == y-1){
                                attackYes(2);
                            }else{
                                algoritmShield();
                            }
                        }


                    } else { // Если равен бьём игрока
                        if (pole.game.combo.character.x == x + 1) { // Производим атаку
                            attackYes(6);
                        }else if(pole.game.combo.character.x==x-1){
                            attackYes(4);
                        }else{
                            algoritmShield();
                        }
                    }
                } else {
                    if (pole.game.combo.character.y != y) { // Если не равен уходим
                        if(pole.game.combo.character.x!=x){ // Тут решаенм есл и оба неравны
                            if(pole.game.combo.character.x>x){
                                    algoritmA.add(6);
                                    x+=1;
                            }else if(pole.game.combo.character.x<x){
                                algoritm.add(4);
                                x-=1;
                            }
                            if(y+1==pole.game.combo.character.y){
                                attackYes(8);
                            }else if(y-1==pole.game.combo.character.y){
                                attackYes(2);
                            }else{algoritmShield();}
                        }else{ // Если равен x
                            if(pole.game.combo.character.y==y+1){
                                attackYes(8);
                            }else if(pole.game.combo.character.y == y-1){
                                attackYes(2);
                            }
                        }
                    } else { // Если равен бьём игрока
                        if (pole.game.combo.character.x == x + 1 ) { // Производим атаку
                            attackYes(6);
                        }else if(pole.game.combo.character.x==x-1){
                            attackYes(4);
                        }else{
                            algoritmShield();
                        }
                    }
                }
                x = buffX;
                y = buffY;
            } else { // по вертикали
                boolean goX = true;
                int bu = 0;
                if(pole.game.combo.character.y>y) {
                    for (int i = x+1; i < pole.game.combo.character.x; i++) { // Проверяем мешает ли нам чтото сверху
                        if (pole.objectInterupt(i, y, 0, 0)) {
                            goX = false;
                        }
                        bu = pole.getObjectIndex(i, y);
                        if (bu == 8 || bu == 6 | bu == 7) {
                            goX = false;
                        }
                        if (goX) {
                            algoritmA.add(6);
                        } else {
                            algoritmA.clear();
                        }
                    }
                }else if(pole.game.combo.character.y<y) {
                    for (int i = x-1; i > pole.game.combo.character.x; i--) { // Проверяем мешает ли нам чтото сверху
                        if (pole.objectInterupt(i, y, 0, 0)) {
                            goX = false;
                        }
                        bu = pole.getObjectIndex(i, y);
                        if (bu == 8 || bu == 6 | bu == 7) {
                            goX = false;
                        }
                        if (goX) {
                            algoritmA.add(4);
                        } else {
                            algoritmA.clear();
                        }
                    }
                }
                boolean goY = true;
                if (goX) { // Если вверх прошли то идём к игроку
                    if (pole.game.combo.character.y > y) {// Если игрок правее нас
                        for (int i = y+1; i < pole.game.combo.character.y; i++) { // Проверяем мешает ли нам чтото справа
                            if (pole.objectInterupt(x, i, 0, 0)) {
                                goY = false;
                            }
                            bu = pole.getObjectIndex(x, i);
                            if (bu == 8 || bu == 6 | bu == 7) {
                                goY = false;
                            }
                            if (goY) {
                                algoritmA.add(8);
                            } else {
                                algoritmA.clear();
                            }
                        }
                    } else { // Если левее нас
                        for (int i = y-1; i > pole.game.combo.character.y; i--) { // Проверяем мешает ли нам чтото слева
                            if (pole.objectInterupt(x, i, 0, 0)) {
                                goY = false;
                            }
                            bu = pole.getObjectIndex(x, i);
                            if (bu == 8 || bu == 6 | bu == 7) {
                                goY = false;
                            }
                            if (goY) {
                                algoritmA.add(2);
                            } else {
                                algoritmA.clear();
                            }
                        }
                    }
                }else{ // Если по горизонтали не вышло
                algoritmShield();
                }
                // Завершаем атаку ударом и выбором направления удара
                int naprav = (int) (Math.random());
                if (naprav == 0) {
                    if (pole.game.combo.character.y != y) { // Если не равен уходим
                        if(pole.game.combo.character.x!=x) {
                            if (pole.game.combo.character.y > y) {
                                algoritmA.add(8);
                            } else if (pole.game.combo.character.y < y) {
                                algoritmA.add(2);
                            }
                            if(pole.game.combo.character.x==x+1){
                                attackYes(6);
                            }else if(pole.game.combo.character.x==x-1) {
                                attackYes(4);
                            }
                            else{
                                    algoritmShield();
                                }
                        }else{
                            if (pole.game.combo.character.y > y+1) {
                                algoritmA.add(8);
                            } else if (pole.game.combo.character.y < y-1) {
                                algoritmA.add(2);
                            }
                            if(pole.game.combo.character.y==y+1){
                                attackYes(8);
                            }else if(pole.game.combo.character.y == y-1){
                                attackYes(2);
                            }else{
                                algoritmShield();
                            }
                        }


                    } else { // Если равен бьём игрока
                        if (pole.game.combo.character.x == x + 1) { // Производим атаку
                            attackYes(6);
                        }else if(pole.game.combo.character.x==x-1){
                            attackYes(4);
                        }else{
                            algoritmShield();
                        }
                    }
                } else {
                    if (pole.game.combo.character.y != y) { // Если не равен уходим
                        if(pole.game.combo.character.x!=x){ // Тут решаенм есл и оба неравны
                            if(pole.game.combo.character.x>x){
                                algoritmA.add(6);
                            }else if(pole.game.combo.character.x<x){
                                algoritm.add(4);
                            }
                            if(y+1==pole.game.combo.character.y){
                                attackYes(8);
                            }else if(y-1==pole.game.combo.character.y){
                                attackYes(2);
                            } else{algoritmShield();}
                        }else{ // Если равен x
                            if(pole.game.combo.character.y==y+1){
                                attackYes(8);
                            }else if(pole.game.combo.character.y == y-1){
                                attackYes(2);
                            }
                        }
                    } else { // Если равен бьём игрока
                        if (pole.game.combo.character.x == x + 1) { // Производим атаку
                            attackYes(6);
                        }else if(pole.game.combo.character.x==x-1){
                            attackYes(4);
                        }else{
                            algoritmShield();
                        }
                    }
                }
            }
        }else { // Производим активацию алгоритма
            if (!bufferRun) {
                System.out.println("UpdateAttack");
                if (pole.game.combo.character.x == xBuffer && pole.game.combo.character.y == yBuffer) { // Алгоритм продолжает работать
                    int buffer = 0;
                    if(!algoritmA.isEmpty()) {
                         buffer = algoritmA.get(0);
                         algoritmA.removeIndex(0);
                    }else{
                        clearAttack();
                        return;
                    }
                    if (buffer == 8) {  // Доделать условия так как могут быть проблемы
                        System.out.println("8");
                        if(napr!=8){
                            rotation = 0;
                            napr = 8;
                            addAction(Actions.rotateTo(rotation,10/speed));
                        }
                        addAction(Actions.moveTo(pole.getCoordinates(x,y+1)[0],pole.getCoordinates(x,y+1)[1],10/speed));
                        xB=0;
                        yB = 1;
                        bufferRun = true;
                    }else if(buffer == 6){System.out.println("6");
                        if(napr!=6){
                            rotation = 270;
                            napr = 6;
                            addAction(Actions.rotateTo(rotation,10/speed));
                        }
                        addAction(Actions.moveTo(pole.getCoordinates(x+1,y)[0],pole.getCoordinates(x+1,y)[1],10/speed));
                        xB=1;
                        yB = 0;
                        bufferRun = true;
                    }else if(buffer == 4){System.out.println("4");
                        if(napr!=4){
                            rotation = 90;
                            napr = 4;
                            addAction(Actions.rotateTo(rotation,10/speed));
                        }
                        addAction(Actions.moveTo(pole.getCoordinates(x-1,y)[0],pole.getCoordinates(x-1,y)[1],10/speed));
                        xB=-1;
                        yB = 0;
                        bufferRun = true;
                    }else if(buffer == 2){System.out.println("2");
                        if(napr!=2){
                            rotation = 180;
                            napr = 2;
                            addAction(Actions.rotateTo(rotation,10/speed));
                        }
                        addAction(Actions.moveTo(pole.getCoordinates(x,y-1)[0],pole.getCoordinates(x,y-1)[1],10/speed));
                        xB=0;
                        yB = -1;
                        bufferRun = true;
                    }else if(buffer == 7){ System.out.println("7");
                    if(!algoritmA.isEmpty()) {
                        buffer = algoritmA.get(0);
                        algoritmA.removeIndex(0);
                        directionA = buffer;
                    }else{
                        if(pole.game.combo.character.x==x+1&&pole.game.combo.character.y==y){
                            buffer = 6;
                        }else if(pole.game.combo.character.x==x-1&&pole.game.combo.character.y==y){
                            buffer = 4;
                        }else if(pole.game.combo.character.x==x&&pole.game.combo.character.y==y+1){
                            buffer = 8;
                        }else if(pole.game.combo.character.x==x&&pole.game.combo.character.y==y-1){
                            buffer =2;
                        }
                    }
                        if(buffer==8){
                            onAnimation = true;
                            animation.restartAnim(8,napr,pole,x,y,30/speed);
                        }else
                            if(buffer == 6){
                                onAnimation = true;
                                animation.restartAnim(6,napr,pole,x,y,30/speed);
                            }else
                                if(buffer == 4){
                                    onAnimation = true;
                                    animation.restartAnim(4,napr,pole,x,y,30/speed);
                                }else
                                    if(buffer == 2){
                                        onAnimation = true;
                                        animation.restartAnim(2,napr,pole,x,y,30/speed);
                                    }
                    }


                } else { // Пересобираем алгоритм атаки
                    clearAttack();
                    algoritmAttack();
                }
            }else if(!hasActions()){
                bufferRun = false;
                x +=xB;
                y +=yB;
                xB = 0;
                yB = 0;

            }else if(pole.objectInterupt(x+xB,y+yB,0,0)){
                clearActions();
                addAction(Actions.moveTo(pole.getCoordinates(x,y)[0],pole.getCoordinates(x,y)[1],15/speed));
                clearAttack();
                xB = 0;
                yB = 0;
                createAlgoritm();
            }
        }
    }
    private void attackYes(int directionA){
        algoritmA.add(7);
        algoritmA.add(directionA);
        if(directionA==8&&napr==2||directionA==2&&napr==8||napr == 6&&directionA==4||napr == 4&&directionA==6){
            noAttaker = true;
        }else{noAttaker = false;}
    }
    private void clearAttack(){ // Чистим переменные атаки
        attackM = false;
        bufferRun = false; bufferoid = 0;
    }
    private void clearShield(){
        shieldM = false;
        bufferRun = false;
        shield = false;bufferoid = 0;
    }
    private void createAlgoritm(){
        clearAttack();
        clearShield();
        algo = false;
        if(bufferIntelect>intelect) {
            bufferIntelect = 0;
            if(Math.abs(pole.game.combo.character.x-x)<radius&&Math.abs(pole.game.combo.character.y-y)<radius) {
                if ((Math.abs(x - pole.game.combo.character.x) > 4) || (Math.abs(y - pole.game.combo.character.y) > 4)) {// Идти к игроку
                    run = true;
                } else {
                    if ((Math.abs(x - pole.game.combo.character.x) < 4) || (Math.abs(y - pole.game.combo.character.y) < 4)) { // Близко к игроку решать что делать
                        if (!(Math.abs(x - pole.game.combo.character.x) < 3) && !(Math.abs(y - pole.game.combo.character.y) < 3)) {
                            if ((int) (Math.random() * 5) == 1) { // Атаковать или защищаться
                                algoritmShield();
                            } else {
                                algoritmAttack();
                            }
                        } else { // Если игрок вплотную
                            if ((int) (Math.random() * 3) == 1) { // Атаковать или защищаться
                                algoritmShield();
                            } else {
                                algoritmAttack();
                            }
                        }
                    }
                }
            }
        }else{
            bufferIntelect+= Gdx.graphics.getDeltaTime();
        }
    }
    private boolean isAlgoritm(){ // Выполняет алгоритм также следит за особыми элементами
        if(run){ // алгоритм похода к игроку
            if((Math.abs(x-pole.game.combo.character.x)>3)||(Math.abs(y-pole.game.combo.character.y)>3)){
            algoritmRunP();
            }else{run = false;}
        }else
            if (attackM) { // При работе алгоритма атаки
                algoritmAttack();
                if(bufferoid>10){ // Время выполнения алгоритма                                                               %%%
                    clearAttack();
                }else{bufferoid += Gdx.graphics.getDeltaTime();}
            } else if(shieldM){
                algoritmShield();
                if(bufferoid>7){ // Время выполнения алгоритма                                                               %%%
                    clearShield();
                }else{bufferoid += Gdx.graphics.getDeltaTime();}
            }
            else if(trap){
                updateWithDoTrap();
            }
        else {
            createAlgoritm();
        }
        return false;
    }

    private void interuptMe(int direction){// При атаке по мне воспроизводим анимацию получения урона и регулируем алгоритм
             // Анимация попадания по игроку
            interuptMу.restartAnim(direction,napr,pole,x,y,10/speed);
            interuptMyOn = true;
        }

    private void clearAlgoritm(){ // Очищение алгоритма
        clearAttack();
        clearShield();
    }
    private int getObject(int x,int y){
      return pole.getObjectIndex(x,y);
    }
    @Override
    public void update(float delta) {
        System.out.println("x:"+x+" y:"+y+" r:"+rotation);
        if(!dead) {
            act(delta);
            if (onAnimation||interuptMyOn) { // При выполнении анимации
                if(shieldMyOn){
                    shieldMу.update(delta);
                    if (!shieldMу.isAnimateAttack()) {
                        onAnimation = false;
                        shieldMyOn=false;
                        clearShield();
                    }
                }else if(interuptMyOn){
                    interuptMу.update(delta);
                    if (!interuptMу.isAnimateAttack()) {
                        interuptMyOn=false;
                    }
                }else {
                    animation.update(delta);
                    if (!animation.isAnimateAttack()) {
                        if(directionA==8) {
                           if(pole.objectInterupt(x, y+1, 2, attack)){
                               if(Constants.music)
                                Sounds.soundA.play(1f);
                            }
                        }else if(directionA == 2){
                           if(pole.objectInterupt(x, y-1, 8, attack)){
                                Sounds.soundA.play(1f);
                            }
                        }else if(directionA == 4){
                            if(pole.objectInterupt(x-1, y, 6, attack)){
                                Sounds.soundA.play(1f);
                            }
                        }else if(directionA == 6){
                            if(pole.objectInterupt(x+1, y, 4, attack)){
                                Sounds.soundA.play(1f);
                            }
                        }
                        onAnimation = false;
                        clearAttack();
                    }
                }
            } else { // Когда нет анимации
                if (isAlgoritm()) {
                    clearAlgoritm();
                }
            }
        }
    }

    @Override
    public void dispose() {
        animation.dispose();
        bot.getTexture().dispose();
        botAttack.getTexture().dispose();
        noAttack.getTexture().dispose();
    }

    @Override
    public boolean interupt(int x, int y, int direction, float attack) {
        if(!dead) {
            if (this.x== x&&this.y == y) {
                if (attack > 0) {
                    if(!shield&direction != naprShiled) {
                        if (health - attack > 0) {
                            health -= attack;
                        } else {
                            if (deadOn) {
                                dead = true;
                            }
                        }
                        interuptMe(direction);
                    }else { // Тут проихосдит активация анимации защиты
                        // Анимация защиты
                        shieldMу.restartAnim(direction, napr, pole, x, y, 10 / speed);
                        if(Constants.music) {
                            Sounds.soundS.play(1.5f);
                        }
                        shieldMyOn = true;
                        onAnimation = true;
                        }

                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMe(int x, int y) {
        if(this.x == x&&this.y ==y ){
            return true;
        }else{return false;}
    }
}
