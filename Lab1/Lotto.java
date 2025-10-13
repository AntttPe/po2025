package Lab1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Lotto {

    public static void main(String[] args) {
        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();

        // Losuje aż będzie 6 unikalnych cyfr
        while (numbers.size() < 6) {
            int number = random.nextInt(49) + 1; // zakres od 1 - 49
            numbers.add(number);
        }
        System.out.println("Wylosowane liczby w lotto: " + numbers);
    }
}
