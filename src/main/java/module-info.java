module com.example.lostcat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lostcat to javafx.fxml;
    exports com.example.lostcat;
}