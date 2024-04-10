package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class CentralBasedReliefWorker {
    private Scanner scanner;
    private DataBaseManager dataBaseManager;

    public CentralBasedReliefWorker() {
        dataBaseManager = new DataBaseManager();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to Central Based Relief Worker Operations");

        while (true) {
            displayCentralMenu();

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    handleInquirerQueries();
                    break;
                case "2":
                    searchForVictim();
                    break;
                case "3":
                    viewInquirerQueries();
                    break;
                case "4":
                    System.out.println("Returning to the main menu.");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }


    }

    private void displayCentralMenu() {
        System.out.println("\nCentral Menu:");
        System.out.println("1. Log Inquirer Queries");
        System.out.println("2. Search for Victim");
        System.out.println("3. View Inquirer Queries");
        System.out.println("4. Return to Main Menu");
        System.out.print("Enter your choice: ");
    }

    public void handleInquirerQueries() {
        System.out.println("1. Add an inquiry");
        System.out.println("2. Edit an inquiry");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addInquiry();
                break;
            case "2":
                editInquiry();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addInquiry() {
        // Gather information to create a new ReliefService entry
        System.out.print("Enter Inquirer's First Name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter Inquirer's Last Name: ");
        String lastName = scanner.nextLine().trim();
        System.out.print("Enter Inquirer's Phone Number: ");
        String phoneNum = scanner.nextLine().trim();
        System.out.print("Enter Inquirer's Info: ");
        String info = scanner.nextLine().trim();

        // Check if Inquirer already exists in the database
        Inquirer existingInquirer = dataBaseManager.getInquirerByName(firstName, lastName);
        Inquirer newInquirer;
        if (existingInquirer == null) {
            // Inquirer does not exist, create a new Inquirer
            newInquirer = new Inquirer(firstName, lastName, phoneNum, info);
            dataBaseManager.addInquirer(newInquirer); // Add new Inquirer to database
        } else {
            // Inquirer already exists, use existing Inquirer
            newInquirer = existingInquirer;
        }

        // Gather information about the missing person
        System.out.print("Enter Missing Person's First Name: ");
        String missingFirstName = scanner.nextLine().trim();
        System.out.print("Enter Missing Person's Last Name: ");
        String missingLastName = scanner.nextLine().trim();

        DisasterVictim missingvictim = new DisasterVictim(missingFirstName, missingLastName);

        String dateOfInquiry;
        while (true) {
            System.out.print("Enter Date of Inquiry (YYYY-MM-DD): ");
            dateOfInquiry = scanner.nextLine().trim();
            if (isValidDate(dateOfInquiry)) {
                break;
            } else {
                System.out.println("Invalid date format or value. Please enter the date in YYYY-MM-DD format.");
            }
        }

        // Gather last known location information
        System.out.print("Enter Last Known Location Name: ");
        String locationName = scanner.nextLine().trim();
        System.out.print("Enter Last Known Location Address: ");
        String locationAddress = scanner.nextLine().trim();

        Location lastKnownLocation = new Location(locationName, locationAddress);

        System.out.print("Enter Information Provided: ");
        String infoProvided = scanner.nextLine().trim();

        // Create a new ReliefService entry
        ReliefService newReliefService = new ReliefService(newInquirer, missingvictim, dateOfInquiry, lastKnownLocation);
        newReliefService.addInteractionLog(infoProvided);

        // Save the ReliefService entry in the database
        dataBaseManager.addReliefServiceEntry(newReliefService);
    }

    private void createReliefService(Inquirer inquirer) {
        // Gather information to create a new ReliefService entry
        System.out.print("Enter Missing Person's First Name: ");
        String missingFirstName = scanner.nextLine().trim();
        System.out.print("Enter Missing Person's Last Name: ");
        String missingLastName = scanner.nextLine().trim();
        System.out.print("Enter Date of Inquiry (YYYY-MM-DD): ");
        String dateOfInquiry = scanner.nextLine().trim();
        System.out.print("Enter Last Known Location Name: ");
        String locationName = scanner.nextLine().trim();
        System.out.print("Enter Last Known Location Address: ");
        String locationAddress = scanner.nextLine().trim();
        System.out.print("Enter Information Provided: ");
        String infoProvided = scanner.nextLine().trim();

        // Create new objects based on user input
        DisasterVictim missingVictim = new DisasterVictim(missingFirstName, missingLastName);
        Location lastKnownLocation = new Location(locationName, locationAddress);

        // Create a new ReliefService instance
        ReliefService newReliefService = new ReliefService(inquirer, missingVictim, dateOfInquiry, lastKnownLocation);

        // Save the new ReliefService entry in the database
        dataBaseManager.addReliefServiceEntry(newReliefService);
    }
    private void editInquiry() {
        // Display all available ReliefService entries for editing
        ArrayList<ReliefService> reliefServices = dataBaseManager.getAllReliefServices();
        if (reliefServices.isEmpty()) {
            System.out.println("No inquiries found.");
            return;
        }

        System.out.println("Available ReliefService Entries:");
        for (int i = 0; i < reliefServices.size(); i++) {
            System.out.println((i + 1) + ". " + reliefServices.get(i).getLogDetails());
        }

        System.out.print("Enter the number of the inquiry to edit: ");
        int choiceIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choiceIndex < 1 || choiceIndex > reliefServices.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        ReliefService selectedReliefService = reliefServices.get(choiceIndex - 1);

        System.out.print("Enter new Information Provided: ");
        String newInfoProvided = scanner.nextLine().trim();

        // Update the interactionHistory array in the selected ReliefService object
        selectedReliefService.addInteractionLog(newInfoProvided);

        // Update the selected ReliefService entry in the database and in-memory object
        dataBaseManager.editInquiry(selectedReliefService, newInfoProvided);

        System.out.println("Information added to interaction history.");
    }

    private void searchForVictim() {
        System.out.print("Enter part of the first to search for victim: ");
        String partialName = scanner.nextLine().trim();

        ArrayList<DisasterVictim> matchingVictims = dataBaseManager.searchVictimsByName(partialName);

        if (matchingVictims.isEmpty()) {
            System.out.println("No victims found with names containing '" + partialName + "'.");
        } else {
            System.out.println("Matching Victims:");
            for (DisasterVictim victim : matchingVictims) {
                System.out.println(formatVictimInfo(victim));
            }
        }
    }

    private String formatVictimInfo(DisasterVictim victim) {
        String info;
        if (victim.getDateOfBirth() != null) {
            info = String.format("Name: %s, Entry Date: %s, Date of Birth: %s",
                    victim.getFirstName(), victim.getEntryDate(), victim.getDateOfBirth());
        } else {
            info = String.format("Name: %s, Entry Date: %s, Age: %d",
                    victim.getFirstName(), victim.getEntryDate(), victim.getApproximateAge());
        }
        return info;
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

    private void viewInquirerQueries() {
        ArrayList<ReliefService> inquiries = dataBaseManager.getAllReliefServices();

        if (inquiries.isEmpty()) {
            System.out.println("No inquiries found.");
        } else {
            System.out.println("Inquirer Queries:");
            for (ReliefService inquiry : inquiries) {
                Inquirer inquirer = inquiry.getInquirer();
                System.out.println("Inquirer: " + inquirer.getFirstName() + " " + inquirer.getLastName());
                System.out.println("Interaction History:");
                System.out.println(inquiry.showInteractionLog());
                System.out.println("---------------------------------------");
            }
        }
    }

}
