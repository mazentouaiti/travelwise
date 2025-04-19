package com.example.travelwise.controllers.Admin;

import com.example.travelwise.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button dash_admin;
    public Button flights_admin;
    public Button hotels_admin;
    public Button transport_admin;
    public Button offers_admin;
    public Button clients_admin;
    public Button agences_admin;
    public Button feed_admin;
    public Button stats_admin;
    public Button chats_admin;
    public Button logout_admin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addListeners();
    }

    private void addListeners() {
        dash_admin.setOnAction(event -> onDashboard());
        flights_admin.setOnAction(event -> onFlights());
        hotels_admin.setOnAction(event -> onHotels());
        transport_admin.setOnAction(event -> onTransport());
        offers_admin.setOnAction(event -> onOffers());
        clients_admin.setOnAction(event -> onClients());
        agences_admin.setOnAction(event -> onAgences());
        feed_admin.setOnAction(event -> onFeed());
        stats_admin.setOnAction(event -> onStats());
        chats_admin.setOnAction(event -> onChats());
        logout_admin.setOnAction(event -> onLogout());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Dashboard");
    }

    private void onFlights() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Flights");
    }

    private void onHotels() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Hotels");
    }

    private void onTransport() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Transport");
    }

    private void onOffers() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Offers");
    }

    private void onClients() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Clients");
    }

    private void onAgences() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Agences");
    }

    private void onFeed() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Feedback");
    }

    private void onStats() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Stats");
    }

    private void onChats() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Chats");
    }

    private void onLogout() {
        Stage stage = (Stage) logout_admin.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginView();
    }

}

