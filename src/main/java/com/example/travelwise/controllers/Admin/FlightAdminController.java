package com.example.travelwise.controllers.Admin;

import com.example.travelwise.Services.FlightServices;
import com.example.travelwise.models.FlightModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FlightAdminController implements Initializable {


    @FXML private ListView<FlightModel> listview_flights;

    private final FlightServices flightService = new FlightServices();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupListView();
        loadFlights();
    }

    private void setupListView() {
        listview_flights.setCellFactory(param -> new ListCell<FlightModel>() {
            private FXMLLoader loader;
            private AnchorPane cell;
            private FlightAdminCellController controller;

            {
                try {
                    loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/FlightAdminCell.fxml"));
                    cell = loader.load();
                    controller = loader.getController();
                    controller.setMainController(FlightAdminController.this);
                } catch (IOException e) {
                    e.printStackTrace();
                    setText("Error loading cell template");
                }
            }

            @Override
            protected void updateItem(FlightModel flight, boolean empty) {
                super.updateItem(flight, empty);
                if (empty || flight == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    controller.setFlightData(flight);
                    setGraphic(cell);
                }
            }
        });
    }



    @FXML
    public void loadFlights() {
        List<FlightModel> flightList = flightService.getAllFlights();
        ObservableList<FlightModel> observableList = FXCollections.observableArrayList(flightList);
        listview_flights.setItems(observableList);
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}