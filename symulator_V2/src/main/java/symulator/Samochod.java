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
    private boolean active = true; // Flaga do działania wątku

    // Konstruktor
    public Samochod(String model, String nrRejestracyjny, double waga, int predkoscMax) {
        this.model = model;
        this.nrRejestracyjny = nrRejestracyjny;
        this.waga = waga;
        this.predkoscMax = predkoscMax;

        // Startujemy na pozycji 0,0
        this.pozycja = new Pozycja(0, 0);

        // Tworzenie podzespołów
        this.sprzeglo = new Sprzeglo("Sprzęgło", waga * 0.1, 500);
        this.skrzynia = new SkrzyniaBiegow("Skrzynia", waga * 0.2, 2000, 5, this.sprzeglo);
        this.silnik = new Silnik("Silnik", waga * 0.3, 5000, predkoscMax);
        

        // 2. Uruchomienie wątku na końcu konstruktora
        start();
    }

    // Konstruktor uproszczony (dla kompatybilności)
    public Samochod(String model, String nrRejestracyjny) {
        this(model, nrRejestracyjny, 1000, 200);
    }

    // --- LOGIKA RUCHU (WĄTEK) ---
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
                e.printStackTrace();
            }
        }
    }

    // --- WZORZEC OBSERWATOR ---
    public void addListener(Listener listener) { listeners.add(listener); }
    public void removeListener(Listener listener) { listeners.remove(listener); }

    public void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }

    // Metody sterujące (z powiadamianiem)
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