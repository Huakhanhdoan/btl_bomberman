package uet.oop.bomberman.entities.explosion;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Item;
import uet.oop.bomberman.graphics.Sprite;

public class Brick_Explosion extends Explosion{
    public Brick_Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void update() {

        if(timeDie==0) {
            img= Sprite.brick_exploded.getFxImage();
        }
        if (timeDie==5) {
            img = Sprite.brick_exploded1.getFxImage();
        }
        if(timeDie == 10) {
            img = Sprite.brick_exploded2.getFxImage();
        }
        if(timeDie == 20) {
            BombermanGame.entities.remove(this);
            timeDie=0;
            if(x/Sprite.SCALED_SIZE==4&&y/Sprite.SCALED_SIZE==5) {
                Item lever = new Item(4,5,Sprite.leverBomb.getFxImage());
                BombermanGame.entities.add(lever);
            }
        }
        timeDie++;

    }
}
