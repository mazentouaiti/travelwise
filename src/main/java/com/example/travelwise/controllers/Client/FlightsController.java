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
import javafx.scene.control.cell.PropertyValueFactory;
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
        @FXML
        private ListView flights_listview;



        private FlightServices flightServices;
        private ObservableList<FlightModel> flightsList;
        private FlightModel selectedFlight;
        private ToggleGroup toggleGroup = new ToggleGroup();


    @Override
         public void initialize(URL url, ResourceBundle resourceBundle) {
            flightServices = new FlightServices();
            initializePriceComboBox();
            loadAllFlights();
            setupSearchButton();
            //reserv_btn.setOnAction(event -> onReserveBtnClicked());
         }

        private void initializePriceComboBox() {
                combo_price.getItems().addAll("All", "Under 100€", "100–300€", "Above 300€");
                combo_price.setValue("All");
        }

        private void loadAllFlights() {
                List<FlightModel> flights = flightServices.getAllFlights();
                flightsList = FXCollections.observableArrayList(flights);
                flights_listview.setItems(flightsList);
                flights_listview.setCellFactory(listView -> new FlightCellFactory());

        }

        private void setupSearchButton() {
                search_btn.setOnAction(event -> searchFlights());
        }

        private void searchFlights() {
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

        flightsList = FXCollections.observableArrayList(filteredFlights);
        flights_listview.setItems(flightsList);
    }


        private void handleFlightSelection(FlightModel flight) {
                // Implement what happens when a flight is selected
            selectedFlight = flight;
            System.out.println("Selected flight: " + flight.getFlightNumber());
                // You can add the flight to a reservation cart or show details
        }
        private void onReserveBtnClicked() {
            if (selectedFlight != null) {
            Model.getInstance().getViewFactory().showReservationFlightView(selectedFlight);
        }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("No flight selected");
                alert.show();
            }
            }
}



