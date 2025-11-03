package com.example.studentattendance.Classes;

import java.time.LocalDate;

public class AttendanceRecord {
   LocalDate attendanceDate;

    public void setAttendance(LocalDate date){
        attendanceDate = date;
    }

    public LocalDate getAttendance(){
        return attendanceDate;
    }
}
