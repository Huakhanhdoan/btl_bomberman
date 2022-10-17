package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.menu.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static boolean[][] canmove = new boolean[50][50];
    public static GraphicsContext gc;
    private Canvas canvas;
    public static int count = 0;
    public static int point = 0;
    public static int time = 7200;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas

        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT+32);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        Text _point = new Text();
        Text _time = new Text();
setPoint(root,_time,_point);
        root.getChildren().add(canvas);


        // Tao scene
        Scene scene = new Scene(root);
        stage.setTitle("Bomberman");
        stage.getIcons().add(new Image("/sprites/icon.png"));

        // Them scene vao stage
        stage.setScene(scene);

        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                time--;
                updateSetpoint(_point,_time );
                render();
                update();
                if (check_endgame()) {

                    reset_game();
                }

            }
        };

        Menu menu = new Menu(stage,root,timer);



        createMap();
        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        bomberman.setSpeed(2);
        creatEntinys();


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
                        Bomb bomb = new Bomb((entities.get(0).getX() + 16) / Sprite.SCALED_SIZE,
                                (entities.get(0).getY() + 16) / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                        entities.add(bomb);


                    }
                }
                ((Bomber) entities.get(0)).setKeycode(keyEvent.getCode());
                ((Bomber) entities.get(0)).keypress();
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent keyEvent) {
                ((Bomber) entities.get(0)).keyReleased(keyEvent.getCode());
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

    public void creatEntinys() {

        Entity enemy = new Enemy(10, 1, Sprite.balloom_left1.getFxImage());
        Entity enemy2 = new Enemy(21, 13, Sprite.balloom_left1.getFxImage());
        Entity enemy3 = new Enemy(3, 11, Sprite.balloom_left1.getFxImage());
        Entity oneal1 = new Oneal(19, 7, Sprite.oneal_left1.getFxImage());
        Entity oneal2 = new Oneal(11, 13, Sprite.oneal_left1.getFxImage());


        entities.add(enemy);
        entities.add(enemy2);
        entities.add(enemy3);
        entities.add(oneal1);
        entities.add(oneal2);
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(gc);
        }

    }

    public boolean check_endgame() {
        if (entities.get(0) instanceof Bomber|| time ==0) return false;
        return true;
    }

    public void reset_game() {
        count++;
        Image image = new Image("/textures/gameover.jpg");
        gc.drawImage(image, 0, 0, 25 * Sprite.SCALED_SIZE, 16 * Sprite.SCALED_SIZE);
        if (count == 70) {

            entities.clear();
            stillObjects.clear();
            Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
            entities.add(bomberman);
            bomberman.setSpeed(2);
            creatEntinys();
            count = 0;
            try {
                createMap();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }
    public void setPoint( Group root,Text timer,Text point) {
        Rectangle rectangle = new Rectangle(0,15*32,25*32,32);
        rectangle.setFill(Color.LIGHTGREEN);
        timer.setX(200);
        timer.setY(500);

        point.setX(32);
        point.setY(500);
        point.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        timer.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        root.getChildren().addAll(rectangle,point,timer);

    }
    public void updateSetpoint(Text _point, Text timer) {
        _point.setText("Point :"+point);
        timer.setText("Time :"+time/60);
    }
}
