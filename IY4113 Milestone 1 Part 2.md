# IY4113 Milestone 1 Part 2

| Assessment Details | Please Complete All Details                                      |
| ------------------ | ---------------------------------------------------------------- |
| Group              | A                                                                |
| Module Title       | Applied Software Engineering using Object Orientated Programming |
| Assessment Type    | Java fundamentals part 2                                         |
| Module Tutor Name  | Johnathon Shore                                                  |
| Student ID Number  | P495305                                                          |
| Date of Submission | 14/06/2026                                                       |
| Word Count         | 2105                                                             |
| GitHub Link        | https://github.com/wisamhaideris-stack/T0495305_Wisam_IYO4113    |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*
- [x] *Where I have used AI, I have cited and referenced appropriately.

------------------------------------------------------------------------------------------------------------------------------

### Purpose of the Program

------------------------------------------------------------------------------------------------------------------------------The aim of CityRide Lite Part 2 is to develop a transport fare management system for the use of passengers and the administrator. The system will allow the passenger to create his profile, record his trips, calculate fares, discount and cap them accordingly and produce trip reports. On the other hand, the administrator will have the capability of managing the configuration of the fares including base fares, discount, daily cap and peak travel hours. In contrast to the first part where the data existed during runtime, this part incorporates the ability to persistently store and retrieve data from JSON and CSV files. Data that will be stored includes rider profiles, trips, and configurations. The system will simulate a public transport fare companion application.

## Constraints of the System

The system should:

* Be developed in Java.

* Be based on object-oriented programming concepts.

* Include inheritance.

* Save user profiles in JSON files.

* Save journeys and reports in CSV files.

* Ensure validation of user input.

* Ensure that no invalid configuration data is saved.

* Run via a menu-based system.

* Perform accurate calculation of fares using the specified dataset.

* Display appropriate error messages for invalid data entry.

## Key Functionalities

Key functionalities of the system include:

* Rider and Admin user types.

* Ability to create, load and save rider data profiles.

* Use of JSON files to store rider data profiles.

* Journey creation, editing and deletion.

* Computation of fares for various zones and time periods.

* Use of passenger discounts.

* Daily fare cap application.

* Ability to import journey data into the program via CSV files.

* Exports of journeys from the program into CSV files.

* End of day reporting and summary generation.

* Saving of reports in text and CSV formats.

* Administrator access control through password protection.

* Fares, discounts, daily fare caps, and peak periods management.

* Input validation prior to saving.

### Input Process Output Table

------------------------------------------------------------------------------------------------------------------------------

![*Add IPO table (It maybe easier to create the table in Word and paste as an image!)*](C:\Users\Wisam%20Haider\IdeaProjects\T0495305_Wisam_IYO4113\IPO%20table%20Part%202.png)
------------------------------------------------------------------------------------------------------------------------------

<style>
</style>

| <mark>Function</mark>                | <mark>Input</mark>                                                  | <mark>Process</mark>                                                                                                                | <mark>Output</mark>                                                        |
| ------------------------------------ | ------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| Start program                        | None                                                                | Creates ‘Journeys’<br> object, initialises variables and loads info from csv files                                                  | Displays welcome text                                                      |
| Display main<br> menu                | None                                                                | Displays Main menu<br> options, receives input and checks for validation                                                            | Menu displayed and<br> confirmation of selected option or error if invalid |
| Add journey                          | User choice                                                         | Call input function,<br> process journeys, store data                                                                               | Journey added<br> confirmation                                             |
| Validate input                       | Date, from zone, to<br> zone, passenger type, time band, Journey ID | Checks ranges 1-5,<br> Checks each data type that’s specific to each variable, if invalid displays<br> error message and re-prompt. | Valid input or<br> displays an error message allowing user to input again  |
| Calculate zone<br> crossed           | From zone, to zone                                                  | Apply formula                                                                                                                       | Number of zones<br> crossed                                                |
| Retrieve base<br> fare               | From zone, to zone,<br> time band                                   | Lookup fare from data<br> set                                                                                                       | Base fare value                                                            |
| Apply<br> discounts                  | Base fare, passenger<br> type                                       | Apply discounts<br> (Adult 0%, Student 25% etc.)                                                                                    | Discounted fare                                                            |
| Apply daily<br> cap                  | Discounted fare,<br> running total, passenger type                  | Check cap, adjust<br> fare if exceeding, Set to 0 if cap reached                                                                    | Final fare                                                                 |
| Generate<br> session ID              | None                                                                | Increment Unique ID                                                                                                                 | Unique session ID                                                          |
| List journeys                        | None                                                                | Retrieve all<br> journeys, format output                                                                                            | Display journeys                                                           |
| Format Journey<br> output            | Journey data                                                        | Round, display output                                                                                                               | Formatted journey<br> display                                              |
| Filter<br> journeys                  | Filter type<br> (zone/type/date/time)                               | Apply filter<br> conditions to data                                                                                                 | Filtered results                                                           |
| Search by<br> passenger type         | Passenger type                                                      | Filter by passenger<br> type                                                                                                        | Matching journeys                                                          |
| Search by time<br> band              | Off peak/peak (time<br> band)                                       | Filter by time band                                                                                                                 | Matching journeys                                                          |
| Search by zone                       | From/to zone                                                        | Filter by zone                                                                                                                      | Matching journeys                                                          |
| Search by date                       | Date                                                                | Filter by date                                                                                                                      | Matching journeys                                                          |
| Remove journey                       | Journey ID                                                          | Validate ID, Confirm,<br> Delete, Remove the cost from running total                                                                | Confirmation/error                                                         |
| Reset journeys                       | Confirmation input                                                  | Clear all data                                                                                                                      | Reset confirmation                                                         |
| Display daily<br> summary            | Journey list                                                        | Count journeys, sum<br> cost, average, find max                                                                                     | Daily summary values                                                       |
| Display totals<br> by passenger type | Journey list                                                        | Group by type,<br> Calculate totals                                                                                                 | Totals per type                                                            |
| Display<br> category counts          | Journey list                                                        | Count off peak and<br> peak, count zones                                                                                            | Category counts                                                            |
| Create rider<br> profile             | Name, passenger type,<br> default payment method                    | Validate profile<br> details and create Rider profile                                                                               | Rider profile created                                                      |
| Save rider<br> profile               | Rider profile data                                                  | Convert rider profile<br> into JSON format and write to file                                                                        | JSON profile saved                                                         |
| Load rider<br> profile               | Profile file name                                                   | Read JSON file and<br> reconstruct rider profile                                                                                    | Rider profile loaded                                                       |
| Edit journey                         | Journey ID, updated<br> values                                      | Locate journey,<br> validate updated values and save changes                                                                        | Journey updated<br> confirmation                                           |
| Import<br> journeys CSV              | CSV file name                                                       | Read CSV file,<br> validate records and create Journey objects                                                                      | Journeys imported<br> successfully                                         |
| Export<br> journeys CSV              | Journey collection                                                  | Convert journey<br> records into CSV format and write to file                                                                       | CSV file exported                                                          |
| Generate<br> report                  | Journey data and<br> summary data                                   | Compile journey<br> information and summary statistics                                                                              | Report generated                                                           |
| Save report                          | Report data                                                         | Write report data to<br> TXT or CSV file                                                                                            | Report file saved                                                          |
| Admin login                          | Username, password                                                  | Validate credentials<br> against stored admin account                                                                               | Access granted or<br> denied                                               |
| View<br> configuration               | None                                                                | Load current fare<br> configuration settings                                                                                        | Current configuration<br> displayed                                        |
| Update fare<br> configuration        | New fare values                                                     | Validate and update<br> fare configuration settings                                                                                 | Fare configuration<br> updated                                             |
| Update<br> discount rates            | New discount<br> percentages                                        | Validate discount<br> values and update configuration                                                                               | Discount rates<br> updated                                                 |
| Update daily<br> caps                | New cap values                                                      | Validate cap values<br> and update configuration                                                                                    | Daily cap settings<br> updated                                             |
| Update peak<br> windows              | New peak start time,<br> new peak end time                          | Validate times and<br> update peak travel configuration                                                                             | Peak window settings<br> updated                                           |
| Save<br> configuration               | Configuration<br> settings                                          | Write configuration<br> data to file                                                                                                | Configuration saved                                                        |
| Exit                                 | User choice                                                         | End loop, terminate<br> program                                                                                                     | Program ends                                                               |

### Algorithm Design

---

Startup menu

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-45-12-image.png)

Create rider

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-48-22-image.png)

Admin Menu 

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-50-33-image.png)

Main Menu

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-51-01-image.png)

Add Journey

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-51-22-image.png)

List Journey

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-52-56-image.png)

Remove Journey

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-53-10-image.png)

Reset Journey

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-53-29-image.png)

Display Summary

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-53-40-image.png)

Edit Journey

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-55-11-image.png)

------------------------------------------------------------------------------------------------------------------------------

### Research

---

## Research 1

**Name of program:** Train Fare Service

**Reference (link)**:[NaponTunglukmongkol/Train-Fare-Service: This respository is for project assignment of Service Oriented Programming KMITL](https://github.com/NaponTunglukmongkol/Train-Fare-Service/tree/master)

(Program isnt in english so I had to tediously translate each thing 1 by 1)

**What it does well:**

Automatically calculates fares between two stations.
Provides options for choosing different routes and selecting the one that is cheaper or faster.
Separates the process of calculating fares from the process of user interaction.

**What it does poorly:**
Mainly concerned with fare calculations and routing choices, without much regard for the management of the user's profile or account.

**Key design ideas you could reuse:**

Calculation of fares using the places travelled.
Transportation data storage separate from fare calculations.
Use of specialized classes for doing the calculations.
Provision of more than one choice in traveling journeys.

**Screenshot:**![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-13-22-52-24-image.png)

## Research 2

**Name of program:** Moovitapp

**Reference (link)**:[Your Public Transit Guide in 112+ Countries](https://moovitapp.com/)

**What it does well:**

Journey tracking and transport details provided via an easily accessible interface.
Frequent locations stored to make using the app more efficient.
All journey details shown easily, including route, duration, and means of transport.

**What it does poorly:** A lot of data can be shown at one time with the app, and this might be confusing to novice users.

**Key design ideas you could reuse:**

Easy to use menu navigation.
Preferences and user profile saved.
Organization of journey details.
Differentiation between account-related features and journey management features.

**Screenshot:**

![](D:\New%20folder\WhatsApp%20Image%202026-06-14%20at%2012.09.34%20AM.jpeg)

------------------------------------------------------------------------------------------------------------------------------

### Gantt Chart

------------------------------------------------------------------------------------------------------------------------------

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-37-42-image.png)

![](C:\Users\Wisam%20Haider\AppData\Roaming\marktext\images\2026-06-14-00-38-17-image.png)

### Diary Entries

------------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------------

#### Diary Entry 1 - 02/06/2026

Today was the first day of working on this milestone, I started off by going over the assignment brief and noting everything meaningful down one by one, after that i went over my previous final milestone document and read all the feedback and noted those points aswell. After that I would go over and solidify the mental picture on what I'm meant to achieve and produce with this submission and what to change, after that I started work on the IPO table, I had used my previous IPO table as a template as it already had most of the of the functions required, this portion wasn't too much of an issue however it was quite time consuming as I had to visualise all the possible functions as it was the first part I was doing in the document, thus it took me a few days due to plenty of time brainstorming.

#### Diary Entry 2 - 06/04/2026

Today was a daunting day, I had to update the flowcharts, I knew this portion of the submission will be very long and finicky as there were alot of moving parts that had to coherently work together, during this time I would get stuck alot as I didnt properly understand all the functional requirements requiring me to go back multiple times and re-reading the brief. I ended up adding about 4 flowchart and updating around 3 of my already existing flowcharts which all together cost me 4 days, however i was quite content with how the flowcharts turned out as i did put alot of effort into them. 

#### Diary Entry 3 - 11/04/2026

Today I did research, this was a very relaxed day as I wasn't bashing my head against a wall trying to figure something out rather I was just researching and experimenting different programs to learn what I could use and not use in my program, this process took me like 4 hours so it was finished without too much hassle, I did have to go through a plethora of programs and 1 of the programs i did end up using wasn't even english however I liked it so much I decided to add it anyway since despite the language barrier I did understand the program, and I had the assistance of google translate which made my life easier. 

#### Diary Entry 4 - 13/04/2026

Today will be my final diary entry, I started off my drafting the gantt chart,Ii decided to use the same template from the prior sibmission since it worked well and did its job, thus it was just a matter of reading the brief and scheduling a realistic workflow that will allow me to get all my work done on time. After I had made the Gantt chart I had 1 task left which was to assemble the document, I left this for the next day as it was quite late that day after finishing the gantt chart. During the assembly of the md document i did notice some issues in the flowcharts specifically the shapes, which I promptly fixed, this is the advantage of doing the assembly last and properly so i can go over everything i did during the week/2 weeks with a fresh pair of eyes ready to spot any errors.  
