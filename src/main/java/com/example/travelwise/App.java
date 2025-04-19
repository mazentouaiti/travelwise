package com.example.travelwise;

import com.example.travelwise.models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
   /* @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showLoginView();
    }*/

    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showAdminView();
    }

}
