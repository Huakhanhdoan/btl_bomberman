package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.canmove;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    //toc do di chuyen cua thuc the
    protected int speed;


    public void setSpeed(int speed) {
        this.speed = speed;
    }



    public int count = 0;
    public int khunghinh = 1;
    public String back = "";
    protected Image img;


    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x * Sprite.SCALED_SIZE;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y * Sprite.SCALED_SIZE;
    }

    public boolean canMove(int x, int y) {
        int check_up_leftX = (x+4 ) / 32;
        int check_up_leftY = (y +4) / 32;
        int check_down_rightX = (x + 32 - 12) / 32;
        int check_down_rightY = (y + 32 - 4) / 32;
        int check_left_downX = (x +4) / 32;
        int check_left_downY = (y + 32 -4) / 32;
        int check_right_upX = (x + 32 - 12) / 32;
        int check_right_upY = (y + 4) / 32;
        if (canmove[check_up_leftX][check_up_leftY]) return false;
        if (canmove[check_down_rightX][check_down_rightY]) return false;
        if ( canmove[check_left_downX][check_left_downY]) return false;
        if (canmove[check_right_upX][check_right_upY]) return false;
        else
            return true;
    }
    public boolean canMove_enemy(int x, int y) {
        int check_up_leftX = (x +1) / 32;
        int check_up_leftY = (y +1) / 32;
        int check_down_rightX = (x + 32 -1) / 32;
        int check_down_rightY = (y + 32 -1) / 32;
        int check_left_downX = (x +1) / 32;
        int check_left_downY = (y + 32 -1) / 32;
        int check_right_upX = (x + 32 -1) / 32;
        int check_right_upY = (y  +1) / 32;
        if (canmove[check_up_leftX][check_up_leftY]) return false;
        if (canmove[check_down_rightX][check_down_rightY]) return false;
        if ( canmove[check_left_downX][check_left_downY]) return false;
        if (canmove[check_right_upX][check_right_upY]) return false;
        else
            return true;
    }
    public void check_collision_enemy() {
        // tao cac toa do la trung diem 4 canh hinh vuong nhan vat
        int check_up_leftX = (x + 4) / 32;
        int check_up_leftY = (y + 4) / 32;
        int check_down_rightX = (x + 32 - 8) / 32;
        int check_down_rightY = (y + 32 - 8) / 32;
        int check_left_downX = (x + 4) / 32;
        int check_left_downY = (y + 32 - 4) / 32;
        int check_right_upX = (x + 32 - 4) / 32;
        int check_right_upY = (y + 4) / 32;
        if (canmove[check_up_leftX][check_up_leftY] || canmove[check_right_upX][check_right_upY]) y += speed;
        if (canmove[check_down_rightX][check_down_rightY] || canmove[check_left_downX][check_left_downY]) y -= speed;
        if (canmove[check_up_leftX][check_up_leftY] || canmove[check_left_downX][check_left_downY]) x += speed;
        if (canmove[check_down_rightX][check_down_rightY] || canmove[check_right_upX][check_right_upY]) x -= speed;
    }
}
