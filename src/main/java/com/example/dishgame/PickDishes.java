package com.example.dishgame;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class PickDishes extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoadingScene.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        Image logo = new Image("file:FoodLogo.jpg");
        primaryStage.getIcons().add(logo);
        primaryStage.setTitle("Dish guessing game");

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            try {
                Parent mainRoot = FXMLLoader.load(getClass().getResource("DishesGame.fxml"));
                Scene mainScene = new Scene(mainRoot);
                primaryStage.setScene(mainScene);
            }catch (IOException e){
                e.printStackTrace();
            }

        });
        pause.play();
        primaryStage.show();
    }
}
