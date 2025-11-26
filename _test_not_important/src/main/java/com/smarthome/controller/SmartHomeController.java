package com.smarthome.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SmartHomeController {

    @FXML
    private void initialize() {
        System.out.println("Smart Home UI załadowany pomyślnie!");
        // Tutaj dodaj logikę inicjalizacji
    }

    @FXML
    private void handleSettingsButton() {
        System.out.println("Ustawienia kliknięte");
    }

    @FXML
    private void handleUserButton() {
        System.out.println("Profil użytkownika kliknięty");
    }
}