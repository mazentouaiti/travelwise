package com.example.travelwise.controllers.Agence;

import com.example.travelwise.models.Model;
import com.example.travelwise.views.AgencyMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgencyMenuController implements Initializable {
    @FXML
    public Button dash_agency;
    @FXML public Button flights_agency;
    @FXML public Button logout_btn;
    @FXML public Button report_btn;
    @FXML private Button acc_agency;
    @FXML private Button trans_agency;
    @FXML private Button stats_agency;
    @FXML private Button offers_agency;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
//        dash_agency.setOnAction(event -> onDashboard());
        flights_agency.setOnAction(event -> onFlights());
        // Add listeners for other buttons
        logout_btn.setOnAction(event -> onLogout());
    }

//    private void onDashboard() {
//        Model.getInstance().getViewFactory().getAgencySelectedMenuItem().set(AgencyMenuOptions.DASHBOARD);
//    }

    private void onFlights() {
        Model.getInstance().getViewFactory().getAgencySelectedMenuItem().set(AgencyMenuOptions.FLIGHTS);
    }

    private void onLogout() {
        // Get the stage from any of the buttons
        Stage stage = (Stage) flights_agency.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginView();
    }
}