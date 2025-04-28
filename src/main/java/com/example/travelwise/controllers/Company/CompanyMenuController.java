package com.example.travelwise.controllers.Company;

import com.example.travelwise.models.Model;
import com.example.travelwise.views.CompanyMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class CompanyMenuController implements Initializable {
    public Button dash_comp;
    public Button flights_comp;
    public Button acc_comp;
    public Button trans_comp;
    public Button offers_comp;
    public Button profile_comp;
    public Button logout_btn;
    public Button report_btn;

    private final Map<Button , CompanyMenuOptions> buttonMenuMap = new HashMap<>();
    private List<Button> menuButtons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuButtons();
        addButtonListeners();
        setActiveButton(flights_comp);
        Model.getInstance().getViewFactory().getCompanySelectedMenuItem().set(CompanyMenuOptions.FLIGHT);
    }
    private void initMenuButtons() {
        buttonMenuMap.put(dash_comp, CompanyMenuOptions.DASHBOARD);
        buttonMenuMap.put(flights_comp, CompanyMenuOptions.FLIGHT);
        buttonMenuMap.put(acc_comp, CompanyMenuOptions.HOTELS);
        buttonMenuMap.put(profile_comp, CompanyMenuOptions.PROFILE);

        menuButtons = Arrays.asList(
                dash_comp,flights_comp,acc_comp,profile_comp
        );
    }
    private void addButtonListeners() {
        for (Map.Entry<Button, CompanyMenuOptions> entry : buttonMenuMap.entrySet()) {
            Button button = entry.getKey();
            CompanyMenuOptions menuOption = entry.getValue();
            button.setOnAction(event -> {
                Model.getInstance().getViewFactory().getCompanySelectedMenuItem().set(menuOption);
                setActiveButton(button);
            });
        }

        logout_btn.setOnAction(event -> handleLogout());
    }
    private void setActiveButton(Button activeButton) {
        for (Button btn : menuButtons) {
            btn.getStyleClass().remove("active");
        }
        if (!activeButton.getStyleClass().contains("active")) {
            activeButton.getStyleClass().add("active");
        }
    }
    private void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText("Confirmer la déconnexion");
        alert.setContentText("Voulez-vous vraiment vous déconnecter ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) logout_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
            Model.getInstance().getViewFactory().showLoginView();
        }
    }
}
