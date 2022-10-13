package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import static javafx.scene.input.KeyCode.*;
import static uet.oop.bomberman.BombermanGame.entities;


public class Bomber extends Entity {
    //  private KeyCode direction;
    private KeyCode keycode;
    private Sprite sprite;
    private boolean keyleft = false;
    private boolean keyright = false;
    private boolean keyup = false;
    private boolean keydown = false;
    public int count = 0;
    public int khunghinh = 1;

    public String back = "";

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        sprite = Sprite.player_right;
    }


    public void setKeycode(KeyCode keycode) {
        this.keycode = keycode;
    }

    public void moving() {


        if (keyup && canMove(x, y - speed)) {

            y -= speed;

        }
        if (keydown && canMove(x, y + speed)) {

            y += speed;

        }
        if (keyleft && canMove(x - speed, y)) {


            x -= speed;

        }
        if (keyright && canMove(x + speed, y)) {

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

        if (keycode == S) {
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

    // kiem tra va cham voi enemy
    public void check_colide_enemy() {
        int size = entities.size();
        for (int i = 1; i < size; i++) {
            if (entities.get(i) instanceof Enemy) {
                if (((entities.get(i).x / Sprite.SCALED_SIZE) == x / Sprite.SCALED_SIZE
                        && (entities.get(i).y / Sprite.SCALED_SIZE) == y / Sprite.SCALED_SIZE)
                        || ((entities.get(i).x + Sprite.SCALED_SIZE - 1) / 32) == x / Sprite.SCALED_SIZE &&
                        ((entities.get(i).y + Sprite.SCALED_SIZE - 1) / 32) == y / Sprite.SCALED_SIZE) {
                    setLives(false);
                }
            }
        }

    }
public void animation_bomber() {
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
    @Override
    public void update() {

        if (lives) {
            check_colide_enemy();
            moving();
            animation_bomber();

        } else {
            timeDie++;
            if (timeDie == 1) {
                img = Sprite.player_dead1.getFxImage();
            }
            if (timeDie == 10) {
                img = Sprite.player_dead2.getFxImage();
            }
            if (timeDie == 100) {
                BombermanGame.entities.remove(this);
                timeDie = 0;

            }

        }

    }


}

