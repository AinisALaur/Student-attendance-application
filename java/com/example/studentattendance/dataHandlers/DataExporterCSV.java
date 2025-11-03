package com.example.studentattendance.dataHandlers;

import com.example.studentattendance.Classes.AttendanceRecord;
import com.example.studentattendance.Classes.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataExporterCSV implements DataExporter {
    @Override
    public void exportStudents(ArrayList<Student> students, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for(Student student : students){
                String info = "";
                info += student.getName() + " " + student.getLastName() + " " + student.getId()
                        + " " + student.getGroup() + " ";
                for(AttendanceRecord attended : student.getAttendance()){
                    info += attended.getAttendance() + " ";
                }
                writer.write(info);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
