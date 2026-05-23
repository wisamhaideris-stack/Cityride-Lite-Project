import java.math.BigDecimal;
import java.math.RoundingMode;

public class FareCalculator {

    // Constructor
    public FareCalculator() {
    }

    // Calculate Base Fare
    // (In28Minutes, 2018)
    public BigDecimal calculateBaseFare(int fromZone, int toZone, CityRideDataset.TimeBand timeBand) {

        return CityRideDataset.getBaseFare(fromZone, toZone, timeBand);

    }

    // Apply Discount
    // (In28Minutes, 2018)
    public BigDecimal applyDiscount(BigDecimal baseFare, CityRideDataset.PassengerType passengerType) {

        BigDecimal discountRate = CityRideDataset.DISCOUNT_RATE.get(passengerType);
        BigDecimal discount = baseFare.multiply(discountRate);
        BigDecimal finalFare = baseFare.subtract(discount);

        // (Oracle, 2026)
        return finalFare.setScale(2, RoundingMode.HALF_UP
        );
    }
}
