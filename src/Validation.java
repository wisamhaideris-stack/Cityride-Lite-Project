public class Validation{
    public Validation() {
    }
    // Validate Zone
    public boolean validateZone(int zone) {

        return zone >= CityRideDataset.MIN_ZONE && zone <= CityRideDataset.MAX_ZONE;

    }

    // Validate Passenger Type
    // (Bro Code, 2024)
    public boolean validatePassengerType(
            String type
    ) {
        try {
            CityRideDataset.PassengerType.valueOf(type);
            return true;

        }

        catch (IllegalArgumentException e) {
            return false;

        }
    }

    // Validate Time Band
    // (Bro Code, 2024)
    public boolean validateTimeBand(
            String band
    ) {
        try {
            CityRideDataset.TimeBand.valueOf(band);
            return true;
        }

        catch (IllegalArgumentException e) {
            return false;
        }
    }
}
