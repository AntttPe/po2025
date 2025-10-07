import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of tickets to generate: ");
        int ticketCount = scanner.nextInt();
        for (int i = 0; i < ticketCount; i++) {
            Set<Integer> ticket = new TreeSet<>();
            Random random = new Random();
            while (ticket.size() < 6) {
                ticket.add(random.nextInt(49) + 1); // Generate numbers between 1 and 49
            }
            System.out.println("Ticket " + (i + 1) + ": " + ticket);
        }
    }
}