package com.example.travelwise;

import com.example.travelwise.models.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


    public class App extends Application {
        @Override
        public void start(Stage stage) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Fxml/Agences/agency_acc.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Accommodation ");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        }

        public static void main(String[] args) {
            launch();
        }
    }