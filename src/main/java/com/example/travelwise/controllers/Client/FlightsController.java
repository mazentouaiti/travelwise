package com.example.travelwise.controllers.Client;

import com.example.travelwise.models.FlightModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class FlightsController implements Initializable {

        @FXML
        private TextField depart_field;
        @FXML
        private TextField destin_field;
        @FXML
        private DatePicker depart_date;
        @FXML
        private DatePicker return_date;
        @FXML
        private ComboBox<String> combo_price;
        @FXML
        private Button search_btn;
        @FXML
        private TableView<FlightModel> tableview_flights;
        @FXML
        private TableColumn<FlightModel, String> airlinecol;
        @FXML
        private TableColumn<FlightModel, String> departurecol;
        @FXML
        private TableColumn<FlightModel, Date> departure_datecol;
        @FXML
        private TableColumn<FlightModel, Integer> capacitycol;
        @FXML
        private TableColumn<FlightModel, Double> pricecol;
        @FXML
        private TableColumn<FlightModel, Void> selectioncol;
        @FXML
        private Button reserv_btn;
        @FXML
        private Button pay_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


}
