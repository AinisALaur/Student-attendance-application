package com.example.studentattendance;

import java.util.ArrayList;

public interface DataExporter {
    public void exportStudents(ArrayList<Student> students, String filePath);
}
