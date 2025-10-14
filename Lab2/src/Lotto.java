import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Lotto {
    public static void main(String[] args) {
        // Wprowadzenie przez użytkowanika losu
        ArrayList<Integer> user_type = new ArrayList<>();

        for (int i = 1; user_type.size() < 6; i++) {
            Scanner type = new Scanner(System.in);
            System.out.print("Enter your " + i + " type:");
            int liczba = type.nextInt();
            user_type.add(liczba);
        }

        System.out.println("Your Lotto Ticket: " + user_type);


        // Generowanie Losu:
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        while (numbers.size() < 6) {
            int number = random.nextInt(49); // Zakres 1-49
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        }

        System.out.println("Lotto: " + numbers);

        // Sprawdzenie trafień
        int hits = 0;
        for (int num: user_type) {
            if (numbers.contains(num)) {
                hits++;
            }
        }
        System.out.println("Number of hits: " + hits);


    }
}