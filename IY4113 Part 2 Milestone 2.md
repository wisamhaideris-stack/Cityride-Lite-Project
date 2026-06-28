# IY4113 Part 2 Milestone 2

| Assessment Details | Please Complete All Details                                      |
| ------------------ | ---------------------------------------------------------------- |
| Group              | A                                                                |
| Module Title       | Applied Software Engineering using Object Orientated Programming |
| Assessment Type    | Java fundamentals part 2                                         |
| Module Tutor Name  | Johnathon Shore                                                  |
| Student ID Number  | P495305                                                          |
| Date of Submission | 28/06/2026                                                       |
| Word Count         | 3541                                                             |
| GitHub Link        | https://github.com/wisamhaideris-stack/T0495305_Wisam_IYO4113    |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*
- [x] *Where I have used AI, I have cited and referenced appropriately.

------------------------------------------------------------------------------------------------------------------------------

### Class Diagram

---

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-28-15-01-46-Part%202%20Class%20Diagram.png)

---

### Research

------------------------------------------------------------------------------------------------------------------------------

Title of research:GSON: Use in IntelliJ
Reference (link):[GSON: Use in IntelliJ](https://www.youtube.com/watch?v=Qc9EfiepfWs)

How does the research help with coding practise?:

It taught me how to firstly install GSON in Java, along with how to use it.  The way it works was quite alien to me so the video was super helpful as it filled alot of holes I had in my understanding on Gson and how to use it to connect programs to files on your computer

Key coding ideas you could reuse in your program:

I will obviously utilise Gson within my program, to import and export summaries along with the Journeys.csv as thats where i will store the journeys.

Screenshot of research:

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-28-14-13-21-image.png)

------------------------------------------------------------------------------------------------------------------------------

Title of research:Learn the Java super keyword in 10 minutes! 🔝
Reference (link):[Learn the Java super keyword in 10 minutes! 🔝](https://www.youtube.com/watch?v=LN45TyPWAAg)

How does the research help with coding practise?: 

Gave me valuable insight on how to use parent classes and the syntax needed in order to properly utilise parent and subclasses

Key coding ideas you could reuse in your program: 

I will use Parent and subclasses in my program, I plan on building a User parent class with 2 subclasses, 1 being Rider and the other being Admin, each hsve some similarities thus the parent class will allow me to write less code

Screenshot of research:![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-28-14-02-39-image.png)

------------------------------------------------------------------------------------------------------------------------------

### Program Code

---

# Main.java:

import java.util.Scanner;  

public class Main {  
    static Scanner input = new Scanner(System.in);  
    static JourneyManager manager = new JourneyManager(input);  
    static Validation validation = new Validation();  
    public static void main(String[] args) {  
        int startupChoice;  

   do {  

displayStartupMenu();  

startupChoice = getUserChoice();  

switch (startupChoice) {  

    case 1:  

        createRiderProfile();  
        break;  

    case 2:  

        System.out.println("\nNo saved riders.");  
        break;  

    case 3:  

        System.out.println("\nAdmin menu not implemented yet.");  
        break;  

    case 4:  

        System.out.println("\nProgram Closed.");  
        return;  

    default:  
        System.out.println("\nInvalid Choice.");  

}  

} while (startupChoice != 1);  

int choice;  
do {  

displayMenu();  
choice = getUserChoice();  

   <mark> switch (choice) {  </mark>

 <mark>// (Bro Code, 2024)  </mark>

<mark>            case 1:  </mark>
                manager.addJourney();  
                break;  

 case 2:  
        manager.viewJourneysMenu();  
        break;  
    case 3:  
        manager.viewDailySummary();  
        break;  

    case 4:  
        manager.removeJourney();  
        break;  

    case 5:  
        manager.resetJourneys();  
        break;  

    case 6:  
        System.out.println("Program closed");  
        break;  

    default:  
        System.out.println("\nInvalid choice");  

}  

} while (choice != 6);  

}  
public static void displayMenu() {  

System.out.println("CityRide Lite\n");  

System.out.println("1. Add Journey");  
System.out.println("2. View Journeys");  
System.out.println("3. Daily summary");  
System.out.println("4. Remove Journey");  
System.out.println("5. Reset Day");  
System.out.println("6. Exit");  

}  
public static int getUserChoice() {  
    int choice = validation.getValidInteger(input, "Enter Choice: ");  
    return choice;  
}  

public static void displayStartupMenu() {  

System.out.println("\nCityRide");  

System.out.println("1. Create Rider Profile");  
System.out.println("2. Load Rider");  
System.out.println("3. Admin Login");  
System.out.println("4. Exit");  

}  

public static void createRiderProfile() {  

System.out.print("Enter Name: ");  

String name = input.nextLine();  

System.out.print("Passenger Type (ADULT/STUDENT/CHILD/SENIOR_CITIZEN): ");  

CityRideDataset.PassengerType passengerType =  
        CityRideDataset.PassengerType.valueOf(  
                input.nextLine().toUpperCase());  

System.out.print("Default Payment Method: ");  

String paymentMethod = input.nextLine();  

Rider rider = new Rider(  
        name,  
        passengerType,  
        paymentMethod  
);  

manager.setCurrentRider(rider);  

System.out.println("\nWelcome " + rider.getName());  

}  

}

# CityRidedataset.java:

import java.math.BigDecimal;  
import java.math.RoundingMode;  
import java.util.HashMap;  
import java.util.Map;  

final class CityRideDataset {  

private CityRideDataset() {}  

public static final int MIN_ZONE = 1;  
public static final int MAX_ZONE = 5;  

public enum TimeBand {  
    PEAK,  
    OFF_PEAK  
}  

public enum PassengerType {  
    ADULT,  
    STUDENT,  
    CHILD,  
    SENIOR_CITIZEN  
}  

public static final Map<PassengerType, BigDecimal> DISCOUNT_RATE = Map.of(  
        PassengerType.ADULT, new BigDecimal("0.00"),  
        PassengerType.STUDENT, new BigDecimal("0.25"),  
        PassengerType.CHILD, new BigDecimal("0.50"),  
        PassengerType.SENIOR_CITIZEN, new BigDecimal("0.30")  
);  

public static final Map<PassengerType, BigDecimal> DAILY_CAP = Map.of(  
        PassengerType.ADULT, new BigDecimal("8.00"),  
        PassengerType.STUDENT, new BigDecimal("6.00"),  
        PassengerType.CHILD, new BigDecimal("4.00"),  
        PassengerType.SENIOR_CITIZEN, new BigDecimal("7.00")  
);  

public static final Map<String, BigDecimal> BASE_FARE = buildBaseFare();  

public static BigDecimal getBaseFare(int fromZone, int toZone, TimeBand timeBand) {  
    return BASE_FARE.get(key(fromZone, toZone, timeBand));  
}  

public static String key(int fromZone, int toZone, TimeBand timeBand) {  
    return fromZone + "-" + toZone + "-" + timeBand.name();  
}  

private static BigDecimal money(String amount) {  
    return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);  
}  

private static Map<String, BigDecimal> buildBaseFare() {  
    Map<String, BigDecimal> m = new HashMap<>();  

// Peak fares  
put(m,1,1,TimeBand.PEAK,"2.50"); put(m,1,2,TimeBand.PEAK,"3.20");  
put(m,1,3,TimeBand.PEAK,"3.80"); put(m,1,4,TimeBand.PEAK,"4.40");  
put(m,1,5,TimeBand.PEAK,"5.00");  

put(m,2,1,TimeBand.PEAK,"3.20"); put(m,2,2,TimeBand.PEAK,"2.30");  
put(m,2,3,TimeBand.PEAK,"3.10"); put(m,2,4,TimeBand.PEAK,"3.80");  
put(m,2,5,TimeBand.PEAK,"4.50");  

put(m,3,1,TimeBand.PEAK,"3.80"); put(m,3,2,TimeBand.PEAK,"3.10");  
put(m,3,3,TimeBand.PEAK,"2.10"); put(m,3,4,TimeBand.PEAK,"3.00");  
put(m,3,5,TimeBand.PEAK,"3.70");  

put(m,4,1,TimeBand.PEAK,"4.40"); put(m,4,2,TimeBand.PEAK,"3.80");  
put(m,4,3,TimeBand.PEAK,"3.00"); put(m,4,4,TimeBand.PEAK,"2.00");  
put(m,4,5,TimeBand.PEAK,"2.90");  

put(m,5,1,TimeBand.PEAK,"5.00"); put(m,5,2,TimeBand.PEAK,"4.50");  
put(m,5,3,TimeBand.PEAK,"3.70"); put(m,5,4,TimeBand.PEAK,"2.90");  
put(m,5,5,TimeBand.PEAK,"1.90");  

// Off-peak fares  
put(m,1,1,TimeBand.OFF_PEAK,"2.00"); put(m,1,2,TimeBand.OFF_PEAK,"2.70");  
put(m,1,3,TimeBand.OFF_PEAK,"3.20"); put(m,1,4,TimeBand.OFF_PEAK,"3.70");  
put(m,1,5,TimeBand.OFF_PEAK,"4.20");  

put(m,2,1,TimeBand.OFF_PEAK,"2.70"); put(m,2,2,TimeBand.OFF_PEAK,"1.90");  
put(m,2,3,TimeBand.OFF_PEAK,"2.60"); put(m,2,4,TimeBand.OFF_PEAK,"3.20");  
put(m,2,5,TimeBand.OFF_PEAK,"3.80");  

put(m,3,1,TimeBand.OFF_PEAK,"3.20"); put(m,3,2,TimeBand.OFF_PEAK,"2.60");  
put(m,3,3,TimeBand.OFF_PEAK,"1.70"); put(m,3,4,TimeBand.OFF_PEAK,"2.50");  
put(m,3,5,TimeBand.OFF_PEAK,"3.10");  

put(m,4,1,TimeBand.OFF_PEAK,"3.70"); put(m,4,2,TimeBand.OFF_PEAK,"3.20");  
put(m,4,3,TimeBand.OFF_PEAK,"2.50"); put(m,4,4,TimeBand.OFF_PEAK,"1.60");  
put(m,4,5,TimeBand.OFF_PEAK,"2.40");  

put(m,5,1,TimeBand.OFF_PEAK,"4.20"); put(m,5,2,TimeBand.OFF_PEAK,"3.80");  
put(m,5,3,TimeBand.OFF_PEAK,"3.10"); put(m,5,4,TimeBand.OFF_PEAK,"2.40");  
put(m,5,5,TimeBand.OFF_PEAK,"1.50");  

return Map.copyOf(m);  

}  

private static void put(Map<String, BigDecimal> m, int from, int to, TimeBand band, String amount) {  
    m.put(key(from, to, band), money(amount));  
}  

}

# User.java

public abstract class User {  

// Constructors  
private String name;  
public User() {  
    this.name = "";  
}  
public User(String name) {  
    this.name = name;  
}  

public String getName() {  
    return name;  
}  

public void setName(String name) {  
    this.name = name;  
}  

}

# Admin.java

public class Admin extends User {  

private String password;  

// Constructors  
public Admin() {  

<mark>// (Oracle, 2026)  </mark>

   <mark> super();  </mark>

password = "";  

}  
public Admin(String name, String password) {  
<mark>    // (Oracle, 2026)  </mark>
   <mark> super(name); </mark> 

this.password = password;  

}  

public String getPassword() {  
    return password;  
}  
public void setPassword(String password) {  
    this.password = password;  
}  

}

# Rider.java

public class Rider extends User {  

private CityRideDataset.PassengerType passengerType;  
private String defaultPaymentMethod;  

// Constructors  
public Rider() {  
    <mark>// (Oracle, 2026) </mark> 
 <mark>   super();  </mark>

passengerType = CityRideDataset.PassengerType.ADULT;  
defaultPaymentMethod = "";  

}  
public Rider(String name,  
             CityRideDataset.PassengerType passengerType,  
             String defaultPaymentMethod) {  
   <mark> // (Oracle, 2026)  </mark>
    <mark>super(name);  </mark>

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

# FileManager.java

import com.google.gson.Gson;  

import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.File;  
import java.io.PrintWriter;  
import java.util.List;  

public class FileManager {  

private static final String PROFILE_FILE = "riderProfile.json";  
private static final String JOURNEY_FILE = "journeys.csv";  

// Rider Profile  

public void saveProfile(Rider rider) {  

<mark>// (HowToDoItInJava, 2023)  </mark>
<mark>Gson gson = new Gson();  </mark>

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

<mark>// (HowToDoItInJava, 2023)  </mark>

   <mark> Gson gson = new Gson();  </mark>

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

# SummaryManager.java

import java.util.ArrayList;  
import java.math.BigDecimal;  
import java.math.RoundingMode;  

public class SummaryManager{  
    // Constructor  
    public SummaryManager() {}  

// Daily Summary  
public void calculateDailySummary(ArrayList<Journey> journeys) {  

if (journeys.isEmpty()) {  
    System.out.println("\nNo Journeys Stored");  
    return;  

}  

BigDecimal totalCharged =  
        BigDecimal.ZERO;  

Journey mostExpensive =  
        journeys.get(0);  

for (Journey journey : journeys) {  
    totalCharged = totalCharged.add(journey.getFinalFare());  

if (journey.getFinalFare().compareTo(mostExpensive.getFinalFare()) > 0) {  
    mostExpensive = journey;  

}  

}  

// (In28Minutes, 2018)  

   <mark> BigDecimal averageFare = totalCharged.divide(BigDecimal.valueOf(journeys</mark>.size()), 2, RoundingMode.HALF_UP);  

System.out.println(  
        "Daily Summary\n"  
);  

System.out.println("Total Journeys: " + journeys.size());  

System.out.println("Total Charged: " + totalCharged);  

System.out.println("Average Fare: " + averageFare);  

System.out.println("Most Expensive Journey ID: " + mostExpensive.getJourneyID());  

System.out.println("Most Expensive Fare:" + mostExpensive.getFinalFare());  

}  

// Passenger Totals  
public void calculateTotalsByPassengerType(ArrayList<Journey> journeys) {  

int adults = 0;  
int students = 0;  
int children = 0;  
int seniors = 0;  

for (Journey journey : journeys) {  

switch (journey.getPassengerType()) {  
    case ADULT:  
        adults++;  
        break;  

    case STUDENT:  
        students++;  
        break;  

    case CHILD:  
        children++;  
        break;  

    case SENIOR_CITIZEN:  
        seniors++;  
        break;  

}  

}  

System.out.println(  
        "Passenger Totals\n"  
);  

System.out.println("Adults: " + adults);  

System.out.println("Students: " + students);  

System.out.println("Children: " + children);  

System.out.println("Senior Citizens: " + seniors);  

}  

// Journey Categories  
public void countJourneyCategories(ArrayList<Journey> journeys) {  

int peak = 0;  
int offPeak = 0;  

for (Journey journey : journeys) {  

if (journey.getTimeBand() == CityRideDataset.TimeBand.PEAK) {  
    peak++;  
}  

else {  
    offPeak++;  
}  

}  

System.out.println("Journey Categories\n");  
System.out.println("Peak Journeys: " + peak);  
System.out.println("Off Peak Journeys: " + offPeak);  

}  

}

# Validation.java

import java.util.Scanner;  

public class Validation{  
    public Validation() {  
    }  
    // Validate Zone  
    public boolean validateZone(int zone) {  

return zone >= CityRideDataset.MIN_ZONE && zone <= CityRideDataset.MAX_ZONE;  

}  

// Validate Passenger Type  
<mark>// (Bro Code, 2024) </mark>

  <mark> public boolean validatePassengerTyp</mark>e(  
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
<mark>// (Bro Code, 2024)</mark>  <mark>  public boolean validateTimeBand(  
        String band  </mark>
) {  
<mark>    try {  </mark>
        CityRideDataset.TimeBand.valueOf(band);  
        return true;  
    }  

<mark>catch (I</mark>llegalArgumentException e) {  
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

# JourneyManager.java

import java.util.ArrayList;  
import java.util.Scanner;  
import java.math.BigDecimal;  

public class JourneyManager{  

private ArrayList<Journey> journeys;  
private Scanner input;  
private Validation validation;  
private FareCalculator calculator;  
private SummaryManager summaryManager;  
private int nextJourneyID;  
private Rider currentRider;  

// Constructor  
public JourneyManager(Scanner input) {  

journeys = new ArrayList<>();  
validation = new Validation();  
this.input = input;  
calculator = new FareCalculator();  
summaryManager = new SummaryManager();  
nextJourneyID = 1;  
currentRider = null;  

}  

public void setCurrentRider(Rider rider) {currentRider = rider;}  
public Rider getCurrentRider() {return currentRider;}  

public void addJourney() {  

boolean valid = true;  

System.out.println("\nAdd Journey");  

// Date  
System.out.print("Enter Date: ");  
String date = input.nextLine();  

// Validate Date  
if (!validation.validateDate(date)) {  

System.out.println("Invalid Date");  

valid = false;  

}  

int fromZone = 0;  
int toZone = 0;  

if (valid) {  

// Zones  
fromZone = validation.getValidInteger(input, "Enter From Zone: ");  
toZone = validation.getValidInteger(input, "Enter To Zone: ");  

// Validate Zones  
if (!validation.validateZone(fromZone) || !validation.validateZone(toZone)) {  

    System.out.println("Invalid Zone");  

    valid = false;  
}  

}  

String passengerInput = "";  
String bandInput = "";  

if (valid) {  

// Passenger Type  
System.out.print("Passenger Type (ADULT/STUDENT/CHILD/SENIOR_CITIZEN): ");  

passengerInput = input.nextLine().toUpperCase();  

// Validate Passenger Type  
if (!validation.validatePassengerType(passengerInput)) {  
    System.out.println("Invalid Passenger Type");  
    valid = false;  
}  

}  

if (valid) {  

// Time Band  
System.out.print("Time Band (PEAK/OFF_PEAK): ");  

bandInput = input.nextLine().toUpperCase();  

// Validate Time Band  
if (!validation.validateTimeBand(bandInput)) {  
    System.out.println("Invalid Time Band");  
    valid = false;  
}  

}  

if (valid) {  

// Convert To Enums  

// (Oracle, 2026)            <mark>CityRideDataset.PassengerType passengerType = CityRideDataset.PassengerType.valueOf(passengerInput);  </mark>
CityRideDataset.TimeBand timeBand = CityRideDataset.TimeBand.valueOf(bandInput);  

// Fare  
BigDecimal baseFare = calculator.calculateBaseFare(fromZone, toZone, timeBand);  
BigDecimal finalFare = calculator.applyDiscount(baseFare, passengerType);  

// Daily cap  
BigDecimal totalSpentToday = BigDecimal.ZERO;  

for (Journey existingJourney : journeys) {  

    if (existingJourney.getDate().equals(date) && existingJourney.getPassengerType() == passengerType) {  

        // (In28Minutes, 2018)  
        <mark>totalSpentToday = totalSpentToday.add(existingJourney.getFinalFare());  
    }  </mark>
}  

finalFare = calculator.applyDailyCap(totalSpentToday, finalFare, passengerType);  

BigDecimal discountApplied = baseFare.subtract(finalFare);  

int zonesCrossed = Math.abs(fromZone - toZone) + 1;  

// Create Journey  
Journey journey = new Journey(nextJourneyID, date, fromZone, toZone, passengerType, timeBand, baseFare, finalFare, discountApplied, zonesCrossed);  

// Store Journey  

// (Oracle, 2026)            journeys.add(journey);  

   <mark>     nextJourneyID++;  </mark>
        System.out.println("\nJourney Added Successfully");  

}  

}  

public void listJourneys() {  
    if (journeys.isEmpty()) {  
        System.out.println("\nNo Journeys Stored");  

return;  

}  

for (Journey journey : journeys) {  
    journey.displayJourney();  
}  

}  

public void filterByPassengerType() {  
    input.nextLine();  
    System.out.print("\nEnter Passenger Type To Filter: ");  

String filter = input.nextLine().toUpperCase();  

boolean found = false;  

for (Journey journey : journeys) {  

<mark>// (Alexandra Obregon, 2024) </mark> 

  <mark>  if (journey.getPassengerType().name().equals(filter)) {journey.displayJourney();  </mark>

found = true;  
}  

}  
if (!found) {  

System.out.println(  
        "\nNo Matching Journeys Found"  
);  

}  

}  

public void filterByTimeBand() {  

input.nextLine();  
System.out.print("\nEnter Time Band (PEAK/OFF_PEAK): ");  

String filter = input.nextLine().toUpperCase();  

boolean found = false;  

for (Journey journey : journeys) {  

if (journey.getTimeBand().name().equals(filter)) {  
    journey.displayJourney();  
    found = true;  
}  

}  
if (!found) {  

System.out.println("\nNo Matching Journeys Found");  

}  

}  

public void filterByZone() {  

System.out.print("\nEnter Zone: ");  
int zone = input.nextInt();  
boolean found = false;  

for (Journey journey : journeys) {  

if (journey.getFromZone() == zone || journey.getToZone() == zone) {  
    journey.displayJourney();  
    found = true;  
}  

}  

if (!found) {  
    System.out.println("\nNo Matching Journeys Found");  
}  

}  

public void filterByDate() {  

input.nextLine();  
System.out.print("\nEnter Date: ");  
String date = input.nextLine();  
boolean found = false;  

for (Journey journey : journeys) {  
    if (journey.getDate().equals(date)) {  

journey.displayJourney();  
    found = true;  
}  

}  

if (!found) {  
    System.out.println("\nNo Matching Journeys Found");  
}  

}  

public void removeJourney() {  
    System.out.print("\nEnter Journey ID To Remove: ");  

int id = input.nextInt();  

boolean removed = false;  

for (int i = 0; i < journeys.size(); i++) {  

if (journeys.get(i).getJourneyID() == id) {  

    journeys.remove(i);  

    removed = true;  

    System.out.println("\nJourney Removed");  

    break;  

}  

}  

if (!removed) {  

System.out.println("\nJourney ID Not Found");  

}  

}  

public void resetJourneys() {  
    journeys.clear();  

System.out.println("\nAll Journeys Reset");  

}  

public void viewDailySummary() {  

summaryManager.calculateDailySummary(journeys);  

}  

public void viewJourneysMenu() {  

int choice;  

do {  

System.out.println("\nView Journeys");  

System.out.println("1. View All Journeys");  

System.out.println("2. Filter By Passenger Type");  

System.out.println("3. Filter By Time Band");  

System.out.println("4. Filter By Zone");  

System.out.println("5. Filter By Date");  

System.out.println("6. Passenger Totals");  

System.out.println("7. Journey categories");  

System.out.println("8. Return");  

System.out.print("Enter Choice: ");  

choice = input.nextInt();  


// (Bro Code, 2024)  

   <mark> switch (choice) {  </mark>

case 1:  
        listJourneys();  
        break;  

    case 2:  
        filterByPassengerType();  
        break;  

    case 3:  
        filterByTimeBand();  
        break;  

    case 4:  
        filterByZone();  
        break;  

    case 5:  
        filterByDate();  
        break;  

    case 6:  
        summaryManager.calculateTotalsByPassengerType(journeys);  
        break;  

    case 7:  
        summaryManager.countJourneyCategories(journeys);  
        break;  

}  

} while (choice != 8);  

}  

public ArrayList<Journey> getJourneys() {  

return journeys;  

}  

}

# Journey.java

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

# Farecalculator.java

import java.math.BigDecimal;  
import java.math.RoundingMode;  

public class FareCalculator {  

// Constructor  
public FareCalculator() {}  

// Calculate Base Fare  
// (In28Minutes, 2018)   <mark> public BigDecimal calculateBaseFare(int fromZone, int toZone, CityRideDataset.TimeBand timeBand) {  </mark>

return CityRideDataset.getBaseFare(fromZone, toZone, timeBand);  

}  

// Apply Discount  
// (In28Minutes, 2018)    <mark>public BigDecimal applyDiscount(BigDecimal baseFare, CityRideDataset.PassengerType passengerType) {  </mark>

BigDecimal discountRate = CityRideDataset.DISCOUNT_RATE.get(passengerType);  
BigDecimal discount = baseFare.multiply(discountRate);  
BigDecimal finalFare = baseFare.subtract(discount);  

// (Oracle, 2026)  

   <mark> return finalFare.setScale(2, RoundingMode.HALF_UP  </mark>
    );  
}  

public BigDecimal applyDailyCap(BigDecimal totalSpentToday, BigDecimal currentFare,  
        CityRideDataset.PassengerType passengerType) {  

BigDecimal cap = CityRideDataset.DAILY_CAP.get(passengerType);  

// (Oracle, 2026)  

 <mark>   BigDecimal newTotal = totalSpentToday.add(currentFare);  </mark>

if (newTotal.compareTo(cap) > 0) {  

// (In28Minutes, 2018)  

   <mark> BigDecimal remaining = cap.subtract(totalSpentToday); </mark> 

if (remaining.compareTo(BigDecimal.ZERO) < 0) {  

    return BigDecimal.ZERO;  

}  

return remaining;  

}  
return currentFare;  

}  

}

------------------------------------------------------------------------------------------------------------------------------

### Updated Gantt Chart

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-28-14-57-13-image.png)

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-28-14-57-52-image.png)

------------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------------

### Diary Entries (at least 4)

## Diary Entry 1 - 22/06/2026

Today was the first day of me working on milestone 2, I learned that i should submit the class diagram in this milestone as i hadn't submitted it in the last one so that was the first target of the day, I started off by drafting how i wanted the program to function, as in what I wanted to see once i started, so i had it envisioned in my mind that there ould first be a startup menu to select create, load or admin menu, which then after create or load after inputting appropriate details would send user to main menu, now after having this idea I broke it down into the functions i would require to achieve this and the attributes, which then I put into classes but I had to re-learn about multiplicity and the arrows again as I forgot how to use them, overall this day took about 4 hours and was glad with the progress I made.

## Diary Entry 2 - 24/06/2026

Today was a day of research, since I knew what I wanted to achieve all that was left was to learn how to achieve it, I made the mistake of trying to jump into it head on and it ended in a big failure, so i turned to Youtube and watched some tutorials, I learned about parent classes and how to properly install and use GSON to use files in java and keep stuff saved to be later again in another instance of the program, I also read up some articles and made sure I had sufficient knowledge on how to begin, in the next few days I'd keep the websites and videos next to me while programming so i can refer back when needed. 

## Diary Entry 3 - 26/06/2026

Today I started on coding the program, now I knew i was a bit late so I had to make a move on. I began by coding the parent class and the 2 subclasses, thanks to by research this was quite straightforward and simple, after that i worked on a filemanager class, this class was purely meant to interact and connect the files to the program thus it didn't need any attributes as it would just access and read or access and write or even export/import, now this wasn't easy I'd say it was very time consuming and I didnt even finish all the methods, I will come back to it after i feel more comfortable. After that the rest of the classes needed updating as it wouldnt work with the new classes, so I spent the rest of that day and the next day just updating.

## Diary Entry 4 - 28/06/2026

This was the final day, I still had some coding left as externally speaking the program looked identical so I wanted to implement the startup menu I talked about in the diary entry a few days ago even if it wouldn't properly perform all the function I just wanted a tangible change in the program that was visible, this process was simple just another menu but I had some issues with input alot of times when the program expects an int and if the user enters a string the program crashes so i designed a method to handle this in validation class that i had to paste in all the places where the error could occur, after that was easy, just put the document together and update the gantt chart.

------------------------------------------------------------------------------------------------------------------------------

# References

Bro Code. (2024, December 9). Learn EXCEPTION HANDLING in 8 minutes! ⚠️. [YouTube video]. Retrieved from: https://www.youtube.com/watch?v=u1PROb-aRUI [Accessed 22 May 2026].

Bro Code. (2024, December 5). Learn Java enhanced switches in 8 minutes! 💡. [YouTube video]. Retrieved from: [Learn Java enhanced switches in 8 minutes! 💡 - YouTube](https://www.youtube.com/watch?v=6q2JKiynteM&t=75s) [Accessed 18 May 2026].

HowToDoInJava. (n.d.). *Gson Tutorial: Read and Write JSON with Examples*. Retrieved from: [Gson Tutorial: Read and Write JSON with Examples](https://howtodoinjava.com/gson/gson/) [Accessed 24 July 2026].

In28Minutes. (2018, March 20). Java BigDecimal Tutorial - 1. [YouTube video]. Retrieved from: [Java BigDecimal Tutorial - 1 - YouTube](https://www.youtube.com/watch?v=MK6LDyQuv6U) [Accessed 23 May 2026].

Obregon, A. (2024, September 1). Java's Objects.equals() Method Explained, Medium. [online] Retrieved from: https://medium.com/@AlexanderObregon/javas-objects-equals-method-explained-3a84c963edfa [Accessed 23 May 2026].

Oracle. (2014). RoundingMode (Java Platform SE 8). Retrieved from: [RoundingMode (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html) [Accessed 23 May 2026].

Oracle. (n.d.). *Using the Keyword super (The Java™ Tutorials > Learning the Java Language > Interfaces and Inheritance)*. Retrieved from: https://docs.oracle.com/javase/tutorial/java/IandI/super.html [Accessed 24 July 2026].
