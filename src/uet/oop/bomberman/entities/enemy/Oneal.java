package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import static uet.oop.bomberman.BombermanGame.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Entity {
    private int direction;
    private int timeloop;


    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    private void randomd() {
        Random random = new Random();
        direction = random.nextInt(4) + 1;
    }

    @Override
    public void update() {
        timeloop++;


        if (x % 32 == 0 &&y % 32 == 0) {
            randomd();
            // System.out.println(1);
            //   if (!AI()) direction = 1;
        }
        while (true) {

          //  if(AI()) break;
                if (direction == 1 && canMove_enemy(x + speed, y)) break;
                if (direction == 2 && canMove_enemy(x - speed, y)) break;
                if (direction == 3 && canMove_enemy(x, y + speed)) break;
                if (direction == 4 && canMove_enemy(x, y - speed)) break;

              else
                    randomd();

        }

        moving_enemy();

        animation_enemy();

        if (timeloop == 40)
            timeloop = 0;
      //  moving_enemy();

    }

    public void moving_enemy() {

        if (direction == 1) {
            x += speed;
        }
        if (direction == 2) {
            x -= speed;
        }
        if (direction == 3) {
            y += speed;
        }
        if (direction == 4) {
            y -= speed;
        }
    }

    public void animation_enemy() {
        if (lives) {
            if (direction == 1 || direction == 3) {
                switch (timeloop % 31) {
                    case 10:
                        img = Sprite.oneal_right1.getFxImage();
                        break;
                    case 20:
                        img = Sprite.oneal_right2.getFxImage();
                        break;
                    case 30:
                        img = Sprite.oneal_right3.getFxImage();
                }
            }
            if (direction == 2 || direction == 4) {
                switch (timeloop % 31) {
                    case 10:
                        img = Sprite.oneal_left1.getFxImage();
                        break;
                    case 20:
                        img = Sprite.oneal_left2.getFxImage();
                        break;
                    case 30:
                        img = Sprite.oneal_left3.getFxImage();
                }
            }
        } else {
            timeDie++;
            direction = 5;
            img = Sprite.oneal_dead.getFxImage();
        }
        if (timeDie == 40) {
point+=150;
            timeDie = 0;
            BombermanGame.entities.remove(this);
Entity enemy1 = new Enemy(x/Sprite.SCALED_SIZE,y/Sprite.SCALED_SIZE,Sprite.balloom_left1.getFxImage());
            Entity enemy2 = new Enemy(x/Sprite.SCALED_SIZE,y/Sprite.SCALED_SIZE,Sprite.balloom_left1.getFxImage());
            BombermanGame.entities.add(enemy1);
            BombermanGame.entities.add(enemy2);
        }
    }

    private boolean AI() {
        double kc = distance(x, y);
        if (canMove_enemy(x + speed, y)&&x % 32 == speed &&y % 32 == 0) {
            if (kc > distance(x + speed, y)) {
                direction = 1;
                return true;
            }
        }
        if (canMove_enemy(x - speed, y)&&(x-speed) % 32 == 0 &&y % 32 == 0) {
            if (kc > distance(x - speed, y)) {
                direction = 2;
                return true;
            }

        }
        if (canMove_enemy(x, y + speed)&&x % 32 == 0 &&y % 32 == speed) {
            if (kc > distance(x, y + speed)) {
                direction = 3;
                return true;
            }

        }
        if (canMove_enemy(x, y - speed)&&x % 32 == 0 &&(y-speed) % 32 == 0) {
            if (kc > distance(x, y - speed)) {
                direction = 4;
                return true;
            }
        }
        return false;

    }

    private double distance(int x, int y) {

        double kc = Math.abs(x - BombermanGame.entities.get(0).getX()) + Math.abs(y - BombermanGame.entities.get(0).getY());
        return kc;
    }
}
