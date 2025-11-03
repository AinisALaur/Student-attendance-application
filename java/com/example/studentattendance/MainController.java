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
        ObservableList<TableInstance> displayContent = FXCollections.observableArrayList(filterByDate(studentsToAppend));
        displayContent.sort((a, b) -> a.getAttended().compareTo(b.getAttended()));
        studentsList.setItems(displayContent);
    }


    public void initialize(){
        AttendanceRecord attended = new AttendanceRecord();
        attended.setAttendance(LocalDate.of(2025, 11, 2));

        AttendanceRecord attended1 = new AttendanceRecord();
        attended1.setAttendance(LocalDate.of(2025, 10, 2));

        AttendanceRecord attended2 = new AttendanceRecord();
        attended2.setAttendance(LocalDate.of(2025, 9, 2));

        AttendanceRecord attended3 = new AttendanceRecord();
        attended3.setAttendance(LocalDate.of(2025, 8, 2));


        ArrayList<AttendanceRecord> attendance1 = new  ArrayList<>(){{
            add(attended);
            add(attended1);
        }};

        ArrayList<AttendanceRecord> attendance2 = new  ArrayList<>(){{
            add(attended1);
        }};

        ArrayList<AttendanceRecord> attendance3 = new  ArrayList<>(){{
            add(attended2);
        }};

        ArrayList<AttendanceRecord> attendance4 = new  ArrayList<>(){{
            add(attended3);
        }};

        student1.setAttendance(attendance1);
        student2.setAttendance(attendance2);
        student3.setAttendance(attendance3);
        student4.setAttendance(attendance4);

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

        displayTable(university.getStudents());
    }

    public void updateFilter(){
        String filter = filterList.getValue().toLowerCase();
        if(filter.equals("student") || filter.equals("group")){
            chooseText.setText("Choose " +  filter);
        }else{
            chooseText.setText("No filter has been chosen");
        }

        filterByList.getItems().clear();

        if(filter.equals("student")){
            for(Student student : university.getStudents()){
                filterByList.getItems().add(student.getInfo());
            }
        }

        if((filter.equals("group"))){
            for(String group : university.getGroups()){
                filterByList.getItems().add(group);
            }
        }
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

    private ArrayList<TableInstance> filterByDate(ArrayList<TableInstance> studentsToDisplay){
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();

        ArrayList<TableInstance> filteredStudents = new ArrayList<>(studentsToDisplay);

        if(start != null) {
            for(int i  = 0; i < filteredStudents.toArray().length; ++i){
                if(filteredStudents.get(i).getAttended().isBefore(start)){
                    filteredStudents.remove(i);
                    --i;
                }
            }
        }

        if(end != null) {
            for(int i  = 0; i < filteredStudents.toArray().length; ++i){
                if(filteredStudents.get(i).getAttended().isAfter(end)){
                    filteredStudents.remove(i);
                    --i;
                }
            }
        }

        return  filteredStudents;
    }

    public void clearStartDate(){
        startDate.setValue(null);
        activateFilter();
    }

    public void clearEndDate(){
        endDate.setValue(null);
        activateFilter();
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

    @FXML
    protected void onManageDataClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("export-import.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
