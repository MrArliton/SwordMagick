package com.arliproduct.game.Elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Line {
private String text;
private BitmapFont texter;
private Sprite fone;
private int x;
private int y;
private int xt;
private int yt;
private int w;
private int h;
private float sizet;
private Color colorT;

    public Line(String text, BitmapFont texter, Sprite fone, int x, int y, int xt, int yt, int w, int h, float sizet) {
        this.text = text;
        this.texter = texter;
        this.fone = fone;
        this.x = x;
        this.y = y;
        this.xt = xt;
        this.yt = yt;
        this.w = w;
        this.h = h;
        this.sizet = sizet;
    }
    public void setText(String str){
        text = str;
    }
    public void setTextColor(Color a){
        colorT = a;
    }
    public void setTextSize(){}
    public void setXYT(int x,int y){
            xt = x;
            yt = y;
    }
    public void draw(SpriteBatch sb){
        fone.setBounds(x,y,w,h);
        fone.draw(sb);
        texter.getData().setScale(sizet);
        texter.setColor(colorT);
        texter.draw(sb,text,x+xt,y+yt);
    }
}
