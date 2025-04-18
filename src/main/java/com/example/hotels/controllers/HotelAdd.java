package com.example.hotels.controllers;

import com.example.hotels.models.Hebergement;
import com.example.hotels.services.ServiceHebergement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HotelAdd {
    @FXML private ComboBox<String> typeCombo;

    @FXML private TextField subjectField;
    @FXML private TextArea messageArea;
    @FXML private Label fileLabel;
    @FXML private HBox starBox;
    @FXML
    private Stage stage;
    @FXML private Scene scene;

    private File selectedFile;
    private IntegerProperty rating = new SimpleIntegerProperty(0);
    private List<String> selectedOptions = new ArrayList<>();

    public HotelAdd() {

    }

    @FXML
    public void initialize() {
        setupOptionCheckboxes();
        SpinnerValueFactory<Integer> capacityFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0); // min, max, valeur par défaut
        capacityspinner.setValueFactory(capacityFactory);
        typeCombo.getItems().addAll("Hotel", "House", "Apartment", "Villa","Hostel","Bungalow");

        // Setup stars
        int maxStars = 5;
        Label[] stars = new Label[maxStars];

        for (int i = 0; i < maxStars; i++) {
            final int index = i;
            Label star = new Label("☆");
            star.setStyle("-fx-font-size: 24px; -fx-text-fill: #f39c12; -fx-cursor: hand;");
            star.setOnMouseClicked(e -> rating.set(index + 1));
            stars[i] = star;
            starBox.getChildren().add(star);
        }

        rating.addListener((obs, oldVal, newVal) -> {
            for (int i = 0; i < maxStars; i++) {
                stars[i].setText(i < newVal.intValue() ? "★" : "☆");
            }
        });
    }

    @FXML
    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file");
        selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            fileLabel.setText(selectedFile.getName());
        } else {
            fileLabel.setText("No file selected");
        }
    }

    @FXML
    private void handleSubmit() {
        System.out.println("Type: " + typeCombo.getValue());
        System.out.println("Subject: " + subjectField.getText());
        System.out.println("Message: " + messageArea.getText());
        System.out.println("File: " + (selectedFile != null ? selectedFile.getAbsolutePath() : "None"));
        System.out.println("Rating: " + rating.get());
    }


    @FXML
    private void handleClear() {
        typeCombo.getSelectionModel().clearSelection();
        subjectField.clear();
        messageArea.clear();
        fileLabel.setText("No file selected");
        selectedFile = null;
        rating.set(0);
    }


    public void switch_admin(ActionEvent event ) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hotels/fxml/hotel_admin.fxml")));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML private TextField namefield;

    @FXML private TextField cityfield;
    @FXML private TextField addressfield;
    @FXML private TextField countryfield;
    @FXML private TextField pricefield;
    @FXML private CheckBox dispoCheck;
    @FXML private TextField photofield;
    @FXML private TextField albumfield;
    @FXML private TextArea descriptionfield;
    @FXML private TextField optionsfield;
    @FXML private HBox getStarBox;
    @FXML private Spinner capacityspinner;
    @FXML
    private CheckBox wifi, pool, meals, air, parking;



    private void setupOptionCheckboxes() {
        setupCheckboxListener(wifi, "Wi-Fi");
        setupCheckboxListener(pool, "Piscine");
        setupCheckboxListener(meals, "Repas");
        setupCheckboxListener(air, "Air Conditioning");
        setupCheckboxListener(parking, "Parking");
    }

    private void setupCheckboxListener(CheckBox checkbox, String label) {
        checkbox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                selectedOptions.add(label);
            } else {
                selectedOptions.remove(label);
            }
            // Affiche ou utilise la chaîne actuelle
            String joined = String.join(", ", selectedOptions);
            System.out.println("Options sélectionnées : " + joined);
        });

    }

    String options = String.join(", ", selectedOptions);



    @FXML
    private void handleSubmit(ActionEvent event) {
        try {
            // Récupération des valeurs des champs

            String name = namefield.getText();
            String type = typeCombo.getValue().toString(); // assure-toi que le ComboBox a des valeurs
            String city = cityfield.getText();
            String address = addressfield.getText();
            String country = countryfield.getText();
            double price = Double.parseDouble(pricefield.getText());
            //boolean dispo = dispoCheck.isSelected();
            //String photo = photofield.getText();
            //String album = albumfield.getText();
            String description = descriptionfield.getText();
            //int rating = getRatingFromStars(); // méthode à écrire
            int capacity = (int) capacityspinner.getValue();
            boolean disponibility;
            if (capacity==0)
                disponibility= false;
            else
                disponibility= true;

            String options = String.join(", ", selectedOptions);

            String photo = "chemin/";
            String album = "chemin/";

            int rating =5;
            // Création de l'objet
            Hebergement h = new Hebergement(name, type, city, address, country, price, disponibility, photo, album, description, options, rating, capacity);
            h.setOptions(options);
            // Appel du service
            ServiceHebergement sh = new ServiceHebergement();
            sh.ajouter(h);

            // Message de succès
            System.out.println("Ajout effectué avec succès !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getRatingFromStars() {
        int rating = 0;
        for (Node node : getStarBox.getChildren()) {
            if (node instanceof ImageView) {
                ImageView star = (ImageView) node;
                if (star.getStyleClass().contains("selected")) {
                    rating++;
                }
            }
        }
        return rating;
    }



}