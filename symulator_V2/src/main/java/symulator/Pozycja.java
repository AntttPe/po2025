package symulator;

public class Pozycja {
    private double x;
    private double y;

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    // Metoda do przesuwania o zadany wektor (używana w wątku samochodu)
    public void aktualizujPozycje(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    // Metoda pomocnicza do wyświetlania
    public String getPozycja() {
        return "x: " + (int)x + ", y: " + (int)y;
    }
}