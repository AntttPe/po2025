package symulator;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert; // Import do obsługi błędów
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

// Kontroler implementuje Listener, żeby słuchać samochodu
public class SymulatorController implements Listener {

    @FXML private TextField modelField;
    @FXML private TextField nrRejestracyjnyField;
    @FXML private TextField stanSilnikaField;
    @FXML private TextField biegField;
    @FXML private TextField obrotyField;
    @FXML private TextField sprzegloField;

    @FXML private ComboBox<Samochod> carComboBox;
    @FXML private ImageView carImage;
    @FXML private StackPane mapaPane;

    private ObservableList<Samochod> listaSamochodow = FXCollections.observableArrayList();
    private Samochod mojSamochod;

    @FXML
    public void initialize() {
        // Ładowanie obrazka
        try {
            InputStream is = getClass().getResourceAsStream("/car.png");
            if (is != null) {
                carImage.setImage(new Image(is));
            }
        } catch (Exception e) {
            System.out.println("Brak obrazka");
        }

        // Tworzenie pierwszego auta (używa konstruktora domyślnego lub nowego)
        // Domyślnie 5 biegów
        Samochod domyslny = new Samochod("Porsche", "KR 12345", 1300, 7000, 5);
        dodajAutoDoListy(domyslny);

        carComboBox.setItems(listaSamochodow);

        // --- OBSŁUGA KLIKNIĘCIA W MAPĘ ---
        mapaPane.setOnMouseClicked(event -> {
            if (mojSamochod != null) {
                // Obliczamy współrzędne względem środka panelu
                double x = event.getX() - (mapaPane.getWidth() / 2);
                double y = event.getY() - (mapaPane.getHeight() / 2);

                // Rozkaz dla auta: jedź tam
                mojSamochod.jedzDo(new Pozycja(x, y));
            }
        });
    }

    // --- METODA Z INTERFEJSU LISTENER ---
    @Override
    public void update() {
        Platform.runLater(() -> {
            odswiezWidok();
        });
    }

    private void odswiezWidok() {
        if (mojSamochod != null) {
            // Aktualizacja tekstów
            modelField.setText(mojSamochod.getModel());
            nrRejestracyjnyField.setText(mojSamochod.getNrRejestracyjny());
            stanSilnikaField.setText(mojSamochod.getSilnik().getObroty() > 0 ? "Włączony" : "Wyłączony");
            biegField.setText(String.valueOf(mojSamochod.getSkrzynia().getAktBieg()));
            obrotyField.setText(String.valueOf(mojSamochod.getSilnik().getObroty()));
            sprzegloField.setText(mojSamochod.getSprzeglo().czyWcisniete() ? "Wciśnięte" : "Zwolnione");

            // Aktualizacja pozycji obrazka na ekranie
            carImage.setVisible(true);
            carImage.setTranslateX(mojSamochod.getAktPozycja().getX());
            carImage.setTranslateY(mojSamochod.getAktPozycja().getY());
        } else {
            // Jeśli brak samochodu (np. usunięto wszystkie), czyścimy widok
            wyczyscPola();
            carImage.setVisible(false);
        }
    }

    private void wyczyscPola() {
        modelField.setText("");
        nrRejestracyjnyField.setText("");
        stanSilnikaField.setText("");
        biegField.setText("");
        obrotyField.setText("");
        sprzegloField.setText("");
    }

    // --- Metody przycisków ---
    @FXML private void onWlaczClick() {
        if (mojSamochod != null) mojSamochod.wlacz();
    }

    @FXML private void onWylaczClick() {
        if (mojSamochod != null) mojSamochod.wylacz();
    }

    // NOWE: Obsługa zmiany biegów z wykrywaniem błędów (Exceptions)
    @FXML private void onZwiekszBiegClick() {
        if (mojSamochod == null) return;
        try {
            // Sprawdzamy, czy sprzęgło jest wciśnięte (logika biznesowa w GUI lub w modelu)
            if (!mojSamochod.getSprzeglo().czyWcisniete()) {
                throw new IllegalStateException("Aby zmienić bieg, wciśnij sprzęgło!");
            }
            mojSamochod.getSkrzynia().zwiekszBieg();
            mojSamochod.notifyListeners();
        } catch (Exception e) {
            pokazBlad(e.getMessage());
        }
    }

    @FXML private void onZmniejszBiegClick() {
        if (mojSamochod == null) return;
        try {
            if (!mojSamochod.getSprzeglo().czyWcisniete()) {
                throw new IllegalStateException("Aby zmienić bieg, wciśnij sprzęgło!");
            }
            mojSamochod.getSkrzynia().zmniejszBieg();
            mojSamochod.notifyListeners();
        } catch (Exception e) {
            pokazBlad(e.getMessage());
        }
    }

    @FXML private void onDodajGazuClick() {
        if (mojSamochod != null) {
            mojSamochod.getSilnik().zwiekszObroty(1000);
            mojSamochod.notifyListeners();
        }
    }

    @FXML private void onUjmijGazuClick() {
        if (mojSamochod != null) {
            mojSamochod.getSilnik().zmniejszObroty(1000);
            mojSamochod.notifyListeners();
        }
    }

    @FXML private void onWcisnijSprzegloClick() {
        if (mojSamochod != null) {
            mojSamochod.getSprzeglo().wcisnij();
            mojSamochod.notifyListeners();
        }
    }

    @FXML private void onZwolnijSprzegloClick() {
        if (mojSamochod != null) {
            mojSamochod.getSprzeglo().zwolnij();
            mojSamochod.notifyListeners();
        }
    }

    // Wybór auta z listy
    @FXML
    private void onCarSelect() {
        Samochod wybrany = carComboBox.getSelectionModel().getSelectedItem();
        if (wybrany != null) {
            if (mojSamochod != null) mojSamochod.removeListener(this); // Odepnij stare auto
            mojSamochod = wybrany;
            mojSamochod.addListener(this); // Podepnij nowe auto
            odswiezWidok();
        }
    }

    // NOWE: Metoda do usuwania samochodu
    @FXML
    private void onUsunClick() {
        Samochod doUsuniecia = carComboBox.getSelectionModel().getSelectedItem();
        if (doUsuniecia == null) {
            pokazBlad("Nie wybrano samochodu do usunięcia!");
            return;
        }

        // 1. Zatrzymujemy wątek i odpinamy listenera
        doUsuniecia.removeListener(this);
        doUsuniecia.usun();

        // 2. Usuwamy z listy
        listaSamochodow.remove(doUsuniecia);

        // 3. Aktualizujemy wybór
        if (listaSamochodow.isEmpty()) {
            mojSamochod = null;
            carComboBox.getSelectionModel().clearSelection();
            odswiezWidok(); // To wyczyści pola i ukryje auto
        } else {
            carComboBox.getSelectionModel().selectFirst();
            // onCarSelect wywoła się automatycznie lub możemy wywołać ręcznie
        }
    }

    // Dodawanie auta (wywoływane przez drugie okno)
    public void dodajAutoDoListy(Samochod noweAuto) {
        listaSamochodow.add(noweAuto);
        carComboBox.getSelectionModel().select(noweAuto);
    }

    // Otwieranie okna dodawania
    @FXML
    private void onDodajNowyClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DodajSamochod.fxml"));
        Scene scene = new Scene(loader.load());

        DodajSamochodController controller = loader.getController();
        controller.setMainController(this);

        Stage stage = new Stage();
        stage.setTitle("Dodaj nowy samochód");
        stage.setScene(scene);
        stage.show();
    }

    // Metoda pomocnicza do wyświetlania błędów
    private void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
}