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
        if(timeloop == 200) {
            BombermanGame.entities.remove(this);
            timeloop = 0;
        }


    }

}