package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import static javafx.scene.input.KeyCode.*;
import static uet.oop.bomberman.BombermanGame.canmove;

public class Bomber extends Entity {
    //  private KeyCode direction;
    private KeyCode keycode;
    private Sprite sprite;
    private boolean keyleft = false;
    private boolean keyright = false;
    private boolean keyup = false;
    private boolean keydown = false;


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        sprite = Sprite.player_right;
    }

    public void setKeycode(KeyCode keycode) {
        this.keycode = keycode;
    }

    public void moving() {

     //   check_collision();

        if (keyup&&canMove(x,y-speed)) {

            y -= speed;

        }
        if (keydown&&canMove(x,y+speed)) {

            y += speed;

        }
        if (keyleft&&canMove(x-speed,y)) {


            x -= speed;

        }
        if (keyright&&canMove(x+speed,y)) {

            x += speed;

        }

    }

    public void keypress() {

        if (keycode == W) {
            keyup = true;
            keyright = false;
            keyleft = false;
            keydown = false;
        }

        if (keycode == S)
        {
            keyup = false;
            keyright = false;
            keyleft = false;
            keydown = true;
        }

        if (keycode == A) {
            keyup = false;
            keyright = false;
            keyleft = true;
            keydown = false;
        }

        if (keycode == D) {
            keyright = true;
            keyup = false;
            keyleft = false;
            keydown = false;
        }

    }
    public void creatBomb() {
        Entity object;
        if(keycode == SPACE) {
            object = new Bomber(x / 32 * 32, y / 32 * 32, Sprite.bomb.getFxImage());

        }
    }

    public void keyReleased(KeyCode keycode) {
        if (keycode == W)
            keyup = false;
        if (keycode == S)
            keydown = false;
        if (keycode == A)
            keyleft = false;
        if (keycode == D)
            keyright = false;

    }

    @Override
    public void update() {
        setSpeed(1);
        moving();
        creatBomb();
        if (keyright) {
            back = "d";
           if (khunghinh == 1) img = Sprite.player_right.getFxImage();
           else if (khunghinh == 2) img = Sprite.player_right_1.getFxImage();
           else if (khunghinh == 3) img = Sprite.player_right_2.getFxImage();

        }
        if (keyup) {
            back = "w";
            if (khunghinh == 1) img = Sprite.player_up.getFxImage();
            else if (khunghinh == 2) img = Sprite.player_up_1.getFxImage();
            else if (khunghinh == 3) img = Sprite.player_up_2.getFxImage();

        }
        if (keydown) {
            back = "s";
            if (khunghinh == 1) img = Sprite.player_down.getFxImage();
            else if (khunghinh == 2) img = Sprite.player_down_1.getFxImage();
            else if (khunghinh == 3) img = Sprite.player_down_2.getFxImage();

        }
        if (keyleft) {
            back = "a";
            if (khunghinh == 1) img = Sprite.player_left.getFxImage();
            else if (khunghinh == 2) img = Sprite.player_left_1.getFxImage();
            else if (khunghinh == 3) img = Sprite.player_left_2.getFxImage();
        }
        count++;
        if (count > 8) {
            if (khunghinh == 1) khunghinh = 2;
            else if (khunghinh == 2) khunghinh = 3;
            else if (khunghinh == 3) khunghinh = 1;
            count = 0;
        }
        if (!keyright && back.equals("d")) img = Sprite.player_right.getFxImage();
        if (!keyleft && back.equals("a")) img = Sprite.player_left.getFxImage();
        if (!keyup && back.equals("w")) img = Sprite.player_up.getFxImage();
        if (!keydown && back.equals("s")) img = Sprite.player_down.getFxImage();

    }


}

