package com.example.travelwise.controllers.Admin;

import com.example.travelwise.Services.FlightServices;
import com.example.travelwise.models.FlightModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FlightAdminController implements Initializable {

/*
    public TextField id;
    public TextField flight_number;
    public TextField origin;
    public TextField destination;
    public TextField price;
    public TextField airline;
    public TextField status;
    public TextField aircraft;
    public TextField capacity;
    public TextField baggage;
    public Button add_btn;
    public Button update_btn;
    public Button delete_btn;
    public DatePicker depar;
    public DatePicker arriv;
    public TableColumn id_col;
    public TableColumn numbcol;
    public TableColumn orgincol;
    public TableColumn desticol;
    public TableColumn statcol;
    public TableColumn airlcol;
    public TableColumn aircrcol;
    public TableColumn capacol;
    public TableColumn baggcol;
    public TableColumn pricecol;
    public TableColumn deparcol;
    public TableColumn arrivcol;
    public TableView table_flight;
*/
    @FXML private TextField id;
    @FXML private TextField flight_number;
    @FXML private TextField origin;
    @FXML private TextField destination;
    @FXML private TextField price;
    @FXML private TextField airline;
    @FXML private ComboBox status;
    /*
    @FXML private TextField aircraft;
    @FXML private TextField capacity;
    @FXML private TextField baggage;

     */
    @FXML private DatePicker depar;
    @FXML private DatePicker arriv;
    @FXML private Button add_btn;
    @FXML private Button update_btn;
    @FXML private Button delete_btn;
    @FXML private TableView<FlightModel> table_flight;
    @FXML private TableColumn<FlightModel, Integer> id_col;
    @FXML private TableColumn<FlightModel, String> numbcol;
    @FXML private TableColumn<FlightModel, String> orgincol;
    @FXML private TableColumn<FlightModel, String> desticol;
    @FXML private TableColumn<FlightModel, String> statcol;
    @FXML private TableColumn<FlightModel, String> airlcol;
    @FXML private TableColumn<FlightModel, Double> pricecol;
    @FXML private TableColumn<FlightModel, Date> deparcol;
    @FXML private TableColumn<FlightModel, Date> arrivcol;

    private final FlightServices flightService = new FlightServices();


    private void setupTableColumns() {
        id_col.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("flight_id"));
        numbcol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("flightNumber"));
        orgincol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("origin"));
        desticol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("destination"));
        statcol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));
        airlcol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("airline"));
        pricecol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("price"));
        deparcol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("departureDate"));
        arrivcol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("returnDate"));
    }

    @FXML
    public void loadFlights() {
        List<FlightModel> flightList = flightService.getAllFlights();
        ObservableList<FlightModel> observableList = FXCollections.observableArrayList(flightList);
        table_flight.setItems(observableList);
    }

    @FXML
    public void addFlight() {
        FlightModel f = new FlightModel();
        f.setFlight_id(Integer.parseInt(id.getText()));
        f.setFlightNumber(flight_number.getText());
        f.setOrigin(origin.getText());
        f.setDestination(destination.getText());
        f.setAirline(airline.getText());
        f.setStatus((String) status.getValue());
        f.setPrice(Double.parseDouble(price.getText()));
        f.setDepartureDate(Date.valueOf(depar.getValue()));
        f.setReturnDate(Date.valueOf(arriv.getValue()));
        f.setClassType("Economy"); // or let user choose from dropdown

        flightService.addFlight(f);
        loadFlights();
        clearFields();
    }

    @FXML
    public void updateFlight() {
        FlightModel f = new FlightModel();
        f.setFlight_id(Integer.parseInt(id.getText()));
        f.setFlightNumber(flight_number.getText());
        f.setOrigin(origin.getText());
        f.setDestination(destination.getText());
        f.setAirline(airline.getText());
        f.setStatus((String) status.getValue());
        f.setPrice(Double.parseDouble(price.getText()));
        f.setDepartureDate(Date.valueOf(depar.getValue()));
        f.setReturnDate(Date.valueOf(arriv.getValue()));
        f.setClassType("Economy");

        flightService.updateFlight(f);
        loadFlights();
        clearFields();
    }

    @FXML
    public void deleteFlight() {
        int flightId = Integer.parseInt(id.getText());
        flightService.deleteFlight(flightId);
        loadFlights();
        clearFields();
    }

    private void clearFields() {
        id.clear();
        flight_number.clear();
        origin.clear();
        destination.clear();
        airline.clear();
        status.setItems(null);
        price.clear();
        depar.setValue(null);
        arriv.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        status.setItems(FXCollections.observableArrayList("Scheduled", "Delayed", "Cancelled", "Boarding", "Landed"));
        setupTableColumns();  // Don't forget this!
        loadFlights();        // Load data on startup


    }

}
