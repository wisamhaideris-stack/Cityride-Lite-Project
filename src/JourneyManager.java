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
    private Rider currentRider;

    // Constructor
    public JourneyManager(Scanner input) {

        journeys = new ArrayList<>();
        validation = new Validation();
        this.input = input;
        calculator = new FareCalculator();
        summaryManager = new SummaryManager();
        nextJourneyID = 1;
        currentRider = null;

    }

    public void setCurrentRider(Rider rider) {currentRider = rider;}
    public Rider getCurrentRider() {return currentRider;}



    public void addJourney() {

        boolean valid = true;

        System.out.println("\nAdd Journey");

        // Date
        System.out.print("Enter Date: ");
        String date = input.nextLine();

        // Validate Date
        if (!validation.validateDate(date)) {

            System.out.println("Invalid Date");

            valid = false;

        }

        int fromZone = 0;
        int toZone = 0;

        if (valid) {

            // Zones
            fromZone = validation.getValidInteger(input, "Enter From Zone: ");
            toZone = validation.getValidInteger(input, "Enter To Zone: ");

            // Validate Zones
            if (!validation.validateZone(fromZone) || !validation.validateZone(toZone)) {

                System.out.println("Invalid Zone");

                valid = false;
            }

        }

        String passengerInput = "";
        String bandInput = "";

        if (valid) {

            // Passenger Type
            System.out.print("Passenger Type (ADULT/STUDENT/CHILD/SENIOR_CITIZEN): ");

            passengerInput = input.nextLine().toUpperCase();

            // Validate Passenger Type
            if (!validation.validatePassengerType(passengerInput)) {
                System.out.println("Invalid Passenger Type");
                valid = false;
            }

        }

        if (valid) {

            // Time Band
            System.out.print("Time Band (PEAK/OFF_PEAK): ");

            bandInput = input.nextLine().toUpperCase();

            // Validate Time Band
            if (!validation.validateTimeBand(bandInput)) {
                System.out.println("Invalid Time Band");
                valid = false;
            }

        }

        if (valid) {

            // Convert To Enums

            // (Oracle, 2026)
            CityRideDataset.PassengerType passengerType = CityRideDataset.PassengerType.valueOf(passengerInput);
            CityRideDataset.TimeBand timeBand = CityRideDataset.TimeBand.valueOf(bandInput);

            // Fare
            BigDecimal baseFare = calculator.calculateBaseFare(fromZone, toZone, timeBand);
            BigDecimal finalFare = calculator.applyDiscount(baseFare, passengerType);

            // Daily cap
            BigDecimal totalSpentToday = BigDecimal.ZERO;

            for (Journey existingJourney : journeys) {

                if (existingJourney.getDate().equals(date) && existingJourney.getPassengerType() == passengerType) {

                    // (In28Minutes, 2018)
                    totalSpentToday = totalSpentToday.add(existingJourney.getFinalFare());
                }
            }

            finalFare = calculator.applyDailyCap(totalSpentToday, finalFare, passengerType);

            BigDecimal discountApplied = baseFare.subtract(finalFare);

            int zonesCrossed = Math.abs(fromZone - toZone) + 1;

            // Create Journey
            Journey journey = new Journey(nextJourneyID, date, fromZone, toZone, passengerType, timeBand, baseFare, finalFare, discountApplied, zonesCrossed);

            // Store Journey

            // (Oracle, 2026)
            journeys.add(journey);
            nextJourneyID++;
            System.out.println("\nJourney Added Successfully");

        }

    }

    public void listJourneys() {
        if (journeys.isEmpty()) {
            System.out.println("\nNo Journeys Stored");

            return;
        }

        for (Journey journey : journeys) {
            journey.displayJourney();
        }

    }

    public void filterByPassengerType() {
        input.nextLine();
        System.out.print("\nEnter Passenger Type To Filter: ");

        String filter = input.nextLine().toUpperCase();

        boolean found = false;

        for (Journey journey : journeys) {

            // (Alexandra Obregon, 2024)
            if (journey.getPassengerType().name().equals(filter)) {journey.displayJourney();

                found = true;
            }

        }
        if (!found) {

            System.out.println(
                    "\nNo Matching Journeys Found"
            );

        }
    }

    public void filterByTimeBand() {

        input.nextLine();
        System.out.print("\nEnter Time Band (PEAK/OFF_PEAK): ");

        String filter = input.nextLine().toUpperCase();

        boolean found = false;

        for (Journey journey : journeys) {

            if (journey.getTimeBand().name().equals(filter)) {
                journey.displayJourney();
                found = true;
            }

        }
        if (!found) {

            System.out.println("\nNo Matching Journeys Found");
        }

    }

    public void filterByZone() {

        System.out.print("\nEnter Zone: ");
        int zone = input.nextInt();
        boolean found = false;

        for (Journey journey : journeys) {

            if (journey.getFromZone() == zone || journey.getToZone() == zone) {
                journey.displayJourney();
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo Matching Journeys Found");
        }

    }

    public void filterByDate() {

        input.nextLine();
        System.out.print("\nEnter Date: ");
        String date = input.nextLine();
        boolean found = false;

        for (Journey journey : journeys) {
            if (journey.getDate().equals(date)) {

                journey.displayJourney();
                found = true;
            }

        }

        if (!found) {
            System.out.println("\nNo Matching Journeys Found");
        }
    }

    public void removeJourney() {
        System.out.print("\nEnter Journey ID To Remove: ");

        int id = input.nextInt();

        boolean removed = false;

        for (int i = 0; i < journeys.size(); i++) {

            if (journeys.get(i).getJourneyID() == id) {

                journeys.remove(i);

                removed = true;

                System.out.println("\nJourney Removed");

                break;

            }

        }

        if (!removed) {

            System.out.println("\nJourney ID Not Found");

        }
    }

    public void resetJourneys() {
        journeys.clear();

        System.out.println("\nAll Journeys Reset");

    }

    public void viewDailySummary() {

        summaryManager.calculateDailySummary(journeys);
    }

    public void viewJourneysMenu() {

        int choice;

        do {

            System.out.println("\nView Journeys");

            System.out.println("1. View All Journeys");

            System.out.println("2. Filter By Passenger Type");

            System.out.println("3. Filter By Time Band");

            System.out.println("4. Filter By Zone");

            System.out.println("5. Filter By Date");

            System.out.println("6. Passenger Totals");

            System.out.println("7. Journey categories");

            System.out.println("8. Return");

            System.out.print("Enter Choice: ");

            choice = input.nextInt();


            // (Bro Code, 2024)

            switch (choice) {

                case 1:
                    listJourneys();
                    break;

                case 2:
                    filterByPassengerType();
                    break;

                case 3:
                    filterByTimeBand();
                    break;

                case 4:
                    filterByZone();
                    break;

                case 5:
                    filterByDate();
                    break;

                case 6:
                    summaryManager.calculateTotalsByPassengerType(journeys);
                    break;

                case 7:
                    summaryManager.countJourneyCategories(journeys);
                    break;

            }

        } while (choice != 8);

    }

    public ArrayList<Journey> getJourneys() {

        return journeys;

    }

}
