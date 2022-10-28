package uet.oop.bomberman.menu;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;

import java.util.concurrent.atomic.AtomicBoolean;

import static uet.oop.bomberman.BombermanGame.*;


public class Menu extends javafx.scene.control.Menu {


    public Menu(Stage stage, Group root, AnimationTimer timer) {

        Image image = new Image("/textures/menu.jpg");

        ImageView menu_ = new ImageView(image);
        menu_.setFitWidth(Sprite.SCALED_SIZE * WIDTH - 5 * Sprite.SCALED_SIZE);
        menu_.setFitHeight(Sprite.SCALED_SIZE * (HEIGHT + 1));
        BackgroundImage newGameBgr = new BackgroundImage(image, null, null, null, null);
        Button start = new Button("START");
        start.setLayoutX(440);
        start.setLayoutY(350);
        start.setScaleX(5);
        start.setScaleY(2);
        start.setTextFill(Color.WHITE);
        start.setBackground(new Background(newGameBgr));

        start.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                start.setTextFill(Color.RED);

            }
        });
        start.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                start.setTextFill(Color.WHITE);
            }
        });
        start.setFont(Font.font("Impact", FontWeight.BOLD, 15));
        Button exit = new Button("EXIT");
        exit.setLayoutX(450);
        exit.setLayoutY(420);
        exit.setScaleX(5);
        exit.setScaleY(2);
        exit.setTextFill(Color.WHITE);


        exit.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exit.setTextFill(Color.RED);
            }
        });
        exit.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exit.setTextFill(Color.WHITE);
            }
        });
        exit.setBackground(new Background(newGameBgr));
        exit.setFont(Font.font("Impact", FontWeight.BOLD, 15));
        exit.setOnAction(event -> {

            stage.close();
        });

        root.getChildren().addAll(menu_, start, exit);

        start.setOnAction(event -> {
            root.getChildren().remove(start);
            root.getChildren().remove(menu_);
            root.getChildren().remove(exit);
            timer.start();

        });

    }

    public static void newgame(Stage stage,AnimationTimer timer) {
        timer.stop();

        Button start = new Button("NEW GAME");
        start.setLayoutX(440);
        start.setLayoutY(350);
        start.setScaleX(5);
        start.setScaleY(2);
        start.setTextFill(Color.SNOW);
        start.setStyle("-fx-background-color: "+Color.BURLYWOOD.toString().replace("0x", "#"));


        start.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                start.setTextFill(Color.SKYBLUE);

            }
        });
        start.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                start.setTextFill(Color.SNOW);
            }
        });
        start.setFont(Font.font("Impact", FontWeight.BOLD, 15));
        Button exit = new Button("EXIT");
        exit.setLayoutX(450);
        exit.setLayoutY(420);
        exit.setScaleX(5);
        exit.setScaleY(2);
        exit.setTextFill(Color.SNOW);
        exit.setStyle("-fx-background-color: "+Color.BURLYWOOD.toString().replace("0x", "#"));


        exit.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exit.setTextFill(Color.SKYBLUE);
            }
        });
        exit.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exit.setTextFill(Color.SNOW);
            }
        });
      //  exit.setBackground(new Background(newGameBgr));
        exit.setFont(Font.font("Impact", FontWeight.BOLD, 15));
        exit.setOnAction(event -> {

            stage.close();
        });

        root.getChildren().addAll( start, exit);

        start.setOnAction(event -> {
            root.getChildren().remove(start);

            root.getChildren().remove(exit);
            timer.start();

        });
    }
    public static void pausegame(AnimationTimer timer) {
        ImageView pause_img = new ImageView(new Image("/textures/pause.png/"));
        Button pause = new Button("",pause_img);
        pause.setLayoutX(900);
        pause.setLayoutY(2);
        pause.setBackground(Background.EMPTY);

        ImageView continue_img = new ImageView(new Image("/textures/continue.png/"));
        Button _continue = new Button("",continue_img);
        _continue.setLayoutX(900);
        _continue.setLayoutY(2);
        _continue.setBackground(Background.EMPTY);


pause.setFocusTraversable(false);
_continue.setFocusTraversable(false);
pause.setOnMouseClicked(new EventHandler<MouseEvent>() {

    @Override
    public void handle(MouseEvent t) {
        timer.stop();
        root.getChildren().remove(pause);
        root.getChildren().add(_continue);
    }
});

        _continue.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                timer.start();
                root.getChildren().remove(_continue);
                root.getChildren().add(pause);
            }

        });

        root.getChildren().add(pause);

    }
    }

