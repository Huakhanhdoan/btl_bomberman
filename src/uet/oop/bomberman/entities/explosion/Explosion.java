package uet.oop.bomberman.entities.explosion;

import javafx.scene.image.Image;

import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public abstract class Explosion extends Entity {
    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void update() {

    }

    public void check_entiny(int bombX, int bombY) {
        int size = entities.size();
        for (int i = 0; i < size; i++) {
            if(((entities.get(i).getX()/Sprite.SCALED_SIZE)==bombX&&(entities.get(i).getY()/Sprite.SCALED_SIZE)==bombY)
                    ||((entities.get(i).getX()+Sprite.SCALED_SIZE-1)/32)==bombX&&((entities.get(i).getY()+Sprite.SCALED_SIZE-1)/32)==bombY) {
                entities.get(i).setLives(false);
            }
        }
    }

}
