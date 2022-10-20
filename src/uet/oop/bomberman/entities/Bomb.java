package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.explosion.Brick_Explosion;
import uet.oop.bomberman.entities.explosion.Explosion;
import uet.oop.bomberman.entities.explosion.Horiontal;
import uet.oop.bomberman.entities.explosion.Vertical;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
    private int timeloop;
    public static int lever =1;


    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    @Override
    public void update() {
        animation();
    }

    // hoat anh bomb no
    public void animation() {

        timeloop++;
        if (timeloop % 20 == 0) {
            img = Sprite.bomb_1.getFxImage();
        }
        if (timeloop % 20 == 10) {
            img = Sprite.bomb_2.getFxImage();
        }

//        if(timeloop == 70) {
//            canmove[x/Sprite.SCALED_SIZE][y/Sprite.SCALED_SIZE] = true;
//        }

        if (timeloop == 150) {
           destroy();
            img = Sprite.bomb_exploded.getFxImage();
        }
        if (timeloop == 155) {
            img = Sprite.bomb_exploded1.getFxImage();
        }
        if (timeloop == 160) {
            img = Sprite.bomb_exploded2.getFxImage();

        }

        if (timeloop > 150) {
         //   destroy();

        }
        if (timeloop == 170) {
     //       canmove[x/Sprite.SCALED_SIZE][y/Sprite.SCALED_SIZE] = false;
            BombermanGame.entities.remove(this);
            timeloop = 0;
        }
    }

    // no bomb + pha bick
    public void destroy() {
        int bombX = (x) / Sprite.SCALED_SIZE;
        int bombY = (y) / Sprite.SCALED_SIZE;
        check_entiny(bombX,bombY);
        if (!canmove[bombX + 1][bombY]) {
            Explosion explosion = new Horiontal(bombX+1,bombY,Sprite.explosion_horizontal.getFxImage());
            entities.add(explosion);
            if(lever==2) {
                if (!canmove[bombX + 2][bombY]) {
                    Explosion explosion2 = new Horiontal(bombX + 2, bombY, Sprite.explosion_horizontal.getFxImage());
                    entities.add(explosion2);
                }
                else {
                    check_brick(bombX+2,bombY);
                }
            }

        } else {
            check_brick(bombX + 1, bombY);

        }

        if (!canmove[bombX - 1][bombY]) {
            Explosion explosion = new Horiontal(x/32-1,y/32,Sprite.explosion_horizontal.getFxImage());
            entities.add(explosion);
            if(lever==2) {
                if (!canmove[bombX - 2][bombY]) {
                    Explosion explosion2 = new Horiontal(bombX - 2, bombY, Sprite.explosion_horizontal.getFxImage());
                    entities.add(explosion2);
                }
                else {
                    check_brick(bombX - 2, bombY);

                }

            }

        } else {
            check_brick(bombX - 1, bombY);

        }
        if (!canmove[bombX][bombY - 1]) {
            Explosion explosion = new Vertical(x/32,y/32-1,Sprite.explosion_vertical.getFxImage());
            entities.add(explosion);
            if(lever==2) {
                if (!canmove[bombX ][bombY-2]) {
                    Explosion explosion2 = new Vertical(bombX , bombY-2, Sprite.explosion_vertical.getFxImage());
                    entities.add(explosion2);
                }
                else {
                    check_brick(bombX,bombY-2);
                }
            }
        } else {
            check_brick(bombX, bombY - 1);

        }
        if (!canmove[bombX][bombY + 1]) {
            Explosion explosion = new Vertical(x/32,y/32+1,Sprite.explosion_vertical.getFxImage());
            entities.add(explosion);
            if(lever==2) {
                if (!canmove[bombX ][bombY+2]) {
                    Explosion explosion2 = new Vertical(bombX , bombY+2, Sprite.explosion_vertical.getFxImage());
                    entities.add(explosion2);
                }
                else {
                    check_brick(bombX,bombY+2);
                }
            }
        } else {
            check_brick(bombX, bombY + 1);

        }
    }

    // check xem co bick trong pham vi no khong
    public void check_brick(int bombX, int bombY) {
        if (stillObjects.get(25 * (bombY) + bombX) instanceof Brick)  {
            Entity entity = new Brick_Explosion(bombX,bombY,Sprite.brick_exploded.getFxImage());
            entities.add(entity);
                stillObjects.remove(25 * (bombY) + bombX);
                stillObjects.add(25 * (bombY) + bombX, new Grass(bombX, bombY, Sprite.grass.getFxImage()));
                canmove[bombX][bombY] = false;

        }
    }
    // check bomber and enemy
    public void check_entiny(int bombX, int bombY) {
        int size = entities.size();
        for (int i = 0; i < size; i++) {
            if(((entities.get(i).x/Sprite.SCALED_SIZE)==bombX&&(entities.get(i).y/Sprite.SCALED_SIZE)==bombY)
                    ||((entities.get(i).x+Sprite.SCALED_SIZE-1)/32)==bombX&&((entities.get(i).y+Sprite.SCALED_SIZE-1)/32)==bombY) {
                entities.get(i).setLives(false);
            }
        }
    }

}