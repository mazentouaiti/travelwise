package com.example.travelwise.controllers.Company;

import com.example.travelwise.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CompanyController implements Initializable {
    public BorderPane company_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getCompanySelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case FLIGHT -> company_parent.setCenter(Model.getInstance().getViewFactory().getCompanyFlightsView());
                default -> company_parent.setCenter(null);
            }
        });
    }
}
