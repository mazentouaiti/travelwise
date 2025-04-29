package com.example.travelwise.controllers.Admin;

import com.example.travelwise.Services.FlightServices;
import com.example.travelwise.models.FlightModel;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FlightAdminController implements Initializable {


    @FXML private ListView<FlightModel> listview_flights;
    private boolean isToolbarVisible = false;
    private final FlightServices flightService = new FlightServices();
    @FXML
    private Button reject_all;
    @FXML
    private Button accept_all;
    @FXML
    private Button reset_btn;
    @FXML
    private Button toggleToolbarBtn;
    @FXML
    private VBox actionToolbar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionToolbar.setTranslateX(150);
        setupListView();
        loadFlights();

        // Make sure toggle button is in correct initial state
        Line middleLine = (Line)((StackPane)toggleToolbarBtn.getGraphic()).getChildren().get(1);
        middleLine.setRotate(0);
    }
    @FXML
    private void toggleToolbar(ActionEvent actionEvent) {
        TranslateTransition slide = new TranslateTransition(Duration.millis(300), actionToolbar);

        if (isToolbarVisible) {
            // Hide toolbar (slide out to right)
            slide.setToX(150); // Matches toolbar width
        } else {
            // Show toolbar (slide in from right)
            slide.setToX(0);
        }

        // Create parallel animation for the hamburger icon
        RotateTransition rotate = new RotateTransition(Duration.millis(300),
                ((StackPane)toggleToolbarBtn.getGraphic()).getChildren().get(1));

        if (isToolbarVisible) {
            rotate.setFromAngle(45);
            rotate.setToAngle(0);
        } else {
            rotate.setFromAngle(0);
            rotate.setToAngle(45);
        }

        // Play both animations together
        new ParallelTransition(slide, rotate).play();
        isToolbarVisible = !isToolbarVisible;
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
    @FXML
    private void onAcceptAllClicked() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Approval");
        confirmation.setHeaderText("Approve all pending flights");
        confirmation.setContentText("Are you sure you want to approve all pending flights?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            flightService.approveAllPendingFlights();
            showAlert("Success", "All pending flights have been approved.");
            loadFlights(); // Refresh the list
        }
    }

    @FXML
    private void onRejectAllClicked() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Rejection");
        confirmation.setHeaderText("Reject all pending flights");
        confirmation.setContentText("Are you sure you want to reject all pending flights?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            flightService.rejectAllPendingFlights();
            showAlert("Success", "All pending flights have been rejected.");
            loadFlights(); // Refresh the list
        }
    }
    @FXML
    private void onResetAllClicked() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Reset All");
        confirmation.setHeaderText("Reset all flight statuses");
        confirmation.setContentText("Are you sure you want to reset ALL approved/rejected flights back to pending?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            flightService.resetAllFlightStatuses();
            showAlert("Success", "All flight statuses have been reset to pending.");
            loadFlights(); // Refresh the list
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}