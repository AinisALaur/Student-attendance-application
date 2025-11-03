package com.example.studentattendance;

import java.util.ArrayList;

public interface DataImporter {
    public ArrayList<Student> getStudents(String filePath);
}
