package com.example.travelwise.views;

import com.example.travelwise.controllers.Client.FlightCellController;
import com.example.travelwise.models.FlightModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class FlightCellFactory extends ListCell<FlightModel> {

    @Override
    protected void updateItem(FlightModel flightModel, boolean empty) {
        super.updateItem(flightModel, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/FlightCell.fxml"));
            loader.setControllerFactory(param -> new FlightCellController(flightModel));
            setText(null);
            try {
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
