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
    public JourneyManager(Scanner input, Configuration configuration) {

        journeys = new ArrayList<>();
        validation = new Validation();
        this.input = input;
        calculator = new FareCalculator(configuration);
        summaryManager = new SummaryManager();
        nextJourneyID = 1;
        currentRider = null;

    }

    public void setCurrentRider(Rider rider) {currentRider = rider;}
    public Rider getCurrentRider() {return currentRider;}



    public void addJourney() {

        boolean valid = true;
        System.out.println("\nAdd Journey");
        String date = validation.getValidDate(input);

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

        CityRideDataset.PassengerType passengerType = null;
        CityRideDataset.TimeBand timeBand = null;

        if (valid) {
            passengerType = validation.getValidPassengerType(input);
        }

        if (valid) {
            timeBand = validation.getValidTimeBand(input);
        }

        if (valid) {
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

        CityRideDataset.PassengerType filter = validation.getValidPassengerType(input);
        boolean found = false;

        for (Journey journey : journeys) {

            // (Alexandra Obregon, 2024)
            if (journey.getPassengerType() == filter) {
                journey.displayJourney();
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nNo Matching Journeys Found");
        }
    }

    public void filterByTimeBand() {

        CityRideDataset.TimeBand filter = validation.getValidTimeBand(input);

        boolean found = false;

        for (Journey journey : journeys) {

            if (journey.getTimeBand() == filter) {
                journey.displayJourney();
                found = true;
            }

        }
        if (!found) {

            System.out.println("\nNo Matching Journeys Found");
        }

    }

    public void filterByZone() {

        int zone = validation.getValidInteger(input, "\nEnter Zone: ");

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

        String date = validation.getValidDate(input);

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
        int id = validation.getValidInteger(input, "\nEnter Journey ID To Remove: ");

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
