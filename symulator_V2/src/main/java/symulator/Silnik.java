package symulator;

public class Silnik extends Komponent {
    private int maxObroty;
    private int obroty;

    public Silnik(String nazwa, double waga, double cena, int maxObroty) {
        super(nazwa, waga, cena);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

    public void uruchom() {
        this.obroty = 800;
        System.out.println("Silnik uruchomiony.");
    }

    public void zatrzymaj() {
        this.obroty = 0;
        System.out.println("Silnik zatrzymany.");
    }

    public void zwiekszObroty(int ilosc) {
        if (obroty + ilosc <= maxObroty) obroty += ilosc;
        else obroty = maxObroty;
    }

    public void zmniejszObroty(int ilosc) { // Wymagana metoda [cite: 46]
        if (obroty - ilosc >= 0) obroty -= ilosc;
        else obroty = 0;
    }

    public int getObroty() { return obroty; }
}