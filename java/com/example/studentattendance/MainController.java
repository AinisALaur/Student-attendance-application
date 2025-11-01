package com.example.studentattendance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    University university = University.getInstance();

    @FXML
    TableView<Student> studentsList;

    @FXML
    TableColumn<Student, String> studIdCol;

    @FXML
    TableColumn<Student, String> studNameCol;

    @FXML
    TableColumn<Student, String> studGroupCol;


    public void initialize(){
        System.out.println("Initializing Main Controller");
        ObservableList<Student> students = FXCollections.observableArrayList(university.getStudents());

        studIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        studNameCol.setCellValueFactory(new PropertyValueFactory<>("info"));
        studGroupCol.setCellValueFactory(new PropertyValueFactory<>("group"));

        if(students == null || students.isEmpty()){
            return;
        }
        studentsList.setItems(students);
    }


    @FXML
    protected void onManageStudentClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("second-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void onManageGroupClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("group-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
