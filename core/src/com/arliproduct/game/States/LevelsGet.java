package com.arliproduct.game.States;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Player.Character;
import com.arliproduct.game.State;
import com.arliproduct.game.StateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LevelsGet extends State {
    Button exit;
    TextureRegion[] yroven;
    Sprite[] sprites;
    Sprite fone;
    int yrovni= Constants.yrovni;
    int max = 590*(yrovni-1);
    int min = 700;
    int x;
    int y;
    float buffer=0;
    boolean end = false;
    int next = 0;
    boolean endFinal = false;
    float buffer1 = 0;
    public LevelsGet(StateManager manager) {
        super(manager);
        fone = new Sprite(new Texture("LevelsGet/Fone.png"));
        yroven = new TextureRegion(new Texture("LevelsGet/LevelsAtlas.png")).split(15,18)[0];
        sprites = new Sprite[yroven.length];
        for(int i=0;i<yroven.length;i++){
            sprites[i]=new Sprite(yroven[i]);
        }
        loadResource();
    }
    public void loadResource(){
        exit = new Button(Constants.WIDTH-90,Constants.HEIGTH-90,70,70,new Texture("LevelsGet/exitButton.png"),new Texture("LevelsGet/exitButtonDown.png"),false,camera);
        exit.setFast(false);
        exit.setDuration(0.3f);
    }
    @Override
    public void update(float delta) {
        if(exit.onClicked()){
            end = true;
            next = -1;
        }
        if(endFinal){
            if(next>-1){
                if(next==1){
                    Character.activeMinus();
                }
                if(next == 0){
                manager.LevelsC.reCreateLevel(1);
                Constants.level = 0;
                Constants.money = 0;
                Constants.exp = 0;
            manager.set(manager.LevelsC.getGame(manager));}
            else if(next == 1){
                    manager.set(new Game(manager,"Level1.txt","Objects1.txt",false));
                }
            }else{
                manager.set(new StartMenu(manager));
            }

        }
        setCamera(delta);
    }

    private void setCamera(float delta){
        if(Gdx.input.isTouched()){
            buffer+=delta;
            System.out.println(buffer);
        if(buffer>0.15){
                        if(camera.position.x+-1*Constants.xDTouch<max&&camera.position.x+-1*Constants.xDTouch>min) {
                            camera.position.x = camera.position.x+-1*Constants.xDTouch;
                            exit.x = (int)(Constants.WIDTH*1.5f)-(Constants.WIDTH-(int)camera.position.x)-90;
                        }else{if(camera.position.x+-1*Constants.xDTouch<max){camera.position.x = 701;}else{camera.position.x=max-1;}}
                }
        }else{
            if(buffer>0&&0.15>buffer){System.out.println("click");
                click();
            }
            buffer = 0;

        }
    }
    private void click(){
        int x=-500;
        for(int i = 0 ;i<yrovni;i++){
            x+=590;
            if((Constants.xTouch > x)&&(Constants.yTouch>60)&&(Constants.xTouch < x+490)&&(Constants.yTouch<600)){
                next = i;
                end = true;
            }
        }

    }
    float foneStart = 1;
    private boolean EndFone(SpriteBatch sb,float delta){ // Уходит фон
        if(foneStart<0.1f){
            return true;
        }
        foneStart = foneStart-delta*2;
        return false;
    }
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        int x = -500;
        fone.setAlpha(foneStart);
        fone.setBounds(0,0,Constants.WIDTH*yrovni/2,Constants.HEIGTH);
        fone.draw(sb);
        for(int i=0;i<yrovni;i++){
        sprites[i].setAlpha(foneStart);
        sprites[i].setBounds(x+=590,60,490,600);
        sprites[i].draw(sb);
        }
        if(!end) {
            exit.draw(sb, Gdx.graphics.getDeltaTime());
            camera.update();
        }else{
            if(EndFone(sb,Gdx.graphics.getDeltaTime())){
                endFinal = true;
            }

        }
    }

    private void getCoordinateClick(){

    }
    @Override
    public void dispose() {

    }
}
