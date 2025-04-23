package com.example.travelwise.controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
