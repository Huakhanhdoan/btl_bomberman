package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
    private int timeloop;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
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
        if (timeloop == 150) {
            img = Sprite.bomb_exploded.getFxImage();
        }
        if (timeloop == 155) {
            img = Sprite.bomb_exploded1.getFxImage();
        }
        if (timeloop == 160) {
            img = Sprite.bomb_exploded2.getFxImage();

        }
        if (timeloop > 150) {
            destroy();

        }
        if (timeloop == 170) {

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
            check_entiny(bombX+1,bombY);
            animation_explosion(bombX + 1, bombY, true);
        } else {
            check_brick(bombX + 1, bombY);

        }
        if (!canmove[bombX - 1][bombY]) {
            check_entiny(bombX-1,bombY);
            animation_explosion(bombX - 1, bombY, true);
        } else {
            check_brick(bombX - 1, bombY);

        }
        if (!canmove[bombX][bombY - 1]) {
            check_entiny(bombX,bombY-1);
            animation_explosion(bombX, bombY - 1, false);
        } else {
            check_brick(bombX, bombY - 1);

        }
        if (!canmove[bombX][bombY + 1]) {
            check_entiny(bombX,bombY+1);
            animation_explosion(bombX, bombY + 1, false);
        } else {
            check_brick(bombX, bombY + 1);

        }
    }

    // check xem co bick trong pham vi no khong
    public void check_brick(int bombX, int bombY) {
        if (stillObjects.get(25 * (bombY) + bombX) instanceof Brick)  {
                stillObjects.remove(25 * (bombY) + bombX);
                stillObjects.add(25 * (bombY) + bombX, new Grass(bombX, bombY, Sprite.grass.getFxImage()));
                canmove[bombX][bombY] = false;

        }
    }
    // check bomber and enemy
    public void check_entiny(int bombX, int bombY) {
        int size = entities.size();
        for (int i = 0; i < size; i++) {
            if(((entities.get(i).x/32)==bombX&&(entities.get(i).y/32)==bombY)
                    ||((entities.get(i).x+Sprite.SCALED_SIZE-1)/32)==bombX&&((entities.get(i).y+Sprite.SCALED_SIZE-1)/32)==bombY) {
                entities.get(i).setLives(false);
            }
        }
    }

    // hoat anh no
    public void animation_explosion(int bombX, int bombY, boolean isHorizontal) {
        if (isHorizontal) {
            if (timeloop > 150 && timeloop < 155) {
                BombermanGame.gc.drawImage(Sprite.explosion_horizontal.getFxImage(), bombX * Sprite.SCALED_SIZE, bombY * Sprite.SCALED_SIZE);
            } else if (timeloop < 160)
                BombermanGame.gc.drawImage(Sprite.explosion_horizontal1.getFxImage(), bombX * Sprite.SCALED_SIZE, bombY * Sprite.SCALED_SIZE);
            else
                BombermanGame.gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), bombX * Sprite.SCALED_SIZE, bombY * Sprite.SCALED_SIZE);
        } else {
            if (timeloop > 150 && timeloop < 155) {
                BombermanGame.gc.drawImage(Sprite.explosion_vertical.getFxImage(), bombX * Sprite.SCALED_SIZE, bombY * Sprite.SCALED_SIZE);
            } else if (timeloop < 160)
                BombermanGame.gc.drawImage(Sprite.explosion_vertical1.getFxImage(), bombX * Sprite.SCALED_SIZE, bombY * Sprite.SCALED_SIZE);
            else
                BombermanGame.gc.drawImage(Sprite.explosion_vertical2.getFxImage(), bombX * Sprite.SCALED_SIZE, bombY * Sprite.SCALED_SIZE);
        }
    }
}