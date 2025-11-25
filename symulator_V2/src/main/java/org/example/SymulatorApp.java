package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.InputStream;

public class SymulatorApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Główny kontener - BorderPane
        BorderPane root = new BorderPane();

        // 2. Sekcja TOP: Menu i Pasek narzędzi [cite: 51, 52]
        VBox topContainer = createTopSection();
        root.setTop(topContainer);

        // 3. Sekcja LEFT: Panele komponentów
        VBox leftPanel = createLeftSection();
        // Dodajemy ScrollPane, żeby panel się przewijał na mniejszych ekranach
        ScrollPane scrollPane = new ScrollPane(leftPanel);
        scrollPane.setFitToWidth(true);
        root.setLeft(scrollPane);

        // 4. Sekcja CENTER: Mapa/Obrazek [cite: 49, 50]
        StackPane centerPanel = createCenterSection();
        root.setCenter(centerPanel);

        // Ustawienie Sceny
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Symulator Samochodu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // --- Metody pomocnicze do budowy sekcji ---

    private VBox createTopSection() {
        // Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        menuBar.getMenus().addAll(menuFile, menuEdit);

        // Tool Bar z ComboBoxem i przyciskami [cite: 53, 54]
        ToolBar toolBar = new ToolBar();
        ComboBox<String> carSelector = new ComboBox<>();
        carSelector.getItems().addAll("Samochód 1", "Samochód 2");
        carSelector.setPromptText("Wybierz samochód");

        Button btnAdd = new Button("Dodaj nowy");
        Button btnDelete = new Button("Usuń");

        toolBar.getItems().addAll(new Label("Samochód: "), carSelector, btnAdd, btnDelete);

        return new VBox(menuBar, toolBar);
    }

    private VBox createLeftSection() {
        VBox leftBox = new VBox(10); // 10px odstępu
        leftBox.setPadding(new Insets(10));
        leftBox.setPrefWidth(250);

        // Wykorzystujemy TitledPane dla każdej sekcji [cite: 13, 48]

        // 1. Panel Samochód
        TitledPane carPane = new TitledPane("Samochód", createCarContent());
        carPane.setCollapsible(true);

        // 2. Panel Skrzynia Biegów
        TitledPane gearPane = new TitledPane("Skrzynia Biegów", createGearboxContent());
        gearPane.setCollapsible(true);

        // 3. Panel Silnik
        TitledPane enginePane = new TitledPane("Silnik", createEngineContent());
        enginePane.setCollapsible(true);

        // 4. Panel Sprzęgło
        TitledPane clutchPane = new TitledPane("Sprzęgło", createClutchContent());
        clutchPane.setCollapsible(true);

        leftBox.getChildren().addAll(carPane, gearPane, enginePane, clutchPane);
        return leftBox;
    }

    private StackPane createCenterSection() {
        StackPane center = new StackPane();
        center.setStyle("-fx-background-color: lightgreen;"); // Tło jak "mapa"

        // Ładowanie obrazka
        try {
            // Upewnij się, że masz plik car.png w src/main/resources
            InputStream is = getClass().getResourceAsStream("/car.png");
            if (is != null) {
                Image image = new Image(is);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                center.getChildren().add(imageView);
            } else {
                center.getChildren().add(new Label("[Brak obrazka car.png]"));
            }
        } catch (Exception e) {
            center.getChildren().add(new Label("Błąd obrazka"));
        }

        return center;
    }

    // --- Metody do wnętrza paneli (Pola tekstowe i przyciski) ---

    private VBox createCarContent() {
        VBox content = new VBox(5);
        content.getChildren().addAll(
                new Label("Model:"), new TextField(),
                new Label("Nr rejestracyjny:"), new TextField(),
                new Label("Waga:"), new TextField(),
                new Label("Prędkość:"), new TextField(),
                new HBox(5, new Button("Włącz"), new Button("Wyłącz")) // [cite: 47]
        );
        return content;
    }

    private VBox createGearboxContent() {
        VBox content = new VBox(5);
        content.getChildren().addAll(
                new Label("Nazwa:"), new TextField(),
                new Label("Cena:"), new TextField(),
                new Label("Waga:"), new TextField(),
                new Label("Bieg:"), new TextField(),
                new HBox(5, new Button("Zwiększ bieg"), new Button("Zmniejsz bieg"))
        );
        return content;
    }

    private VBox createEngineContent() {
        VBox content = new VBox(5);
        content.getChildren().addAll(
                new Label("Nazwa:"), new TextField(),
                new Label("Cena:"), new TextField(),
                new Label("Waga:"), new TextField(),
                new Label("Obroty:"), new TextField(),
                new HBox(5, new Button("Dodaj gazu"), new Button("Ujmij gazu"))
        );
        return content;
    }

    private VBox createClutchContent() {
        VBox content = new VBox(5);
        content.getChildren().addAll(
                new Label("Nazwa:"), new TextField(),
                new Label("Cena:"), new TextField(),
                new Label("Waga:"), new TextField(),
                new Label("Stan:"), new TextField(),
                new HBox(5, new Button("Wciśnij"), new Button("Zwolnij"))
        );
        return content;
    }

    public static void main(String[] args) {
        launch(args);
    }
}