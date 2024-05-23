module com.example.salesdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.salesdb to javafx.fxml;
    exports com.example.salesdb;
    exports com.example.salesdb.controller;
    opens com.example.salesdb.controller to javafx.fxml;
}