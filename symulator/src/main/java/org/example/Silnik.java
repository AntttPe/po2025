package org.example;

public class Silnik extends Komponent{
    private int maxObroty;
    private int obroty;

    public Silnik(String producent, String model, int maxObroty, int obroty) {
        super(producent, model);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

    public void startEngine() {
        obroty = 1000;
        System.out.println("Silnik start. Obroty: " + obroty);
    }
    public void stopEngine() {
        obroty = 0;
        System.out.println("Silnik stop. Obroty: " + obroty);
        System.out.println("Silnik zatrzymany.");
    }

    public int getObroty() {
        return obroty;
    }
}
