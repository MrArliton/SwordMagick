package com.arliproduct.game.Actor;

import com.arliproduct.game.States.Groups.GroupsGame.Pole;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
    Sprite sp;
    public Bullet(Sprite sp, int x, int y, Pole pole,float rotation) {
        this.sp = sp;
       setOrigin(getWidth(),getHeight());
        setX(pole.getCoordinates(x,y)[0]);
        setY(pole.getCoordinates(x,y)[1]);
        setWidth(pole.getCoordinates(x,y)[2]);
        setHeight(pole.getCoordinates(x,y)[3]);
        setRotation(rotation);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sp.setRotation(getRotation());
    sp.setBounds(getX(),getY(),getWidth(),getHeight());
    sp.draw(batch);
    }
}
