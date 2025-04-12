package com.example.travelwise.controllers.Client;

import com.example.travelwise.models.Model;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
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
    }
    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Dashboard");
    }
    private void onFlights() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Flights");
    }
}
