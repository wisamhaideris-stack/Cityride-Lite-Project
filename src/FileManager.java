import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static final String PROFILE_FILE = "riderProfile.json";

    // Rider Profile

    public void saveProfile(Rider rider) {

        // (HowToDoItInJava, 2023)
        Gson gson = new Gson();

        try {

            FileWriter writer = new FileWriter(PROFILE_FILE);

            gson.toJson(rider, writer);

            writer.close();

            System.out.println("\nProfile saved successfully.");

        }
        catch (IOException e) {

            System.out.println("\nError saving profile.");

        }

    }
    public Rider loadProfile() {

        Rider rider = null;

        // (HowToDoItInJava, 2023)
        Gson gson = new Gson();

        try {

            FileReader reader = new FileReader(PROFILE_FILE);

            rider = gson.fromJson(reader, Rider.class);

            reader.close();

            System.out.println("\nProfile loaded successfully.");

        }
        catch (FileNotFoundException e) {

            System.out.println("\nNo rider profile found.");

        }
        catch (IOException e) {

            System.out.println("\nError loading profile.");

        }

        return rider;

    }

    // Configuration

    public void saveConfiguration() {

    }
    public void loadConfiguration() {

    }

    // Journey Files

    public void importJourneysCSV(JourneyManager manager) {

    }
    public void exportJourneysCSV(JourneyManager manager) {

    }

    // Reports

    public void exportSummaryCSV() {

    }
    public void exportSummaryTXT() {

    }

}