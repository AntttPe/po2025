package symulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajSamochodController {

    // Pola tekstowe z pliku DodajSamochod.fxml [cite: 271-276]
    @FXML private TextField modelTextField;
    @FXML private TextField registrationTextField;
    @FXML private TextField weightTextField;
    @FXML private TextField speedTextField; // Odpowiada za max obroty/prędkość

    // Przyciski
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    // Referencja do głównego kontrolera (żebyśmy mogli tam dodać auto)
    private SymulatorController mainController;

    // Setter, który wywołujemy w SymulatorController przy otwieraniu okna
    public void setMainController(SymulatorController mainController) {
        this.mainController = mainController;
    }

    // Obsługa przycisku "Zatwierdź" [cite: 278-291]
    @FXML
    private void onConfirmButton() {
        // 1. Pobieranie danych tekstowych
        String model = modelTextField.getText();
        String nrRej = registrationTextField.getText();

        // 2. Walidacja i konwersja liczb
        try {
            // Próba zamiany tekstu na liczby
            double waga = Double.parseDouble(weightTextField.getText());
            int predkosc = Integer.parseInt(speedTextField.getText());

            // 3. Tworzenie obiektu Samochod (używamy nowego konstruktora)
            Samochod noweAuto = new Samochod(model, nrRej, waga, predkosc);

            // 4. Przekazanie obiektu do głównego okna
            if (mainController != null) {
                mainController.dodajAutoDoListy(noweAuto); // [cite: 290]
            }

            // 5. Zamknięcie okna
            zamknijOkno();

        } catch (NumberFormatException e) {
            // Jeśli użytkownik wpisze litery zamiast cyfr w wadze/prędkości
            System.out.println("Błąd: Waga i Prędkość muszą być liczbami!");
            // Opcjonalnie: zmiana koloru ramki na czerwony
            weightTextField.setStyle("-fx-border-color: red;");
            speedTextField.setStyle("-fx-border-color: red;");
        }
    }

    // Obsługa przycisku "Anuluj" [cite: 294-296]
    @FXML
    private void onCancelButton() {
        zamknijOkno();
    }

    // Metoda pomocnicza do zamykania Stage (okna)
    private void zamknijOkno() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}