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
import java.time.LocalDate;
import java.util.ArrayList;

public class GroupController {
    University university = University.getInstance();

    @FXML
    ChoiceBox<String> groupsList;

    @FXML
    ChoiceBox<String> studentsList;

    @FXML
    Button remGroup;

    @FXML
    TextField groupName;

    @FXML
    TableView<Student> SelectedStudentsTable;

    @FXML
    TableColumn<Student, String> membersTable;

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

        studentsList.getItems().add("None");
        remGroup.setDisable(true);
    }

    public void groupHandler(){
        String selectedGroup = groupsList.getValue();
        if(selectedGroup.equals("New group")){
            remGroup.setDisable(true);
            studentsAddedToGroup.clear();
            groupName.setText("");
        }else{
            for(Student student : students){
                if(student.getGroup().equals(selectedGroup)){
                    studentsAddedToGroup.add(student);
                }
            }
            groupName.setText(selectedGroup);
            remGroup.setDisable(false);
        }
        ObservableList<Student> members = FXCollections.observableArrayList(studentsAddedToGroup);
        membersTable.setCellValueFactory(new PropertyValueFactory<>("info"));
        SelectedStudentsTable.setItems(members);
    }

    private boolean groupExists(String groupToCheck){
        for(String group : groups){
            if(group.equals(groupToCheck)){
                return true;
            }
        }
        return false;
    }

    public void goBackToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private Student getStudentByName(String name){
        for(Student student : students){
            if((student.getName() + " " +student.getLastName()).equals(name)){
                return student;
            }
        }
        return null;
    }

    public void addStudentToGroup(){
        String studentName = studentsList.getValue();
        Student student = getStudentByName(studentName);

        if(student == null || studentsAddedToGroup.contains(student)){
            return;
        }

        studentsAddedToGroup.add(student);
        ObservableList<Student> members = FXCollections.observableArrayList(studentsAddedToGroup);
        membersTable.setCellValueFactory(new PropertyValueFactory<>("info"));
        SelectedStudentsTable.setItems(members);
    }

    public void remStudentFromGroup(){
        String studentName = studentsList.getValue();
        Student student = getStudentByName(studentName);

        if(student == null || !studentsAddedToGroup.contains(student)){
            return;
        }

        student.setGroup("Not selected");
        studentsAddedToGroup.remove(student);
        ObservableList<Student> members = FXCollections.observableArrayList(studentsAddedToGroup);
        membersTable.setCellValueFactory(new PropertyValueFactory<>("info"));
        SelectedStudentsTable.setItems(members);
    }


    public void handleUpdateBtn(ActionEvent actionEvent) throws IOException {
        String nameOfGroup = groupName.getText();

        if(nameOfGroup == null){
            return;
        }

        if(!groupExists(nameOfGroup) || !groupsList.getValue().equals("New group")){
            university.addGroup(nameOfGroup);
            for(Student student : students){
                if(studentsAddedToGroup.contains(student)) {
                    student.setGroup(nameOfGroup);
                }
            }
            goBackToMain(actionEvent);
        }else{
            System.out.println("Group " + nameOfGroup + " already exists");
        }
    }

    public void deleteGroup(ActionEvent actionEvent) throws IOException {
        String groupToDelete = groupsList.getValue();
        if(groupToDelete == null){
            return;
        }

        for(Student student : students){
            if(student.getGroup().equals(groupToDelete)){
                student.setGroup("Not selected");
            }
        }

        studentsAddedToGroup.clear();
        university.remGroup(groupToDelete);
        goBackToMain(actionEvent);
    }


}
