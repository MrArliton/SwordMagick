package com.arliproduct.game.Elements;

import com.arliproduct.game.Constants;
import com.arliproduct.game.Sounds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Button {
   public int x;
    public int y;
    private int Width;
    private int Heigth;
    private Texture up1;
    private Texture down1;
    private Sprite up;
    public boolean sound = true;
    private boolean overlay = false;
    private Sprite down;
    private Vector3 vector = new Vector3();
    private Camera camera;
    private boolean Ondown;
    private boolean downed;
    private boolean two = false;
    private boolean twoTexture;
    private boolean on = true;
    private boolean bufferBool;
    private boolean fast = true;
    private boolean noFast = false; // No usable
    private float duration = 0.7f;
    private float lastButton = 0;
    private boolean touched;
    private int xAC = 0;
    private int yAC = 0;
    private int hAC = 0;
    private int wAC = 0;
    private boolean onText;
    public int xText = 0;
    public int yText = 0;
    public float TextHeigth = 0;
    public float TextWidth = 0;
    public boolean centerText = true;
    public String textButton="";
    BitmapFont texter;
    public Button(int x, int y,int Width,int Heigth,Texture up, Texture down,boolean text, Camera camera) {
        this.x = x;
        this.y = y;
        this.up1 = up;
        this.down1 = down;
        this.up = new Sprite(up1);
        this.down = new Sprite(down1);
        this.Width = Width;
        this.Heigth =Heigth;
        this.camera =camera;
        twoTexture = true;
        onText = text;
    }
    public Button(int x, int y,int Width,int Heigth,boolean text, Texture button,Camera camera) {
        this.x = x;
        this.y = y;
        this.up1 = button;
        up = new Sprite(up1);
        this.Width = Width;
        this.Heigth =Heigth;
        this.camera =camera;
        twoTexture = false;
        onText = text;
    }
    public void setText(int x,int y,String text,float Width,float Heigth,BitmapFont texter){ // Устанавливаем текст относительно координат кнопки
        if(onText){
        this.texter =texter;
        textButton = text;
        yText = y;
        xText = x;
        TextWidth = Width;
        TextHeigth = Heigth;
    }}
    public void setAlpha(float alpha){ // Изменение прозрачности кнопки
        up.setAlpha(alpha);
        down.setAlpha(alpha);
    }
    public void setCoordinateActive(int x,int y,int width,int heigth){ // Редактирование координат нажатие на кнопку
        xAC = x;
        yAC = y;
        hAC = heigth;
        wAC = width;

    }
    public void setTextCenter(boolean centerText){
        this.centerText = centerText;
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
    private void drawText(Color color,SpriteBatch sb){ // Рисование кнопки
        if(onText&&texter!=null){
        texter.setColor(color);
        texter.getData().setScale(TextWidth,TextHeigth);
        texter.draw(sb,textButton,getCenter(x,y,getWidth(),getHeigth(),textButton.length())[0],getCenter(x,y,getWidth(),getHeigth(),textButton.length())[1]);
    }}
    public void setFast(boolean fast){
    this.fast = fast;
    } // С анимацией или без
    public boolean IsAnimation() {
        if (lastButton < duration && lastButton != 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean onClicked(){ // Проверяем нажата ли кнопка
        if(on){
            if(noFast){
            if(!IsAnimation()){
                noFast = false;
                return true;
            }
            }
            if(!Gdx.input.isTouched()){touched=false;}
        if(!touched&&Gdx.input.isTouched()){
            touched=true;
            if(!bufferBool){
            vector.set(Constants.xTouch,Constants.yTouch,0);}
            bufferBool = true;
            if(vector.x > x+xAC&& Width+x+wAC > vector.x&&vector.y > y+yAC&&Heigth+y+hAC>vector.y){
                if(sound&&Constants.music) {
                    Sounds.soundB.play(1f);
                }
                if(!downed){
                Ondown = true;}
                if(fast) {
                    return true;
                }else{noFast = true;}
            }else{return false;}
        }else{bufferBool = false;;return false;}
    }else{return false;}
    return false;}
    public void setClickable(boolean on){
        this.on = on;
    } // Можно ли кликать
    public void setDuration(float duration){ // Длительность анимации
        if(twoTexture){
            this.duration = duration;
        }
    }
    public void OnDown(boolean ondown){ // Переключение текстуры кнопки
        if(twoTexture) {
            two = ondown;
        }
    }
    public void setOnDown(boolean ondown,boolean overlay){ // Включение  функции без анимации
        if(twoTexture){
            this.downed = ondown;
            this.overlay = overlay;
            if(ondown == false){two = false;overlay = false;}
        }
    }
    public void draw(SpriteBatch sb,float delta) { // Рисуем кнопку
        if (twoTexture) {
            if (Ondown) {
                lastButton+=delta;
                if(lastButton>duration){
                    Ondown = false;
                    lastButton = 0;
                }
                sb.draw(down, x, y, Width, Heigth);
                drawText(Color.RED,sb);
            } else {
                if(two){
                    if(overlay){sb.draw(up,x,y,Width,Heigth);}
                    sb.draw(down,x,y,Width,Heigth);
                }else{
                sb.draw(up, x, y, Width, Heigth);
                    }
                drawText(Color.BLACK,sb);}
        } else {
            sb.draw(up, x, y, Width, Heigth);
            drawText(Color.BLACK,sb);
        }
    }
    // Гетеры
    public int getWidth(){return Width;}
    public int getHeigth(){return Heigth;}
    public int getX(){return x;}
    public int getY(){return y;}
    //
    public void dispose(){
        try{
            on = false;
            onText = false;
        up1.dispose();
        down1.dispose();
    }catch(Exception e){}}

}
