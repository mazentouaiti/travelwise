module com.example.travelwise {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;

    opens com.example.travelwise to javafx.fxml;
    exports com.example.travelwise;
    exports com.example.travelwise.controllers;
    exports com.example.travelwise.views;
    exports com.example.travelwise.models;
    exports com.example.travelwise.controllers.Admin;
    exports com.example.travelwise.controllers.Client;
}