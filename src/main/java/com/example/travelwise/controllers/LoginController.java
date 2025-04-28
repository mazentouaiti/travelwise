package com.example.travelwise.controllers;

import com.example.travelwise.models.Model;
import com.example.travelwise.views.AccountType;
import javafx.collections.FXCollections;
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
    public ChoiceBox<AccountType> acc_selector;
    public Button signup_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.PASSENGER, AccountType.AGENCY, AccountType.COMPANY, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable->Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(event -> onLogin());
        signup_btn.setOnAction(event -> onSignup());
    }
    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        if (Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.PASSENGER) {
            Model.getInstance().getViewFactory().showClientWindow();
        }else if (Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.ADMIN) {
            Model.getInstance().getViewFactory().showAdminWindow();
        }else if (Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.AGENCY) {
            Model.getInstance().getViewFactory().showAgencyWindow();
        } else if (Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.COMPANY) {
            Model.getInstance().getViewFactory().showCompanyWindow();
        }
    }
    private void onSignup() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showSignupView();
    }
}
