package com.example.studentattendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GroupController {
    University university = University.getInstance();

    @FXML
    ChoiceBox<String> groupsList;

    @FXML
    ChoiceBox<String> studentsList;

    @FXML
    TextField groupName;

    ArrayList<String> groups = null;
    ArrayList<Student> students = null;
    ArrayList<Student> studentsAddedToGroup = new ArrayList<>();

    public void initialize(){
        groups = university.getGroups();
        students = university.getStudents();

        System.out.println(groups.toArray().length);

        if(groups != null){
            for(String group : groups){
                System.out.println(group);
                groupsList.getItems().add(group);
            }
        }

        groupsList.getItems().add("New group");

        if(students != null){
            for(Student student : students){
                studentsList.getItems().add(student.getName() + " " +student.getLastName());
            }
        }
    }

    private boolean groupExists(String groupToCheck){
        for(String group : groups){
            if(group.equals(group)){
                return true;
            }
        }
        return false;
    }

    private void goBackToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void handleUpdateBtn(ActionEvent actionEvent) throws IOException {
        String nameOfGroup = groupName.getText();

        if(nameOfGroup == null){
            return;
        }

        university.addGroup(nameOfGroup);

        for(Student student : studentsAddedToGroup){
            student.setGroup(nameOfGroup);
        }

        goBackToMain(actionEvent);
    }




}
