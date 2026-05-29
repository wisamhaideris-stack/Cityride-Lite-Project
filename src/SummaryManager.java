import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SummaryManager{
    // Constructor
    public SummaryManager() {}

    // Daily Summary
    public void calculateDailySummary(ArrayList<Journey> journeys) {

        if (journeys.isEmpty()) {
            System.out.println("\nNo Journeys Stored");
            return;

        }

        BigDecimal totalCharged =
                BigDecimal.ZERO;

        Journey mostExpensive =
                journeys.get(0);

        for (Journey journey : journeys) {
            totalCharged = totalCharged.add(journey.getFinalFare());

            if (journey.getFinalFare().compareTo(mostExpensive.getFinalFare()) > 0) {
                mostExpensive = journey;

            }

        }

        // (In28Minutes, 2018)

        BigDecimal averageFare = totalCharged.divide(BigDecimal.valueOf(journeys.size()), 2, RoundingMode.HALF_UP);

        System.out.println(
                "Daily Summary\n"
        );

        System.out.println("Total Journeys: " + journeys.size());

        System.out.println("Total Charged: " + totalCharged);

        System.out.println("Average Fare: " + averageFare);

        System.out.println("Most Expensive Journey ID: " + mostExpensive.getJourneyID());

        System.out.println("Most Expensive Fare:" + mostExpensive.getFinalFare());

    }

    // Passenger Totals
    public void calculateTotalsByPassengerType(ArrayList<Journey> journeys) {

        int adults = 0;
        int students = 0;
        int children = 0;
        int seniors = 0;

        for (Journey journey : journeys) {

            switch (journey.getPassengerType()) {
                case ADULT:
                    adults++;
                    break;

                case STUDENT:
                    students++;
                    break;

                case CHILD:
                    children++;
                    break;

                case SENIOR_CITIZEN:
                    seniors++;
                    break;

            }

        }

        System.out.println(
                "Passenger Totals\n"
        );

        System.out.println("Adults: " + adults);

        System.out.println("Students: " + students);

        System.out.println("Children: " + children);

        System.out.println("Senior Citizens: " + seniors);

    }

    // Journey Categories
    public void countJourneyCategories(ArrayList<Journey> journeys) {

        int peak = 0;
        int offPeak = 0;

        for (Journey journey : journeys) {

            if (journey.getTimeBand() == CityRideDataset.TimeBand.PEAK) {
                peak++;
            }

            else {
                offPeak++;
            }

        }

        System.out.println("Journey Categories\n");
        System.out.println("Peak Journeys: " + peak);
        System.out.println("Off Peak Journeys: " + offPeak);

    }

}
