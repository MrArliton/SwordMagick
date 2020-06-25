package com.arliproduct.game;

import com.arliproduct.game.Elements.Group;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InputController  {
    Sprite test;
    float x;
    float y;
    Viewport view;

    public void updateCoordinate(Viewport view){
        this.view = view;
    }

    public void updateCoordinate(){
        if(Gdx.input.isTouched()){
            Vector3 vect = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
            // System.out.println("No"+x+" - "+y);
            view.unproject(vect);
            Constants.xTouch = vect.x;
            Constants.yTouch = vect.y;
            Constants.xDTouch = Gdx.input.getDeltaX();
            //System.out.println("yes"+Constants.xTouch+" - "+Constants.yTouch);
        }
    }
}
