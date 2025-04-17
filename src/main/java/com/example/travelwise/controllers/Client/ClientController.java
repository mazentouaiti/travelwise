package com.example.travelwise.controllers.Client;

import com.example.travelwise.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Flights" -> client_parent.setCenter(Model.getInstance().getViewFactory().getFlightView());
                case "Profile" -> client_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
                case "Hotels" -> client_parent.setCenter(Model.getInstance().getViewFactory().getHotelsView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
