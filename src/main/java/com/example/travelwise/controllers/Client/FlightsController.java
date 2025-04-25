package com.example.travelwise.controllers.Client;

import com.example.travelwise.models.FlightModel;
import com.example.travelwise.Services.FlightServices;
import com.example.travelwise.models.Model;
import com.example.travelwise.views.FlightCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FlightsController implements Initializable {

    @FXML private TextField depart_field;
    @FXML private TextField destin_field;
    @FXML private DatePicker depart_date;
    @FXML private ComboBox<String> combo_price;
    @FXML private Button search_btn;
    @FXML private ListView<FlightModel> flights_listview;

    private FlightServices flightServices;
    private ObservableList<FlightModel> flightsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            flightServices = new FlightServices();
            initializePriceComboBox();
            loadAllFlights();
            setupSearchButton();
        } catch (Exception e) {
            showErrorAlert("Initialization Error", "Failed to initialize controller: " + e.getMessage());
        }
    }

    private void initializePriceComboBox() {
        combo_price.getItems().addAll("All", "Under 100€", "100–300€", "Above 300€");
        combo_price.setValue("All");
    }

    private void loadAllFlights() {
        try {
            List<FlightModel> flights = flightServices.getAllFlights();
            flightsList = FXCollections.observableArrayList(flights);
            flights_listview.setItems(flightsList);
            flights_listview.setCellFactory(listView -> new FlightCellFactory());
        } catch (Exception e) {
            showErrorAlert("Loading Error", "Failed to load flights: " + e.getMessage());
        }
    }

    private void setupSearchButton() {
        search_btn.setOnAction(event -> searchFlights());
    }

    private void searchFlights() {
        try {
            String departure = depart_field.getText().trim();
            String destination = destin_field.getText().trim();
            LocalDate departureDate = depart_date.getValue();
            String priceFilter = combo_price.getValue();

            List<FlightModel> filteredFlights = flightServices.searchFlights(
                    departure.isEmpty() ? null : departure,
                    destination.isEmpty() ? null : destination,
                    departureDate != null ? Date.valueOf(departureDate) : null,
                    priceFilter
            );

            if (filteredFlights.isEmpty()) {
                showInformationAlert("No Results", "No flights match your search criteria");
            }

            flightsList = FXCollections.observableArrayList(filteredFlights);
            flights_listview.setItems(flightsList);
        } catch (Exception e) {
            showErrorAlert("Search Error", "Failed to search flights: " + e.getMessage());
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInformationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}