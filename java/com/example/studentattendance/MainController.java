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
    TableView<TableInstance> studentsList;

    @FXML
    TableColumn<TableInstance, String> studIdCol;

    @FXML
    TableColumn<TableInstance, String> studNameCol;

    @FXML
    TableColumn<TableInstance, String> studGroupCol;

    @FXML
    TableColumn<TableInstance, LocalDate> dateCol;

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

    Student student1 = new Student("Ainis", "Laurinavicius", "523487", "Informatikas");
    Student student2 = new Student("Sarunas", "Sinkevicius", "345998", "Informatikas");
    Student student3 = new Student("Arunas", "Kolabavicius", "948572", "BioInformatikas");
    Student student4 = new Student("Migle", "Alejunaite", "9087234", "BioInformatikas");

    private ArrayList<TableInstance> getTableInstances(ArrayList<Student> students){
        ArrayList<TableInstance> studentsToAppend = new ArrayList<>();
        for(Student student : students){
            for(AttendanceRecord attended : student.getAttendance()){
                TableInstance tableInstance = new TableInstance(student, attended.getAttendance());
                studentsToAppend.add(tableInstance);
            }
        }

        return studentsToAppend;
    }

    private void displayTable(ArrayList<Student> students){
        ArrayList<TableInstance> studentsToAppend = getTableInstances(students);
        studIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        studNameCol.setCellValueFactory(new PropertyValueFactory<>("info"));
        studGroupCol.setCellValueFactory(new PropertyValueFactory<>("group"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("attended"));
        ObservableList<TableInstance> displayContent = FXCollections.observableArrayList(studentsToAppend);
        if(displayContent.isEmpty()){
            return;
        }
        studentsList.setItems(displayContent);
    }


    public void initialize(){
        AttendanceRecord attended = new AttendanceRecord();
        attended.setAttendance(LocalDate.of(2025, 11, 2));

        ArrayList<AttendanceRecord> attendance = new  ArrayList<>(){{
            add(attended);
        }};

        student1.setAttendance(attendance);
        student2.setAttendance(attendance);
        student3.setAttendance(attendance);
        student4.setAttendance(attendance);

        university.addStudent(student1);
        university.addStudent(student2);
        university.addStudent(student3);
        university.addStudent(student4);

        university.addGroup("Informatikas");
        university.addGroup("BioInformatikas");

        ArrayList<String> filters = new ArrayList<>(){
            {
                add("Student");
                add("Group");
                add("No filter");
            }
        };

        filterList.getItems().addAll(filters);
        filterList.setValue("No filter");
        startDate.setValue(LocalDate.now());

        displayTable(university.getStudents());
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

        displayTable(university.getStudents());
    }

    public void activateFilter(){
        String filter = filterList.getValue();
        String choice = filterByList.getValue();

        if(filter.equals("No filter")){
            displayTable(university.getStudents());
            return;
        }

        ArrayList<Student> studentsToDisplay = new ArrayList<>();
        if(filter.equals("Student")){
            for(Student student : university.getStudents()){
                if(student.getInfo().equals(choice)){
                    studentsToDisplay.add(student);
                }
            }
        }else{
            for(Student student : university.getStudents()){
                if(student.getGroup().equals(choice)){
                    studentsToDisplay.add(student);
                }
            }
        }

        displayTable(studentsToDisplay);
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
