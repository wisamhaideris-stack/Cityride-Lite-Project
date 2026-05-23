import java.util.ArrayList;
import java.util.Scanner;

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
    }

    public void listJourneys() {

    }

    public void filterJourneys() {

    }

    public void removeJourney() {

    }

    public void resetJourneys() {

    }

    public void viewDailySummary() {

    }

    public void viewJourneysMenu() {

    }
}
