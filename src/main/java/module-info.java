module com.example.travelwise {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;
    requires jdk.jsobject;
    requires javafx.web;
    requires org.json;


    opens com.example.travelwise to javafx.fxml;
    opens com.example.travelwise.controllers to javafx.fxml;
    opens com.example.travelwise.controllers.Admin to javafx.fxml;
    opens com.example.travelwise.controllers.Client to javafx.fxml;
    opens com.example.travelwise.controllers.Agence to javafx.fxml;

    opens com.example.travelwise.models to javafx.fxml;
    exports com.example.travelwise;
    exports com.example.travelwise.controllers;
    exports com.example.travelwise.views;
    exports com.example.travelwise.models;
    exports com.example.travelwise.controllers.Admin;
    exports com.example.travelwise.controllers.Client;
    exports com.example.travelwise.controllers.Agence;

}