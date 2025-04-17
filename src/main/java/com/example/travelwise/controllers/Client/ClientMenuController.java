package com.example.travelwise.controllers.Client;

import com.example.travelwise.models.Model;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button flights_btn;
    public Button acc_btn;
    public Button trans_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;
    public Button dash_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addListeners();
    }
    private void addListeners() {
        dash_btn.setOnAction(event -> onDashboard());
        flights_btn.setOnAction(event -> onFlights());
        acc_btn.setOnAction(actionEvent -> onHotels());
        profile_btn.setOnAction(actionEvent -> onProfile());
        logout_btn.setOnAction(actionEvent -> onLogout());
    }
    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Dashboard");
    }
    private void onFlights() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Flights");
    }
    private void onHotels(){Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Hotels");}
    private void onProfile() {Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Profile");}
    private void onLogout() {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginView();
    }
}
