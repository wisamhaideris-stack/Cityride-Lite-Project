import java.util.Scanner;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;

public class Validation {
    public Validation() {
    }

    // Validate Zone
    public boolean validateZone(int zone) {

        return zone >= CityRideDataset.MIN_ZONE && zone <= CityRideDataset.MAX_ZONE;

    }

    // Validate Passenger Type
    // (Bro Code, 2024)
    public boolean validatePassengerType(String type) {

        boolean valid = true;
        try {
            CityRideDataset.PassengerType.valueOf(type);
        } catch (IllegalArgumentException e) {
            valid = false;
        }
        return valid;
    }

    // Validate Time Band
    // (Bro Code, 2024)
    public boolean validateTimeBand(String band) {

        boolean valid = true;


        try {
            CityRideDataset.TimeBand.valueOf(band);
        } catch (IllegalArgumentException e) {
            valid = false;
        }
        return valid;
    }

    // Validate Date
    public boolean validateDate(String date) {

        boolean valid = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return valid;
    }

    public String getValidDate(Scanner input) {
        String date = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter Date (dd/MM/yyyy): ");
            date = input.nextLine();
            if (validateDate(date)) {
                valid = true;
            } else {
                System.out.println("Invalid date. Please use the format dd/MM/yyyy.");
            }
        }
        return date;
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
            } else {
                System.out.println("Invalid input. Please enter a whole number.");
                input.nextLine();
            }
        }
        return number;

    }

    // Decimal Input
    public BigDecimal getValidDecimal(Scanner input, String message) {

        BigDecimal number = BigDecimal.ZERO;
        boolean valid = false;
        while (!valid) {

            System.out.print(message);

            if (input.hasNextBigDecimal()) {
                number = input.nextBigDecimal();
                input.nextLine();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                input.nextLine();
            }
        }
        return number;
    }

    // Passenger Type Input
    public CityRideDataset.PassengerType getValidPassengerType(Scanner input) {

        CityRideDataset.PassengerType passengerType = null;
        boolean valid = false;

        while (!valid) {

            System.out.print("Passenger Type (ADULT/STUDENT/CHILD/SENIOR_CITIZEN): ");
            String value = input.nextLine().toUpperCase();

            if (validatePassengerType(value)) {
                passengerType = CityRideDataset.PassengerType.valueOf(value);
                valid = true;
            } else {
                System.out.println("Invalid Passenger Type.");
            }
        }
        return passengerType;
    }

    // Time Band Input
    public CityRideDataset.TimeBand getValidTimeBand(Scanner input) {

        CityRideDataset.TimeBand timeBand = null;
        boolean valid = false;
        while (!valid) {

            System.out.print("Time Band (PEAK/OFF_PEAK): ");
            String value = input.nextLine().toUpperCase();

            if (validateTimeBand(value)) {
                timeBand = CityRideDataset.TimeBand.valueOf(value);
                valid = true;
            } else {
                System.out.println("Invalid Time Band.");
            }
        }
        return timeBand;

    }

    public boolean validateClockTime(String time) {
        // ( Oracle, 2026)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(time, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String getValidClockTime(Scanner input) {
        String time = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter Time (HH:mm, e.g. 08:30): ");
            time = input.nextLine();
            if (validateClockTime(time)) valid = true;
            else System.out.println("Invalid time. Use HH:mm.");
        }
        return time;
    }

    public boolean validateDiscount(BigDecimal d) {
        return d.compareTo(BigDecimal.ZERO) >= 0 && d.compareTo(BigDecimal.ONE) <= 0;
    }

    public boolean validateMoney(BigDecimal m) {
        return m.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean validateFareKey(int fromZone, int toZone) {
        return validateZone(fromZone) && validateZone(toZone);
    }
}