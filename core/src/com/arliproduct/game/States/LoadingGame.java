package com.arliproduct.game.States;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Language;
import com.arliproduct.game.State;
import com.arliproduct.game.StateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingGame extends State {
    float i = 0f;
    Texture logoFone;
    Sprite logoFoneImage;
    ParticleEffect axeEffect;
    float o = 1f;
    public LoadingGame(StateManager manager) {
    super(manager);
        // Получение ресурсов
        logoFone = new Texture("LoadingGame/Fone.png");
        axeEffect = new ParticleEffect();
        axeEffect.load(Gdx.files.internal("LoadingGame/AxeEffectLogo"),Gdx.files.internal("LoadingGame"));
        logoFoneImage = new Sprite(logoFone);
        logoFoneImage.setBounds(0,0,Constants.WIDTH,Constants.HEIGTH);
    }

    @Override
    public void update(float delta) { // Обновляем эффект
        axeEffect.setPosition(Constants.WIDTH/2,Constants.HEIGTH/1.5f);
        axeEffect.setDuration(3);
        axeEffect.update(Gdx.graphics.getDeltaTime());
        axeEffect.start();
    }

    @Override
    public void render(SpriteBatch sb) { // рендерим окно загрузки
        sb.setProjectionMatrix(camera.combined);
        if(i<Constants.LogoFoneDuration) {
            i += Gdx.graphics.getDeltaTime();
            logoFoneImage.draw(sb);
            logoFoneImage.setAlpha(o);
            if(i+0.5>Constants.LogoFoneDuration&&o>0){
                o-=Gdx.graphics.getDeltaTime()*2;
            }
            if(o>0.2f) {
                axeEffect.draw(sb, Gdx.graphics.getDeltaTime());
            }
            }else{
            if(!Constants.setLanguage){
            manager.set(new StartMenu(manager));
        }else{manager.set(new StartMenu(manager));}}
        }

    @Override
    public void dispose() {
    logoFone.dispose();
    axeEffect.dispose();
    texter.dispose();
    }
}
