package com.arliproduct.game.Elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextView {
    private String buffer = "";
    BitmapFont texter;
    int x;
    int y;
    int yText;
    int xText;
    int Width;
    int Heigth;
    float textWidth;
    float textHeigth;
    Color color = Color.BLACK;
    Texture view;
    public TextView(int x, int y, int Width, int Heigth,float textWidth,float textHeigth, Texture view, BitmapFont texter){
    this.texter = texter;
    this.x = x;
    this.y = y;
    this.Width =Width;
    this.Heigth =Heigth;
    this.view =view;
    this.textWidth = textWidth;
    this.textHeigth =textHeigth;
    }
  //  private int[] getCenter(int x,int y,int width,int heigth,int size){

    //}
    public void setCoordinateText(int x,int y){
        xText = x;
        yText = y;
    }
    public void setColor(Color color){
        this.color =color;
    }
    public void print(String str){
        buffer+=str;
    }
    public void println(String str){
        str+="\n";
        buffer+=str;
    }
    public String getText(){return buffer;}
    public void clear(){
        buffer = "";
    }
    public void draw(SpriteBatch sb){
        if(view!=null) {
            sb.draw(view, x, y, Width, Heigth);
        }
        texter.setColor(color);
        texter.getData().setScale(textWidth,textHeigth);
        texter.draw(sb,buffer,x+xText,y+Heigth+yText);
    }
    public void dispose(){
        if(view!=null){view.dispose();}
    }
}
