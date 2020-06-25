package com.arliproduct.game.Elements;

import com.arliproduct.game.StateManager;
import com.arliproduct.game.States.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class LevelCreator {
    boolean boss=false;
    int level;
    StringBuilder file = new StringBuilder();
    public LevelCreator(){

    }

    public void reCreateLevel(int level){ // Пересоздаёт уровень
        if(level%5!=0){
            boss = false;
            file = new StringBuilder();
            // Формируем стены
            for(int i = 0;i<20;i++) {
             for (int o = 0;o<20;o++) {
                 if(i == 0 || o == 0 || i == 19 || o == 19) {
                     if((i==9&&o==0)||(i==10&o==0)){
                         file.append("Object;" + i + ";" + o + ";1;200;1;Door.png,");
                     }else {
                         file.append("Object;" + i + ";" + o + ";1;1000;0;NoTexture.png,");
                     }
                     }
                 }
            }
            // Стены сформированы
            // Формируем ловушки и монстра
            if(level>1&&level<10) { // Если лвл высокий
                // Обычные ловушки по середине
                int nabor = (int) (Math.random() * 4);
                if (nabor == 0) {
                    file.append("Trap;");
                    file.append("18;");
                    for (int i = 1; i < 19; i += 2) {
                        file.append(i + ";");
                        file.append("9;");
                    }
                    file.append(4 * (1+(int)(level / 5)) + ";");
                    file.append("Level1Trap.png;");
                    file.append("Level1TrapDown.png;");
                    file.append(0.2f + ",");
                    file.append("Trap;");
                    file.append("18;");
                    for (int i = 1; i < 19; i += 2) {
                        file.append(i + ";");
                        file.append("11;");
                    }
                    file.append(4 * (1+(int)(level / 5)) + ";");
                    file.append("Level1Trap.png;");
                    file.append("Level1TrapDown.png;");
                    file.append(0.2f + ",");
                } else if (nabor == 1) {
                    file.append("Trap;");
                    file.append("18;");
                    for (int i = 1; i < 19; i += 2) {
                        file.append(i + ";");
                        file.append("9;");
                    }
                    file.append(4 * (1+(int)(level / 5)) + ";");
                    file.append("Level1Trap.png;");
                    file.append("Level1TrapDown.png;");
                    file.append(0.2f + ",");
                    file.append("Trap;");
                    file.append("18;");
                    for (int i = 2; i < 19; i += 2) {
                        file.append(i + ";");
                        file.append("11;");
                    }
                    file.append(4 * (1+(int)(level / 5)) + ";");
                    file.append("Level1Trap.png;");
                    file.append("Level1TrapDown.png;");
                    file.append(0.2f + ",");
                } else if (nabor == 2) {
                    file.append("Trap;");
                    file.append("18;");
                    for (int i = 1; i < 19; i += 2) {
                        file.append(i + ";");
                        file.append("9;");
                    }
                    file.append(4 * (1+(int)(level / 5)) + ";");
                    file.append("Level1Trap.png;");
                    file.append("Level1TrapDown.png;");
                    file.append(0.2f + ",");
                    file.append("Trap;");
                    file.append("18;");
                    for (int i = 1; i < 19; i += 2) {
                        file.append(i + ";");
                        file.append("10;");
                    }
                    file.append(4 * (1+(int)(level / 5)) + ";");
                    file.append("Level1Trap.png;");
                    file.append("Level1TrapDown.png;");
                    file.append(0.2f + ",");
                } else if (nabor == 3) {
                    file.append("Trap;");
                    file.append("10;");                //
                    for (int i = 1; i < 6; i ++) {
                        file.append(i + ";");
                        file.append("9;");
                    }
                    file.append(4 * (1+(int)(level / 5)) + ";");
                    file.append("Level1Trap.png;");
                    file.append("Level1TrapDown.png;");
                    file.append(0.2f + ",");
                    file.append("Trap;");
                    file.append("10;");
                    for (int i = 18; i > 13; i --) {
                        file.append(i + ";");
                        file.append("9;");
                    }
                    file.append(4 * (1+(int)(level / 5)) + ";");
                    file.append("Level1Trap.png;");
                    file.append("Level1TrapDown.png;");
                    file.append(0.2f + ",");
                }
            }
            // пушки по краям
            if(level>5&&level<15){ // GunA.png texture
                int nabor = (int)(Math.random()*4);
                nabor = 2;
                if(nabor==0){
                    // 1
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1.2f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("2;");        // Направление
                    file.append("4;");         // x
                    file.append("18;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                // 2
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("2;");        // Направление
                    file.append("5;");         // x
                    file.append("18;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                    // 3
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1.3f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("2;");        // Направление
                    file.append("14;");         // x
                    file.append("18;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                    //4
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("2;");        // Направление
                    file.append("13;");         // x
                    file.append("18;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                }else if(nabor == 1){
                    // 1
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1.2f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("6;");        // Направление
                    file.append("1;");         // x
                    file.append("6;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                    // 2
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("6;");        // Направление
                    file.append("1;");         // x
                    file.append("7;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                    // 3
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1.3f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("4;");        // Направление
                    file.append("18;");         // x
                    file.append("13;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                    //4
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("4;");        // Направление
                    file.append("18;");         // x
                    file.append("14;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину

                }else if(nabor == 2){
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1.2f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("2;");        // Направление
                    file.append("4;");         // x
                    file.append("15;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                    // 2
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("6;");        // Направление
                    file.append("3;");         // x
                    file.append("14;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                    // 3
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1.3f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("8;");        // Направление
                    file.append("14;");         // x
                    file.append("3;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину
                    //4
                    file.append("Gun;");
                    file.append("GunA.png;");
                    file.append("1f;");                                                 // Скорость выстрела
                    file.append(1/(1.5+level/5)+";");                    // Скорость пули
                    file.append("4;");        // Направление
                    file.append("15;");         // x
                    file.append("4;");         // y
                    file.append(15*(level/5)+";"); // Жизни
                    file.append("1;"); // Смертность
                    file.append(4*((int)(level/5))+";"); // Урон
                    file.append("16;"); // Размер в длину фрейма
                    file.append("16,"); // Длину

                }

            }
            //
            //Смертельные ловушки
            if(level>3){
                int nabor = (int)(Math.random()*5);
                if(nabor!=4&&nabor!=5) {
                    file.append("Death;");
                }
                if(nabor == 0){
                    file.append("24;");
                    file.append("4;");
                    file.append("14;");
                    file.append("6;");
                    file.append("14;");
                    file.append("14;");
                    file.append("14;");
                    file.append("12;");
                    file.append("14;");
                    file.append("5;");
                    file.append("14;");
                    file.append("13;");
                    file.append("14;");
                    //
                    file.append("4;");
                    file.append("6;");
                    file.append("5;");
                    file.append("6;");
                    file.append("6;");
                    file.append("6;");
                    file.append("14;");
                    file.append("6;");
                    file.append("12;");
                    file.append("6;");
                    file.append("13;");
                    file.append("6;");
                    // Длина и высота текстуры
                    file.append("16;");
                    file.append("16;");
                    file.append("0.5f;"); // Скорость текстуры
                    // Текстуры
                    file.append("Death1.png,");
                    // конец
                }else if(nabor == 1){
                    file.append("24;");
                    file.append("4;");
                    file.append("14;");
                    file.append("4;");
                    file.append("15;");
                    file.append("4;");
                    file.append("16;");
                    file.append("15;");
                    file.append("15;");
                    file.append("15;");
                    file.append("16;");
                    file.append("15;");
                    file.append("14;");
                    //
                    file.append("4;");
                    file.append("6;");
                    file.append("5;");
                    file.append("6;");
                    file.append("6;");
                    file.append("6;");
                    file.append("15;");
                    file.append("6;");
                    file.append("13;");
                    file.append("6;");
                    file.append("14;");
                    file.append("6;");
                    // Длина и высота текстуры
                    file.append("16;");
                    file.append("16;");
                    file.append("0.5f;"); // Скорость текстуры
                    // Текстуры
                    file.append("Death1.png,");
                }else if(nabor == 2){
                    file.append("24;");
                    file.append("5;");
                    file.append("14;");
                    file.append("5;");
                    file.append("13;");
                    file.append("5;");
                    file.append("15;");
                    file.append("14;");
                    file.append("14;");
                    file.append("14;");
                    file.append("15;");
                    file.append("14;");
                    file.append("13;");
                    //
                    file.append("5;");
                    file.append("6;");
                    file.append("6;");
                    file.append("6;");
                    file.append("7;");
                    file.append("6;");
                    file.append("14;");
                    file.append("6;");
                    file.append("12;");
                    file.append("6;");
                    file.append("13;");
                    file.append("6;");
                    // Длина и высота текстуры
                    file.append("16;");
                    file.append("16;");
                    file.append("0.5f;"); // Скорость текстуры
                    // Текстуры
                    file.append("Death1.png,");
                    // конец
                }else if(nabor == 3){
                    file.append("24;");
                    file.append("4;");
                    file.append("14;");
                    file.append("6;");
                    file.append("14;");
                    file.append("8;");
                    file.append("14;");
                    file.append("10;");
                    file.append("14;");
                    file.append("12;");
                    file.append("14;");
                    file.append("14;");
                    file.append("14;");
                    //
                    file.append("4;");
                    file.append("6;");
                    file.append("6;");
                    file.append("6;");
                    file.append("8;");
                    file.append("6;");
                    file.append("10;");
                    file.append("6;");
                    file.append("12;");
                    file.append("6;");
                    file.append("14;");
                    file.append("6;");
                    // Длина и высота текстуры
                    file.append("16;");
                    file.append("16;");
                    file.append("0.5f;"); // Скорость текстуры
                    // Текстуры
                    file.append("Death1.png,");
                    // конец
                }
            }
            //
            // Рандомные стены по карте
            if(level<5) {
                for (int i = 0; i < 3; i++) {
                    file.append("Object;");
                    file.append(3 + (int) (Math.random() * 4) + ";");
                    file.append(12 + (int) (Math.random() * 4) + ";");
                    file.append(2 * (int) (1 + level / 5) + ";");
                    file.append(6 * (int) (1 + level / 5) + ";");
                    file.append("1;");
                    file.append("Stone1.png,");
                }
                for (int i = 0; i < 3; i++) {
                    file.append("Object;");
                    file.append(16 - (int) (Math.random() * 4) + ";");
                    file.append(12 + (int) (Math.random() * 4) + ";");
                    file.append(2 * (int) (1 + level / 5) + ";");
                    file.append(6 * (int) (1 + level / 5) + ";");
                    file.append("1;");
                    file.append("Stone1.png,");
                }
                for (int i = 0; i < 3; i++) {
                    file.append("Object;");
                    file.append(3 + (int) (Math.random() * 4) + ";");
                    file.append(3 + (int) (Math.random() * 4) + ";");
                    file.append(2 * (int) (1 + level / 5) + ";");
                    file.append(6 * (int) (1 + level / 5) + ";");
                    file.append("1;");
                    file.append("Stone1.png,");
                }
                for (int i = 0; i < 3; i++) {
                    file.append("Object;");
                    file.append(15 - (int) (Math.random() * 4) + ";");
                    file.append(3 + (int) (Math.random() * 4) + ";");
                    file.append(2 * (int) (1 + level / 5) + ";");
                    file.append(6 * (int) (1 + level / 5) + ";");
                    file.append("1;");
                    file.append("Stone1.png,");
                }
            }
            //
            // Монстр
            // Закоментированно отсутствие текстур
            file.append("BotObj;");
            file.append("81;");
            file.append("54;");
            file.append("BotsSprites/ForwardAttackAtlas.png;");
            file.append("BotsSprites/BackAttackAtlas.png;");
            file.append("BotsSprites/RAttackAtlas.png;");
            file.append("BotsSprites/LAttackAtlas.png;");
            file.append("BotsSprites/FShieldAtlas.png;");
            file.append("BotsSprites/BackAttackAtlas.png;");
            file.append("BotsSprites/RShieldAtlas.png;");
            file.append("BotsSprites/LShieldAtlas.png;");
            file.append("BotsSprites/FIAtlas.png;" );
            file.append("BotsSprites/BIAtlas.png;" );
            file.append("BotsSprites/RIAtlas.png;" );
            file.append("BotsSprites/LIAtlas.png;" );
            file.append("BotsSprites/Normal.png;" );
            file.append("BotsSprites/Attack.png;" );
            file.append("BotsSprites/NoAttack.png;" );
            file.append("10;" );
            file.append("1;" );
            file.append("8;" );
            file.append(9.5*(1+level/5)+";");
            file.append(10+4.5*(1+level/5)+";");
            file.append(4+2*(1+level/5)+";");
            file.append(1f+";"); // Intelect
            file.append(1+"");
            file.append(",");
            //
        }else{ // Если это уровень с боссом
            boss = true;
             file = new StringBuilder();
            // Формируем стены
            for(int i = 0;i<20;i++) {
                for (int o = 0;o<20;o++) {
                    if(i == 0 || o == 0 || i == 19 || o == 19) {
                        if((i==9&&o==0)||(i==10&o==0)){
                            file.append("Object;" + i + ";" + o + ";1;200;1;Door.png,");
                        }else {
                            file.append("Object;" + i + ";" + o + ";1;1000;0;NoTexture.png,");
                        }
                    }
                }
            }
            // Стены сформированы
            // Формируем босса
            // Для босса будет отдельная текстура по типу кольца над головой
            file.append("BotObj;" );
            file.append("81;");
            file.append("54;");
            file.append("BotsSprites/ForwardAttackAtlas.png;");
            file.append("BotsSprites/BackAttackAtlas.png;");
            file.append("BotsSprites/RAttackAtlas.png;");
            file.append("BotsSprites/LAttackAtlas.png;");
            file.append("BotsSprites/FShieldAtlas.png;");
            file.append("BotsSprites/BackAttackAtlas.png;");
            file.append("BotsSprites/RShieldAtlas.png;");
            file.append("BotsSprites/LShieldAtlas.png;");
            file.append("BotsSprites/FIAtlas.png;" );
            file.append("BotsSprites/BIAtlas.png;" );
            file.append("BotsSprites/RIAtlas.png;" );
            file.append("BotsSprites/LIAtlas.png;" );
            file.append("BotsSprites/Normal.png;" );
            file.append("BotsSprites/Attack.png;" );
            file.append("BotsSprites/NoAttack.png;" );
            file.append("10;");
            file.append("1;");
            file.append("6;");
            file.append(9*(level/5)+";");
            file.append(11+5*(level/5)+";");
            file.append(2.5f*(level/5)+";");
            file.append(0.8f+";");
            file.append(1+"");
            file.append(",");
            //Формирование завершенно

            //
        }
    }

    public Game  getGame(StateManager manager){ // Класс возвращает новый уровень если игрок идёт дальше
        return new Game(manager,(boss)? "UNDEGROUD/UNDEGROUND_BOSS.txt":"/UNDEGROUD/Standart.txt",file.toString(),"",(level==1)? true: false);
    }
}
