package symulator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class SymulatorController {

    // 1. Pola z widoku (muszą mieć te same nazwy co fx:id w FXML) [cite: 94-95]
    @FXML private TextField modelField;
    @FXML private TextField nrRejestracyjnyField;
    @FXML private TextField stanSilnikaField;
    @FXML private TextField biegField;
    @FXML private TextField obrotyField;
    @FXML private TextField sprzegloField;
    @FXML private ComboBox<String> carComboBox;
    @FXML private ImageView carImage;

    // Obiekt logiczny (Backend)
    private Samochod mojSamochod;

    // Metoda uruchamiana automatycznie po załadowaniu widoku
    @FXML
    public void initialize() {
        // Tworzymy domyślny samochód
        mojSamochod = new Samochod("Porsche", "KR 12345");

        // Ustawiamy początkowe wartości w polach
        modelField.setText("Porsche");
        nrRejestracyjnyField.setText("KR 12345");

        // Konfiguracja listy aut (Laboratorium wymaga ComboBoxa) [cite: 108]
        carComboBox.getItems().addAll("Porsche", "Fiat 126p");

        // Ładowanie obrazka (opcjonalne)
        try {
            InputStream is = getClass().getResourceAsStream("/car.png");
            if (is != null) carImage.setImage(new Image(is));
        } catch (Exception e) { System.out.println("Brak obrazka"); }

        odswiezWidok();
    }

    // --- Metody obsługi zdarzeń (Event Handlers) [cite: 91-97] ---

    @FXML
    private void onWlaczClick() {
        mojSamochod.wlacz();
        odswiezWidok();
    }

    @FXML
    private void onWylaczClick() {
        mojSamochod.wylacz();
        odswiezWidok();
    }

    @FXML
    private void onZwiekszBiegClick() {
        mojSamochod.getSkrzynia().zwiekszBieg();
        odswiezWidok();
    }

    @FXML
    private void onZmniejszBiegClick() {
        mojSamochod.getSkrzynia().zmniejszBieg();
        odswiezWidok();
    }

    @FXML
    private void onDodajGazuClick() {
        mojSamochod.getSilnik().zwiekszObroty(500);
        odswiezWidok();
    }

    @FXML
    private void onUjmijGazuClick() {
        mojSamochod.getSilnik().zmniejszObroty(500);
        odswiezWidok();
    }

    @FXML
    private void onWcisnijSprzegloClick() {
        mojSamochod.getSprzeglo().wcisnij();
        odswiezWidok();
    }

    @FXML
    private void onZwolnijSprzegloClick() {
        mojSamochod.getSprzeglo().zwolnij();
        odswiezWidok();
    }

    @FXML
    private void onCarSelect() {
        String wybrany = carComboBox.getValue();
        System.out.println("Wybrano: " + wybrany);
        // Tu można by podmieniać obiekt mojSamochod na nowy
    }

    // Metoda pomocnicza do aktualizacji wszystkich pól tekstowych naraz [cite: 112]
    private void odswiezWidok() {
        stanSilnikaField.setText(mojSamochod.getSilnik().getObroty() > 0 ? "Włączony" : "Wyłączony");
        biegField.setText(String.valueOf(mojSamochod.getSkrzynia().getAktBieg()));
        obrotyField.setText(String.valueOf(mojSamochod.getSilnik().getObroty()));
        sprzegloField.setText(mojSamochod.getSprzeglo().czyWcisniete() ? "Wciśnięte" : "Zwolnione");
    }
}