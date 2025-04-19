package com.example.travelwise.controllers.Admin;

import com.example.travelwise.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Flights" -> admin_parent.setCenter(Model.getInstance().getViewFactory().getFlightView());
                case "Profile" -> admin_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
                case "Hotels" -> admin_parent.setCenter(Model.getInstance().getViewFactory().getHotelsAdminView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}