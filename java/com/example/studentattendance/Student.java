package com.example.studentattendance;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person{
    private String id;
    private String group;
    private String lastName;
    private ArrayList<AttendanceRecord> attendance;

    public Student(String name, String lastName, String id, String group) {
        this.name = name;
        this.group = group;
        this.lastName = lastName;
        this.id = id;
    }

    public String getInfo(){
        return name + " " + lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public void addAttendance(AttendanceRecord date){
        attendance.add(date);
    }

    public void setAttendance(ArrayList<AttendanceRecord> attendance){
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

    public String getLastName(){
        return lastName;
    }

    public ArrayList<AttendanceRecord> getAttendance(){
        return attendance;
    }
}
