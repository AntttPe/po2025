package symulator;

public class Samochod {
    private Silnik silnik;
    private SkrzyniaBiegow skrzynia;
    private Sprzeglo sprzeglo;
    private Pozycja pozycja;
    private String model;
    private String nrRejestracyjny;

    // Konstruktor składający samochód [cite: 126, 127]
    public Samochod(String model, String nrRejestracyjny) {
        this.model = model;
        this.nrRejestracyjny = nrRejestracyjny;
        this.pozycja = new Pozycja(0, 0);

        // Tworzenie komponentów wewnątrz (Kompozycja)
        this.sprzeglo = new Sprzeglo("Sprzęgło Standard", 10, 300);
        this.skrzynia = new SkrzyniaBiegow("Manualna", 40, 1500, 5, this.sprzeglo);
        this.silnik = new Silnik("1.9 TDI", 150, 4000, 5000);
    }

    public void wlacz() {
        silnik.uruchom();
    }

    public void wylacz() {
        silnik.zatrzymaj();
    }

    public void jedzDo(Pozycja cel) {
        this.pozycja = cel;
        System.out.println("Przemieszczono samochód.");
    }

    // Gettery do debugowania
    public Silnik getSilnik() { return silnik; }
    public SkrzyniaBiegow getSkrzynia() { return skrzynia; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public Pozycja getAktPozycja() { return pozycja; } // Zgodnie z diagramem [cite: 29]
}