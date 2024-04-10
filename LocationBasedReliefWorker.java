package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisasterVictimInterface {
    private List<Location> locations;
    private Scanner scanner;
    private DataBaseManager databaseManager;
    private String locationName;

    public DisasterVictimInterface() {
        locations = new ArrayList<>();
        scanner = new Scanner(System.in);
        databaseManager = new DataBaseManager();
        databaseManager.initializeLocations(locations); // Load locations from the database
    }

    public void run() {
        System.out.println("Welcome to Disaster Relief Service");

        while (true) {
            displayMainMenu();

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    handleDisasterVictimInformation();
                    break;
                case "2":
                    handleLogInquirerQueries();
                    break;
                case "3":
                    System.out.println("Thank you for using Disaster Relief Service.");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Disaster Victim Information");
        System.out.println("2. Log Inquirer Queries");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void handleDisasterVictimInformation() {
        displayLocations();

        System.out.print("Enter the location number or 'add' to add a new location: ");
        String locationInput = scanner.nextLine().trim();

        if (locationInput.equalsIgnoreCase("add")) {
            addNewLocation();
            return;
        }

        try {
            int locationIndex = Integer.parseInt(locationInput) - 1;
            if (locationIndex >= 0 && locationIndex < locations.size()) {
                Location selectedLocation = locations.get(locationIndex);
                locationName = selectedLocation.getName(); // Save the location name
                handleLocationOperations(selectedLocation);
            } else {
                System.out.println("Invalid location number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid location number.");
        }

        // Now you can use 'locationName' for further processing if needed
        if (locationName != null) {
            System.out.println("Selected location: " + locationName);
        }
    }


    private void handleLocationOperations(Location location) {
        System.out.println("\nLocation: " + location.getName());
        System.out.println("1. Add Victim");
        System.out.println("2. Edit Victim"); // To be implemented later
        System.out.print("Enter your choice: ");

        String operationChoice = scanner.nextLine().trim();

        switch (operationChoice) {
            case "1":
                handleAddVictim(location);
                break;
            case "2":
                handleEditVictim(location);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void handleEditVictim(Location location) {
        System.out.println("\nSelect a victim to edit:");
        List<DisasterVictim> occupants = location.getOccupants();
        if (occupants.isEmpty()) {
            System.out.println("No victims found in this location.");
            return;
        }

        for (int i = 0; i < occupants.size(); i++) {
            System.out.println((i + 1) + ". " + occupants.get(i).getFirstName());
        }

        System.out.print("Enter the number of the victim to edit: ");
        try {
            int victimIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (victimIndex >= 0 && victimIndex < occupants.size()) {
                DisasterVictim victim = occupants.get(victimIndex);
                addAdditionalInfoForEdit(victim, scanner);
                System.out.println("Victim information updated successfully.");
            } else {
                System.out.println("Invalid victim number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private void handleAddVictim(Location location) {
        System.out.println("Adding a new victim:");

        System.out.print("Enter Victim's First Name: ");
        String firstName = scanner.nextLine().trim();

        String entryDate = null;
        while (true) {
            System.out.print("Enter Entry Date (YYYY-MM-DD): ");
            entryDate = scanner.nextLine().trim();
            if (isValidDate(entryDate)) {
                break;
            } else {
                System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }

        Integer age = null;
        String dob = null;

        while (true) {
            System.out.print("Would you like to enter Age or Date of Birth? (age/dob): ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("age")) {
                while (true) {
                    System.out.print("Age: ");
                    String ageInput = scanner.nextLine().trim();
                    if (isValidAge(ageInput)) {
                        age = Integer.parseInt(ageInput);
                        break;
                    } else {
                        System.out.println("Invalid age. Please enter a valid age (0-100).");
                    }
                }
                break;
            } else if (choice.equalsIgnoreCase("dob")) {
                while (true) {
                    System.out.print("Date of Birth (YYYY-MM-DD): ");
                    dob = scanner.nextLine().trim();
                    if (isValidDate(dob)) {
                        break;
                    } else {
                        System.out.println("Invalid date of birth format. Please enter the date in YYYY-MM-DD format.");
                    }
                }
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'age' or 'dob'.");
            }
        }

        // Add the basic victim information to the database
        databaseManager.addDisasterVictim(firstName, entryDate, age, dob, location.getName());

        DisasterVictim newVictim;

        if (age != null) {
            newVictim = new DisasterVictim(firstName, entryDate, age);
        } else {
            newVictim = new DisasterVictim(firstName, entryDate, dob);
        }

        // Prompt user to add additional information
        addAdditionalInfo(newVictim, scanner);

        // Add the new victim to the location
        location.addOccupant(newVictim);

        System.out.println("Victim added successfully to " + location.getName());

    }

    private boolean isValidAge(String ageInput) {
        try {
            int age = Integer.parseInt(ageInput);
            return age >= 0 && age <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDate(String date) {
        if (date == null || date.isEmpty() || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }

        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        try {
            LocalDate.parse(date); // This will throw an exception if the date is invalid
            return year >= 1900 && year <= 2030 &&
                    month >= 1 && month <= 12 &&
                    day >= 1 && day <= 31; // You may need more specific date validation
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    private void addAdditionalInfo(DisasterVictim victim, Scanner scanner) {
        while (true) {
            System.out.println("Choose additional information to add:");
            System.out.println("1. Last Name");
            System.out.println("2. Comments");
            System.out.println("3. Medical Records");
            System.out.println("4. Family Connection");
            System.out.println("5. Personal Belongings");
            System.out.println("6. Gender");
            System.out.println("7. Dietary Restrictions");
            System.out.println("8. Done");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine().trim();
                    victim.setLastName(lastName);
                    break;
                case "2":
                    System.out.print("Enter comments: ");
                    String comments = scanner.nextLine().trim();
                    victim.addComments(comments);
                    break;
                case "3":
                    System.out.print("Enter treatment details: ");
                    String treatmentDetails = scanner.nextLine().trim();
                    while (true) {
                        System.out.print("Enter date of treatment (YYYY-MM-DD): ");
                        String dateOfTreatment = scanner.nextLine().trim();
                        if (isValidDate(dateOfTreatment)) {
                            MedicalRecord medicalRecord = new MedicalRecord(databaseManager.getLocationByName(locationName), treatmentDetails, dateOfTreatment);
                            victim.addMedicalRecord(medicalRecord);
                            break;
                        } else {
                            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                        }
                    }
                    break;
                case "4":
                    // Handle adding family connection
                    System.out.print("Enter person's first name who has a connection to this victim: ");
                    String firstName1 = scanner.nextLine().trim();
                    System.out.print("Enter relationship to the second person: ");
                    String relationship = scanner.nextLine().trim();
                    DisasterVictim person1 = databaseManager.getDisasterVictimByFirstName(firstName1);
                    if (person1 != null) {
                        FamilyRelation familyRelation = new FamilyRelation(person1, relationship, victim);
                        victim.addFamilyConnection(familyRelation);
                    } else {
                        System.out.println("Error: One or both persons not found.");
                    }
                    break;
                case "5":
                    // Handle adding personal belongings
                    System.out.print("Enter type of belonging: ");
                    String type = scanner.nextLine().trim();
                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine().trim());
                    Location belongingsLocation = databaseManager.getLocationByName(locationName);
                    if (belongingsLocation != null) {
                        Supply supply = new Supply(type, quantity);
                        victim.addPersonalBelonging(supply, belongingsLocation, quantity);
                    } else {
                        System.out.println("Error: Location not found.");
                    }
                    break;
                case "6":
                    // Handle adding gender
                    while (true) {
                        System.out.print("Enter gender: ");
                        String gender = scanner.nextLine().trim().toLowerCase();
                        try {
                            victim.setGender(gender); // Call the method to validate and set the gender
                            break; // Break out of the loop if the gender is valid
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                            System.out.println("Please enter a valid gender.");
                        }
                    }
                    break;
                case "7":
                    // Handle adding dietary restrictions
                    while (true) {
                        System.out.println("Available dietary restrictions:");
                        for (DisasterVictim.Diet diet : DisasterVictim.Diet.values()) {
                            System.out.println(diet.name() + " - " + getDietDescription(diet));
                        }
                        System.out.print("Enter dietary restriction: ");
                        String dietaryRestriction = scanner.nextLine().trim().toUpperCase();
                        try {
                            DisasterVictim.Diet diet = DisasterVictim.Diet.valueOf(dietaryRestriction);
                            victim.addDietaryRestriction(diet);
                            break; // Break out of the loop if the dietary restriction is valid
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: Invalid dietary restriction. Please try again.");
                        }
                    }
                    break;
                case "8":
                    // Done adding additional information
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    break;
            }
        }
    }


    private String getDietDescription(DisasterVictim.Diet diet) {
        switch (diet) {
            case AVML:
                return "Asian vegetarian meal";
            case DBML:
                return "Diabetic meal";
            case GFML:
                return "Gluten intolerant meal";
            case KSML:
                return "Kosher meal";
            case LSML:
                return "Low salt meal";
            case MOML:
                return "Muslim meal";
            case PFML:
                return "Peanut-free meal";
            case VGML:
                return "Vegan meal";
            case VJML:
                return "Vegetarian Jain meal";
            default:
                return "";
        }
    }

    private void displayLocations() {
        System.out.println("\nAvailable Locations:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i).getName());
        }
    }

    private void addNewLocation() {
        System.out.println("Adding a new location:");

        System.out.print("Enter Location Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Location Address: ");
        String address = scanner.nextLine().trim();

        databaseManager.addLocation(name, address);
        System.out.println("Location added successfully.");
        databaseManager.initializeLocations(locations); // Reload locations from the database
    }

    private void handleLogInquirerQueries() {
        System.out.println("\nLog Inquirer Queries:");
        System.out.println("Choose your mode:");
        System.out.println("1. Central-based relief worker");
        System.out.println("2. Location-based relief worker");
        System.out.print("Enter your choice: ");

        String modeChoice = scanner.nextLine().trim();

        switch (modeChoice) {
            case "1":
                System.out.println("Central-based relief worker mode selected. Feature to be implemented later.");
                break;
            case "2":
                System.out.println("Location-based relief worker mode selected. Feature to be implemented later.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void addAdditionalInfoForEdit(DisasterVictim victim, Scanner scanner) {
        while (true) {
            System.out.println("Choose information to edit:");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Age");
            System.out.println("4. Date of Birth");
            System.out.println("5. Comments");
            System.out.println("6. Medical Records");
            System.out.println("7. Family Connection");
            System.out.println("8. Personal Belongings");
            System.out.println("9. Gender");
            System.out.println("10. Dietary Restrictions");
            System.out.println("11. Done");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter new first name: ");
                    String newFirstName = scanner.nextLine().trim();
                    victim.setFirstName(newFirstName);
                    break;
                case "2":
                    System.out.print("Enter new last name: ");
                    String newLastName = scanner.nextLine().trim();
                    victim.setLastName(newLastName);
                    break;
                case "3":
                    System.out.print("Enter new age: ");
                    int newAge = Integer.parseInt(scanner.nextLine().trim());
                    victim.setApproximateAge(newAge);
                    break;
                case "4":
                    System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                    String newDob = scanner.nextLine().trim();
                    victim.setDateOfBirth(newDob);
                    break;
                case "5":
                    System.out.print("Enter new comments: ");
                    String newComments = scanner.nextLine().trim();
                    victim.addComments(newComments);
                    break;
                case "6":
                    // Handle editing medical records
                    System.out.print("Enter new treatment details: ");
                    String newTreatmentDetails = scanner.nextLine().trim();
                    while (true) {
                        System.out.print("Enter date of treatment (YYYY-MM-DD): ");
                        String newDateOfTreatment = scanner.nextLine().trim();
                        if (isValidDate(newDateOfTreatment)) {
                            MedicalRecord newMedicalRecord = new MedicalRecord(databaseManager.getLocationByName(locationName), newTreatmentDetails, newDateOfTreatment);
                            victim.addMedicalRecord(newMedicalRecord);
                            break;
                        } else {
                            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                        }
                    }
                    break;
                case "7":
                    // Handle editing family connection
                    System.out.print("Enter person's first name who has a connection to this victim: ");
                    String firstName1 = scanner.nextLine().trim();
                    System.out.print("Enter new relationship to the second person: ");
                    String newRelationship = scanner.nextLine().trim();
                    DisasterVictim person1 = databaseManager.getDisasterVictimByFirstName(firstName1);
                    if (person1 != null) {
                        FamilyRelation newFamilyRelation = new FamilyRelation(person1, newRelationship, victim);
                        victim.addFamilyConnection(newFamilyRelation);
                    } else {
                        System.out.println("Error: One or both persons not found.");
                    }
                    break;
                case "8":
                    // Handle editing personal belongings
                    System.out.print("Enter type of belonging: ");
                    String type = scanner.nextLine().trim();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = Integer.parseInt(scanner.nextLine().trim());
                    Location belongingsLocation = databaseManager.getLocationByName(locationName);
                    if (belongingsLocation != null) {
                        Supply newSupply = new Supply(type, newQuantity);
                        victim.addPersonalBelonging(newSupply, belongingsLocation, newQuantity);
                    } else {
                        System.out.println("Error: Location not found.");
                    }
                    break;
                case "9":
                    // Handle editing gender
                    System.out.print("Enter new gender: ");
                    String newGender = scanner.nextLine().trim().toLowerCase();
                    victim.setGender(newGender);
                    break;
                case "10":
                    // Handle editing dietary restrictions
                    while (true) {
                        System.out.println("Available dietary restrictions:");
                        for (DisasterVictim.Diet diet : DisasterVictim.Diet.values()) {
                            System.out.println(diet.name() + " - " + getDietDescription(diet));
                        }
                        System.out.print("Enter new dietary restriction: ");
                        String dietaryRestriction = scanner.nextLine().trim().toUpperCase();
                        try {
                            DisasterVictim.Diet newDiet = DisasterVictim.Diet.valueOf(dietaryRestriction);
                            victim.addDietaryRestriction(newDiet);
                            break; // Break out of the loop if the dietary restriction is valid
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: Invalid dietary restriction. Please try again.");
                        }
                    }
                    break;
                case "11":
                    // Done editing
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 11.");
                    break;
            }
        }
    }


    public static void main(String[] args) {
        DisasterVictimInterface victimInterface = new DisasterVictimInterface();
        victimInterface.run();
    }
}
