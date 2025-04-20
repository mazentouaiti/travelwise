package com.example.travelwise.controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FlightAdminController implements Initializable {


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
