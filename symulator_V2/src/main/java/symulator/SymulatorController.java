package symulator;

import javafx.collections.FXCollections; // ZMIANA: Importy do listy
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class SymulatorController {

    @FXML private TextField modelField;
    @FXML private TextField nrRejestracyjnyField;
    @FXML private TextField stanSilnikaField;
    @FXML private TextField biegField;
    @FXML private TextField obrotyField;
    @FXML private TextField sprzegloField;
    @FXML private ComboBox<Samochod> carComboBox;
    @FXML private ImageView carImage;

    private ObservableList<Samochod> listaSamochodow = FXCollections.observableArrayList();

    // Obiekt logiczny (Backend) - aktualnie wybrany samochód
    private Samochod mojSamochod;

    @FXML
    public void initialize() {
        // Ładowanie obrazka
        try {
            InputStream is = getClass().getResourceAsStream("/car.png");
            if (is != null) carImage.setImage(new Image(is));
        } catch (Exception e) { System.out.println("Brak obrazka"); }

        // ZMIANA: Tworzymy domyślny samochód i dodajemy go do LISTY
        Samochod domyslny = new Samochod("Porsche", "KR 12345", 1300, 7000);
        listaSamochodow.add(domyslny);

        // ZMIANA: Podpinamy listę pod ComboBox
        carComboBox.setItems(listaSamochodow);

        // Wybieramy pierwszy element
        carComboBox.getSelectionModel().select(0);
        mojSamochod = domyslny;

        odswiezWidok();
    }

    // --- Metody obsługi zdarzeń ---

    @FXML private void onWlaczClick() { mojSamochod.wlacz(); odswiezWidok(); }
    @FXML private void onWylaczClick() { mojSamochod.wylacz(); odswiezWidok(); }
    @FXML private void onZwiekszBiegClick() { mojSamochod.getSkrzynia().zwiekszBieg(); odswiezWidok(); }
    @FXML private void onZmniejszBiegClick() { mojSamochod.getSkrzynia().zmniejszBieg(); odswiezWidok(); }
    @FXML private void onDodajGazuClick() { mojSamochod.getSilnik().zwiekszObroty(500); odswiezWidok(); }
    @FXML private void onUjmijGazuClick() { mojSamochod.getSilnik().zmniejszObroty(500); odswiezWidok(); }
    @FXML private void onWcisnijSprzegloClick() { mojSamochod.getSprzeglo().wcisnij(); odswiezWidok(); }
    @FXML private void onZwolnijSprzegloClick() { mojSamochod.getSprzeglo().zwolnij(); odswiezWidok(); }

    // ZMIANA: Obsługa wyboru z listy
    @FXML
    private void onCarSelect() {
        Samochod wybrany = carComboBox.getSelectionModel().getSelectedItem();
        if (wybrany != null) {
            mojSamochod = wybrany; // Podmieniamy auto na to wybrane z listy
            odswiezWidok();
        }
    }


    @FXML
    private void onDodajNowyClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DodajSamochod.fxml"));
        Scene scene = new Scene(loader.load());

        // Przekazujemy referencję do 'this' (MainController) do nowego okna
        DodajSamochodController controller = loader.getController();
        controller.setMainController(this);

        Stage stage = new Stage();
        stage.setTitle("Dodaj nowy samochód");
        stage.setScene(scene);
        stage.show();
    }

    public void dodajAutoDoListy(Samochod noweAuto) {
        listaSamochodow.add(noweAuto);
        carComboBox.getSelectionModel().select(noweAuto); // Automatycznie wybierz nowe auto
        mojSamochod = noweAuto;
        odswiezWidok();
    }

    private void odswiezWidok() {
        if (mojSamochod != null) {
            modelField.setText(mojSamochod.getModel());
            nrRejestracyjnyField.setText(mojSamochod.getNrRejestracyjny());

            stanSilnikaField.setText(mojSamochod.getSilnik().getObroty() > 0 ? "Włączony" : "Wyłączony");
            biegField.setText(String.valueOf(mojSamochod.getSkrzynia().getAktBieg()));
            obrotyField.setText(String.valueOf(mojSamochod.getSilnik().getObroty()));
            sprzegloField.setText(mojSamochod.getSprzeglo().czyWcisniete() ? "Wciśnięte" : "Zwolnione");
        }
    }
}