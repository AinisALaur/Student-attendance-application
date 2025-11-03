module com.example.studentattendance {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;


    opens com.example.studentattendance to javafx.fxml;
    exports com.example.studentattendance;
}