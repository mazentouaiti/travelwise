package com.example.travelwise.controllers.Admin;

import com.example.travelwise.models.Hebergement;
import com.example.travelwise.services.ServiceHebergement;
import com.example.travelwise.views.AccCellAdminFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccAdmin implements Initializable {
    @FXML
    private TextField namefilteragence;
    @FXML
    private ComboBox<String> dispocomboagence;
    @FXML
    private TextField countryfilteragence;
    @FXML
    private ComboBox<String> typecombifilteragence;
    @FXML
    private ListView<Hebergement> listHebergement;

    private ObservableList<Hebergement> list;
    private FilteredList<Hebergement> filteredList;
    private ServiceHebergement service;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            service = new ServiceHebergement();
            loadHebergements();
            setupSearchFilters();
        } catch (Exception e) {
            showErrorAlert("Initialization Error", "Failed to initialize controller: " + e.getMessage());
        }

        listHebergement.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Hebergement selected = listHebergement.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    ouvrirFenetreDetails(selected);
                }
            }
        });
    }

    private void loadHebergements() {
        try {
            List<Hebergement> hebergements = service.afficher();
            list = FXCollections.observableArrayList(hebergements);
            filteredList = new FilteredList<>(list, p -> true);

            listHebergement.setItems(filteredList);
            listHebergement.setCellFactory(param -> new AccCellAdminFactory());
        } catch (Exception e) {
            showErrorAlert("Loading Error", "Failed to load hebergements: " + e.getMessage());
        }
    }

    private void setupSearchFilters() {
        namefilteragence.textProperty().addListener((obs, oldValue, newValue) -> applySearch());
        countryfilteragence.textProperty().addListener((obs, oldValue, newValue) -> applySearch());
        dispocomboagence.valueProperty().addListener((obs, oldValue, newValue) -> applySearch());
        typecombifilteragence.valueProperty().addListener((obs, oldValue, newValue) -> applySearch());
        typecombifilteragence.getItems().addAll("Hotel", "House", "Apartment", "Villa","Hostel","Bungalow");
        dispocomboagence.getItems().addAll("waiting", "accepted", "rejected");
    }

    private void applySearch() {
        String nameFilter = namefilteragence.getText().toLowerCase();
        String countryFilter = countryfilteragence.getText().toLowerCase();
        String dispoFilter = dispocomboagence.getValue() != null ? dispocomboagence.getValue().toLowerCase() : "";
        String typeFilter = typecombifilteragence.getValue() != null ? typecombifilteragence.getValue().toLowerCase() : "";

        filteredList.setPredicate(hebergement -> {
            boolean matchName = hebergement.getName().toLowerCase().contains(nameFilter);
            boolean matchCountry = hebergement.getCountry().toLowerCase().contains(countryFilter);
            boolean matchDispo = dispoFilter.isEmpty() || hebergement.getStatus().toLowerCase().contains(dispoFilter);
            boolean matchType = typeFilter.isEmpty() || hebergement.getType().toLowerCase().contains(typeFilter);

            return matchName && matchCountry && matchDispo && matchType;
        });
    }

    public void ouvrirFenetreDetails(Hebergement hebergement) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotels/fxml/hotel_info_admin.fxml"));
            Parent root = loader.load();

            HotelInfoAdmin controller = loader.getController();
            controller.setHebergementDetails(hebergement);

            Stage stage = (Stage) listHebergement.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void onReset(ActionEvent actionEvent) {
        namefilteragence.clear();
        countryfilteragence.clear();
        dispocomboagence.setValue(null);
        typecombifilteragence.setValue(null);
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
