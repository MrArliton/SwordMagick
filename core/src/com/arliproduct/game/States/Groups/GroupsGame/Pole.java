package com.arliproduct.game.States.Groups.GroupsGame;

import com.arliproduct.game.Actor.Character;
import com.arliproduct.game.Actor.Object;
import com.arliproduct.game.Constants;
import com.arliproduct.game.Elements.Group;
import com.arliproduct.game.States.Game;
import com.arliproduct.game.States.StartMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.files.FileHandle;


public class Pole extends Group {
    int size1;
    int size2;
    int xP;
    int yP;
    int wP;
    int hP;
    public final int maxy = 20;
   public final int maxx = 20;
    float alpha=1;
    Texture rama;
    TextureRegion[][] texturesMap;
    Sprite[] sprites;
    Array<Object> objects;
    int[][] pole;
    String pathTexture = "Game/Pole/MapTextures.png";
    int wi = 6;
    int he = 6;
    public Game game;
    Array<String> colors = new Array<String>();
    public Pole(Camera camera, int[][] pole , int mode, String pathMap, Game game){
        this.game = game;
        objects = new Array<Object>();
      xP = 20;
      yP = 20;
      hP = Constants.HEIGTH-20;
      wP = Constants.WIDTH/2;
      this.pole  = pole;
      size1 = pole.length;
      size2 = pole[0].length;
      loadPole(pathMap);
      createPole();
    }
    private void loadPole(String path){
        if(!Gdx.files.external("Game/MapLevels/"+path).exists()) {
            FileHandle fileMap = Gdx.files.internal("Game/MapLevels/" + path);
            String[] buffer1 = fileMap.readString().split("!")[0].split(":");
            pathTexture = "Game/Pole/"+buffer1[0];
            wi = Integer.parseInt(buffer1[1].trim());
            he = Integer.parseInt(buffer1[2].trim());
            buffer1 = fileMap.readString().split("!")[1].split(":");
            int biffer = 0;
            for(int i=0;i<20;i++){
                for(int o = 0;o<20;o++){
                    pole[i][o] = Integer.parseInt(buffer1[i*20+o]);
                }
            }
            System.out.println("PoleCreated");
        }else{game.stateManager.set(new StartMenu(game.stateManager));}
        }
    private void createPole(){
        Sprite[][] sprites;
        texturesMap = new TextureRegion(new Texture(pathTexture)).split(wi,he);
        sprites = new Sprite[texturesMap.length][texturesMap[0].length];
        this.sprites = new Sprite[wi*he];
        for(int i = 0;i<texturesMap.length;i++){
            for(int o = 0; o<texturesMap[0].length;o++){
                sprites[i][o] = new Sprite(texturesMap[i][o]);
            }
        }
        for(int i = 0;i<texturesMap.length;i++){
            for(int o = 0; o<texturesMap[0].length;o++){
                this.sprites[i*texturesMap.length+o] = sprites[i][o];
            }
        }
    }
    public int getObjectIndex(int x,int y){ // Выдаёт индекс объекта
        for(int i=0;i<game.combo.comboRender.objectsA.size;i++){ // все объекты
            if(game.combo.comboRender.objectsA.get(i).isMe(x,y)){
                return game.combo.comboRender.objectsA.get(i).index;
            }
        }
        if(game.combo.character.x == x&&game.combo.character.y == y){ // сам игрок
            game.combo.character.attackMe(0,0);
            return 1;
        }
        return 0;
    }
    public int[] getCoordinates(int x,int y){ // Получение координаты ячейки на поле
        int[] otvet = new int[4];
        if(x<size1&&y<size2){
           int w = wP-xP;
           int h = hP-yP;
           w = w/size1;
           h = h/size2;
           otvet[0] = w*x+xP;
           otvet[1] = h*y+yP;
           otvet[2] = w;
           otvet[3] = h;
        }
        return otvet;
    }
    public void setAlpha(float alpha){
        this.alpha = alpha;
    }
    private void getResources(){ // Получение ресурсов для рисовки поля

    }
    public void addYellowColor(int x,int y){
        colors.add("y-"+x+"-"+y);
    }
    public void addRedColor(int x,int y){
        colors.add("r-"+x+"-"+y);
    }
    public void clearColors(){
        colors.clear();
    }
    @Override
    public void render(SpriteBatch sb, float delta) {
        for(int i = 0;i<size1;i++){
            for(int o = 0; o<size2;o++){
                colorsDraw(i,o);
                sprites[pole[i][o]].setBounds(getCoordinates(i,o)[0],getCoordinates(i,o)[1],getCoordinates(i,o)[2],getCoordinates(i,o)[3]);
                sprites[pole[i][o]].draw(sb);
                sprites[pole[i][o]].setColor(Color.WHITE);
            }
        }
        renderObjects(sb);
    }
    public boolean objectInterupt(int x,int y,int direction,float attack){
            for(int i=0;i<game.combo.comboRender.objectsA.size;i++){ // все объекты
                if(game.combo.comboRender.objectsA.get(i).interupt(x,y,direction,attack)){
                    return true;
                }
            }
        if(game.combo.character.x == x&&game.combo.character.y == y){ // сам игрок
            game.combo.character.attackMe(direction,attack);
            return true;
        }
            return false;
    }
    private void colorsDraw(int x,int y){
        String[] buffer;
        try{
        for(int i=0;i<colors.size;i++){
            buffer = colors.get(i).split("-");
            if(buffer[0].equalsIgnoreCase("y")){
                if(x==Integer.parseInt(buffer[1])&y==Integer.parseInt(buffer[2])){
                    sprites[pole[x][y]].setColor(Color.GRAY);
                }
            }else
            if(buffer[0].equalsIgnoreCase("r")){
                if(x==Integer.parseInt(buffer[1])&y==Integer.parseInt(buffer[2])){
                    sprites[pole[x][y]].setColor(Color.RED);
                }
            }else{sprites[pole[x][y]].setColor(Color.WHITE);}
        }
    }catch (NumberFormatException e){}}

    @Override
    public void update(float delta) {
        for (int i = 0; i < objects.size; i++) {
            objects.get(i).update(delta);
        }
    }
    private void renderObjects(SpriteBatch sb){
        for(int i=0;i<objects.size;i++){
            objects.get(i).render(sb,Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void dispose() {
        for(int i=0;i<texturesMap.length;i++){
            for(int o=0;o<texturesMap[0].length;o++){
                texturesMap[i][o].getTexture().dispose();
            }

        }
    }
}
