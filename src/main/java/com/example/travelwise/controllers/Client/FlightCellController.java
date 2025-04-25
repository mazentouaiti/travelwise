package com.example.travelwise.controllers.Client;

import com.example.travelwise.models.FlightModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class FlightCellController implements Initializable {

    @FXML private Label des_lbl;
    @FXML private Label price_lbl;
    @FXML private Label status_lbl;
    @FXML private Label airline_lbl;
    @FXML private Label origin_lbl;
    @FXML private Label depart_lbl;
    @FXML private Button view_btn;

    private FlightModel flightModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        view_btn.setOnAction(event -> {
            if (flightModel != null) {
                openReservationWindow();
            }
        });
    }

    private void openReservationWindow() {
        try {
            // 1. Load the FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Fxml/Client/WindowReservation.fxml"));
            Parent root = loader.load();

            // 2. Get controller and set flight
            WindowReservationController controller = loader.getController();
            controller.setSelectedFlight(flightModel);

            // 3. Create and configure stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Flight Reservation");
            stage.initModality(Modality.APPLICATION_MODAL);

            // 4. Show window
            stage.show();

        } catch (IOException e) {
            System.err.println("Error loading WindowReservation.fxml");
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Window Error",
                    "Failed to open reservation window: " + e.getMessage());
        }
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void setFlight(FlightModel flightModel) {
        this.flightModel = flightModel;
        updateFlightData();
    }

    private void updateFlightData() {
        if (flightModel != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            des_lbl.setText(flightModel.getDestination());
            origin_lbl.setText(flightModel.getOrigin());
            price_lbl.setText(String.format("€%.2f", flightModel.getPrice()));
            status_lbl.setText(flightModel.getStatus());
            depart_lbl.setText(dateFormat.format(flightModel.getDepartureDate()));
            airline_lbl.setText(flightModel.getAirline());
        }
    }

    private void showErrorAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}