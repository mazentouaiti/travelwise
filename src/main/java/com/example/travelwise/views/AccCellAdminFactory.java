package com.example.travelwise.views;

import com.example.travelwise.controllers.Admin.AccCellAdmin;
import com.example.travelwise.controllers.Agence.AccCellController;
import com.example.travelwise.controllers.Agence.AccController;
import com.example.travelwise.models.Hebergement;
import com.example.travelwise.services.ServiceHebergement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;

public class AccCellAdminFactory extends ListCell<Hebergement> {

    @Override
    protected void updateItem(Hebergement hebergement, boolean empty) {
        super.updateItem(hebergement, empty);

        if (empty || hebergement == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotels/fxml/AccCell_admin.fxml"));
                Node cellView = loader.load();
                AccCellAdmin controller = loader.getController();
                controller.setHebergement(hebergement);

                // Supprimer cette ligne si ton FXML contient du texte
                setText(null);
                setGraphic(cellView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}



