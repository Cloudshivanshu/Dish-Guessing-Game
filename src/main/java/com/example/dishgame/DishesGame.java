package com.example.dishgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.Random;

public class DishesGame {
    @FXML
    Stage primaryStage;

    private int randomNum;

    public void genRandom() {
        Random random = new Random();
        randomNum = random.nextInt(8);
    }

    @FXML
    public void initialize() {
        genRandom();
        System.out.println("randomNum:" + randomNum);
        setImage();
        donuts.setUserData(0);
        macherjhol.setUserData(1);
        ketchupcake.setUserData(2);
        rburger.setUserData(3);
        banana.setUserData(4);
        burrito.setUserData(5);
        pesarattu.setUserData(6);
        kozhukattai.setUserData(7);
        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null) {
                changeColor();
            }
        });
    }

    @FXML
    ImageView dishImage;

    private final String[] imgPath = {
            "spaghattiesDonuts.jpg",
            "MacherJhol.jpg",
            "KetchupCake.jpg",
            "RamenBurger.jpg",
            "bananaCurry.jpg",
            "burrito.jpg",
            "pesarattu.jpg",
            "kozhukattai.jpg"
    };

    public void setImage() {
        Image image = new Image(getClass().getResourceAsStream(imgPath[randomNum]));
        dishImage.setImage(image);
    }

    @FXML
    private RadioButton donuts, macherjhol, ketchupcake, rburger, banana, burrito, pesarattu, kozhukattai;
    @FXML
    private javafx.scene.control.ToggleGroup toggleGroup;

    public void changeColor() {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

        if (selectedRadioButton == null) {
            System.out.println("No radio button selected.");
            return; // Avoid NPE
        }

        for (var toggle : toggleGroup.getToggles()) {
            ((RadioButton) toggle).setStyle("");
        }

        if (randomNum == (int) selectedRadioButton.getUserData()) {
            System.out.println("Right");
            selectedRadioButton.setStyle(
                    "-fx-background-color: #00ff00; " + "-fx-border-color: #00ff00; " + "-fx-text-fill: black;"
            );
        } else {
            System.out.println("Wrong");
            selectedRadioButton.setStyle(
                    "-fx-background-color:#ff3333; " + "-fx-border-color: #ff3333; " + "-fx-text-fill: Black;"
            );
        }
    }

    @FXML
    public void restart() {
        System.out.println("User Restart");
        for (var toggle : toggleGroup.getToggles()) {
            ((RadioButton) toggle).setStyle("");
        }
        toggleGroup.selectToggle(null);
        genRandom();
        setImage();
    }

    ButtonType yesButton = new ButtonType("YES", ButtonBar.ButtonData.YES);
    ButtonType cancelButton = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

    @FXML
    public void exit(ActionEvent event) {

        Alert logoutConformation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to proceed?", yesButton, cancelButton);
        logoutConformation.setTitle("EXIT");
        logoutConformation.setHeaderText("You are about to Exit!");
        logoutConformation.setContentText("Are you sure?");

        if (logoutConformation.showAndWait().get() == yesButton) {
            primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            System.out.println("User Exited");
            primaryStage.close();
        }
    }
}
