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
    private Spinner<Integer> passenger_number;
    @javafx.fxml.FXML
    private TextField dest_field;
    @javafx.fxml.FXML
    private TextField status_field;
    @javafx.fxml.FXML
    private TextField origin_field;
    @javafx.fxml.FXML
    private TextField depart_field;
    @javafx.fxml.FXML
    private ComboBox<String> class_combo;
    @javafx.fxml.FXML
    private TextField auto_price;

    private FlightModel selectedFlight;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        class_combo.getItems().addAll("Economy", "Business", "First Class");
        class_combo.setValue("Economy");

       // class_combo.setOnAction(event -> updateTotalPrice());
       // passenger_number.valueProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());


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
            auto_price.setText(String.valueOf(selectedFlight.getPrice()));
            passenger_number.getValueFactory().setValue(1);
            ((SpinnerValueFactory.IntegerSpinnerValueFactory) passenger_number.getValueFactory())
                    .setMax(selectedFlight.getCapacity());

            passenger_number.valueProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
            class_combo.valueProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
            updateTotalPrice();

        }
    }
    private void updateTotalPrice() {
        if (selectedFlight == null) {
            return;
        } else {

            String classType = class_combo.getValue();
            int passangerNumber = passenger_number.getValue();
            double price = selectedFlight.getPrice();

            double miltiplier = switch (classType) {
                case "Business" -> 1.5;
                case "First Class" -> 2;
                default -> 1;
            };
            double totalPrice = price * miltiplier * passangerNumber;
            auto_price.setText(String.format("%.2f", totalPrice));
        }
    }
}
