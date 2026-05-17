# IY4113 Milestone 3

| Assessment Details | Please Complete All Details                                      |
| ------------------ | ---------------------------------------------------------------- |
| Group              | A                                                                |
| Module Title       | Applied Software Engineering using Object Orientated Programming |
| Assessment Type    | Java Fundamentals part 1                                         |
| Module Tutor Name  | Jonathon Shore                                                   |
| Student ID Number  | P495305                                                          |
| Date of Submission | 17/05/2026                                                       |
| Word Count         | 1646                                                             |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*
- [x] *Where I have used AI, I have cited and referenced appropriately.

------------------------------------------------------------------------------------------------------------------------------

### Research (minimum of 2, at least 3)

---

Conduct research to support your coding process, including use of code examples, tutortials, documentation and AI tools (if used).
Use the structure below to capture your evidence:

------------------------------------------------------------------------------------------------------------------------------

Title of research:Learn Java Object Oriented Programming in 10 minutes! 🧱
Reference (link):[Learn Java Object Oriented Programming in 10 minutes! 🧱](https://www.youtube.com/watch?v=DYbi93vuSaU)
How does the research help with coding practise?: This video teaches me the basics on how to create and write within classes and how they can interact with the main java class.
Key coding ideas you could reuse in your program: I'll use different classes to hold attributes and methods related to the class, in practice I will segregate the program into different small functions that all work coherantly together to produce the final output.
Screenshot of research:![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-05-17-15-27-20-image.png)

------------------------------------------------------------------------------------------------------------------------------

Title of research:Learn CONSTRUCTORS in 10 minutes! 🔨
Reference (link):[Learn CONSTRUCTORS in 10 minutes! 🔨 - YouTube](https://www.youtube.com/watch?v=ZD7CB6wKg8A)
How does the research help with coding practise?: Taught me how to initialise objects to pass arguments into and set the initial variables
Key coding ideas you could reuse in your program: I will use the constructors in order to actually create objects of a certain class within a program that holds a unique attribute, for example each journey will have different zones it passes and passengers etc... I will use this to cater to that.
Screenshot of research:![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-05-17-15-29-47-image.png)

------------------------------------------------------------------------------------------------------------------------------

Title of research:Learn Java getters and setters in 10 minutes! 🔐
Reference (link):[Learn Java getters and setters in 10 minutes! 🔐](https://www.youtube.com/watch?v=OjrR_C_UPjc)

How does the research help with coding practise?: It teaches me how to protect my data and force the user to use certain (get) methods in order to access specific data or modify them (set methods).
Key coding ideas you could reuse in your program: I could possible use this to filter journeys to display to the user, I could locate matching queries and only the journeys that have the matching queries.
Screenshot of research:![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-05-17-15-37-53-image.png)

---

Title of research:Learn Java enhanced switches in 8 minutes! 💡
Reference (link):[Learn Java enhanced switches in 8 minutes! 💡](https://www.youtube.com/watch?v=6q2JKiynteM)

How does the research help with coding practise?: It shows me an alternative to using If, else statements that increase effeciency and reduce the amount of redundant code

Key coding ideas you could reuse in your program: I'll use the switches for my menu system, it will allow me to handle all possibilities without too much repeated code.
Screenshot of research:![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-05-17-15-57-53-image.png)

---

### Program Code

---

Program has no errors but doesn't functionally work as it doesnt output anything, I made the menu system and the Journey class, the rest of the classes are created but they only contain constructors, its able to hold the correct attributes and is properly linked to the dataset class for some of the data, I havent made the rest of the classes as thats still outside my scope of knowledge as of submitting this document

*Program code goes here:*
------------------------------------------------------------------------------------------------------------------------------

### Main class -

import java.util.Scanner;  

public class Main {  
    static Scanner input = new Scanner(System.in);  
    static JourneyManager manager = new JourneyManager();  

    public static void main(String[] args) {  
    
        int choice;  
        do {  
    
            displayMenu();  
            choice = getUserChoice();  
    
            switch (choice) {  
    
                case 1:  
                    // add journey  
                    break;  
    
                case 2:  
                    // list journey  
                    break;  
                case 3:  
                    // daily summary  
                    break;  
                case 4:  
                    // remove journey  
                    break;  
    
                case 5:  
                    // reset journeys  
                    break;  
    
                case 6:  
                    System.out.println("Program closed");  
                    break;  
    
                default:  
                    System.out.println("Invalid choice");  
    
            }  
    
        } while (choice != 6);  
    
    
    }  
    public static void displayMenu() {  
    
        System.out.println("\nCityRide Lite\n");  
    
        System.out.println("1. Add Journey");  
        System.out.println("2. List Journeys");  
        System.out.println("3. Daily summary");  
        System.out.println("4. Remove Journey");  
        System.out.println("5. Reset Day");  
        System.out.println("6. Exit");  
    
    }  
    public static int getUserChoice() {  
    
        System.out.print("Enter Choice: ");  
        return input.nextInt();  
    
    }  

}

### Journey class -

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

### JourneyManager class -

import java.util.ArrayList;  
import java.util.Scanner;  

public class JourneyManager{  

    private ArrayList<Journey> journeys;  
    private Scanner input;  
    private FareCalculator calculator;  
    private SummaryManager summaryManager;  
    private int nextJourneyID;  
    
    // Constructor  
    public JourneyManager() {  
    
        journeys = new ArrayList<>();  
    
        input = new Scanner(System.in);  
    
        calculator = new FareCalculator();  
    
        summaryManager = new SummaryManager();  
    
        nextJourneyID = 1;  
    
    }  

}

### Fare calculator class -

public class FareCalculator{  
    public FareCalculator() {  
    }  
}

### Validation class-

public class Validation{  
    public Validation() {  
    }  
}

### Summary Manager class -

public class SummaryManager{  
    public SummaryManager() {  
    }  
}



  

### Updated Gantt Chart

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-05-17-21-37-04-image.png)

------------------------------------------------------------------------------------------------------------------------------

#### Diary Entry 1 - 14/05/2026

Today I started this milestone by reading through the template, I realised that I had to start coding this week thus I started doing research, I used bro code, a very good youtube channel I've been following for a while as they produce pretty well made content, I spent about a total of an hour or 2 watching and practicing directly in IntelliJ everything I had learnt from each video to ensure my mastery, I ended the day after watching 2 of those videos and added the research in the research portion of the milestone. 

#### Diary Entry 2 - 15/05/2026

Today I went back to the VLE and added the data set, as originally I planned on starting coding today, but very quickly I realised I was in over my head as there were alot of errors and impracticalities in my code and wasnt properly coding in OOP format as I mixed it up alot with procedural. I went back to do more research, I watched 2 more videos and again I practiced side by side with the video to properly learn what they were teaching and finally I felt I got the hang of it, after that I finished as I was quite tires since I started a bit late at night. 

#### Diary Entry 3 - 16/05/2026

Today I wanted to quickly fix my github (or try to) as I noticed my images werent showing on the md milestone files so I tried adding them to the github files but that still wasnt working I kept at it for about an hour but then I gave up as i was getting no where and I had already uploaded the images to GitHub so they can be checked directly, I also decided to do the Gantt chart today as I didnt want to start the code and leave it halfway to sleep and complete tomorrow as it breaks my flow so I decided to just update the Gantt chart and attach it to the md file.

#### Diary Entry 4 - 17/05/2026

Today I started work on the code and man it was a very long day, I spent the entire day working on it using 2 hour intervals, work for 2 hours then a break then work again etc... I started by importing the cityridedataset given and created the empty classes for each that i will use, then i started the main class which was effectively the menu system that loops until the user decides to quit, this part was a bit easy as it was very simple, after a break I worked on the Journey class which in theory shouldnt have been to hard as it a simple task of making sure I would use all the attributes of the journeys and the methods it would use. But i ran into an issue I had to use some data from the dataset so I spent more time than I'd like to admit to try to figure out as to how to use it, after I figured it out I made that class and made the rest of the constructors, even though the program doesn't functionally work I felt I made good progress and submitted.

------------------------------------------------------------------------------------------------------------------------------
