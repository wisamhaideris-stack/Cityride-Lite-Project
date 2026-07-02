import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class FileManager {

    private static final String RIDER_FILE = "riders.json";
    private static final String JOURNEY_FILE = "journeys.csv";

    // Rider Profile

    public void saveRiders(ArrayList<Rider> riders) {

        // (HowToDoItInJava, 2023)
        Gson gson = new Gson();

        try {
            // (Geeks4Geeks, 2025)
            FileWriter writer = new FileWriter(RIDER_FILE);
            gson.toJson(riders, writer);
            writer.close();
            System.out.println("\nRiders saved successfully.");

        }

        catch (IOException e) {System.out.println("\nError saving riders.");}

    }
    public ArrayList<Rider> loadRiders() {

        ArrayList<Rider> riders = new ArrayList<>();

        // (HowToDoItInJava, 2023)
        Gson gson = new Gson();

        try {
            // (Geeks4Geeks, 2025)
            FileReader reader = new FileReader(RIDER_FILE);
            Type riderListType = new TypeToken<ArrayList<Rider>>() {}.getType();
            riders = gson.fromJson(reader, riderListType);
            reader.close();
        }

        catch (FileNotFoundException e) {riders = new ArrayList<>();}
        catch (IOException e) {System.out.println("\nError loading riders.");}

        if (riders == null) {riders = new ArrayList<>();}

        return riders;

    }

    public void addRider(Rider rider) {
        ArrayList<Rider> riders = loadRiders();
        riders.add(rider);
        saveRiders(riders);
    }

    // Configuration

    public void saveConfiguration() {

    }
    public void loadConfiguration() {

    }

    // Journey Files

    public void importJourneysCSV(JourneyManager manager) {

    }
    public void exportJourneysCSV(List<Journey> journeys) {

        try {

            PrintWriter writer = new PrintWriter(new File(JOURNEY_FILE));

            // CSV Header
            writer.println("Journey ID,Date,From Zone,To Zone,Passenger Type,Time Band,Base Fare,Final Fare");

            // Journey Data
            for (Journey journey : journeys) {

                writer.println(journey.getJourneyID() + "," + journey.getDate() + "," + journey.getFromZone() + "," +
                        journey.getToZone() + "," + journey.getPassengerType() + "," + journey.getTimeBand() + "," +
                        journey.getBaseFare() + "," + journey.getFinalFare()
                );
            }

            writer.close();

            System.out.println("\nJourneys exported successfully.");

        }
        catch (IOException e) {

            System.out.println("\nError exporting journeys.");

        }

    }

    // Reports

    public void exportSummaryCSV() {

    }
    public void exportSummaryTXT() {

    }

}