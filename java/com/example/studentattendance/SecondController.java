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

public class SecondController {
    University university = University.getInstance();

    @FXML
    ChoiceBox<String> studentsList;
    @FXML
    TextField studentName;
    @FXML
    TextField studentLName;
    @FXML
    TextField studentId;
    @FXML
    ChoiceBox<String> groupList;

    ArrayList<Student> students = university.getStudents();

    public void initialize(){
        if(students != null){
            for(Student student : students){
                String info = student.getName();
                studentsList.getItems().add(info);
            }
        }
        studentsList.getItems().add("New student");
    }

    public void handleStudentManager(ActionEvent event) throws IOException {
        String selectedStudent = studentsList.getValue();
        String name =  studentName.getText();
        String lastName = studentLName.getText();
        String id = studentId.getText();
        String group = groupList.getValue();

        if(group == null){
            group = "Not selected";
        }

        if(selectedStudent.equals("New student")){
            Student student = new Student(name, lastName, id, group);
            university.addStudent(student);
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
