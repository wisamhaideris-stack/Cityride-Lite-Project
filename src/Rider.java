public class Rider extends User {

    private CityRideDataset.PassengerType passengerType;
    private String defaultPaymentMethod;

    // Constructors
    public Rider() {
        // (Oracle, 2026)
        super();

        passengerType = CityRideDataset.PassengerType.ADULT;
        defaultPaymentMethod = "";
    }
    public Rider(String name,
                 CityRideDataset.PassengerType passengerType,
                 String defaultPaymentMethod) {
        // (Oracle, 2026)
        super(name);

        this.passengerType = passengerType;
        this.defaultPaymentMethod = defaultPaymentMethod;
    }


    public CityRideDataset.PassengerType getPassengerType() {
        return passengerType;
    }
    public void setPassengerType(CityRideDataset.PassengerType passengerType) {
        this.passengerType = passengerType;
    }


    public String getDefaultPaymentMethod() {
        return defaultPaymentMethod;
    }
    public void setDefaultPaymentMethod(String defaultPaymentMethod) {
        this.defaultPaymentMethod = defaultPaymentMethod;
    }

}