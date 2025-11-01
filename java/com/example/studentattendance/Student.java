package com.example.studentattendance;

import java.text.DateFormat;
import java.util.ArrayList;

public class Student extends Person{
    private int id;
    private String group;
    private ArrayList<DateFormat> attendance;

    public Student(String name, String lastName, int id, String group) {
        this.name = name;
        this.lastName = lastName;
        this.group = group;
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public void addAttendance(DateFormat date){
        attendance.add(date);
    }

    public void removeAttendance(DateFormat date){
        if(!attendance.contains(date)){
            return;
        }
        attendance.remove(date);
    }

    public String getName(){
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    public int getId(){
        return id;
    }

    public String getGroup(){
        return group;
    }

    public ArrayList<DateFormat> getAttendance(){
        return attendance;
    }
}
