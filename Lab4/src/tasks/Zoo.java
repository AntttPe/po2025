package tasks;

import animals.*;
import java.util.Random;

public class Zoo{
    Animal[] animals = new Animal[100];
    Random random = new Random();

    public Zoo() {
        fillZoo();
    }

    public void fillZoo(){
        String[] names = {"Charlie", "Buddy", "Kiki", "Max", "Luna", "Nemo", "Coco"};

        for (int i = 0; i < animals.length; i++){
            int choice = random.nextInt(3); // 0,1,2
            String name = names[random.nextInt(names.length)];

            switch (choice) {
                case 0:
                    animals[i] = new Dog(name);
                    break;
                case 1:
                    animals[i] = new Parrot(name);
                    break;
                case 2:
                    animals[i] = new Snake(name);
                    break;
            }
        }
    }

    public int getTotalLeges() {
        int sum = 0;
        for (Animal a : animals) {
            sum += a.getLegs();
        }
        return sum;
    }
    public void showAllAnimals(){
        for (Animal a : animals){
            System.out.println(a.getDescription());
        }
    }

    // metoda main do testu
    public static void  main(String[] args){
        Zoo zoo = new Zoo();
        zoo.showAllAnimals();
        System.out.println("Total leges: " + zoo.getTotalLeges());
    }
}
