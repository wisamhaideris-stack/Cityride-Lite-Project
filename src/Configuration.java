import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private Map<CityRideDataset.PassengerType, BigDecimal> discountRates;
    private Map<CityRideDataset.PassengerType, BigDecimal> dailyCaps;
    private Map<String, BigDecimal> baseFares;
    private String adminPassword;
    private String peakStart;
    private String peakEnd;
    

    public Configuration() {
        // (W3School, 2026)
        discountRates = new HashMap<>();
        dailyCaps = new HashMap<>();
        baseFares = new HashMap<>();
        loadDefaultValues();
    }

    public void loadDefaultValues() {
        discountRates.put(CityRideDataset.PassengerType.ADULT, new BigDecimal("0.00"));
        discountRates.put(CityRideDataset.PassengerType.STUDENT, new BigDecimal("0.25"));
        discountRates.put(CityRideDataset.PassengerType.CHILD, new BigDecimal("0.50"));
        discountRates.put(CityRideDataset.PassengerType.SENIOR_CITIZEN, new BigDecimal("0.30"));

        dailyCaps.put(CityRideDataset.PassengerType.ADULT, new BigDecimal("8.00"));
        dailyCaps.put(CityRideDataset.PassengerType.STUDENT, new BigDecimal("6.00"));
        dailyCaps.put(CityRideDataset.PassengerType.CHILD, new BigDecimal("4.00"));
        dailyCaps.put(CityRideDataset.PassengerType.SENIOR_CITIZEN, new BigDecimal("7.00"));

        baseFares.putAll(CityRideDataset.BASE_FARE);

        adminPassword = "admin123";
        peakStart = "07:00";
        peakEnd = "10:00";
    }

    // (Oracle, 2026)
    public Map<CityRideDataset.PassengerType, BigDecimal> getDiscountRates() { return discountRates; }
    public Map<CityRideDataset.PassengerType, BigDecimal> getDailyCaps() { return dailyCaps; }
    public Map<String, BigDecimal> getBaseFares() { return baseFares; }

    public String getAdminPassword() { return adminPassword; }
    public void setAdminPassword(String adminPassword) { this.adminPassword = adminPassword; }

    public String getPeakStart() { return peakStart; }
    public void setPeakStart(String peakStart) { this.peakStart = peakStart; }

    public String getPeakEnd() { return peakEnd; }
    public void setPeakEnd(String peakEnd) { this.peakEnd = peakEnd; }

    public void resetToDefaults() {
        discountRates.clear();
        dailyCaps.clear();
        baseFares.clear();
        loadDefaultValues();
    }
}