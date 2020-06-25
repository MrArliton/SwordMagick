package com.arliproduct.game;

public class Language {
    public int language;
    public String getText(String text){
        if(text.equalsIgnoreCase("start")){ // Start
            if(language == 0){
                text = "Start";
            }else if(language == 1){
                text = "Играть";
            }else{text = "";}
        } //
        else  if(text.equalsIgnoreCase("developers")){ // Developers
            if(language == 0){
                text = "About Developers.";
            }else if(language == 1){
                text = "О разработчиках.";
            }else{text = "";}
        } //
        else  if(text.equalsIgnoreCase("editinventory")){ // Developers
            if(language == 0){
                text = "Inventory.";
            }else if(language == 1){
                text = "Инвентарь.";
            }else{text = "";}
        }
        else  if(text.equalsIgnoreCase("back")){ // Developers
            if(language == 0){
                text = "Back in the game";
            }else if(language == 1){
                text = "Вернуться в игру";
            }else{text = "";}
        }
        else  if(text.equalsIgnoreCase("Quit")){ // Developers
            if(language == 0){
                text = "Exit the menu";
            }else if(language == 1){
                text = "Выйти в меню";
            }else{text = "";}
        }
        else  if(text.equalsIgnoreCase("QuitWarning")){ // Developers
            if(language == 0){
                text = "Want go the menu?\nYou lose progress!";
            }else if(language == 1){
                text = "Вы хотите выйти в меню?\nВы потеряете прогресс!";
            }else{text = "";}
        }
        else  if(text.equalsIgnoreCase("yes")){ // Developers
            if(language == 0){
                text = "Yes";
            }else if(language == 1){
                text = "Да";
            }else{text = "";}
        }
        else  if(text.equalsIgnoreCase("no")){ // Developers
            if(language == 0){
                text = "Not";
            }else if(language == 1){
                text = "Нет";
            }else{text = "";}
        }
        else  if(text.equalsIgnoreCase("restartGame")){ // Developers
            if(language == 0){
                text = "Restart";
            }else if(language == 1){
                text = "Заново";
            }else{text = "";}
        }
        else  if(text.equalsIgnoreCase("sound")){ // Developers
            if(language == 0){
                text = "Sound";
            }else if(language == 1){
                text = "Звук";
            }else{text = "";}
        }
        else  if(text.equalsIgnoreCase("test")){ // Developers
            if(language == 0){
                text = "TestTest";
            }else if(language == 1){
                text = "ТестТест";
            }else{text = "";}
        }else if(text.equalsIgnoreCase("clear")){
            if(language == 0){
                text = "Clear";
            }else if(language == 1){
                text = "Стереть";
            }else{text = "";}
        }else if(text.equalsIgnoreCase("activate")){
            if(language == 0){
                text = "Activate";
            }else if(language == 1){
                text = "Активация";
            }else{text = "";}
        }else if(text.equalsIgnoreCase("advancement")){
            if(language == 0){
                text = "Advancement";
            }else if(language==1){
                text = "Улучшения";
            }
        }else if(text.equalsIgnoreCase("SkillWarning")){
            if(language == 0){
                text = "Produce gain?";
            }else if(language == 1){
                text = "Произвести усиление?";
            }
        }else if(text.equalsIgnoreCase("level")){
            if(language==0){
                text = "Level:";
            }else{
                text = "Уровень:";
            }
        }else if(text.equalsIgnoreCase("name")){
            if(language==0){
                text="Name.";
            }else if(language == 1){
                text = "Имя.";
            }
        }else if(text.equalsIgnoreCase("getName")){
            if(language == 0){
                text="Enter your name.";
            }else if(language == 1){
                text ="Введите ваше имя.";
            }
        }else if(text.equalsIgnoreCase("levels")){
            if(language == 0){
                text="Levels";
            }else if(language == 1){
                text = "Уровни";
            }
        }else if(text.equalsIgnoreCase("causedamage")){
            if(language == 0){
                text="Caused damage";
            }else if(language == 1){
                text = "Нанесено урона";
            }
        }else if(text.equalsIgnoreCase("recieveddamage")){
            if(language == 0){
                text="Recieved damage";
            }else if(language == 1){
                text = "Получено урона";
            }
        }else if(text.equalsIgnoreCase("passedcells")){
            if(language == 0){
                text="Passed cells";
            }else if(language == 1){
                text = "Пройдено клеток";
            }
        }else if(text.equalsIgnoreCase("Blockeddamage")){
        if(language == 0){
            text="Blocked damage";
        }else if(language == 1){
            text = "Блокированный урон";
        }
    }else if(text.equalsIgnoreCase("maxLevel")){
            if(language == 0){
                text="Maximum level";
            }else if(language == 1){
                text = "Наибольший уровень";
            }
        }else if(text.equalsIgnoreCase("maxExp")){
            if(language == 0){
                text="Maximum experience";
            }else if(language == 1){
                text = "Наибольший опыт";
            }
        }else if(text.equalsIgnoreCase("maxMoney")){
            if(language == 0){
                text="Maximum money";
            }else if(language == 1){
                text = "Наибольшее денег";
            }
        }else if(text.equalsIgnoreCase("buy")){
            if(language == 0){
                text = "Purchase";
            }else if(language == 1){
                text = "Купить";
            }
        }else if(text.equalsIgnoreCase("clearB")){
            if(language == 0){
                text = "Clear Effect";
            }else if(language == 1){
                text = "Очистить эфекты";
            }
        }else if(text.equalsIgnoreCase("okey")){
            if(language == 0){
                text = "Okey";
            }else if(language == 1){
                text = "Хорошо";
            }
        }else if(text.equalsIgnoreCase("buy?")){
            if(language == 0){
                text = "          Are you sure you\n           want to buy?";
            }else if(language == 1){
                text = "            Вы уверенны,\nчто хотите купить усиление?";
            }
        }else if(text.equalsIgnoreCase("errorYesInv")){
            if(language == 0){
                text = "    You already have\n       the gain you chose!";
            }else if(language == 1){
                text = "У вас уже имеется усиление,\n      которое вы выбрали!";
            }
        }else if(text.equalsIgnoreCase("errorNoMoney")){
            if(language == 0){
                text = "You don't have enough money.\n    Get coins in the dungeon.";
            }else if(language == 1){
                text = "У вас недостаточно монет.\n       Добудьте монеты\n         в подземелье.";
            }
        }







        else if(text.equalsIgnoreCase("ForCreator")){
            if(language == 0){
                text = "The project is under development.\n" +
                        "Since I develop the game alone \n" +
                        "and for the first time,\nI really hope for your support\n https://vk.com/mrarliton\nAlpha 1";
            }else if (language == 1){
                text = "Проэкт на стадии разработки.\nТак как я разрабатываю игру в одиночку \n и впервые,очень надеюсь на вашу поддержку. \n https://vk.com/mrarliton \n Alpha 1";
            }
        }
        return text;
    }
    public String getText1(String str){
        String text= str;
        if(text.equalsIgnoreCase("CLRE")){
            if(language == 0){
                text = "Do you want to clear the gain?";
            }else if(language == 1){
                text = "Вы хотите очистить усиления?";
            }
        }else if(text.equalsIgnoreCase("NoOptBuy")){
            if(language == 0){
                text = "            You didn't pick\n               any power.";
            }else if(language == 1){
                text = "            Вы не выбрали \n       ни одного усиления.";
            }
        }else if(text.equalsIgnoreCase("dead")){
            if(language == 0){
                text = "    You dead.";
            }else if(language == 1){
                text = "     Вы погибли.";
            }
        }else if(text.equalsIgnoreCase("go menu")){
            if(language == 0){
                text = "Go the menu?.";
            }else if(language == 1){
                text = "Вернуться в меню?.";
            }
        }else if(text.equalsIgnoreCase("language")){
            if(language == 0){
                text = "English";
            }else if(language == 1){
                text = "Russia";
            }
        }
        return text;
    }
}
