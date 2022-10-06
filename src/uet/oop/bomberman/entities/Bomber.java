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
    private boolean keyleft = false;
    private boolean keyright = false;
    private boolean keyup = false;
    private boolean keydown = false;


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setKeycode(KeyCode keycode) {
        this.keycode = keycode;
    }

    public void moving() {
        check_collision();

        if (keyup) {

            y -= 2;

        }
        if (keydown) {

            y += 2;

        }
        if (keyleft) {


            x -= 2;

        }
        if (keyright) {

            x += 2;

        }

    }

    public void keypress() {

        if (keycode == W)
            keyup = true;
        if (keycode == S)
            keydown = true;
        if (keycode == A)
            keyleft = true;
        if (keycode == D)
            keyright = true;

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

    public void check_collision() {
        // tao cac toa do la trung diem 4 canh hinh vuong nhan vat
        int check_upX = (x+16) / 32;
        int check_upY = (y+4) / 32;
        int check_downX = (x+16) / 32;
        int check_downY = (y + 32-4) / 32;
        int check_leftX = (x ) / 32;
        int check_leftY = (y+16) / 32;
        int check_rightX = (x + 32-8) / 32;
        int check_rightY = (y + 16) / 32;
        if (canmove[check_upX][check_upY]) y+=2;
        if (canmove[check_downX][check_downY]) y-=2;

        if (canmove[check_leftX][check_leftY]) x+=2;
        if (canmove[check_rightX][check_rightY]) x-=2;

    }
}

