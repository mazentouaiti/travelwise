package com.example.travelwise.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/travelwise";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Force le chargement du driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("Pilote JDBC MySQL introuvable", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
