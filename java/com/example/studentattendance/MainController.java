package com.example.studentattendance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @FXML
    ChoiceBox<String> filterList;

    @FXML
    Label chooseText;

    @FXML
    DatePicker startDate;

    @FXML
    DatePicker endDate;

    @FXML
    ChoiceBox<String> filterByList;


    public void initialize(){
        System.out.println("Initializing Main Controller");
        ObservableList<Student> students = FXCollections.observableArrayList(university.getStudents());

        studIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        studNameCol.setCellValueFactory(new PropertyValueFactory<>("info"));
        studGroupCol.setCellValueFactory(new PropertyValueFactory<>("group"));

        ArrayList<String> filters = new ArrayList<>(){
            {
                add("Student");
                add("Group");
            }
        };

        filterList.getItems().addAll(filters);
        startDate.setValue(LocalDate.now());

        if(students == null || students.isEmpty()){
            return;
        }
        studentsList.setItems(students);
    }

    public void updateFilter(){
        String filter = filterList.getValue().toLowerCase();
        chooseText.setText("Choose " +  filter);
        filterByList.getItems().clear();
        if(filter.equals("student")){
            for(Student student : university.getStudents()){
                filterByList.getItems().add(student.getInfo());
            }
        }else{
            for(String group : university.getGroups()){
                filterByList.getItems().add(group);
            }
        }

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
