package com.example.travelwise.Utiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/travelwise";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connexion à la base réussie !");
            } catch (SQLException e) {
                System.err.println("❌ Erreur de connexion à la base : " + e.getMessage());
            }
        }
        return connection;
    }


}
