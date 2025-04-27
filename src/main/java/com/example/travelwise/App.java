package com.example.travelwise;

import com.example.travelwise.models.Model;
import com.example.travelwise.views.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showLoginView();

        //openTestWindows();
    }
    private void openTestWindows() {
        ViewFactory viewFactory = Model.getInstance().getViewFactory();

        // Open admin window
        viewFactory.showAdminWindow();

        // Open agency window
        viewFactory.showAgencyWindow();

        // Open client window
        viewFactory.showClientWindow();
    }
}
