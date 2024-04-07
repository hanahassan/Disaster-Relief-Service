package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    private List<Location> locations;

    public Main() {
        locations = new ArrayList<>();
    }

    public void run() {
        initializeLocations(); // Add default locations

        System.out.println("Welcome to Disaster Relief Service");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayLocations();
            System.out.println("Enter the location number, 'add' to add a new location, or 'exit' to quit: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.equalsIgnoreCase("add")) {
                addNewLocation(scanner);
            } else {
                try {
                    int locationIndex = Integer.parseInt(input) - 1;
                    if (locationIndex >= 0 && locationIndex < locations.size()) {
                        handleLocationSelection(locations.get(locationIndex));
                    } else {
                        System.out.println("Invalid location number. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid location number, 'add', or 'exit'.");
                }
            }
        }

        System.out.println("Thank you for using Disaster Relief Service.");
    }

    private void addNewLocation(Scanner scanner) {
        System.out.println("Enter the new location details:");

        System.out.print("Location Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Location Address: ");
        String address = scanner.nextLine().trim();

        if (name.isEmpty() || address.isEmpty()) {
            System.out.println("Both location name and address are required. Please try again.");
            return;
        }

        addLocation(name, address);
        System.out.println("Location added successfully.");
    }

    private void initializeLocations() {
        addLocation("Location 1", "Address 1");
        addLocation("Location 2", "Address 2");
    }

    private void addLocation(String name, String address) {
        locations.add(new Location(name, address));
    }

    private void displayLocations() {
        System.out.println("Available Locations:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i).getName());
        }
    }

    private void handleLocationSelection(Location location) {
        System.out.println("You've selected " + location.getName());
        System.out.println("1. Add Victim");
        System.out.println("2. Search Victim");
        System.out.println("3. Log Inquirer");
        System.out.println("Enter your choice:");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                handleAddVictim(location);
                break;
            case "2":
                handleSearchVictim(location);
                break;
            case "3":
                // Handle logging an inquirer
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private void handleAddVictim(Location location) {
        System.out.println("Enter Victim Information:");

        Scanner scanner = new Scanner(System.in);

        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Entry Date (YYYY-MM-DD): ");
        String entryDate = scanner.nextLine().trim();

        String dobResult = "";
        while (true) {
            System.out.print("Would you like to enter Age or Date of Birth? (age/dob): ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("age")) {
                System.out.print("Age: ");
                String age = scanner.nextLine().trim();
                if (!age.isEmpty()) {
                    int ageYears = Integer.parseInt(age);
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    dobResult = (currentYear - ageYears) + "-01-01";
                    break;
                } else {
                    System.out.println("Please enter the age.");
                }
            } else if (choice.equalsIgnoreCase("dob")) {
                System.out.print("Date of Birth (YYYY-MM-DD): ");
                String dob = scanner.nextLine().trim();
                if (!dob.isEmpty()) {
                    dobResult = dob;
                    break;
                } else {
                    System.out.println("Please enter the date of birth.");
                }
            } else {
                System.out.println("Invalid choice. Please enter 'age' or 'dob'.");
            }
        }

        // Ask if additional fields should be added
        System.out.print("Do you want to add additional information? (yes/no): ");
        String addMoreInfo = scanner.nextLine().trim();

        DisasterVictim victim = null;
        try {
            if (dobResult.isEmpty()) {
                victim = new DisasterVictim(firstName, entryDate, Integer.parseInt(addMoreInfo));
            } else {
                victim = new DisasterVictim(firstName, entryDate, dobResult);
            }

            // Optionally, you can set additional information for the victim
            // victim.setComments(additionalInfo);

            // Check if additional information should be added
            if (addMoreInfo.equalsIgnoreCase("yes")) {
                addAdditionalInfo(victim, scanner);
            }

            // Add the victim to the location
            location.addOccupant(victim);

            System.out.println("Victim information submitted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addAdditionalInfo(DisasterVictim victim, Scanner scanner) {
        while (true) {
            System.out.println("Choose additional information to add:");
            System.out.println("1. Comments");
            System.out.println("2. Medical Records");
            System.out.println("3. Family Connection");
            System.out.println("4. Personal Belongings");
            System.out.println("5. Gender");
            System.out.println("6. Dietary Restrictions");
            System.out.println("7. Done");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter comments: ");
                    String comments = scanner.nextLine().trim();
                    victim.setComments(comments);
                    break;
                case "2":
                    // Handle adding medical records
                    System.out.print("Enter location of treatment: ");
                    String locationOfTreatment = scanner.nextLine().trim();
                    System.out.print("Enter treatment details: ");
                    String treatmentDetails = scanner.nextLine().trim();
                    System.out.print("Enter date of treatment (YYYY-MM-DD): ");
                    String dateOfTreatment = scanner.nextLine().trim();
                    try {
                        MedicalRecord medicalRecord = new MedicalRecord(new Location(locationOfTreatment), treatmentDetails, dateOfTreatment);
                        victim.addMedicalRecord(medicalRecord);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "3":
                    // Handle adding family connection
                    // Assuming you have a method to retrieve the DisasterVictim objects involved
                    System.out.print("Enter first person's first name: ");
                    String firstName1 = scanner.nextLine().trim();
                    System.out.print("Enter first person's relationship to the second person: ");
                    String relationship = scanner.nextLine().trim();
                    System.out.print("Enter second person's first name: ");
                    String firstName2 = scanner.nextLine().trim();
                    // Retrieve DisasterVictim objects based on the names (you need to implement this)
                    DisasterVictim person1 = getDisasterVictimByName(firstName1);
                    DisasterVictim person2 = getDisasterVictimByName(firstName2);
                    if (person1 != null && person2 != null) {
                        try {
                            FamilyRelation familyRelation = new FamilyRelation(person1, relationship, person2);
                            victim.addFamilyConnection(familyRelation);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Error: One or both persons not found.");
                    }
                    break;
                case "4":
                    // Handle adding personal belongings
                    // Assuming you have a method to retrieve the Location object
                    System.out.print("Enter type of belonging: ");
                    String type = scanner.nextLine().trim();
                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine().trim());
                    // Retrieve Location object (you need to implement this)
                    Location belongingsLocation = getLocationByName("LocationName");
                    if (belongingsLocation != null) {
                        Supply supply = new Supply(type, quantity);
                        victim.addPersonalBelonging(supply, belongingsLocation);
                    } else {
                        System.out.println("Error: Location not found.");
                    }
                    break;
                case "5":
                    // Handle adding gender
                    try {
                        FileManager fileManager = new FileManager();
                        List<String> genders = fileManager.readConfigurationFile();
                        System.out.println("Available genders:");
                        for (String gender : genders) {
                            System.out.println(gender);
                        }
                        System.out.print("Enter gender: ");
                        String gender = scanner.nextLine().trim().toLowerCase();
                        if (genders.contains(gender)) {
                            victim.setGender(gender);
                        } else {
                            System.out.println("Error: Invalid gender.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error: Failed to read gender options.");
                    }
                    break;
                case "6":
                    // Handle adding dietary restrictions
                    System.out.println("Available dietary restrictions:");
                    for (DisasterVictim.Diet diet : DisasterVictim.Diet.values()) {
                        System.out.println(diet.name() + " - " + getDietDescription(diet));
                    }
                    System.out.print("Enter dietary restriction: ");
                    String dietaryRestriction = scanner.nextLine().trim().toUpperCase();
                    try {
                        DisasterVictim.Diet diet = DisasterVictim.Diet.valueOf(dietaryRestriction);
                        victim.addDietaryRestriction(diet);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Invalid dietary restriction.");
                    }
                    break;
                case "7":
                    // Done adding additional information
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
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


    private void handleSearchVictim(Location location) {
        System.out.print("Enter part of first or last name: ");
        Scanner scanner = new Scanner(System.in);
        String searchQuery = scanner.nextLine().trim();

        if (searchQuery.isEmpty()) {
            System.out.println("Please enter a search query.");
            return;
        }

        List<DisasterVictim> results = location.searchVictims(searchQuery);
        displaySearchResults(results);
    }

    private void displaySearchResults(List<DisasterVictim> results) {
        System.out.println("Search Results:");
        for (DisasterVictim victim : results) {
            System.out.println("Name: " + victim.getFirstName() + " " + victim.getLastName());
            // Display other victim details as needed
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
