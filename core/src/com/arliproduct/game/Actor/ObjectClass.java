package com.arliproduct.game.Actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ObjectClass extends Actor {
    public int index = 0;
    public boolean dead = false;
    public abstract void render(Batch sb,float delta);
    public abstract  void update(float delta);
    public abstract void dispose();
    public abstract boolean interupt(int x,int y,int direction,float attack);
    public abstract boolean isMe(int x,int y);
}
