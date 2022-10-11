package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.canmove;

public class Explosion extends Entity{
    public Bomb bomb;
    int time;
    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    @Override
    public void update() {
        time++;
        destroy();
        if(time == 5) {
            img = Sprite.explosion_horizontal.getFxImage();
        }
        if(time == 20) {
            int size = BombermanGame.entities.size();
            for (int i = 0; i <size ; i++) {
                if(BombermanGame.entities.get(i) instanceof Explosion) {
                    BombermanGame.entities.remove(i);
                    size--;
                }
            }
            time =0;
        }

    }
    public void destroy() {
        int bombX = bomb.getX()/ Sprite.SCALED_SIZE;
        int bombY = bomb.getY()/Sprite.SCALED_SIZE;
        Entity entity = null;
        if(!canmove[bombX+1][bombY]) {
            entity = new Explosion(bombX+1,bombY,Sprite.explosion_vertical1.getFxImage());
            BombermanGame.entities.add(entity);
        }
        if(!canmove[bombX-1][bombY]) {
            entity = new Explosion(bombX-1,bombY,Sprite.explosion_vertical1.getFxImage());
            BombermanGame.entities.add(entity);
        }
        if(!canmove[bombX][bombY-1]) {
            entity = new Explosion(bombX,bombY-1,Sprite.explosion_horizontal1.getFxImage());
            BombermanGame.entities.add(entity);
        }
        if(!canmove[bombX+1][bombY+1]) {
            entity = new Explosion(bombX,bombY+1,Sprite.explosion_horizontal1.getFxImage());
            BombermanGame.entities.add(entity);
        }
    }

}
