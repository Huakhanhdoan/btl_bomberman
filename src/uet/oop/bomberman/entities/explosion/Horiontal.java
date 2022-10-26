package uet.oop.bomberman.entities.explosion;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Horiontal extends Explosion{
    public Horiontal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        check_entiny(x/Sprite.SCALED_SIZE,y/Sprite.SCALED_SIZE);
        if(timeDie==0) {
            img= Sprite.explosion_horizontal.getFxImage();

        }
        if (timeDie==5) {
            img = Sprite.explosion_horizontal1.getFxImage();
        }
        if(timeDie == 10) {
            img = Sprite.explosion_horizontal2.getFxImage();
        }
        if(timeDie == 20) {
            BombermanGame.entities.remove(this);
        }
        timeDie++;

    }


}