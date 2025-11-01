package com.example.studentattendance;

import java.text.DateFormat;
import java.util.ArrayList;

public class Student extends Person{
    private String id;
    private String group;
    private ArrayList<DateFormat> attendance;

    public Student(String name, String lastName, String id, String group) {
        this.name = name + " " + lastName;
        this.group = group;
        this.id = id;
    }

    public void setName(String name, String lastName) {
        this.name = name + " " + lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public void addAttendance(DateFormat date){
        attendance.add(date);
    }

    public void setAttendance(ArrayList<DateFormat> attendance){
        this.attendance = attendance;
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

    public String getId(){
        return id;
    }

    public String getGroup(){
        return group;
    }

    public ArrayList<DateFormat> getAttendance(){
        return attendance;
    }
}
