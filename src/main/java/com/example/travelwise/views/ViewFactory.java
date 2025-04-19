package com.example.travelwise.views;

import com.example.travelwise.controllers.Client.ClientController;
import com.example.travelwise.controllers.SignupController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    // client views
    private final StringProperty clientSelectedMenuItem;
    private final StringProperty adminSelectedMenuItem;

    private AnchorPane dashboardView;
    private AnchorPane flightView;
    private AnchorPane profileView;
    private AnchorPane hotelsView;
    private AnchorPane signupView;

    public ViewFactory() {
        this.clientSelectedMenuItem = new SimpleStringProperty("");
        this.adminSelectedMenuItem = new SimpleStringProperty("");

    }

    public StringProperty getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }


    public StringProperty getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }



    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getFlightView() {
        if (flightView == null) {
            try{
                flightView = new FXMLLoader(getClass().getResource("/Fxml/Client/Flights.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return flightView;
    }

    public AnchorPane getHotelsView() {
        if (hotelsView == null) {
            try {
                hotelsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Hotels.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return hotelsView;
    }

    public AnchorPane getProfileView() {
        if (profileView == null) {
            try {
                profileView = new FXMLLoader(getClass().getResource("/Fxml/Client/Profile.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return profileView;
    }


    public void showLoginView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }
    public void showSignupView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Signup.fxml"));
        createStage(loader);
    }

    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }
    public void showAdminView(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    public AnchorPane getHotelsAdminView() {
        if (profileView == null) {
            try {
                profileView = new FXMLLoader(getClass().getResource("/Fxml/Admin/hotel_admin.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return profileView;
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("TravelWise");
        stage.setResizable(false);
        stage.show();
    }
    public void closeStage(Stage stage){
        stage.close();
    }
}
