package com.example.sistemavendas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/pdv_hudson";
            String user = "root";
            String password = "";

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão: " + e.getMessage());
        }
    }
}