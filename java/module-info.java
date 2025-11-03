module com.example.studentattendance {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires org.apache.poi.ooxml;


    opens com.example.studentattendance to javafx.fxml;
    exports com.example.studentattendance;
    exports com.example.studentattendance.dataHandlers;
    opens com.example.studentattendance.dataHandlers to javafx.fxml;
    exports com.example.studentattendance.Controllers;
    opens com.example.studentattendance.Controllers to javafx.fxml;
    exports com.example.studentattendance.Classes;
    opens com.example.studentattendance.Classes to javafx.fxml;
}