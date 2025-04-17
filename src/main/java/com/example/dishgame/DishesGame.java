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

    Random random = new Random();
    int randomNum = random.nextInt(8);

    @FXML
    public void initialize(){
        System.out.println(randomNum);
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
    public void setImage(){
        String[] imgPath = {
                "spaghattiesDonuts.jpg",
                "MacherJhol.jpg",
                "KetchupCake.jpg",
                "RamenBurger.jpg",
                "bananaCurry.jpg",
                "burrito.jpg",
                "pesarattu.jpg",
                "kozhukattai.jpg"
        };

        Image image = new Image(getClass().getResourceAsStream(imgPath[randomNum]));
        dishImage.setImage(image);
    }

    @FXML
    private RadioButton donuts, macherjhol, ketchupcake, rburger, banana, burrito, pesarattu, kozhukattai;
    @FXML
    private javafx.scene.control.ToggleGroup toggleGroup;
   public void changeColor(){
       RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

       if (selectedRadioButton == null) {
              System.out.println("No radio button selected.");
           return; // Avoid NPE
       }

       for (var toggle : toggleGroup.getToggles()) {
           ((RadioButton) toggle).setStyle("");
       }

       if(randomNum == (int)selectedRadioButton.getUserData()){
           selectedRadioButton.setStyle(
                   "-fx-background-color: #00ff00; " + "-fx-border-color: #00ff00; " + "-fx-text-fill: black;"
           );
           }else {
           selectedRadioButton.setStyle(
                   "-fx-background-color:#ff3333; " + "-fx-border-color: #ff3333; " + "-fx-text-fill: Black;"
           );
       }
   }

    ButtonType yesButton = new ButtonType("YES", ButtonBar.ButtonData.YES);
    ButtonType cancelButton = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
    @FXML
    public void exit(ActionEvent event){

        Alert logoutConformation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to proceed?", yesButton ,cancelButton);
        logoutConformation.setTitle("EXIT");
        logoutConformation.setHeaderText("You are about to Exit!");
        logoutConformation.setContentText("Are you sure?");

        if(logoutConformation.showAndWait().get() == yesButton) {
            primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            System.out.println("you Exited");
            primaryStage.close();
        }
    }
}
