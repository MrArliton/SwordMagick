package com.arliproduct.game.Elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Group { // Для облегчённого управления группами объектов на экране
    int otvetZap = 0;
    public abstract void render(SpriteBatch sb,float delta);
    public abstract void update(float delta);
    public abstract void dispose();
}
