package symulator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajSamochodController {

    // Pola tekstowe z pliku DodajSamochod.fxml
    @FXML private TextField modelTextField;
    @FXML private TextField registrationTextField;
    @FXML private TextField weightTextField;
    @FXML private TextField speedTextField; // Odpowiada za max obroty/prędkość

    // NOWE POLE: Lista rozwijana do wyboru liczby biegów
    @FXML private ComboBox<Integer> gearBoxComboBox;

    // Przyciski
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    // Referencja do głównego kontrolera
    private SymulatorController mainController;

    // Metoda inicjalizująca (uruchamiana automatycznie po załadowaniu FXML)
    @FXML
    public void initialize() {
        // Wypełniamy listę wyboru biegów (np. 4, 5, 6, 7)
        gearBoxComboBox.setItems(FXCollections.observableArrayList(4, 5, 6, 7));
        // Ustawiamy domyślną wartość na 5 biegów
        gearBoxComboBox.getSelectionModel().select(Integer.valueOf(5));
    }

    // Setter, który wywołujemy w SymulatorController przy otwieraniu okna
    public void setMainController(SymulatorController mainController) {
        this.mainController = mainController;
    }

    // Obsługa przycisku "Zatwierdź"
    @FXML
    private void onConfirmButton() {
        // 1. Pobieranie danych tekstowych
        String model = modelTextField.getText();
        String nrRej = registrationTextField.getText();

        // Walidacja: czy pola nie są puste?
        if (model == null || model.trim().isEmpty() || nrRej == null || nrRej.trim().isEmpty()) {
            pokazBlad("Pola Model i Numer Rejestracyjny muszą być wypełnione!");
            return;
        }

        // 2. Walidacja i konwersja liczb
        try {
            // Próba zamiany tekstu na liczby
            double waga = Double.parseDouble(weightTextField.getText());
            int predkosc = Integer.parseInt(speedTextField.getText());

            // Pobranie liczby biegów z ComboBoxa (jeśli null, domyślnie 5)
            Integer iloscBiegow = gearBoxComboBox.getValue();
            if (iloscBiegow == null) iloscBiegow = 5;

            // 3. Tworzenie obiektu Samochod (używamy nowego konstruktora z liczbą biegów)
            Samochod noweAuto = new Samochod(model, nrRej, waga, predkosc, iloscBiegow);

            // 4. Przekazanie obiektu do głównego okna
            if (mainController != null) {
                mainController.dodajAutoDoListy(noweAuto);
            }

            // 5. Zamknięcie okna
            zamknijOkno();

        } catch (NumberFormatException e) {
            // Jeśli użytkownik wpisze litery zamiast cyfr
            pokazBlad("Waga i Prędkość muszą być poprawnymi liczbami!");

            // Opcjonalnie: zmiana koloru ramki na czerwony dla błędnych pól
            weightTextField.setStyle("-fx-border-color: red;");
            speedTextField.setStyle("-fx-border-color: red;");
        }
    }

    // Obsługa przycisku "Anuluj"
    @FXML
    private void onCancelButton() {
        zamknijOkno();
    }

    // Metoda pomocnicza do zamykania Stage (okna)
    private void zamknijOkno() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    // NOWA METODA: Wyświetlanie okienek z błędami (Alert)
    private void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd wprowadzania danych");
        alert.setHeaderText(null); // Brak nagłówka, tylko treść
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
}