package com.example.travelwise.controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class FlightsController implements Initializable {
    public TextField depart_field;
    public TextField destin_field;
    public DatePicker depart_date;
    public DatePicker return_date;
    public ComboBox combo_price;
    public Button search_btn;
    public TableView tableview_flights;
    public Button reserv_btn;
    public Button pay_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
