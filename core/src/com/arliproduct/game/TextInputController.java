package com.arliproduct.game;

import com.badlogic.gdx.Input;

public class TextInputController implements Input.TextInputListener {
    String buffer = "";
    boolean get = false;
    boolean update = false;
    @Override
    public void input(String text) {
        buffer = text;
        get = true;
        update = true;
    }

    @Override
    public void canceled() {
        get = false;
        update = true;
    }
    public boolean getUpdate(){
        if(update) {
            update = false;
            return true;
        }else{return false;}
    }
    public boolean getResult(){return get;}
    public String getText(){return buffer;}
}
