import java.math.BigDecimal;
import java.math.RoundingMode;

public class FareCalculator {
    private Configuration configuration;

    // Constructor
    public FareCalculator(Configuration configuration) {
        this.configuration = configuration;

    }
    // Calculate Base Fare
    // (In28Minutes, 2018)
    public BigDecimal calculateBaseFare(int fromZone, int toZone, CityRideDataset.TimeBand timeBand) {

        String key = CityRideDataset.key(fromZone, toZone, timeBand);
        BigDecimal fare = configuration.getBaseFares().get(key);
        return fare;
    }

    // Apply Discount
    // (In28Minutes, 2018)
    public BigDecimal applyDiscount(BigDecimal baseFare, CityRideDataset.PassengerType passengerType) {

        BigDecimal discountRate = configuration.getDiscountRates().get(passengerType);
        BigDecimal discount = baseFare.multiply(discountRate);
        BigDecimal finalFare = baseFare.subtract(discount);

        // (Oracle, 2026)
        return finalFare.setScale(2, RoundingMode.HALF_UP
        );
    }

    public BigDecimal applyDailyCap(BigDecimal totalSpentToday, BigDecimal currentFare,
            CityRideDataset.PassengerType passengerType) {

        BigDecimal cap = configuration.getDailyCaps().get(passengerType);

        // (Oracle, 2026)
        BigDecimal newTotal = totalSpentToday.add(currentFare);

        if (newTotal.compareTo(cap) > 0) {

            // (In28Minutes, 2018)
            BigDecimal remaining = cap.subtract(totalSpentToday);

            if (remaining.compareTo(BigDecimal.ZERO) < 0) {

                return BigDecimal.ZERO;

            }

            return remaining;

        }
        return currentFare;

    }

}
