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
        String time = validation.getValidClockTime(input);

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

        CityRideDataset.PassengerType passengerType = validation.getValidPassengerType(input);
        CityRideDataset.TimeBand timeBand = validation.getValidTimeBand(input);

        addJourneyFromData(date, time, fromZone, toZone, passengerType, timeBand);

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

            System.out.println("6. Edit Journey");

            System.out.println("7. Passenger Totals");

            System.out.println("8. Journey Categories");

            System.out.println("9. Return");

            System.out.print("Enter Choice: ");

            choice = validation.getValidInteger(input, "Enter Choice 1-9: ");

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
                    editJourney();
                    break;

                case 7:
                    summaryManager.calculateTotalsByPassengerType(journeys);
                    break;

                case 8:
                    summaryManager.countJourneyCategories(journeys);
                    break;

                case 9:
                    return;
            }

        } while (choice != 9);

    }

    public ArrayList<Journey> getJourneys() {

        return journeys;

    }
    public void editJourney() {
        if (journeys.isEmpty()) {
            System.out.println("\nNo journeys available.");
            return;
        }

        int id = validation.getValidInteger(input, "\nEnter Journey ID: "
        );

        Journey selectedJourney = null;

        for (Journey journey : journeys) {

            if (journey.getJourneyID() == id) {
                selectedJourney = journey;
            }
        }

        if (selectedJourney == null) {
            System.out.println("\nJourney not found.");
            return;
        }

        System.out.println("\nCurrent Journey");

        selectedJourney.displayJourney();

        System.out.println("\nSelect Field");

        System.out.println("1. Date");

        System.out.println("2. From Zone");

        System.out.println("3. To Zone");

        System.out.println("4. Passenger Type");

        System.out.println("5. Time Band");

        System.out.println("6. Cancel");

        int choice = validation.getValidInteger(input, "Enter Choice 1-6: ");

        switch (choice) {

            case 1:
                selectedJourney.setDate(validation.getValidDate(input));
                break;

            case 2:

                int fromZone = validation.getValidInteger(input, "New From Zone: ");
                if (validation.validateZone(fromZone))
                {
                    selectedJourney.setFromZone(fromZone);
                }

                break;

            case 3:
                int toZone = validation.getValidInteger(input, "New To Zone: ");
                if (validation.validateZone(toZone))
                {
                    selectedJourney.setToZone(toZone);
                }
                break;
            case 4:
                selectedJourney.setPassengerType(validation.getValidPassengerType(input));
                break;

            case 5:
                selectedJourney.setTimeBand(validation.getValidTimeBand(input));
                break;

            case 6:
                return;

        }

        BigDecimal baseFare = calculator.calculateBaseFare(selectedJourney.getFromZone(), selectedJourney.getToZone(), selectedJourney.getTimeBand());

        BigDecimal finalFare = calculator.applyDiscount(baseFare, selectedJourney.getPassengerType());

        BigDecimal totalSpentToday = BigDecimal.ZERO;

        for (Journey journey : journeys) {
            if (journey != selectedJourney && journey.getDate().equals(selectedJourney.getDate())
                    && journey.getPassengerType() == selectedJourney.getPassengerType())
            {
                totalSpentToday = totalSpentToday.add(journey.getFinalFare());

            }
        }

        finalFare = calculator.applyDailyCap(totalSpentToday, finalFare, selectedJourney.getPassengerType());
        selectedJourney.setBaseFare(baseFare);
        selectedJourney.setFinalFare(finalFare);
        selectedJourney.setDiscountApplied(baseFare.subtract(finalFare));
        selectedJourney.setZonesCrossed(Math.abs(selectedJourney.getFromZone() - selectedJourney.getToZone()) + 1);
        System.out.println("\nJourney Updated Successfully.");
    }
    public void setJourneys(ArrayList<Journey> loadedJourneys) {
        journeys = (loadedJourneys == null) ? new ArrayList<>() : loadedJourneys;
        int maxId = 0;
        for (Journey j : journeys) if (j.getJourneyID() > maxId) maxId = j.getJourneyID();
        nextJourneyID = maxId + 1;
    }


    public void addJourneyFromData(String date, String time, int fromZone, int toZone,
                                   CityRideDataset.PassengerType passengerType,
                                   CityRideDataset.TimeBand timeBand) {

        BigDecimal baseFare = calculator.calculateBaseFare(fromZone, toZone, timeBand);
        if (baseFare == null) {
            System.out.println("No base fare configured for this route/time band.");
            return;
        }

        BigDecimal discountedFare = calculator.applyDiscount(baseFare, passengerType);

        BigDecimal totalSpentToday = BigDecimal.ZERO;
        for (Journey existingJourney : journeys) {
            if (existingJourney.getDate().equals(date) && existingJourney.getPassengerType() == passengerType) {
                totalSpentToday = totalSpentToday.add(existingJourney.getFinalFare());
            }
        }
        BigDecimal finalFare = calculator.applyDailyCap(totalSpentToday, discountedFare, passengerType);
        boolean capApplied = finalFare.compareTo(discountedFare) < 0;
        BigDecimal discountApplied = baseFare.subtract(finalFare);
        int zonesCrossed = Math.abs(fromZone - toZone) + 1;

        Journey journey = new Journey(nextJourneyID, date, time, fromZone, toZone, passengerType, timeBand,
                baseFare, finalFare, discountApplied, zonesCrossed, capApplied);

        journeys.add(journey);
        nextJourneyID++;
        System.out.println("\nJourney Added Successfully");
    }
    public SummaryManager getSummaryManager() {
        return summaryManager;
    }

}
