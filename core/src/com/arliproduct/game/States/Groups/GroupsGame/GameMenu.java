package com.arliproduct.game.States.Groups.GroupsGame;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.States.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu extends Group {
    public boolean end = false;
    public int nextWindow = 0;
    Texture fone;
    Sprite foneS;
    Button exitMenu;
    Button exitGame;
    Button restart;
    boolean lock = false;
    float foneStart = 0;
    int x = Constants.WIDTH/2-175;
    int y = Constants.HEIGTH-600-(Constants.HEIGTH-600)/2;
    int heigth = 600;
    int width = 350;
    Game game;
    public GameMenu(Camera camera, BitmapFont texter,Game game) {
        this.game = game;
        fone = new Texture("Game/GameMenu/Fone.png");
        foneS = new Sprite(fone);
        exitMenu = new Button((x+width/2)-125,y+(heigth)/3-100,250,100,new Texture("Game/GameMenu/Button.png"),new Texture("Game/GameMenu/ButtonDown.png"),true,camera);
        exitMenu.setFast(false);
        exitMenu.setText(0,5,Constants.lang.getText("back"),0.8f,0.8f,texter);
        exitMenu.setDuration(0.2f);

        exitGame = new Button((x+width/2)-125,y+(heigth)/2-50,250,100,new Texture("Game/GameMenu/Button.png"),new Texture("Game/GameMenu/ButtonDown.png"),true,camera);
        exitGame.setFast(false);
        exitGame.setText(0,5,Constants.lang.getText("Quit"),0.8f,0.8f,texter);
        exitGame.setDuration(0.2f);
        // Кнопку рестарт доработать после реализации уровней
        restart= new Button((x+width/2)-125,y+(heigth)/2+100,250,100,new Texture("Game/GameMenu/Button.png"),new Texture("Game/GameMenu/ButtonDown.png"),true,camera);
        restart.setFast(false);
        restart.setText(0,5,Constants.lang.getText("restartGame"),0.8f,0.8f,texter);
        restart.setDuration(0.2f);
    }

    public boolean isClose(){
        if(end){foneStart = 0;}
        return end;
    }

    @Override
    public void render(SpriteBatch sb, float delta) {
        if(StartFone(sb)){
            foneS.draw(sb);
            exitMenu.draw(sb,delta);
            exitGame.draw(sb,delta);
            restart.draw(sb,delta);
        }
    }

    @Override
    public void update(float delta) {
        if(exitMenu.onClicked()){
            end = true;
        }
        if(exitGame.onClicked()){
            nextWindow = 2;
            end = true;
        }
        if(restart.onClicked()){ // Перенастроить при добавлении уровней
            if(game.onerS) {
                game.manager.LevelsC.reCreateLevel(1);
                Constants.level = 1;
                Constants.exp = 0;
                Constants.money = 0;
                game.manager.set(game.manager.LevelsC.getGame(game.stateManager));
            }else{game.manager.set(new Game(game.manager,"Level1.txt","Objects1.txt",false));}
            }
    }

    @Override
    public void dispose() {
        fone.dispose();
        exitGame.dispose();
        exitMenu.dispose();
    }
    private boolean StartFone(SpriteBatch sb){
        foneS.setAlpha(foneStart);
        foneS.setBounds(x,y,width,heigth);
        if(foneStart>=0.95||lock){
            return true;
        }
        foneStart+= Gdx.graphics.getDeltaTime()*1.8f;
        foneS.draw(sb);
        return false;
    }
}
