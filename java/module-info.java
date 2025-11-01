module com.example.studentattendance {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;


    opens com.example.studentattendance to javafx.fxml;
    exports com.example.studentattendance;
}