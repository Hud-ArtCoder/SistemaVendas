module com.example.sistemavendas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires mysql.connector.j;
    requires javafx.base;

    opens com.example.sistemavendas to javafx.fxml;
    opens com.example.sistemavendas.controller to javafx.fxml;
    opens com.example.sistemavendas.model to javafx.base;

    exports com.example.sistemavendas;
    exports com.example.sistemavendas.controller;
}
