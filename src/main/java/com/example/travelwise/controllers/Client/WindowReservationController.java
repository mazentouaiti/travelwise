package com.example.travelwise.controllers.Client;

import com.example.travelwise.models.FlightModel;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowReservationController implements Initializable {


    @javafx.fxml.FXML
    private Button cancel_btn;
    @javafx.fxml.FXML
    private Button confirm_btn;
    @javafx.fxml.FXML
    private TextField num_field;
    @javafx.fxml.FXML
    private TextField id_field;
    @javafx.fxml.FXML
    private Spinner passanger_number;
    @javafx.fxml.FXML
    private TextField dest_field;
    @javafx.fxml.FXML
    private TextField status_field;
    @javafx.fxml.FXML
    private TextField origin_field;
    @javafx.fxml.FXML
    private TextField depart_field;
    @javafx.fxml.FXML
    private ComboBox classcombo;
    @javafx.fxml.FXML
    private TextField autoprice;

    private FlightModel selectedFlight;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classcombo.getItems().addAll("Economy", "Business", "First Class");
        classcombo.setValue("Economy");

        passanger_number.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100)); // 100 as a temporary max value

    }
    public void setSelectedFlight(FlightModel flight) {
        this.selectedFlight = flight;
        fillFormWithFlightData();
    }
    private void fillFormWithFlightData() {
        if (selectedFlight != null) {
            num_field.setText(selectedFlight.getFlightNumber());
            id_field.setText(String.valueOf(selectedFlight.getFlight_id()));
            origin_field.setText(selectedFlight.getOrigin());
            dest_field.setText(selectedFlight.getDestination());
            status_field.setText(selectedFlight.getStatus());
            depart_field.setText(String.valueOf(selectedFlight.getDepartureDate()));


        }
    }
}
