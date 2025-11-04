package org.example;

public class Samochod {
    private Silnik silnik;
    private SkrzyniaBiegow skrzyniaBiegow;
    private Sprzeglo sprzeglo;
    private Pozycja pozycja;

    public Samochod(Silnik silnik, SkrzyniaBiegow skrzyniaBiegow, Sprzeglo sprzeglo, Pozycja pozycja) {
        this.silnik = silnik;
        this.skrzyniaBiegow = skrzyniaBiegow;
        this.sprzeglo = sprzeglo;
        this.pozycja = pozycja;
    }

    public void wlacz() {
        silnik.startEngine();
    }

    public void wylacz() {
        silnik.startEngine();
        skrzyniaBiegow.zerujBieg();
    }

    public void pokazStan() {
        System.out.println("Silnik:");
    }
}
