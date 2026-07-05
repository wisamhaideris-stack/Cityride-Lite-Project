import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

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

    public String buildSummaryText(ArrayList<Journey> journeys, Rider rider) {
        String riderName = (rider == null) ? "UnknownRider" : rider.getName();

        if (journeys.isEmpty()) {
            return "CityRide End-of-Day Summary\nRider: " + riderName + "\nNo journeys recorded.\n";
        }

        BigDecimal totalCharged = BigDecimal.ZERO;
        BigDecimal totalBase = BigDecimal.ZERO;
        int capHits = 0;
        Journey mostExpensive = journeys.get(0);

        int peak = 0, offPeak = 0;
        Map<String, Integer> zonePairCounts = new HashMap<>();

        for (Journey j : journeys) {
            totalCharged = totalCharged.add(j.getFinalFare());
            totalBase = totalBase.add(j.getBaseFare());
            if (j.isCapApplied()) capHits++;
            if (j.getTimeBand() == CityRideDataset.TimeBand.PEAK) peak++; else offPeak++;

            String pair = j.getFromZone() + "-" + j.getToZone();
            zonePairCounts.put(pair, zonePairCounts.getOrDefault(pair, 0) + 1);

            if (j.getFinalFare().compareTo(mostExpensive.getFinalFare()) > 0) mostExpensive = j;
        }

        BigDecimal avg = totalCharged.divide(BigDecimal.valueOf(journeys.size()), 2, RoundingMode.HALF_UP);
        BigDecimal savings = totalBase.subtract(totalCharged);

        StringBuilder sb = new StringBuilder();
        sb.append("CityRide End-of-Day Summary\n");
        sb.append("Rider: ").append(riderName).append("\n");
        sb.append("Total Journeys: ").append(journeys.size()).append("\n");
        sb.append("Total Cost: £").append(totalCharged).append("\n");
        sb.append("Average Cost: £").append(avg).append("\n");
        sb.append("Most Expensive Journey ID: ").append(mostExpensive.getJourneyID()).append("\n");
        sb.append("Most Expensive Fare: £").append(mostExpensive.getFinalFare()).append("\n");
        sb.append("Caps Applied Count: ").append(capHits).append("\n");
        sb.append("Savings vs Base: £").append(savings).append("\n");
        sb.append("Peak Journeys: ").append(peak).append("\n");
        sb.append("Off-Peak Journeys: ").append(offPeak).append("\n");
        sb.append("Zone Pair Counts:\n");
        for (String pair : zonePairCounts.keySet()) {
            sb.append("  ").append(pair).append(": ").append(zonePairCounts.get(pair)).append("\n");
        }

        return sb.toString();
    }

    public String buildSummaryCsv(ArrayList<Journey> journeys, Rider rider) {
        String riderName = (rider == null) ? "UnknownRider" : rider.getName();

        BigDecimal totalCharged = BigDecimal.ZERO;
        BigDecimal totalBase = BigDecimal.ZERO;
        int capHits = 0;
        Journey mostExpensive = journeys.isEmpty() ? null : journeys.get(0);

        for (Journey j : journeys) {
            totalCharged = totalCharged.add(j.getFinalFare());
            totalBase = totalBase.add(j.getBaseFare());
            if (j.isCapApplied()) capHits++;
            if (mostExpensive != null && j.getFinalFare().compareTo(mostExpensive.getFinalFare()) > 0) mostExpensive = j;
        }

        BigDecimal avg = journeys.isEmpty() ? BigDecimal.ZERO :
                totalCharged.divide(BigDecimal.valueOf(journeys.size()), 2, RoundingMode.HALF_UP);
        BigDecimal savings = totalBase.subtract(totalCharged);

        StringBuilder sb = new StringBuilder();
        sb.append("Metric,Value\n");
        sb.append("Rider,").append(riderName).append("\n");
        sb.append("Total Journeys,").append(journeys.size()).append("\n");
        sb.append("Total Cost,").append(totalCharged).append("\n");
        sb.append("Average Cost,").append(avg).append("\n");
        sb.append("Most Expensive Journey ID,").append(mostExpensive == null ? "" : mostExpensive.getJourneyID()).append("\n");
        sb.append("Most Expensive Fare,").append(mostExpensive == null ? "" : mostExpensive.getFinalFare()).append("\n");
        sb.append("Caps Applied Count,").append(capHits).append("\n");
        sb.append("Savings vs Base,").append(savings).append("\n");
        return sb.toString();
    }
}
