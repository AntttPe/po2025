package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SymulatorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Ładowanie widoku z pliku FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view.fxml"));
        BorderPane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Symulator Samochodu - Lab 7");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
