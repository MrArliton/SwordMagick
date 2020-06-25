package com.arliproduct.game.States;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Button;
import com.arliproduct.game.Elements.LineActive;
import com.arliproduct.game.State;
import com.arliproduct.game.StateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class LanguageChose extends State {
    private Texture fone;
    private Sprite foneS;
    private float foneStart = 0;
    boolean end;
    Button apply;
    Button english;
    Button russian;
    Array<Button> buttons;
    public LanguageChose(StateManager manager) {
        super(manager);
        buttons = new Array<Button>();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        fone = new Texture("All/Fone.png");
        foneS = new Sprite(fone);

        apply = new Button(Constants.WIDTH / 2 - 62*4/2, Constants.HEIGTH / 4 - 32*4/2, 62*4, 32*4, new Texture("All/apply.png"), new Texture("All/applyDown.png"),false, camera);
        apply.setFast(false);
        apply.setDuration(0.5f);

        english = new Button(apply.getX()-72,apply.getY()+Constants.HEIGTH/6,62*3,32*3,new Texture("All/Button1.png"),new Texture("All/Button1Down.png"),true,camera);
        english.setFast(false);
        english.setDuration(0.5f);
        english.setText(0,10,"English",0.8f,0.8f,texter);
        english.setOnDown(true,false);

        russian = new Button(english.getX()+20+62*3,apply.getY()+Constants.HEIGTH/6,62*3,32*3,new Texture("All/Button1.png"),new Texture("All/Button1Down.png"),true,camera);
        russian.setFast(false);
        russian.setDuration(0.5f);
        russian.setText(0,10,"Русский",0.85f,0.85f,texter);
        russian.setOnDown(true,false);
        buttons.add(english);
        buttons.add(russian);
        Texture[] text = new Texture[2];
        text[0]= new Texture("StartMenu/Settings/ButtonOkey.png");
        text[1] = new Texture("StartMenu/Settings/ButtonOkeyActive.png");
    }

    @Override
    public void update(float delta) {
        if(apply.onClicked()){ // Кнопка Apply
            end = true;
        }
        if(russian.onClicked()){
        russian.OnDown(true);
        offDownButtons(russian);
        Constants.lang.language = 1;
        }
        if(english.onClicked()){
        english.OnDown(true);
        offDownButtons(english);
        Constants.lang.language = 0;
        }
    }
    private void offDownButtons(Button buff){
        for(int i = 0;i<buttons.size;i++){
            if(!buttons.get(i).equals(buff)){
                buttons.get(i).OnDown(false);
            }
        }
    }
    @Override
    public void render(SpriteBatch sb) {
        if(!end) // Создаём плавное убывание
        {
            sb.setProjectionMatrix(camera.combined);
            camera.update();
            if (StartFone(sb)) { // Создаём плавное появление
                sb.draw(foneS, 0, 0, Constants.WIDTH, Constants.HEIGTH);
                apply.draw(sb, Gdx.graphics.getDeltaTime());
                english.draw(sb,Gdx.graphics.getDeltaTime());
                russian.draw(sb,Gdx.graphics.getDeltaTime());
            }
        }else
        if(EndFone(sb)){
            manager.set(new StartMenu(manager));
        }
    }

    @Override
    public void dispose() {
        texter.dispose();
        apply.dispose();
        english.dispose();
        russian.dispose();
    }
    private boolean StartFone(SpriteBatch sb){
        foneS.setAlpha(foneStart);
        foneS.setBounds(0,0,Constants.WIDTH,Constants.HEIGTH);
        if(foneStart>=0.95f){
            return true;
        }
        foneStart+=Gdx.graphics.getDeltaTime();
        foneS.draw(sb);
        return false;
    }
    private boolean EndFone(SpriteBatch sb){
        foneS.setAlpha(foneStart);
        if(foneStart<0.1f){
            return true;
        }
        foneStart-=Gdx.graphics.getDeltaTime();
        foneS.draw(sb);
        return false;
    }
}
