import java.util.ArrayList;
import java.util.Scanner;

public class JourneyManager{

    private ArrayList<Journey> journeys;
    private Scanner input;
    private FareCalculator calculator;
    private SummaryManager summaryManager;
    private int nextJourneyID;

    // Constructor
    public JourneyManager() {

        journeys = new ArrayList<>();

        input = new Scanner(System.in);

        calculator = new FareCalculator();

        summaryManager = new SummaryManager();

        nextJourneyID = 1;

    }

    public void addJourney() {

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
