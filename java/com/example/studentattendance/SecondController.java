package com.example.studentattendance;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
