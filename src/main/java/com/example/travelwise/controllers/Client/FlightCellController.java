package com.example.travelwise.controllers.Client;

import com.example.travelwise.models.FlightModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FlightCellController implements Initializable {


    @javafx.fxml.FXML
    private Label des_lbl;
    @javafx.fxml.FXML
    private Label price_lbl;
    @javafx.fxml.FXML
    private Label status_lbl;
    @javafx.fxml.FXML
    private Label airline_lbl;
    @javafx.fxml.FXML
    private Label origin_lbl;
    @javafx.fxml.FXML
    private Label depart_lbl;
    @javafx.fxml.FXML
    private Button view_btn;


    private final  FlightModel flightModel;

    public FlightCellController(FlightModel flightModel) {
        this.flightModel = flightModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        des_lbl.setText(flightModel.getDestination());
        origin_lbl.setText(flightModel.getOrigin());
        price_lbl.setText(String.valueOf(flightModel.getPrice()));
        status_lbl.setText(flightModel.getStatus());
        depart_lbl.setText(flightModel.getDepartureDate().toString());
        airline_lbl.setText(flightModel.getAirline());

        status_lbl.getStyleClass().add("status_badge");
        if (Objects.equals(flightModel.getStatus(), "Cancelled")){
            status_lbl.getStyleClass().add("status_cancelled");
        }else if (Objects.equals(flightModel.getStatus(), "Scheduled")){
            status_lbl.getStyleClass().add("status_scheduled");
        }else { status_lbl.getStyleClass().clear();}




//        view_btn.setOnAction(event -> {
//            // Call the reservation screen
//            Model.getInstance().getViewFactory().showReservationFlightView(flightModel);
//        });
    }
}
