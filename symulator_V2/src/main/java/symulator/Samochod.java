package symulator;

import java.util.ArrayList;
import java.util.List;

public class Samochod extends Thread { // 1. Dziedziczenie po Thread
    // Komponenty
    private Silnik silnik;
    private SkrzyniaBiegow skrzynia;
    private Sprzeglo sprzeglo;

    // Dane o stanie
    private Pozycja pozycja;
    private Pozycja cel; // Cel podróży
    private String model;
    private String nrRejestracyjny;
    private double waga;
    private int predkoscMax;

    // Lista słuchaczy (GUI)
    private List<Listener> listeners = new ArrayList<>();

    // Zmieniono na volatile dla bezpieczeństwa wątkowego przy zatrzymywaniu
    private volatile boolean active = true;

    // --- KONSTRUKTORY ---

    // 1. Główny konstruktor (z liczbą biegów)
    public Samochod(String model, String nrRejestracyjny, double waga, int predkoscMax, int iloscBiegow) {
        this.model = model;
        this.nrRejestracyjny = nrRejestracyjny;
        this.waga = waga;
        this.predkoscMax = predkoscMax;

        // Startujemy na pozycji 0,0
        this.pozycja = new Pozycja(0, 0);

        // Tworzenie podzespołów
        this.sprzeglo = new Sprzeglo("Sprzęgło", waga * 0.1, 500);
        // Przekazujemy iloscBiegow do skrzyni zamiast sztywnej 5
        this.skrzynia = new SkrzyniaBiegow("Skrzynia", waga * 0.2, 2000, iloscBiegow, this.sprzeglo);
        this.silnik = new Silnik("Silnik", waga * 0.3, 5000, predkoscMax);

        // Uruchomienie wątku na końcu konstruktora
        start();
    }

    // 2. Konstruktor kompatybilny (domyślnie 5 biegów) - używany np. w testach lub kodzie legacy
    public Samochod(String model, String nrRejestracyjny, double waga, int predkoscMax) {
        this(model, nrRejestracyjny, waga, predkoscMax, 5);
    }

    // 3. Konstruktor uproszczony
    public Samochod(String model, String nrRejestracyjny) {
        this(model, nrRejestracyjny, 1000, 200, 5);
    }

    // --- LOGIKA RUCHU (WĄTEK) ---

    // Metoda do bezpiecznego zatrzymywania wątku (np. przy usuwaniu auta)
    public void usun() {
        this.active = false;
        this.interrupt(); // Przerywamy sleep, jeśli wątek śpi
    }

    public void jedzDo(Pozycja cel) {
        this.cel = cel;
        System.out.println("Jadę do: " + cel.getPozycja());
    }

    @Override
    public void run() {
        while (active) {
            try {
                // Ruch tylko gdy jest cel i silnik włączony
                if (cel != null && silnik.getObroty() > 0) {
                    double dx = cel.getX() - pozycja.getX();
                    double dy = cel.getY() - pozycja.getY();
                    double odleglosc = Math.sqrt(dx * dx + dy * dy);

                    if (odleglosc > 5) { // Jeśli jeszcze nie dotarł
                        // Prędkość zależna od obrotów i biegu (uproszczona fizyka)
                        double speed = (silnik.getObroty() / 3000.0) * (skrzynia.getAktBieg() * 2 + 1);

                        if (speed > 0) {
                            double moveX = (dx / odleglosc) * speed;
                            double moveY = (dy / odleglosc) * speed;

                            pozycja.aktualizujPozycje(moveX, moveY);
                            notifyListeners(); // WAŻNE: Odśwież widok
                        }
                    } else {
                        cel = null; // Dotarł
                    }
                }
                Thread.sleep(50); // Odświeżanie co 50ms (płynny ruch)
            } catch (InterruptedException e) {
                // Wątek został przerwany (np. przez metodę usun()), kończymy pętlę
                active = false;
            }
        }
    }

    // --- WZORZEC OBSERWATOR ---
    public void addListener(Listener listener) { listeners.add(listener); }
    public void removeListener(Listener listener) { listeners.remove(listener); }

    public void notifyListeners() {
        // Kopia listy, aby uniknąć błędów współbieżności przy modyfikacji listy w trakcie powiadamiania
        List<Listener> currentListeners = new ArrayList<>(listeners);
        for (Listener listener : currentListeners) {
            listener.update();
        }
    }

    // Metody sterujące
    public void wlacz() { silnik.uruchom(); notifyListeners(); }
    public void wylacz() { silnik.zatrzymaj(); notifyListeners(); }

    // Gettery
    public String getModel() { return model; }
    public String getNrRejestracyjny() { return nrRejestracyjny; }
    public Silnik getSilnik() { return silnik; }
    public SkrzyniaBiegow getSkrzynia() { return skrzynia; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public Pozycja getAktPozycja() { return pozycja; }

    @Override
    public String toString() { return model + " (" + nrRejestracyjny + ")"; }
}