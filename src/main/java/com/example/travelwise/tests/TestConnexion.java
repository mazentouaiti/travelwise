package com.example.travelwise.tests;

import com.example.travelwise.utils.DBConnection;

import java.sql.Connection;

public class TestConnexion {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("Connexion établie avec succès !");
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }
    }
}
