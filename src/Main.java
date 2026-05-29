import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static JourneyManager manager = new JourneyManager(input);
    public static void main(String[] args) {

        int choice;
        do {

            displayMenu();
            choice = getUserChoice();

            switch (choice) {

                // (Bro Code, 2024)

                case 1:
                    manager.addJourney();
                    break;

                case 2:
                    manager.viewJourneysMenu();
                    break;
                case 3:
                    manager.viewDailySummary();
                    break;

                case 4:
                    manager.removeJourney();
                    break;

                case 5:
                    manager.resetJourneys();
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
        System.out.println("2. View Journeys");
        System.out.println("3. Daily summary");
        System.out.println("4. Remove Journey");
        System.out.println("5. Reset Day");
        System.out.println("6. Exit");

    }
    public static int getUserChoice() {

        System.out.print("Enter Choice: ");
        int choice = input.nextInt();
        input.nextLine();

        return choice;
    }
}