package com.arliproduct.game.Elements;

import com.arliproduct.game.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class LineActive {

    Texture t1;
    Texture t2;
    Sprite s1;
    Sprite s2;
    int x;
    int y;
    int xText;
    int yText;
    int width;
    int heigth;
    float duration = 1f;
    float buffer = 0;
    BitmapFont texter;
    boolean touched = false;
    boolean active=false;
    boolean on = true;
    public boolean down= true;
    float delta;
    String text;
    float tSize;
    Vector3 vector = new Vector3();

    boolean buffer1 = true;
    public LineActive(Texture t1, Texture t2, int x, int y, int width, int heigth, BitmapFont texter) {
        this.t1 = t1;
        this.t2 = t2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.texter = texter;
        s1 = new Sprite(t1);
        s2 = new Sprite(t2);
        s1.setBounds(x,y,width,heigth);
        s2.setBounds(x,y,width,heigth);
    }
    public void setDowned(boolean down){
        this.down = down;
        if(down){s2.setAlpha(1);}
        else{s2.setAlpha(0);}
    }
    public boolean IsAnimation(){
        if(active){
            if(buffer<duration-0.08f){
                if(buffer1) // Меняем прозрачность первой
                {
                 if(0.5>0.8-buffer){buffer1=false;}
                    s1.setAlpha(1 - buffer*2);
                }
                else
                    s1.setAlpha(buffer);

                if(!down)
                s2.setAlpha(buffer);
                else
                    s2.setAlpha(1-buffer);
                buffer+=delta/duration;
            }else{buffer = 0;active = false;buffer1 = true;
            if(down){down = false;}else{down = true;}}
            return false;
        }

        return true;
    }
    public void setText(int x,int y,String text,float size){
        xText = x;
        yText = y;
        this.text =text;
        tSize = size;
        texter.getData().setScale(size);
    }
    public boolean onClicked(){
        if(on){
                if(!IsAnimation()){
                    return true;
                }
            if(!Gdx.input.isTouched()){touched=false;}
            if(!touched&&Gdx.input.isTouched()){
                touched=true;
                    vector.set(Constants.xTouch,Constants.yTouch,0);
                if(vector.x > x&& width+x > vector.x&&vector.y > y&&heigth+y>vector.y){
                    active = true;
                        return true;
                }else{return false;}
            }else{return false;}
        }else{return false;}
    }
    public void draw(SpriteBatch sb,float delta){
        this.delta =delta;
        s1.draw(sb);
        s2.draw(sb);
        drawText(sb);
    }
    public void drawText(SpriteBatch sb){
        texter.getData().setScale(tSize);
        texter.draw(sb,Constants.lang.getText(text),getCenter(x,y,width,heigth,4)[0]+xText,getCenter(x,y,width,heigth,4)[1]+yText);
    }
    public void dispose(){
        t1.dispose();
        t2.dispose();
    }
    private int[] getCenter(int x,int y,int width,int heigth,int size){
        x = (2*x+width)/2;
        y = (2*y+heigth)/2;
        x-=size*6;
        int[] otvet = new int[2];
        otvet[0] = x+xText;
        otvet[1] = y+yText;
        return otvet;
    }

}
