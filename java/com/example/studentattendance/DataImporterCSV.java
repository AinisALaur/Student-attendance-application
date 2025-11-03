package com.example.studentattendance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataImporterCSV implements DataImporter {
    @Override
    public ArrayList<Student> getStudents(String filePath) {
        String line;
        String splitBy = " ";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            ArrayList<Student> students = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(splitBy);
                Student student = new Student(values[0], values[1], values[2], values[3]);
                for(int i = 4; i < values.length; i++){
                    AttendanceRecord attendanceRecord = new AttendanceRecord();
                    attendanceRecord.setAttendance(LocalDate.parse(values[i]));
                    if(attendanceRecord.getAttendance() != null){
                        student.addAttendance(attendanceRecord);
                    }else{
                        break;
                    }
                }
                students.add(student);
            }
            return students;
        } catch (IOException e) {
            return null;
        }
    }
}
