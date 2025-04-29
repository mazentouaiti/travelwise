package com.example.travelwise.Utiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/airplan";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    private static DBConnection instance;

    private DBConnection() {
        try{
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("connected!");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
    public Connection getConnection() {return connection;}

}
