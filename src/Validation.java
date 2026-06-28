import java.util.Scanner;

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

    // Validate Date
    public boolean validateDate(String date) {
        return !date.isBlank();

    }

    public int getValidInteger(Scanner input, String message) {

        int number = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(message);
            if (input.hasNextInt()) {

                number = input.nextInt();
                input.nextLine();

                valid = true;
            }
            else {
                System.out.println("Invalid input. Please enter a whole number.");
                input.nextLine();
            }
        }
        return number;

    }
}
