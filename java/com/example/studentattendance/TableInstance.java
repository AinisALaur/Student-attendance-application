package com.example.studentattendance;

import java.time.LocalDate;
import java.util.ArrayList;

public class TableInstance {
    private String name;
    private String lastName;
    private String id;
    private String group;
    private LocalDate attended;

    public TableInstance(Student student, LocalDate date){
        this.name = student.getName();
        this.lastName = student.getLastName();
        this.id = student.getId();
        this.group = student.getGroup();
        this.attended = date;
    }

    public String  getInfo(){
        return name + " " + lastName;
    }

    public String getGroup(){
        return group;
    }

    public String getId(){
        return id;
    }

    public LocalDate getAttended(){
        return attended;
    }
}
