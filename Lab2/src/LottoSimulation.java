import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LottoSimulation {
    public static void main(String[] args) {

        int hits = 0;
        int counter = 0;

        while(hits < 6) {
            hits = 0;
//            // Wprowadzenie przez użytkowanika losu
//            ArrayList<Integer> user_type = new ArrayList<>();
//
//            for (int i = 1; user_type.size() < 6; i++) {
//                Scanner type = new Scanner(System.in);
//                System.out.print("Enter your " + i + " type:");
//                int liczba = type.nextInt();
//                user_type.add(liczba);
//            }
//
//            System.out.println("Your Lotto Ticket: " + user_type);

            // Generowanie losu urzytkownika
            ArrayList<Integer> user_type = new ArrayList<>();
            Random user_random = new Random();

            while (user_type.size() < 6) {
                int number = user_random.nextInt(49); // Zakres 1-49
                if (!user_type.contains(number)) {
                    user_type.add(number);
                }
            }


            // Generowanie Losu:
            ArrayList<Integer> numbers = new ArrayList<>();
            Random random = new Random();

            while (numbers.size() < 6) {
                int number = random.nextInt(49); // Zakres 1-49
                if (!numbers.contains(number)) {
                    numbers.add(number);
                }
            }


            // Sprawdzenie trafień
            for (int num : user_type) {
                if (numbers.contains(num)) {
                    hits++;
                }
            }

            counter += 1;
            if (counter % 1000000 == 0) {
                System.out.println("Working..... Number of attempts:  " + counter);
            }

        }
        System.out.println("Total hits: " + hits);
//        System.out.println( "Number of attempts: " + counter);
        System.out.println("Number of attempts: "
                + String.format("%,d", counter).replace(',', ' '));

    }
}