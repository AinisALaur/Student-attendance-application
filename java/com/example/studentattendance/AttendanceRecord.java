package com.example.studentattendance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceRecord {
   LocalDate attendanceDate;

    public void setAttendance(LocalDate date){
        attendanceDate = date;
    }

    public LocalDate getAttendance(){
        return attendanceDate;
    }
}
