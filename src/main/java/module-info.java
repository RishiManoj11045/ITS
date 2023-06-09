module com.example.page12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.page12 to javafx.fxml;
    exports com.example.page12;
}