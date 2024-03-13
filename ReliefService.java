/*
@author: Alison Gartner, Caleb Bourbonnais, Hanna Cho, and Hana Hassan
@group number: 34
@version: 1.3
@since: 1.0

The ReliefService class provides functionality to manage relief efforts
for disaster victims. It facilitates the coordination between inquirers,
missing persons, and relevant information regarding inquiries and last known
locations. This class ensures organized and effective relief service operations
in response to disasters and emergencies.
*/

package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReliefService {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;
    private ArrayList<String> interactionHistory;

    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson,
            String dateOfInquiry, String infoProvided, Location lastKnownLocation) throws IllegalArgumentException {
        setInquirer(inquirer);
        setMissingPerson(missingPerson);
        setDateOfInquiry(dateOfInquiry);
        setInfoProvided(infoProvided);
        setLastKnownLocation(lastKnownLocation);
        interactionHistory = new ArrayList<>();
    }

    public void setInquirer(Inquirer inquirer) throws IllegalArgumentException {
        if (inquirer instanceof Inquirer) {
            this.inquirer = inquirer;
            return;
        }
        throw new IllegalArgumentException("Invalid inquirer entered: " + inquirer);
    }

    public void setMissingPerson(DisasterVictim missingPerson) throws IllegalArgumentException {
        if (missingPerson instanceof DisasterVictim) {
            this.missingPerson = missingPerson;
            return;
        }
        throw new IllegalArgumentException("Invalid missing person: " + missingPerson);
    }

    public void setDateOfInquiry(String dateOfInquiry) throws IllegalArgumentException {
        if (!dateOfInquiry.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid inquiry date format: " + dateOfInquiry);
        }

        String[] dateParts = dateOfInquiry.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        if (!(year >= 2000 && year <= 2050 && month >= 1 && month <= 12 && day >= 1 && day <= 31)) {
            throw new IllegalArgumentException("Invalid inquiry date: " + dateOfInquiry);
        }

        this.dateOfInquiry = dateOfInquiry;
    }

    public void setInfoProvided(String infoProvided) throws IllegalArgumentException {
        if (infoProvided instanceof String) {
            this.infoProvided = infoProvided;
            return;
        }
        throw new IllegalArgumentException("Invalid information provided: " + infoProvided);
    }

    public void setLastKnownLocation(Location lastKnownLocation) throws IllegalArgumentException {
        if (lastKnownLocation instanceof Location) {
            this.lastKnownLocation = lastKnownLocation;
            return;
        }
        throw new IllegalArgumentException("Invalid location: " + lastKnownLocation);
    }

    public Inquirer getInquirer() {
        return inquirer;
    }

    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    public String getDateOfInquiry() {
        return dateOfInquiry;
    }

    public String getInfoProvided() {
        return infoProvided;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public String getLogDetails() {
        String logDetails = "Inquirer: " + inquirer.getFirstName() + ", Missing Person: " + missingPerson.getFirstName()
                +
                ", Date of Inquiry: " + this.dateOfInquiry + ", Info Provided: " + this.infoProvided +
                ", Last Known Location: " + this.lastKnownLocation.getName();

        return logDetails;
    }

    public void addInteractionLog(String logDetails) {
        interactionHistory.add(logDetails);
    }

    public String showInteractionLog() {
        // Concatenate interaction logs into a single string
        return String.join("\n", interactionHistory);
    }

    /**
     * Search for disaster victims by a part of their name, regardless of case
     * sensitivity.
     * 
     * @param searchName The part of the name to search for.
     * @return A list of matching disaster victims.
     */
    public List<DisasterVictim> searchDisasterVictims(String searchName) {
        String searchNameLowerCase = searchName.toLowerCase();
        return lastKnownLocation.getOccupants().stream()
                .filter(victim -> victim.getFirstName().toLowerCase().contains(searchNameLowerCase))
                .collect(Collectors.toList());
    }

    // Method to determine the mode of the relief worker
    public void determineWorkerMode(boolean isCentral) {
        if (isCentral) {
            System.out.println("Entering as a central relief worker.");
            // Additional logic for central relief worker mode
        } else {
            System.out.println("Entering as a location-based relief worker.");
            // Additional logic for location-based relief worker mode
        }
    }
}
