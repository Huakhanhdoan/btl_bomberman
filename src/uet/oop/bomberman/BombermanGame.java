package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;

import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static boolean[][] canmove = new boolean[50][50];
   public static GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();



    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // Tao Canvas

        final int[] time = {0};
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();

        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);

        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                if(!(entities.get(0) instanceof Bomber)) {
                    time[0]++;
                    Image image = new Image("/textures/gameover.jpg");
                    gc.drawImage(image, 0,0,25*32,15*32);
                    if(time[0]==70)
                    stage.close();
                }

            }
        };
        timer.start();

        createMap();

        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        Entity enemy = new Enemy(10, 1, Sprite.balloom_left1.getFxImage());
        Entity enemy2 = new Enemy(21, 13, Sprite.balloom_left1.getFxImage());
        Entity enemy3 = new Enemy(3, 11, Sprite.balloom_left1.getFxImage());
        entities.add(bomberman);
        entities.add(enemy);
        entities.add(enemy2);
        entities.add(enemy3);



        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE) {

                    boolean check_bomb = false;
                    for (Entity entity : entities) {
                        if (entity instanceof Bomb) {
                            check_bomb = true;
                        }
                    }
                    if (!check_bomb) {
                        Bomb bomb = new Bomb((bomberman.getX() + 16) / Sprite.SCALED_SIZE,
                                (bomberman.getY() + 16) / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                        entities.add(bomb);


                    }
                }
                bomberman.setKeycode(keyEvent.getCode());
                bomberman.keypress();
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent keyEvent) {
                bomberman.keyReleased(keyEvent.getCode());
            }
        });

    }

    public void createMap() throws FileNotFoundException {


        File myObj = new File("src\\uet\\oop\\bomberman\\map.txt");
        Scanner myReader = new Scanner(myObj);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 25; j++) {
                Entity object;
                int data = myReader.nextInt();
                if (data == 0) {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                }
                if (data == 1) {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                    stillObjects.add(object);
                    canmove[j][i] = true;
                }
                if (data == 2) {
                    object = new Brick(j, i, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    canmove[j][i] = true;
                }

            }
        }
        myReader.close();

    }


    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
      //  entities.forEach(g -> g.render(gc));
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(gc);
        }

    }

}
