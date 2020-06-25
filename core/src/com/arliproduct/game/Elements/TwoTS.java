package com.arliproduct.game.Elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TwoTS{
    Sprite one;
    Sprite two;
    int x;
    int y;
    int w;
    int h;
    boolean mode = false;

    public TwoTS(Sprite one, Sprite two, int x, int y, int w, int h) {
        this.one = one;
        this.two = two;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(SpriteBatch sb){
        if(!mode){
        one.setBounds(x,y,w,h);
        one.draw(sb);
        }else {
            two.setBounds(x, y, w, h);
            two.draw(sb);
        }
    }
    public void set(boolean mode){
        this.mode = mode;
    }
}
