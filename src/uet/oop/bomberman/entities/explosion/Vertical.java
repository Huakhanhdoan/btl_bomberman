package uet.oop.bomberman.entities.explosion;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Vertical extends Explosion{
    public Vertical(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void update() {
        check_entiny(x/32,y/32);
        if(timeDie==0) {
            img= Sprite.explosion_vertical.getFxImage();
        }
        if (timeDie==5) {
            img = Sprite.explosion_vertical1.getFxImage();
        }
        if(timeDie == 10) {
            img = Sprite.explosion_vertical2.getFxImage();
        }
        if(timeDie == 20) {
            BombermanGame.entities.remove(this);
            timeDie=0;
        }
        timeDie++;

    }
}
