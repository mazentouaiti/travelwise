package com.example.travelwise.controllers;

import com.example.travelwise.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    
    public Label passenger_address_label;
    public TextField passenger_address_fld;
    public TextField pass_fld;
    public Button login_btn;
    public Label error_lbl;
    public ComboBox acc_selector;
    public Button signup_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(event -> onLogin());
        signup_btn.setOnAction(event -> onSignup());
    }
    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }
    private void onSignup() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showSignupView();
    }
}
