package com.example.travelwise.controllers.Admin;

import com.example.travelwise.models.Hebergement;
import com.example.travelwise.services.ServiceHebergement;
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


public class HotelAdmin {

    public Button add_btn;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Hebergement> tableaccomodation;
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
    @FXML
    private TableColumn<Hebergement, Void> colActions;



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

        // Ajouter les actions (modifier / supprimer)
        colActions.setCellFactory(param -> new TableCell<Hebergement, Void>() {
            Button btnEdit = new Button();
            ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/com/example/hotels/images/modifier.png")));
            Button btnDelete = new Button();
            ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/com/example/hotels/images/annuler.png")));
            private final HBox pane = new HBox(10, btnEdit, btnDelete);

            {
                // Configurer les boutons
                editIcon.setFitWidth(16);
                editIcon.setFitHeight(16);
                btnEdit.setGraphic(editIcon);
                //btnEdit.setStyle("-fx-background-color: transparent;");

                deleteIcon.setFitWidth(16);
                deleteIcon.setFitHeight(16);
                btnDelete.setGraphic(deleteIcon);
                //btnDelete.setStyle("-fx-background-color: transparent;");

                // Action Modifier
                btnEdit.setOnAction(event -> {
                    Hebergement selected = getTableView().getItems().get(getIndex());


                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotels/fxml/hotel_add.fxml"));
                        Parent root = loader.load();
                        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        // Passer l'hébergement au contrôleur
                        HotelAdd controller = loader.getController();
                        controller.initData(selected);

                        loadHebergements(); // rafraîchir après modif
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


                // Action Supprimer
                btnDelete.setOnAction(event -> {
                    Hebergement selected = getTableView().getItems().get(getIndex());
                    ServiceHebergement service = new ServiceHebergement();
                    service.supprimer(selected.getId());
                    getTableView().getItems().remove(selected);
                });

                pane.setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });
    }

    private void loadHebergements() {
        ServiceHebergement service = new ServiceHebergement();
        ObservableList<Hebergement> list = FXCollections.observableArrayList(service.afficher());
        tableaccomodation.setItems(list);
    }
        public void switch_add(ActionEvent event ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotels/fxml/hotel_add.fxml"));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



}
