package com.example.studentattendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataController {
    @FXML
    ChoiceBox<String> importChoiceBox;

    @FXML
    ChoiceBox<String> exportChoiceBox;

    @FXML
    TextField importFilePath;

    @FXML
    TextField exportFilePath;

    public void initialize(){
        ArrayList<String> fileOptions = new  ArrayList<>(){{
            add("XLSX");
            add("CSV");
        }};
        importChoiceBox.getItems().addAll(fileOptions);
        exportChoiceBox.getItems().addAll(fileOptions);
    }

    public void goBackToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void browseImport(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        importFilePath.setText(file.getAbsolutePath());
    }

    public void browseExport(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        exportFilePath.setText(file.getAbsolutePath());
    }


}
