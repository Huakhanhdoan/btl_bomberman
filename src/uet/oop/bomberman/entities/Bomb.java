package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.canmove;
import static uet.oop.bomberman.BombermanGame.stillObjects;

public class Bomb extends Entity {
    private int timeloop;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void update() {
animation();


    }
    public void animation() {
        timeloop++;
        if(timeloop %20 == 0 ) {
            img = Sprite.bomb_1.getFxImage();
        }
        if(timeloop %20== 10) {
            img = Sprite.bomb_2.getFxImage();
        }
        if(timeloop %200== 180) {
            img = Sprite.bomb_exploded.getFxImage();
        }
        if(timeloop %200== 190) {
            img = Sprite.bomb_exploded2.getFxImage();

        }
        if(timeloop>180) {

           destroy();


        }
        if(timeloop == 200) {

            BombermanGame.entities.remove(this);
            timeloop = 0;
        }
    }
    public void destroy() {
        int bombX = x/ Sprite.SCALED_SIZE;
        int bombY = y/Sprite.SCALED_SIZE;
      //  Entity brick_ = new Brick(x+1,y,Sprite.brick.getFxImage());

        if(!canmove[bombX+1][bombY]) {
            BombermanGame.gc.drawImage(Sprite.explosion_horizontal2.getFxImage(),x+32,y);
        }
        else {
            if(stillObjects.get(25*bombY+bombX+1) instanceof Brick) {
                stillObjects.remove(25*bombY+bombX+1);
                stillObjects.add(25*bombY+bombX+1,new Grass(bombX+1,bombY,Sprite.grass.getFxImage()));

                canmove[bombX+1][bombY] = false;
            }
        }
        if(!canmove[bombX-1][bombY]) {
            BombermanGame.gc.drawImage(Sprite.explosion_horizontal2.getFxImage(),x-32,y);
        }
        else {
            if(stillObjects.get(25*bombY+bombX-1) instanceof Brick) {
                stillObjects.remove(25*bombY+bombX-1);
                stillObjects.add(25*bombY+bombX-1,new Grass(bombX-1,bombY,Sprite.grass.getFxImage()));
                canmove[bombX-1][bombY] = false;
            }
        }
        if(!canmove[bombX][bombY-1]) {
            BombermanGame.gc.drawImage(Sprite.explosion_vertical2.getFxImage(),x,y-32);
        }
        else {
            if(stillObjects.get(25*(bombY+1)+bombX) instanceof Brick) {
                stillObjects.remove(25*(bombY-1)+bombX);
                stillObjects.add(new Grass(bombX,bombY-1,Sprite.grass.getFxImage()));

                canmove[bombX][bombY-1] = false;
            }
        }
        if(!canmove[bombX][bombY+1]) {
            BombermanGame.gc.drawImage(Sprite.explosion_vertical2.getFxImage(),x,y+32);
        }
        else {
            if(stillObjects.get(25*(bombY+1)+bombX) instanceof Brick) {
                stillObjects.remove(25*(bombY+1)+bombX);
                stillObjects.add(25*(bombY+1)+bombX,new Grass( bombX,bombY+1,Sprite.grass.getFxImage()));

                canmove[bombX][bombY+1] = false;
            }
        }
    }



}