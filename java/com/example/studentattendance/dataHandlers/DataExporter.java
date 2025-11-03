package com.example.studentattendance.dataHandlers;

import com.example.studentattendance.Classes.Student;

import java.util.ArrayList;

public interface DataExporter {
    public void exportStudents(ArrayList<Student> students, String filePath);
}
