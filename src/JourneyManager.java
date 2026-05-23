import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;

public class JourneyManager{

    private ArrayList<Journey> journeys;
    private Scanner input;
    private Validation validation;
    private FareCalculator calculator;
    private SummaryManager summaryManager;
    private int nextJourneyID;

    // Constructor
    public JourneyManager(Scanner input) {

        journeys = new ArrayList<>();
        validation = new Validation();
        this.input = input;
        calculator = new FareCalculator();
        summaryManager = new SummaryManager();
        nextJourneyID = 1;

    }

    public void addJourney() {
        System.out.println("\nAdd Journey");

        // Date
        System.out.print("Enter Date: ");
        String date = input.nextLine();

        // Zones
        System.out.print("Enter From Zone: ");
        int fromZone = input.nextInt();

        System.out.print("Enter To Zone: ");
        int toZone = input.nextInt();

        // Validate Zones
        if (!validation.validateZone(fromZone) || !validation.validateZone(toZone)) {

            System.out.println("Invalid Zone");

            return;
        }

        input.nextLine();

        // Passenger Type
        System.out.print(
                "Passenger Type (Adult/Student/Child/Senior_Citezen): "
        );

        String passengerInput =
                input.nextLine().toUpperCase();

        // Validate Passenger Type
        if (!validation.validatePassengerType(passengerInput)) {

            System.out.println("Invalid Passenger Type");

            return;
        }
        // Time Band
        System.out.print(
                "Time Band (PEAK/OFF_PEAK): "
        );

        String bandInput =
                input.nextLine().toUpperCase();

        // Validate Time Band
        if (!validation.validateTimeBand(bandInput)) {

            System.out.println("Invalid Time Band");

            return;
        }
        
        // Convert To Enums
        CityRideDataset.PassengerType passengerType =
                CityRideDataset.PassengerType
                        .valueOf(passengerInput);

        CityRideDataset.TimeBand timeBand =
                CityRideDataset.TimeBand
                        .valueOf(bandInput);

        // Fare
        BigDecimal baseFare = calculator.calculateBaseFare(fromZone, toZone, timeBand);
        BigDecimal finalFare = calculator.applyDiscount(baseFare, passengerType);

        // Create Journey
        Journey journey = new Journey(nextJourneyID, date, fromZone, toZone,
                passengerType, timeBand, baseFare, finalFare);
        // Store Journey
        journeys.add(journey);

        nextJourneyID++;

        System.out.println(
                "\nJourney Added Successfully"
        );
    }

    public void listJourneys() {
        if (journeys.isEmpty()) {

            System.out.println(
                    "\nNo Journeys Stored"
            );

            return;

        }

        for (Journey journey : journeys) {

            journey.displayJourney();

        }

    }

    public void filterJourneys() {
        input.nextLine();
        System.out.print(
                "\nEnter Passenger Type To Filter: "
        );

        String filter = input.nextLine().toUpperCase();

        boolean found = false;

        for (Journey journey : journeys) {

            // (Alexandra Obregon, 2024)
            if (journey.getPassengerType().name().equals(filter)) {
                journey.displayJourney();

                found = true;
            }

        }
        if (!found) {

            System.out.println(
                    "\nNo Matching Journeys Found"
            );

        }
    }

    public void removeJourney() {
        System.out.print(
                "\nEnter Journey ID To Remove: "
        );

        int id = input.nextInt();

        boolean removed = false;

        for (int i = 0; i < journeys.size(); i++) {

            if (
                    journeys.get(i)
                            .getJourneyID() == id
            ) {

                journeys.remove(i);

                removed = true;

                System.out.println(
                        "\nJourney Removed"
                );

                break;

            }

        }

        if (!removed) {

            System.out.println(
                    "\nJourney ID Not Found"
            );

        }
    }

    public void resetJourneys() {
        journeys.clear();

        System.out.println(
                "\nAll Journeys Reset"
        );

    }

    public void viewDailySummary() {
        // Not implemented yet
    }

    public void viewJourneysMenu() {

        int choice;

        do {

            System.out.println(
                    "\nView Journeys"
            );

            System.out.println(
                    "1. View All Journeys"
            );

            System.out.println(
                    "2. Filter Journeys"
            );

            System.out.println(
                    "3. Passenger Totals"
            );

            System.out.println(
                    "4. Return"
            );

            System.out.print(
                    "Enter Choice: "
            );

            choice = input.nextInt();


            // (Bro Code, 2024)

            switch (choice) {

                case 1:
                    listJourneys();
                    break;

                case 2:
                    filterJourneys();
                    break;

                case 3:
                    // Not implemented yet
                    break;

            }

        } while (choice != 4);

    }
}
