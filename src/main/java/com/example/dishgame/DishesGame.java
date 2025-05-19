package com.example.dishgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.*;

public class DishesGame {
    @FXML
    public GridPane gridPane;
    @FXML
    Stage primaryStage;

    private int randomNum;
    private File[] AllImage;
    private File[] WrongOpt;

    @FXML
    private javafx.scene.control.ToggleGroup toggleGroup;
    private static final String imageDir = "C:\\Users\\hp\\IdeaProjects\\DishGame\\src\\main\\resources\\com\\example\\dishgame\\Images";
    List<RadioButton> options = new ArrayList<>();

    @FXML
    public void initialize() {
        toggleGroup = new ToggleGroup();
        File dir = new File(imageDir);
        if (dir.isDirectory()) {
            AllImage = dir.listFiles((d, name) -> {
                String lower = name.toLowerCase();
                return lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".gif");
            });
        }
        genRandom();
        setImage();
        wrongOpts();
        setradiobutton();
        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null) {
                changeColor();
            }
        });
    }

    public void genRandom() {
        Random random = new Random();
        randomNum = random.nextInt(AllImage.length);
        System.out.println("randomNum:" + randomNum);
    }

    @FXML
    ImageView dishImage;

    public void setImage() {
        if (AllImage != null) {
            File imgFile = AllImage[randomNum];
            imgFile.getName();
            Image image = new Image(imgFile.toURI().toString());
            dishImage.setImage(image);
        }
        String imageName = AllImage[randomNum].getName();
        String imgWOext = imageName.substring(0, imageName.lastIndexOf('.'));
        RadioButton option = new RadioButton(imgWOext);
        options.add(option);
        option.setUserData("correct");
        option.setToggleGroup(toggleGroup);
    }

    public void wrongOpts() {
        File WrongDir = new File(imageDir);
        WrongOpt = WrongDir.listFiles();
        List<File> fileList = new ArrayList<>(Arrays.asList(WrongOpt));
        fileList.remove(AllImage[randomNum]);
        WrongOpt = fileList.toArray(new File[0]);
        int i;
        for (i = 0; i < 7; i++) {
            String wrImageName = WrongOpt[i].getName();
            String imgWOext = wrImageName.substring(0, wrImageName.lastIndexOf('.'));
            RadioButton wrOption = new RadioButton(imgWOext);
            options.add(wrOption);
            wrOption.setUserData("wrong");
            wrOption.setToggleGroup(toggleGroup);
        }
    }

    public void setradiobutton() {
        Collections.shuffle(options);
        int k = 0;
        for (int p = 0; p < 2; p++) {
            for (int q = 0; q < 4; q++) {
                gridPane.add(options.get(k), q, p);
                k++;
            }
        }
    }

    public void changeColor() {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

        if (selectedRadioButton == null) {
            System.out.println("No radio button selected.");
            return;
        }

        for (var toggle : toggleGroup.getToggles()) {
            ((RadioButton) toggle).setStyle("");
        }

        if ("correct".equals(selectedRadioButton.getUserData())) {
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
        gridPane.getChildren().clear();
        options.clear();
        toggleGroup.selectToggle(null);
        genRandom();
        setImage();
        wrongOpts();
        setradiobutton();
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
