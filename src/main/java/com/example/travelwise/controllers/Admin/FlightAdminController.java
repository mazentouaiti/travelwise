package com.example.travelwise.controllers.Admin;
import javafx.animation.*;
import com.example.travelwise.Services.FlightServices;
import com.example.travelwise.models.FlightModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane actionToolbar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionToolbar.setTranslateX(actionToolbar.getWidth());
        // Initialize buttons as invisible and disabled
        accept_all.setOpacity(0);
        reject_all.setOpacity(0);
        reset_btn.setOpacity(0);
        accept_all.setDisable(true);
        reject_all.setDisable(true);
        reset_btn.setDisable(true);
        setupListView();
        loadFlights();
    }
    @FXML
    private void toggleToolbar(ActionEvent actionEvent) {
        if (isToolbarVisible) {
            hideToolbar();
        } else {
            showToolbar();
        }
        isToolbarVisible = !isToolbarVisible;
    }

    private void showToolbar() {
        // Slide in the toolbar
        TranslateTransition slide = new TranslateTransition(Duration.millis(300), actionToolbar);
        slide.setToX(0);

        // Reset opacity for animation (buttons start invisible)
        accept_all.setOpacity(0);
        reject_all.setOpacity(0);
        reset_btn.setOpacity(0);

        // Enable buttons for interaction
        accept_all.setDisable(false);
        reject_all.setDisable(false);
        reset_btn.setDisable(false);

        // Create sequential animations
        SequentialTransition buttonSequence = new SequentialTransition(
                new PauseTransition(Duration.millis(100)),
                createFadeAnimation(accept_all),
                new PauseTransition(Duration.millis(100)),
                createFadeAnimation(reject_all),
                new PauseTransition(Duration.millis(100)),
                createFadeAnimation(reset_btn)
        );

        // Play both animations together
        new ParallelTransition(slide, buttonSequence).play();
    }

    private FadeTransition createFadeAnimation(Button button) {
        FadeTransition fade = new FadeTransition(Duration.millis(200), button);
        fade.setFromValue(0);
        fade.setToValue(1);
        return fade;
    }

    private void hideToolbar() {
        // Slide out the toolbar
        TranslateTransition slide = new TranslateTransition(Duration.millis(300), actionToolbar);
        slide.setToX(actionToolbar.getWidth());

        // Fade out all buttons simultaneously
        ParallelTransition fadeOut = new ParallelTransition(
                createFadeOutAnimation(accept_all),
                createFadeOutAnimation(reject_all),
                createFadeOutAnimation(reset_btn)
        );

        // Disable buttons during hide
        fadeOut.setOnFinished(e -> {
            accept_all.setDisable(true);
            reject_all.setDisable(true);
            reset_btn.setDisable(true);
        });

        new ParallelTransition(slide, fadeOut).play();
    }

    private FadeTransition createFadeOutAnimation(Button button) {
        FadeTransition fade = new FadeTransition(Duration.millis(150), button);
        fade.setToValue(0);
        return fade;
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