package org.example;

public class Pozycja {
    private double x;
    private double y;

    public void aktualizujPozycje(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public String getPozycja() {
        return "(" + x + "," + y + ")";
    }
}
