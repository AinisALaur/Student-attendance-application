package com.example.studentattendance;

import java.util.ArrayList;

public class University {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<String> groups = new ArrayList<>();

    private static University instance = null;
    private University(){}

    public static University getInstance(){
        if (instance == null)
            instance = new University();
        return instance;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
    }

    public void addGroup(String group){
        groups.add(group);
    }

    public void remGroup(String group){
        if(groups.contains(group)){
            groups.remove(group);
        }
    }

    public ArrayList<String> getGroups(){
        return groups;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }
}
