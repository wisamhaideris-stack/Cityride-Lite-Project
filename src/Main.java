import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static Scanner input = new Scanner(System.in);
    static FileManager fm = new FileManager();
    static Configuration configuration = fm.loadConfiguration();
    static JourneyManager manager = new JourneyManager(input, configuration);
    static Validation validation = new Validation();
    static Admin admin = new Admin("", "admin123");


    public static void main(String[] args) {
        boolean programRunning = true;
        while (programRunning) {

            boolean riderLoggedIn = false;
            while (!riderLoggedIn && programRunning) {
                displayStartupMenu();
                int startupChoice = getUserChoice();
                switch (startupChoice) {
                    case 1:
                        createRiderProfile();
                        riderLoggedIn = true;
                        break;
                    case 2:
                        riderLoggedIn = loadRider();
                        break;
                    case 3:
                        adminLogin();
                        break;
                    case 4:
                        System.out.println("\nProgram Closed.");
                        programRunning = false;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            }
            while (riderLoggedIn && programRunning) {
                displayMenu(manager.getCurrentRider());
                int choice = getUserChoice();
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
                        manager.setCurrentRider(null);
                        riderLoggedIn = false;
                        System.out.println("\nLogged out successfully.");
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            }
        }
    }
    public static void displayMenu(Rider rider) {

        System.out.println("-------------------------");
        System.out.println("       CITYRIDE");
        System.out.println("-------------------------");

        System.out.println("Current Rider : " + rider.getName());
        System.out.println("Passenger Type: " + rider.getPassengerType());
        System.out.println("Payment Method: " + rider.getDefaultPaymentMethod());

        System.out.println();

        System.out.println("1. Add Journey");
        System.out.println("2. View Journeys");
        System.out.println("3. Daily summary");
        System.out.println("4. Remove Journey");
        System.out.println("5. Reset Day");
        System.out.println("6. Logout");

    }
    public static int getUserChoice() {
        int choice = validation.getValidInteger(input, "Enter Choice: ");
        return choice;
    }

    public static void displayStartupMenu() {

        System.out.println("\nCityRide");

        System.out.println("1. Create Rider Profile");
        System.out.println("2. Load Rider");
        System.out.println("3. Admin Login");
        System.out.println("4. Exit");

    }

    public static void createRiderProfile() {

        System.out.print("Enter Name: ");
        String name = input.nextLine();
        System.out.print("Passenger Type (ADULT/STUDENT/CHILD/SENIOR_CITIZEN): ");
        CityRideDataset.PassengerType passengerType = CityRideDataset.PassengerType.valueOf(input.nextLine().toUpperCase());
        System.out.print("Default Payment Method: ");
        String paymentMethod = input.nextLine();
        Rider rider = new Rider(name, passengerType, paymentMethod);

// Save rider
        fm.addRider(rider);

// Set as current rider
        manager.setCurrentRider(rider);
        System.out.println("\nProfile created successfully.");
        System.out.println("Welcome " + rider.getName());
    }


    public static boolean loadRider() {

        boolean loaded = false;
        ArrayList<Rider> riders = fm.loadRiders();
        if (riders.isEmpty()) {
            System.out.println("\nNo riders found.");
        }
        else {

            System.out.println("\nAvailable Riders");
            for (int i = 0; i < riders.size(); i++) {
                System.out.println((i + 1) + ". " + riders.get(i).getName());
            }
            int choice = validation.getValidInteger(
                    input, "Select Rider: "
            );

            if (choice >= 1 && choice <= riders.size()) {
                Rider rider = riders.get(choice - 1);
                manager.setCurrentRider(rider);
                System.out.println("\nWelcome back " + rider.getName());
                loaded = true;
            }
            else {
                System.out.println("\nInvalid selection.");
            }
        }
        return loaded;
    }

    public static void adminLogin() {

        System.out.print("\nEnter Admin Password: ");
        String password = input.nextLine();

        if (password.equals(admin.getPassword())) {

            System.out.println("\nLogin Successful.");
            adminMenu();
        }
        else {
            System.out.println("\nIncorrect Password.");
        }

    }

    public static void adminMenu() {

        int choice;

        do {

            System.out.println("\n-------------------------");
            System.out.println("       ADMIN MENU");
            System.out.println("-------------------------");

            System.out.println("1. View Configuration");
            System.out.println("2. Edit Discount Rates");
            System.out.println("3. Edit Daily Caps");
            System.out.println("4. Edit Base Fares");
            System.out.println("5. Save Configuration");
            System.out.println("6. Reload Default Configuration");
            System.out.println("7. Return");

            choice = validation.getValidInteger(input, "Enter Choice: ");

            switch (choice) {

                case 1:
                    viewConfiguration();
                    break;
                case 2:
                    editDiscountRates();
                    break;
                case 3:
                    editDailyCaps();
                    break;
                case 4:
                    editBaseFares();
                    break;
                case 5:
                    fm.saveConfiguration(configuration);
                    break;

                case 6:

                    configuration.resetToDefaults();
                    System.out.println("\nDefault configuration restored.");
                    break;
                case 7:
                    System.out.println("\nReturning...");
                    break;
                default:
                    System.out.println("\nInvalid Choice.");
            }

        } while (choice != 7);

    }
    public static void viewConfiguration() {

        System.out.println("\n----------------------------");
        System.out.println("      CONFIGURATION");
        System.out.println("----------------------------");

        System.out.println("\nDISCOUNT RATES");

        for (CityRideDataset.PassengerType type : CityRideDataset.PassengerType.values()) {

            System.out.println(type + " : "
                    + configuration.getDiscountRates().get(type));

        }

        System.out.println("\nDAILY CAPS");

        for (CityRideDataset.PassengerType type : CityRideDataset.PassengerType.values()) {

            System.out.println(type + " : £"
                    + configuration.getDailyCaps().get(type));

        }

        System.out.println("\nBASE FARES");

        for (String key : configuration.getBaseFares().keySet()) {

            System.out.println(key + " : £"
                    + configuration.getBaseFares().get(key));

        }

    }
    public static void editDiscountRates() {

        System.out.println("\nEdit Discount Rates");
        System.out.println("Coming next.");

    }
    public static void editDailyCaps() {
        System.out.println("\nEdit Daily Caps");
        System.out.println("Coming next.");
    }
    public static void editBaseFares() {

        System.out.println("\nEdit Base Fares");
        System.out.println("Coming next.");

    }
}