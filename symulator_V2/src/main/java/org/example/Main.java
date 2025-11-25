package org.example;

// Importujemy wszystko z naszego nowego pakietu
import symulator.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start symulatora...");

        // 1. Tworzenie obiektu
        Samochod auto = new Samochod("BMW", "WAW12345");

        // 2. Testowanie - tu możesz postawić breakpoint
        auto.wlacz();

        // Próba zmiany biegu bez sprzęgła
        auto.getSkrzynia().zwiekszBieg();

        // Zmiana biegu poprawna
        auto.getSprzeglo().wcisnij();
        auto.getSkrzynia().zwiekszBieg();
        auto.getSprzeglo().zwolnij();

        auto.getSilnik().zwiekszObroty(1000);

        System.out.println("Obroty silnika: " + auto.getSilnik().getObroty());
        System.out.println("Aktualny bieg: " + auto.getSkrzynia().getAktBieg());

        auto.wylacz();
    }
}