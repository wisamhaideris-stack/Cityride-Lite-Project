# IY4113 Milestone 4

| Assessment Details | Please Complete All Details                                      |
| ------------------ | ---------------------------------------------------------------- |
| Group              | A                                                                |
| Module Title       | Applied Software Engineering using Object Orientated Programming |
| Assessment Type    | Java fundamentals part 1                                         |
| Module Tutor Name  | Jonathon Shore                                                   |
| Student ID Number  | P495305                                                          |
| Date of Submission | 24/05/2026                                                       |
| Word Count         | 2336                                                             |
| GItHub Link        | https://github.com/wisamhaideris-stack/T0495305_Wisam_IYO4113    |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*
- [x] *Where I have used AI, I have cited and referenced appropriately.

------------------------------------------------------------------------------------------------------------------------------

### Program Code

---

Most of the code works, currently i havent made summary manager thus, view daily summary wont work aswell as the option to view by passenger type.



Main
------------------------------------------------------------------------------------------------------------------------------

import java.util.Scanner;  

public class Main {  
    static Scanner input = new Scanner(System.in);  
    static JourneyManager manager = new JourneyManager(input);  
    public static void main(String[] args) {  

        int choice;  
        do {  
    
            displayMenu();  
            choice = getUserChoice();  
    
            switch (choice) {  
    
                // (Bro Code, 2024)  
    
                case 1:  
                    manager.addJourney();  
                    break;  
    
                case 2:  
                    manager.viewJourneysMenu();  
                    break;  
                case 3:  
                    // daily summary  
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
        System.out.println("2. List Journeys");  
        System.out.println("3. Daily summary");  
        System.out.println("4. Remove Journey");  
        System.out.println("5. Reset Day");  
        System.out.println("6. Exit");  
    
    }  
    public static int getUserChoice() {  
    
        System.out.print("Enter Choice: ");  
        int choice = input.nextInt();  
        input.nextLine();  
    
        return choice;  
    }  

}

## Validation

public class Validation{  
    public Validation() {  
    }  
    // Validate Zone  
    public boolean validateZone(int zone) {  

        return zone >= CityRideDataset.MIN_ZONE  
                && zone <= CityRideDataset.MAX_ZONE;  
    
    }  
    
    // Validate Passenger Type  
    // (Bro Code, 2024)    public boolean validatePassengerType(  
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
    // (Bro Code, 2024)    public boolean validateTimeBand(  
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



## Fare Calculator

import java.math.BigDecimal;  
import java.math.RoundingMode;  

public class FareCalculator {  

    // Constructor  
    public FareCalculator() {  
    }  
    
    // Calculate Base Fare  
    // (In28Minutes, 2018)    public BigDecimal calculateBaseFare(int fromZone, int toZone, CityRideDataset.TimeBand timeBand) {  
    
        return CityRideDataset.getBaseFare(fromZone, toZone, timeBand);  
    
    }  
    
    // Apply Discount  
    // (In28Minutes, 2018)    public BigDecimal applyDiscount(BigDecimal baseFare, CityRideDataset.PassengerType passengerType) {  
    
        BigDecimal discountRate = CityRideDataset.DISCOUNT_RATE.get(passengerType);  
        BigDecimal discount = baseFare.multiply(discountRate);  
        BigDecimal finalFare = baseFare.subtract(discount);  
    
        // (Oracle, 2026)  
        return finalFare.setScale(2, RoundingMode.HALF_UP  
        );  
    }  

}

## Journey Manager

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
    
    // Constructor  
    public JourneyManager(Scanner input) {  
    
        journeys = new ArrayList<>();  
        validation = new Validation();  
        this.input = input;  
        calculator = new FareCalculator();  
        summaryManager = new SummaryManager();  
        nextJourneyID = 1;  
    
    }  
    
    public void addJourney() {  
        System.out.println("\nAdd Journey");  
    
        // Date  
        System.out.print("Enter Date: ");  
        String date = input.nextLine();  
    
        // Zones  
        System.out.print("Enter From Zone: ");  
        int fromZone = input.nextInt();  
    
        System.out.print("Enter To Zone: ");  
        int toZone = input.nextInt();  
    
        // Validate Zones  
        if (!validation.validateZone(fromZone) || !validation.validateZone(toZone)) {  
    
            System.out.println("Invalid Zone");  
    
            return;  
        }  
    
        input.nextLine();  
    
        // Passenger Type  
        System.out.print(  
                "Passenger Type (Adult/Student/Child/Senior_Citezen): "  
        );  
    
        String passengerInput =  
                input.nextLine().toUpperCase();  
    
        // Validate Passenger Type  
        if (!validation.validatePassengerType(passengerInput)) {  
    
            System.out.println("Invalid Passenger Type");  
    
            return;  
        }  
        // Time Band  
        System.out.print(  
                "Time Band (PEAK/OFF_PEAK): "  
        );  
    
        String bandInput =  
                input.nextLine().toUpperCase();  
    
        // Validate Time Band  
        if (!validation.validateTimeBand(bandInput)) {  
    
            System.out.println("Invalid Time Band");  
    
            return;  
        }  
    
        // Convert To Enums  
        CityRideDataset.PassengerType passengerType =  
                CityRideDataset.PassengerType  
                        .valueOf(passengerInput);  
    
        CityRideDataset.TimeBand timeBand =  
                CityRideDataset.TimeBand  
                        .valueOf(bandInput);  
    
        // Fare  
        BigDecimal baseFare = calculator.calculateBaseFare(fromZone, toZone, timeBand);  
        BigDecimal finalFare = calculator.applyDiscount(baseFare, passengerType);  
    
        // Create Journey  
        Journey journey = new Journey(nextJourneyID, date, fromZone, toZone,  
                passengerType, timeBand, baseFare, finalFare);  
        // Store Journey  
        journeys.add(journey);  
    
        nextJourneyID++;  
    
        System.out.println(  
                "\nJourney Added Successfully"  
        );  
    }  
    
    public void listJourneys() {  
        if (journeys.isEmpty()) {  
    
            System.out.println(  
                    "\nNo Journeys Stored"  
            );  
    
            return;  
    
        }  
    
        for (Journey journey : journeys) {  
    
            journey.displayJourney();  
    
        }  
    
    }  
    
    public void filterJourneys() {  
        input.nextLine();  
        System.out.print(  
                "\nEnter Passenger Type To Filter: "  
        );  
    
        String filter = input.nextLine().toUpperCase();  
    
        boolean found = false;  
    
        for (Journey journey : journeys) {  
    
            // (Alexandra Obregon, 2024)  
            if (journey.getPassengerType().name().equals(filter)) {  
                journey.displayJourney();  
    
                found = true;  
            }  
    
        }  
        if (!found) {  
    
            System.out.println(  
                    "\nNo Matching Journeys Found"  
            );  
    
        }  
    }  
    
    public void removeJourney() {  
        System.out.print(  
                "\nEnter Journey ID To Remove: "  
        );  
    
        int id = input.nextInt();  
    
        boolean removed = false;  
    
        for (int i = 0; i < journeys.size(); i++) {  
    
            if (  
                    journeys.get(i)  
                            .getJourneyID() == id  
            ) {  
    
                journeys.remove(i);  
    
                removed = true;  
    
                System.out.println(  
                        "\nJourney Removed"  
                );  
    
                break;  
    
            }  
    
        }  
    
        if (!removed) {  
    
            System.out.println(  
                    "\nJourney ID Not Found"  
            );  
    
        }  
    }  
    
    public void resetJourneys() {  
        journeys.clear();  
    
        System.out.println(  
                "\nAll Journeys Reset"  
        );  
    
    }  
    
    public void viewDailySummary() {  
        // Not implemented yet  
    }  
    
    public void viewJourneysMenu() {  
    
        int choice;  
    
        do {  
    
            System.out.println(  
                    "\nView Journeys"  
            );  
    
            System.out.println(  
                    "1. View All Journeys"  
            );  
    
            System.out.println(  
                    "2. Filter Journeys"  
            );  
    
            System.out.println(  
                    "3. Passenger Totals"  
            );  
    
            System.out.println(  
                    "4. Return"  
            );  
    
            System.out.print(  
                    "Enter Choice: "  
            );  
    
            choice = input.nextInt();  
    
    
            // (Bro Code, 2024)  
    
            switch (choice) {  
    
                case 1:  
                    listJourneys();  
                    break;  
    
                case 2:  
                    filterJourneys();  
                    break;  
    
                case 3:  
                    // Not implemented yet  
                    break;  
    
            }  
    
        } while (choice != 4);  
    
    }  

}



## Journey

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

    // Constructor  
    public Journey(int journeyID, String date, int fromZone, int toZone, CityRideDataset.PassengerType passengerType,  
                   CityRideDataset.TimeBand timeBand, BigDecimal baseFare, BigDecimal finalFare)  
    {  
    
        this.journeyID = journeyID;  
        this.date = date;  
        this.fromZone = fromZone;  
        this.toZone = toZone;  
        this.passengerType = passengerType;  
        this.timeBand = timeBand;  
        this.baseFare = baseFare;  
        this.finalFare = finalFare;  
    
    }  
    
    // Get methods  
    public int getJourneyID() {  
        return journeyID;  
    }  
    public String getDate() {  
        return date;  
    }  
    public int getFromZone() {  
        return fromZone;  
    }  
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
    
    // Methods  
    public int calculateZonesCrossed() {  
        return Math.abs(fromZone - toZone) + 1;  
    
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
    }  

}



## Summary Manager

public class SummaryManager{  
    public SummaryManager() {  
    }  
}




------------------------------------------------------------------------------------------------------------------------------

### Updated Gantt Chart

------------------------------------------------------------------------------------------------------------------------------

### ![](Milestone%204%20gantt%20chart1.png)

![](Milestone%204%20gantt%20chart%202.png)

## Diary Entries

------------------------------------------------------------------------------------------------------------------------------

Diary Entry 1 - 20/05/2026
------------------------------------------------------------------------------------------------------------------------------

Today I started the day off by updating my Gantt chart, I was told in class that I should've filled in most of the future tasks and milestones beforehand which is why i lost some marks in the earlier milestones, so today I decided to add as much to my Gantt chart as I can so I'll maximise the marks I score in that aspect, I referred to the ATI and found all the task points that made sense to note down in the gantt chart with the appropriate dates. After I finished I decided instead of continuing the code today, I'd rather fix up my github and ensure all the images are on it and would show on each mileston to ensure future marks, I was struggling on this in earlier milestones as i had no idea, I would add the images to the github and embed it with the correct path but it would never seem to work, luckily I spoke with my tutor and figured I needed to copy the link from the repository online and embed that way, and so after doing that i was finished.



## Diary Entry 2 - 21/05/2026

Today I got to work on my code, I started by creating the empty methods that i will use in my Journey manager class, then I was thinking about starting filling in the methods but I realized I needed to work on the validation part of the code first so I got to that. Now this part gave me some issues I knew that there had to be a way similar to python where they used try except blocks, but I just couldn't get the syntax down in java thus i had to refer and watch some bro code to learn how to use try and catch after that i simply just added it to the class and was finished, I ensured to cite it on the code however i will highlight it on this report and add the reference at the bottom as i didnt do that stuff in the program obviously.



## Diary Entry 3 - 22/05/2026

Today I continued my coding, I decided to start working on the first method of the journey manager, add journey, this method was pretty simple just a case of taking in the input of each variable, and validating it, midway through I stopped to run the program and check, but I ran into the issue of my menu system taking in input twice before actually running, this was due to the way I declared the scanner in my other class and because of a bug in the journey manager class, I quickly fixed that up and option 1 in the menu was partially working. After that I continued the add journey method but I first had to learn how to use Big decimal as after converting the some of the input to enums i wanted to start working on the fares, which meant I needed to work on the Fare calculator class which meant i needed to study how to use big decimals, now another thing i did recieve some critism for by my teacher was why did i make fare calculator a seperate class, it's because if i didn't the journey manager class would be even bigger than what i imagine it will be and the way I designed the classes are each class carries different responsibilities in the code as a means to keep it tidy. I was tired thus i decided to call it a day and continue work tomorrow.



## Diary Entry 4 - 23/05/2026

Today i did quite alot of research, I had to learn how to use big decimal, in order to make use of the dataset as everything was in big decimal, one short video later I learned what i needed and got to work, for this section i decided just to start by calculating base fare, and applying the discount, I decided not to add the daily cap yet as I wasn't feeling comfartable adding it so early, I also had to learn how to round in java as that was mentioned in the ATI so i did some more research on that using oracle and quickly finished fare calculator. I moved back to working on journey manager, so I continued with the first method by creating a method of calculating base fare and final fare, creating each journey and storing it and then i was finished with the first method, after that I added view list method which was pretty easy just 1 if statement checking if there are any journeys then a for loop looping through each journey. 

Then after that I go to work on filter journey method, this method wasn't the hardest either i did need to research a method on checking the list and comparing, so i learned that, implemented it and moved on, the filter can only filter based on passenger at this point, will add a menu system later on to let the user choose what to filter by. Reset journey filter was dead easy just use the method I already made earlier in journey class and i made the remove journey class using a for loop to just compare the journey ID's and compare if exists, after that i was going to work on summary but decided i would work on it before the final submission as I still havent made the summary manager class so instead I made the second menu system for after the user decides to view the list they can then further decide to filter, i chose this to try and tidy it up a bit.

 

## Diary Entry 5 - 24/05/2026

Today was the last day of this milestone, all that was left was to assemble the document, add my citations and referencing, suprisingly this took some time as I couldn't get my hands on the ntic guide to good referencing for code guidelines, luckily after a bit of searching and asking around I found it and tediously start adding all the sitation, highlighting and adding the references, I wanted to make sure I was doing everything right in order for me to maximise my marks this time around.





---

### References

- Bro Code. (2024, December 9). *Learn EXCEPTION HANDLING in 8 minutes! ⚠️*. [YouTube video]. Retrieved from: [Learn EXCEPTION HANDLING in 8 minutes! ⚠️ - YouTube](https://www.youtube.com/watch?v=u1PROb-aRUI) [Accessed 22 May 2026].
  
  
- In28Minutes (2018, March 20). *Java BigDecimal Tutorial - 1*. [YouTube video]. Retrieved from: [Java BigDecimal Tutorial - 1 - YouTube](https://www.youtube.com/watch?v=MK6LDyQuv6U) [Accessed 23 May 2026].
  
  
- Oracle. (2014). *RoundingMode (Java Platform SE 8)*. Retrieved from: [RoundingMode (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html) [Accessed 23 May 2026].
  
  
- Bro Code. (2024, December 5). *Learn Java enhanced switches in 8 minutes! 💡*. [YouTube video]. Retrieved from: [Learn Java enhanced switches in 8 minutes! 💡 - YouTube](https://www.youtube.com/watch?v=6q2JKiynteM&t=75s) [Accessed 18 May 2026].
  
  
- Obregon, A. (2024, September 1). Java's Objects.equals() Method Explained, *Medium*. [online] Retrieved from: https://medium.com/@AlexanderObregon/javas-objects-equals-method-explained-3a84c963edfa [Accessed 23 May 2026].
