package symulator;

public class Samochod {
    // Pola komponentów
    private Silnik silnik;
    private SkrzyniaBiegow skrzynia;
    private Sprzeglo sprzeglo;
    private Pozycja pozycja;

    // Pola informacyjne
    private String model;
    private String nrRejestracyjny;
    private double waga;       // Nowe pole, potrzebne do formularza
    private int predkoscMax;   // Nowe pole (lub max obroty), potrzebne do formularza

    // --- NOWY KONSTRUKTOR (Parametryczny) ---
    // Służy do tworzenia auta na podstawie danych z formularza "Dodaj Samochód"
    public Samochod(String model, String nrRejestracyjny, double waga, int predkoscMax) {
        this.model = model;
        this.nrRejestracyjny = nrRejestracyjny;
        this.waga = waga;
        this.predkoscMax = predkoscMax;
        this.pozycja = new Pozycja(0, 0);

        // Tworzenie komponentów na podstawie wprowadzonych danych
        // (Przykładowa logika: cięższe auto ma większe sprzęgło itp.)
        this.sprzeglo = new Sprzeglo("Sprzęgło " + model, waga * 0.1, 500);
        this.skrzynia = new SkrzyniaBiegow("Skrzynia " + model, waga * 0.2, 2000, 5, this.sprzeglo);
        // Przekazujemy predkoscMax jako maksymalne obroty silnika
        this.silnik = new Silnik("Silnik " + model, waga * 0.3, 5000, predkoscMax);
    }

    // --- STARY KONSTRUKTOR (Domyślny) ---
    // Aby Twój stary kod w Main/Controllerze nadal działał
    public Samochod(String model, String nrRejestracyjny) {
        this(model, nrRejestracyjny, 1000.0, 6000);
    }

    // Metody operacyjne
    public void wlacz() { silnik.uruchom(); }
    public void wylacz() { silnik.zatrzymaj(); }

    // Gettery potrzebne do GUI (refresh)
    public String getModel() { return model; }
    public String getNrRejestracyjny() { return nrRejestracyjny; }
    public double getWaga() { return waga; }
    public int getPredkoscMax() { return predkoscMax; }

    public Silnik getSilnik() { return silnik; }
    public SkrzyniaBiegow getSkrzynia() { return skrzynia; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public Pozycja getAktPozycja() { return pozycja; }

    // --- KLUCZOWE DLA COMBOBOXA ---
    // Ta metoda decyduje, co wyświetla się na liście rozwijanej
    @Override
    public String toString() {
        return model + " (" + nrRejestracyjny + ")";
    }
}