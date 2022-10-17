package uet.oop.bomberman.menu;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;

import static java.awt.Color.CYAN;
import static java.awt.Color.cyan;

public class Menu extends javafx.scene.control.Menu {
    private int x;
    private int y;

    public Menu(Stage stage, Group root, AnimationTimer timer) {

        Image image = new Image("/textures/menu.jpg");

        ImageView menu_ = new ImageView(image);
        menu_.setFitHeight(16*32);
        menu_.setFitWidth(25*32);
        BackgroundImage newGameBgr = new BackgroundImage(image, null, null, null, null);
        Image test= new Image("/sprites/newgame.png");
        ImageView view = new ImageView(test);
        view.setFitWidth(100);
        view.setFitHeight(50);
        Button button = new Button("START");
        button.setLayoutX(390);
        button.setLayoutY(250);
        button.setScaleX(5);
        button.setScaleY(2);
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(newGameBgr));
        button.setStyle("-fx-border-color: blue;");
        button.setFont(Font.font ("Verdana", FontWeight.BOLD, 11));
        Button exit = new Button("EXIT");
        exit.setLayoutX(395);
        exit.setLayoutY(320);
        exit.setScaleX(5);
        exit.setScaleY(2);
        exit.setTextFill(Color.WHITE);

        exit.setStyle("-fx-border-color: blue;");

        exit.setBackground(new Background(newGameBgr));
        exit.setFont(Font.font ("Verdana", FontWeight.BOLD, 11));
        exit.setOnAction(event ->{

            stage.close();
        } );

        root.getChildren().addAll(menu_,button,exit);

        button.setOnAction(event ->  {
            root.getChildren().remove(button);
            root.getChildren().remove(menu_);
            root.getChildren().remove(exit);
            timer.start();

        });

    }

    public Menu(String file) {

    }

    public static void demo_menu(Stage stage) {
        MenuBar menuBar = new MenuBar();


        // Tạo các Menu
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
    }
}
