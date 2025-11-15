import devices.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SmartHomeSystem system = new SmartHomeSystem();
        Scanner scanner = new Scanner(System.in);

        // Dodajmy jakieś urządzenia startowe
        system.addDevice(new SmartLight("Salon Light"));
        system.addDevice(new SmartThermostat("Main Thermostat"));
        system.addDevice(new SmartCamera("Front Door Camera"));

        while (true) {
            System.out.println("\n=== SMART HOME MENU ===");
            System.out.println("1. Show all devices");
            System.out.println("2. Show device details");
            System.out.println("3. Switch ON device");
            System.out.println("4. Switch OFF device");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // czyści enter

            switch (choice) {
                case 1:
                    system.showAllDevices();
                    break;

                case 2:
                    System.out.print("Enter device name: ");
                    String name = scanner.nextLine();
                    system.showDeviceDetails(name);
                    break;

                case 3:
                    System.out.print("Device to switch ON: ");
                    system.switchDevice(scanner.nextLine(), true);
                    break;

                case 4:
                    System.out.print("Device to switch OFF: ");
                    system.switchDevice(scanner.nextLine(), false);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}