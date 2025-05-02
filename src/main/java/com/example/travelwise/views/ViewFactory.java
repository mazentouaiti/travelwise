package com.example.travelwise.views;

import com.example.travelwise.controllers.Admin.AdminController;
import com.example.travelwise.controllers.Agence.AgencyController;
import com.example.travelwise.controllers.Client.ClientController;
import com.example.travelwise.controllers.Client.WindowReservationController;
import com.example.travelwise.controllers.ChatbotController;
import com.example.travelwise.models.FlightModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AccountType loginAccountType;
    // client views
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane flightView;
    private AnchorPane profileView;
    private AnchorPane hotelsView;
    private AnchorPane chatbotView;

    //AdminViews
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane FlightAdminView;
    //AgencyViews
    private final ObjectProperty<AgencyMenuOptions> agencySelectedMenuItem;
    private AnchorPane agencyFlightsView;

    //viewFactory
    public ViewFactory() {
        this.loginAccountType = AccountType.PASSENGER;
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.agencySelectedMenuItem = new SimpleObjectProperty<>();

    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }
//***************************************************************************************
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
//************************************************************************************************
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
    public void showReservationFlightView(FlightModel selectedFlight) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/windowReservation.fxml"));
            Parent root = loader.load();
            WindowReservationController controller = loader.getController();
            controller.setSelectedFlight(selectedFlight);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reservation");

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//*******************************************************************************************************
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
//*********************************************************************************************************
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
//************************************************************************************************************
    public void showLoginView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }
    //************************************************************************************************************
    public void showSignupView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Signup.fxml"));
        createStage(loader);
    }
    //************************************************************************************************************
    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }
    //************************************************************************************************************
    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }
    //********************************************************************************************
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    //************************************************************************************************************
    public ObjectProperty<AgencyMenuOptions> getAgencySelectedMenuItem() { return agencySelectedMenuItem; }
    public AnchorPane getAgencyFlightsView() {
        if (agencyFlightsView == null) {
            try {
                agencyFlightsView = new FXMLLoader(getClass().getResource("/Fxml/Agences/FlightAgency.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return agencyFlightsView;
    }

    //Agency window
    public void showAgencyWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Agences/Agency.fxml"));
        AgencyController agencyController = new AgencyController();
        loader.setController(agencyController);
        createStage(loader);
    }


    // ************************************************************************************************************
    public AnchorPane getChatbotView() {
        if (chatbotView == null) {
            try {
                chatbotView = new FXMLLoader(getClass().getResource("/Fxml/chatbot.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chatbotView;
    }
    public void showChatbotWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml//chatbot.fxml"));
        ChatbotController chatbotController = new ChatbotController();
        loader.setController(chatbotController);
        createStage(loader);
    }

    // ************************************************************************************************************
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("TravelWise");
        stage.show();
    }
    public void closeStage(Stage stage){
        stage.close();
    }
}
