package com.example.studentattendance.dataHandlers;

import com.example.studentattendance.Classes.Student;

import java.util.ArrayList;

public interface DataImporter {
    public ArrayList<Student> getStudents(String filePath);
}
