package com.example.travelwise.controllers.Agence;

import com.example.travelwise.models.Hebergement;
import com.example.travelwise.Services.ServiceHebergement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccController implements Initializable {

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
    private ServiceHebergement service;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = new ServiceHebergement();

        listHebergement.setCellFactory(lv -> new ListCell<Hebergement>() {
            @Override
            protected void updateItem(Hebergement hebergement, boolean empty) {
                super.updateItem(hebergement, empty);
                if (empty || hebergement == null) {
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotels/fxml/AccCell_agency.fxml"));
                        AnchorPane pane = loader.load();
                        AccCellController controller = loader.getController();

                        controller.setListHebergement(listHebergement);
                        controller.setHebergement(hebergement);

                        setGraphic(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        loadHebergements();

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
            listHebergement.setItems(list);
        } catch (Exception e) {
            showErrorAlert("Loading Error", "Failed to load hébergements: " + e.getMessage());
        }
    }

    public void ouvrirFenetreDetails(Hebergement hebergement) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotels/fxml/hotel_info_agence.fxml"));
            Parent root = loader.load();

            HotelInfo controller = loader.getController();
            controller.setHebergementDetails(hebergement);

            Stage stage = (Stage) listHebergement.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switch_add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotels/fxml/hotel_add.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void onReset(ActionEvent actionEvent) {
        // Réinitialiser les filtres si nécessaire
        //namefilteragence.clear();
        countryfilteragence.clear();
        dispocomboagence.getSelectionModel().clearSelection();
        //typecombifilteragence.getSelectionModel().clearSelection();
        loadHebergements();
    }
}
