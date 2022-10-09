package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.canmove;
import static uet.oop.bomberman.BombermanGame.stillObjects;

public class Enemy extends Entity {
    private int direction;
    private int timeloop;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
private void randomd() {
    Random random = new Random();
    direction = random.nextInt(4)+1;
}
    @Override
    public void update() {
        setSpeed(1);
        timeloop++;
        while (true) {
            if (direction == 1 && canMove_enemy(x+1,y)) break ;
            if (direction == 2 && canMove_enemy(x-1,y)) break ;
            if (direction == 3 && canMove_enemy(x,y+1)) break ;
            if (direction == 4 && canMove_enemy(x,y-1)) break ;
            randomd();
        }
       if(timeloop==20 ) {


           randomd();
       };
        if (timeloop==60)
            timeloop =0;
        moving_enemy();

    }
    public void moving_enemy() {

        if(direction == 1) {
            x++;
        }
        if(direction == 2) {
            x--;
        }
        if(direction == 3) {
            y++;
        }
        if(direction == 4) {
            y--;
        }
    }

}