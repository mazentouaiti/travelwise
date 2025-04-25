package com.example.hotels.controllers;

import com.example.hotels.models.Hebergement;
import com.example.hotels.services.ServiceHebergement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HotelUserr {

    @FXML
    private TableView<Hebergement> tableaccuser;
    @FXML
    private TableColumn<Hebergement, String> colName;
    @FXML
    private TableColumn<Hebergement, String> colType;
    @FXML
    private TableColumn<Hebergement, String> colCountry;
    @FXML
    private TableColumn<Hebergement, String> colCity;
    @FXML
    private TableColumn<Hebergement, Double> colPrice;
    @FXML
    private TableColumn<Hebergement, Integer> colCapacity;
    @FXML
    private TableColumn<Hebergement, Boolean> colDispo;
    @FXML
    private TableColumn<Hebergement, Integer> colRating;


//affichaaageeeee

    public void initialize() {
        // Initialisation des colonnes
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colDispo.setCellValueFactory(new PropertyValueFactory<>("disponibility"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        // Charger les hébergements
        loadHebergements();
    }



    private void loadHebergements() {
        ServiceHebergement service = new ServiceHebergement();
        List<Hebergement> allHebergements = service.afficher();

        // Filtrer les hébergements avec status "accepted"
        List<Hebergement> acceptedHebergements = allHebergements.stream()
                .filter(h -> "accepted".equalsIgnoreCase(h.getStatus()))
                .collect(Collectors.toList());

        ObservableList<Hebergement> list = FXCollections.observableArrayList(acceptedHebergements);
        tableaccuser.setItems(list);
    }





}
