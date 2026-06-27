import java.math.BigDecimal;

public class Journey {
    private int journeyID;
    private String date;
    private int fromZone;
    private int toZone;
    private CityRideDataset.PassengerType passengerType;
    private CityRideDataset.TimeBand timeBand;
    private BigDecimal baseFare;
    private BigDecimal finalFare;
    private BigDecimal discountApplied;
    private int zonesCrossed;

    // Constructor
    public Journey(int journeyID, String date, int fromZone, int toZone, CityRideDataset.PassengerType passengerType,
                   CityRideDataset.TimeBand timeBand, BigDecimal baseFare, BigDecimal finalFare,
                   BigDecimal discountApplied, int zonesCrossed)
    {

        this.journeyID = journeyID;
        this.date = date;
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.passengerType = passengerType;
        this.timeBand = timeBand;
        this.baseFare = baseFare;
        this.finalFare = finalFare;
        this.discountApplied = discountApplied;
        this.zonesCrossed = zonesCrossed;

    }

    // Get methods
    public int getJourneyID() {
        return journeyID;
    }
    public String getDate() {
        return date;
    }
    public int getFromZone() { return fromZone;}
    public int getToZone() {
        return toZone;
    }
    public CityRideDataset.PassengerType getPassengerType() {
        return passengerType;
    }
    public CityRideDataset.TimeBand getTimeBand() {
        return timeBand;
    }
    public BigDecimal getBaseFare() {
        return baseFare;
    }
    public BigDecimal getFinalFare() {
        return finalFare;
    }
    public BigDecimal getDiscountApplied() {return discountApplied;}
    public int getZonesCrossed() {return zonesCrossed;}

    // Set methods
    public void setJourneyID(int journeyID) {
        this.journeyID = journeyID;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setFromZone(int fromZone) {
        this.fromZone = fromZone;
    }
    public void setToZone(int toZone) {
        this.toZone = toZone;
    }
    public void setPassengerType(CityRideDataset.PassengerType passengerType) {
        this.passengerType = passengerType;
    }
    public void setTimeBand(CityRideDataset.TimeBand timeBand) {
        this.timeBand = timeBand;
    }
    public void setBaseFare(BigDecimal baseFare) {
        this.baseFare = baseFare;
    }
    public void setFinalFare(BigDecimal finalFare) {
        this.finalFare = finalFare;
    }
    public void setDiscountApplied(BigDecimal discountApplied) {this.discountApplied = discountApplied;}

    public void setZonesCrossed(int zonesCrossed) {this.zonesCrossed = zonesCrossed;}

    // Methods
    public int calculateZonesCrossed() {

        zonesCrossed = Math.abs(fromZone - toZone) + 1;

        return zonesCrossed;

    }
    public void displayJourney() {
        System.out.println("\nJourney ID: " + journeyID);
        System.out.println("Date: " + date);
        System.out.println("From Zone: " + fromZone);
        System.out.println("To Zone: " + toZone);
        System.out.println("Passenger Type: " + passengerType);
        System.out.println("Time Band: " + timeBand);
        System.out.println("Base Fare: " + baseFare);
        System.out.println("Final Fare: " + finalFare);
        System.out.println("Zones Crossed: " + zonesCrossed);
        System.out.println("Discount Applied: " + discountApplied);
    }
}
