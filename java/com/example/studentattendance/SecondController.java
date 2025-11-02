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
    DatePicker attendanceDate;

    @FXML
    TableView<AttendanceRecord> attendanceList;
    @FXML
    TableColumn<AttendanceRecord, LocalDate> dateCol;

    @FXML
    Button remStudentBtn;

    @FXML
    Button updateStudentBtn;


    ArrayList<Student> students = university.getStudents();
    ArrayList<AttendanceRecord> attended = new ArrayList<>();

    Student selectedStudent = null;

    public void initialize(){
        if(students != null){
            for(Student student : students){
                String info = student.getName() + " " + student.getLastName();
                studentsList.getItems().add(info);
            }
        }
        studentsList.getItems().add("New student");
        remStudentBtn.setDisable(true);
    }

    private ArrayList<AttendanceRecord> copyAttendance(ArrayList<AttendanceRecord> attendance){
        ArrayList<AttendanceRecord> list = new ArrayList<>();
        list.addAll(attendance);
        return list;
    }

    public void handleStudentSelection(){
        String info1 = studentsList.getValue();

        if(info1.equals("New student")){
            selectedStudent = null;
            remStudentBtn.setDisable(true);
            studentName.setText("");
            studentLName.setText("");
            studentId.setText("");
            attended.clear();
            updateStudentBtn.setText("Add student");
        }else{
            remStudentBtn.setDisable(false);
            for(Student student : students){
                String info =  student.getName() + " " + student.getLastName();
                if(info.equals(info1)){
                    selectedStudent = student;
                }
            }
            updateStudentBtn.setText("Update student");

            studentName.setText(selectedStudent.getName());
            studentLName.setText(selectedStudent.getLastName());
            studentId.setText(selectedStudent.getId());
            attended = copyAttendance(selectedStudent.getAttendance());
        }

        ObservableList<AttendanceRecord> attendance = FXCollections.observableArrayList(attended);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        attendanceList.setItems(attendance);
    }


    private AttendanceRecord hasAttended(LocalDate date){
        for(AttendanceRecord record : attended){
            if(date.isEqual(record.getAttendance())){
                return record;
            }
        }
        return null;
    }

    public void addAttendance(){
        LocalDate date = attendanceDate.getValue();
        if(date == null){
            return;
        }

        if(hasAttended(date) == null){
            AttendanceRecord attendance = new AttendanceRecord();
            attendance.setAttendance(date);
            attended.add(attendance);
        }

        ObservableList<AttendanceRecord> attendance = FXCollections.observableArrayList(attended);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        attendanceList.setItems(attendance);
    }

    public void remAttendance(){
        LocalDate date = attendanceDate.getValue();
        if(date == null){
            return;
        }

        AttendanceRecord recordToRemove = hasAttended(date);

        if(recordToRemove != null){
            attended.remove(recordToRemove);
            ObservableList<AttendanceRecord> attendance = FXCollections.observableArrayList(attended);
            dateCol.setCellValueFactory(new PropertyValueFactory<>("attendance"));
            attendanceList.setItems(attendance);
        }
    }

    public void goBackToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleStudentManager(ActionEvent event) throws IOException {
        String name =  studentName.getText();
        String lastName = studentLName.getText();
        String id = studentId.getText();
        String group = "Not selected";

        if(selectedStudent == null){
            Student student = new Student(name, lastName, id, group);
            student.setAttendance(attended);
            university.addStudent(student);
        }else{
            selectedStudent.setName(name);
            selectedStudent.setLastName(lastName);
            selectedStudent.setId(id);
            selectedStudent.setGroup(group);
            selectedStudent.setAttendance(attended);
        }


        goBackToMain(event);
    }

    public void remStudentManager(ActionEvent event) throws IOException {
        university.removeStudent(selectedStudent);
        goBackToMain(event);
    }




}
