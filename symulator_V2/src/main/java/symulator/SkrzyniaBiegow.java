package symulator;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;
    private Sprzeglo sprzeglo;

    public SkrzyniaBiegow(String nazwa, double waga, double cena, int iloscBiegow, Sprzeglo sprzeglo) {
        super(nazwa, waga, cena);
        this.iloscBiegow = iloscBiegow;
        this.sprzeglo = sprzeglo;
        this.aktualnyBieg = 0;
    }

    public void zwiekszBieg() {
        if (sprzeglo.czyWcisniete()) {
            if (aktualnyBieg < iloscBiegow) {
                aktualnyBieg++;
                System.out.println("Bieg: " + aktualnyBieg);
            }
        } else {
            System.out.println("Nie można zmienić biegu bez sprzęgła!");
        }
    }

    public void zmniejszBieg() {
        if (sprzeglo.czyWcisniete()) {
            if (aktualnyBieg > 0) {
                aktualnyBieg--;

                System.out.println("Bieg: " + aktualnyBieg);
            }
        } else {
            System.out.println("Wciśnij sprzęgło!");
        }
    }

    public int getAktBieg() { return aktualnyBieg; }
}