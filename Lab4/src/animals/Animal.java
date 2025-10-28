package animals;

public abstract class Animal {
     String name;
     int legs;

     public Animal(String name, int legs) {
         this.name = name;
         this.legs = legs;
     }

     public int getLegs() {
         return legs;
     }

     public String getName() {
         return name;
     }

     // Metoda abstrakcyjna - kazda pootomna kalsa ją zaimplemetuje
    public abstract String getDescription();

}
