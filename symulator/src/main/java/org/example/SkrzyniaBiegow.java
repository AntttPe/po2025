package org.example;

public class SkrzyniaBiegow extends Komponent {
    private int aktulanyBieg;
    private int iloscBiegow;

    public SkrzyniaBiegow(String prducent, String model, int iloscBiegow) {
        super(prducent, model);
        this.iloscBiegow = iloscBiegow;;
        this.aktulanyBieg = 0;
    }

    public void zwiekszBieg() {
        if (aktulanyBieg < iloscBiegow) aktulanyBieg++;
        System.out.print("Aktualny bieg: " + aktulanyBieg);
    }

    public void zmiejszBieg() {
        if (aktulanyBieg < 0 ) aktulanyBieg--;
        System.out.print("Aktualny bieg: " + aktulanyBieg);
    }

    public void zerujBieg() {
        aktulanyBieg = 0;
    }
}
