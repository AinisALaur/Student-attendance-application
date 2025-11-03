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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class DataController {
    @FXML
    ChoiceBox<String> importChoiceBox;

    @FXML
    ChoiceBox<String> exportChoiceBox;

    @FXML
    TextField importFilePath;

    @FXML
    TextField exportFilePath;

    University university = University.getInstance();
    ArrayList<Student> students = new ArrayList<>(university.getStudents());

    String filePathExport = "";
    String filePathImport = "";

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
        fileChooser.setTitle("Open Data File");
        String dataFilter = importChoiceBox.getValue().toLowerCase();

        fileChooser.setTitle("Save File As");
        fileChooser.setInitialFileName("example." + dataFilter);
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Data files", "*."+dataFilter)
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            importFilePath.setText(file.getAbsolutePath());
            filePathImport = file.getAbsolutePath();
        }
    }

    public void browseExport(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        String dataFilter = exportChoiceBox.getValue().toLowerCase();

        fileChooser.setTitle("Save File As");
        fileChooser.setInitialFileName("example." + dataFilter);
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Data files", "*."+dataFilter)
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            exportFilePath.setText(file.getAbsolutePath());
            filePathExport = file.getAbsolutePath();
        }
    }

    public void importData(){
        DataImporter dataImporter = null;
        if(importChoiceBox.getValue().equals("XLSX")){
            dataImporter = new DataImporterExcel();
        }else{
            dataImporter = new DataImporterCSV();
        }
        ArrayList<Student> students = dataImporter.getStudents(filePathImport);
        if(students == null){
            return;
        }

        university.clear();
        for(Student student : students){
            university.addStudent(student);
            if(!university.getGroups().contains(student.getGroup())){
                university.addGroup(student.getGroup());
            }
        }
    }

    public void exportData(){
        DataExporter dataexporter = null;
        if(exportChoiceBox.getValue().equals("XLSX")){
            dataexporter = new DataExporterExcel();
        }else{
            dataexporter = new DataExporterCSV();
        }

        dataexporter.exportStudents(students, filePathExport);
    }


}
