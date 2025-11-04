package org.example;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;

    public void wcisnij() {
        stanSprzegla = true;
        System.out.println("Sprzęgło wciśnięte!");
    }

    public void zwolnij() {
        stanSprzegla = false;
        System.out.println("Sprzęgło zwolnione!");
    }

    public boolean stanSprzegla() {
        return stanSprzegla;
    }
}