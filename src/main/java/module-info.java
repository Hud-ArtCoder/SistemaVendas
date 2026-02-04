module com.example.sistemavendas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.sistemavendas to javafx.fxml;
    opens com.example.sistemavendas.controller to javafx.fxml;

    exports com.example.sistemavendas;
    exports com.example.sistemavendas.controller;
}
