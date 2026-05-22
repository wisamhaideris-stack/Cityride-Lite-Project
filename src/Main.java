import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static JourneyManager manager = new JourneyManager();
    public static void main(String[] args) {

        int choice;
        do {

            displayMenu();
            choice = getUserChoice();

            switch (choice) {

                case 1:
                    // add journey
                    break;

                case 2:
                    // list journey
                    break;
                case 3:
                    // daily summary
                    break;
                case 4:
                    // remove journey
                    break;

                case 5:
                    // reset journeys
                    break;

                case 6:
                    System.out.println("Program closed");
                    break;

                default:
                    System.out.println("\nInvalid choice");

            }

        } while (choice != 6);


    }
    public static void displayMenu() {

        System.out.println("CityRide Lite\n");

        System.out.println("1. Add Journey");
        System.out.println("2. List Journeys");
        System.out.println("3. Daily summary");
        System.out.println("4. Remove Journey");
        System.out.println("5. Reset Day");
        System.out.println("6. Exit");

    }
    public static int getUserChoice() {

        System.out.print("Enter Choice: ");
        return input.nextInt();

    }
}