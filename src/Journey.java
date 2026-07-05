import java.math.BigDecimal;

public class Journey {
    private int journeyID;
    private String date; // dd/MM/yyyy
    private String time; // HH:mm
    private int fromZone;
    private int toZone;
    private CityRideDataset.PassengerType passengerType;
    private CityRideDataset.TimeBand timeBand;
    private BigDecimal baseFare;
    private BigDecimal finalFare;
    private BigDecimal discountApplied;
    private int zonesCrossed;
    private boolean capApplied;

    // Constructor
    public Journey(int journeyID, String date, String time, int fromZone, int toZone,
                   CityRideDataset.PassengerType passengerType,
                   CityRideDataset.TimeBand timeBand, BigDecimal baseFare, BigDecimal finalFare,
                   BigDecimal discountApplied, int zonesCrossed, boolean capApplied) {
        this.journeyID = journeyID;
        this.date = date;
        this.time = time;
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.passengerType = passengerType;
        this.timeBand = timeBand;
        this.baseFare = baseFare;
        this.finalFare = finalFare;
        this.discountApplied = discountApplied;
        this.zonesCrossed = zonesCrossed;
        this.capApplied = capApplied;
    }

    // Backward-compatible constructor (if you still call old one anywhere)
    public Journey(int journeyID, String date, int fromZone, int toZone,
                   CityRideDataset.PassengerType passengerType,
                   CityRideDataset.TimeBand timeBand, BigDecimal baseFare, BigDecimal finalFare,
                   BigDecimal discountApplied, int zonesCrossed) {
        this(journeyID, date, "00:00", fromZone, toZone, passengerType, timeBand, baseFare, finalFare,
                discountApplied, zonesCrossed, false);
    }

    public int getJourneyID() { return journeyID; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public int getFromZone() { return fromZone; }
    public int getToZone() { return toZone; }
    public CityRideDataset.PassengerType getPassengerType() { return passengerType; }
    public CityRideDataset.TimeBand getTimeBand() { return timeBand; }
    public BigDecimal getBaseFare() { return baseFare; }
    public BigDecimal getFinalFare() { return finalFare; }
    public BigDecimal getDiscountApplied() { return discountApplied; }
    public int getZonesCrossed() { return zonesCrossed; }
    public boolean isCapApplied() { return capApplied; }

    public void setJourneyID(int journeyID) { this.journeyID = journeyID; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setFromZone(int fromZone) { this.fromZone = fromZone; }
    public void setToZone(int toZone) { this.toZone = toZone; }
    public void setPassengerType(CityRideDataset.PassengerType passengerType) { this.passengerType = passengerType; }
    public void setTimeBand(CityRideDataset.TimeBand timeBand) { this.timeBand = timeBand; }
    public void setBaseFare(BigDecimal baseFare) { this.baseFare = baseFare; }
    public void setFinalFare(BigDecimal finalFare) { this.finalFare = finalFare; }
    public void setDiscountApplied(BigDecimal discountApplied) { this.discountApplied = discountApplied; }
    public void setZonesCrossed(int zonesCrossed) { this.zonesCrossed = zonesCrossed; }
    public void setCapApplied(boolean capApplied) { this.capApplied = capApplied; }

    public int calculateZonesCrossed() {
        zonesCrossed = Math.abs(fromZone - toZone) + 1;
        return zonesCrossed;
    }

    public void displayJourney() {
        System.out.println("\nJourney ID: " + journeyID);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("From Zone: " + fromZone);
        System.out.println("To Zone: " + toZone);
        System.out.println("Passenger Type: " + passengerType);
        System.out.println("Time Band: " + timeBand);
        System.out.println("Base Fare: " + baseFare);
        System.out.println("Final Fare: " + finalFare);
        System.out.println("Discount Applied: " + discountApplied);
        System.out.println("Zones Crossed: " + zonesCrossed);
        System.out.println("Cap Applied: " + (capApplied ? "YES" : "NO"));
    }
}