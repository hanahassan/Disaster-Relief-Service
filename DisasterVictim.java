/*
@author: Alison Gartner, Caleb Bourbonnais, Hanna Cho, and Hana Hassan
@group number: 34
@version: 1.7
@since: 1.0

The DisasterVictim class represents an individual affected by a disaster. 
It encapsulates personal information such as name, date of birth, gender, and comments, 
while also managing unique identifiers, medical records, family connections, and personal belongings. 
The class provides robust validation for input data, ensuring accurate and reliable representation of disaster victims, 
making it a comprehensive and well-structured model for disaster management scenarios.
*/

package edu.ucalgary.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisasterVictim extends Person {
    // Variables
    private String dateOfBirth;
    private int approximateAge;
    private ArrayList<String> comments;;
    private static int counter = 0;
    private final int ASSIGNED_SOCIAL_ID;
    private ArrayList<MedicalRecord> medicalRecords;
    private ArrayList<FamilyRelation> familyConnections;
    private final String ENTRY_DATE;
    private ArrayList<Supply> personalBelongings;
    private String gender;
    private ArrayList<Diet> dietaryRestrictions;

    private String locationName;

    private boolean status = true;
    // True means not missing
    // False means missing

    public enum Diet {
        AVML,
        DBML,
        GFML,
        KSML,
        LSML,
        MOML,
        PFML,
        VGML,
        VJML
    }

    // Constructor
    public DisasterVictim(String firstName, String ENTRY_DATE, int approximateAge) throws IllegalArgumentException {
        super(firstName);
        setApproximateAge(approximateAge);

        if (ENTRY_DATE.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String[] dateParts = ENTRY_DATE.split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            if (!(year >= 1920 && year <= 2050 && month >= 1 && month <= 12 && day >= 1 && day <= 31)) {
                throw new IllegalArgumentException("Invalid entry date: " + ENTRY_DATE);
            }
            this.ENTRY_DATE = ENTRY_DATE;
        } else {
            throw new IllegalArgumentException("Invalid entry date: " + ENTRY_DATE);
        }

        this.ASSIGNED_SOCIAL_ID = counter++;
        this.medicalRecords = new ArrayList<>();
        this.familyConnections = new ArrayList<>();
        this.personalBelongings = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public DisasterVictim(String firstName, String ENTRY_DATE, String dateOfBirth) throws IllegalArgumentException {
        super(firstName);
        setDateOfBirth(dateOfBirth);

        if (ENTRY_DATE.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String[] dateParts = ENTRY_DATE.split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            if (!(year >= 1920 && year <= 2050 && month >= 1 && month <= 12 && day >= 1 && day <= 31)) {
                throw new IllegalArgumentException("Invalid entry date: " + ENTRY_DATE);
            }
            this.ENTRY_DATE = ENTRY_DATE;
        } else {
            throw new IllegalArgumentException("Invalid entry date: " + ENTRY_DATE);
        }

        this.ASSIGNED_SOCIAL_ID = counter++;
        this.medicalRecords = new ArrayList<>();
        this.familyConnections = new ArrayList<>();
        this.personalBelongings = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public DisasterVictim(String firstName, String lastName) {
        super(firstName, lastName);
        this.status = false;
        this.ENTRY_DATE = "0000-00-00";

        this.ASSIGNED_SOCIAL_ID = counter++;
        this.medicalRecords = new ArrayList<>();
        this.familyConnections = new ArrayList<>();
        this.personalBelongings = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    // Getters

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public int getApproximateAge() {
        return this.approximateAge;
    }

    public List<String> getComments() {
        return this.comments;
    }

    public ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public String getEntryDate() {
        return this.ENTRY_DATE;
    }

    public int getAssignedSocialID() {
        return this.ASSIGNED_SOCIAL_ID;
    }

    public ArrayList<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    public ArrayList<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    } 

    public String getGender() {
        return this.gender;
    }

    // Method to get dietary restrictions
    public ArrayList<Diet> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    // Setters

    public void addComments(String comments) {
        if (comments == null) {
            throw new IllegalArgumentException("Comments cannot be null.");
        }
        this.comments.add(comments);
    }

    public void setGender(String gender) throws IllegalArgumentException {
        FileManager fileManager = new FileManager();
        List<String> genders = fileManager.readConfigurationFile();

        if (genders.isEmpty()) {
            System.out.println("Error: No gender options found. Defaulting to 'Unknown' gender.");
            this.gender = "Unknown";
        } else {
            if (genders.contains(gender.toLowerCase())) {
                this.gender = gender;
            } else {
                throw new IllegalArgumentException("Invalid gender: " + gender + ". Please select from the following options: " + genders);
            }
        }
    }


    public void setDateOfBirth(String dateOfBirth) throws IllegalArgumentException {
        if (dateOfBirth.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String[] dateParts = dateOfBirth.split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);

            if (!(year >= 1920 && year <= 2025 && month >= 1 && month <= 12 && day >= 1 && day <= 31)) {
                throw new IllegalArgumentException("Invalid date of birth: " + dateOfBirth);
            }

            this.dateOfBirth = dateOfBirth;
        } else {
            throw new IllegalArgumentException("Invalid date of birth: " + dateOfBirth);
        }

    }

    public void setApproximateAge(int approximateAge) throws IllegalArgumentException {
        if (approximateAge < 0 || approximateAge > 120) {
            throw new IllegalArgumentException("Approximate age must be between 0 and 120.");
        }
        this.approximateAge = approximateAge;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord == null) {
            System.out.println("You can't add null medical records");
        } else {
            if (this.medicalRecords == null) {
                this.medicalRecords = new ArrayList<>();
            }
            this.medicalRecords.add(medicalRecord);
        }
    }

    // Personal Belongings
    public void addPersonalBelonging(Supply supply, Location location, int quantity) {
        if (supply != null) {
            if (this.personalBelongings == null) {
                this.personalBelongings = new ArrayList<>();
            }
            this.personalBelongings.add(supply);
            if (location != null) {
                location.supplyTracker(supply, quantity);
            } else {
                System.out.println("Location is null. Cannot addFamilyConnectiontrack supply.");
            }
        } else {
            System.out.println("You can't add null supplies");
        }
    }
    

    public void removePersonalBelonging(Supply supply) {
        if (this.personalBelongings.contains(supply)) {
            this.personalBelongings.remove(supply);
            return;
        }
        System.out.println("Supply not found");
    }

    // Family Connections
    public void setFamilyConnections(ArrayList<FamilyRelation> relations) {
        if (relations == null) {
            System.out.println("Family Relation records cannot be null");
        }
        this.familyConnections = new ArrayList<>(relations);
    }

    public void setLocationName(String locationName) {
        if (locationName == null) {
            System.out.println("Location name cannot be null");
        }
        this.locationName = locationName;
    }

    public void addFamilyConnection(FamilyRelation familyConnection) {
        if (familyConnection == null) {
            System.out.println("You can't add null family connections");
        } else {
            // Check for duplicate relationship
            if (!checkDuplicate(familyConnection)) {
                this.familyConnections.add(familyConnection);
                // Check series of relationships and add extra if needed
                bidirectionalRelationships(familyConnection.getPersonTwo(), familyConnection.getRelationshipTo(), familyConnection.getPersonOne());
                handleReciprocalRelationships(familyConnection);
                System.out.println("Family connection added!\n");
            } else {
                System.out.println("Duplicate relationship already exists.\n");
            }
        }
    }

    private void handleReciprocalRelationships(FamilyRelation newRelation) {
        DisasterVictim personOne = newRelation.getPersonOne();
        DisasterVictim personTwo = newRelation.getPersonTwo();
        String relationshipType = newRelation.getRelationshipTo();

        // Handle reciprocal relationships based on the relationship type
        switch (relationshipType) {
            case "Sibling":
                // Make all siblings of personOne (excluding personTwo) siblings of personTwo
                for (FamilyRelation relation : personOne.getFamilyConnections()) {
                    if (relation.getRelationshipTo().equals("Sibling") && !relation.getPersonTwo().equals(personTwo)) {
                        FamilyRelation reciprocalRelation = new FamilyRelation(personTwo, "Other", relation.getPersonTwo());
                        personTwo.addFamilyConnection(reciprocalRelation);
                    }
                }
                // Make all siblings of personTwo (excluding personOne) siblings of personOne
                for (FamilyRelation relation : personTwo.getFamilyConnections()) {
                    if (relation.getRelationshipTo().equals("Sibling") && !relation.getPersonTwo().equals(personOne)) {
                        FamilyRelation reciprocalRelation = new FamilyRelation(personOne, "Other", relation.getPersonTwo());
                        personOne.addFamilyConnection(reciprocalRelation);
                    }
                }
                break;
            case "Parent/Child":
                // Make all parent/child relationships of personOne (excluding personTwo) relationships with personTwo
                for (FamilyRelation relation : personOne.getFamilyConnections()) {
                    if (relation.getRelationshipTo().equals("Parent/Child") && !relation.getPersonTwo().equals(personTwo)) {
                        FamilyRelation reciprocalRelation = new FamilyRelation(personTwo, "Other", relation.getPersonTwo());
                        personTwo.addFamilyConnection(reciprocalRelation);
                    }
                }
                // Make all parent/child relationships of personTwo (excluding personOne) relationships with personOne
                for (FamilyRelation relation : personTwo.getFamilyConnections()) {
                    if (relation.getRelationshipTo().equals("Parent/Child") && !relation.getPersonTwo().equals(personOne)) {
                        FamilyRelation reciprocalRelation = new FamilyRelation(personOne, "Other", relation.getPersonTwo());
                        personOne.addFamilyConnection(reciprocalRelation);
                    }
                }
                break;
        }
    }

    public boolean checkDuplicate(FamilyRelation newRelation) {
        if (familyConnections != null) {
            for (FamilyRelation relation : familyConnections) {
                if (relation.checkExisting(relation.getPersonOne(), newRelation.getPersonTwo())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void bidirectionalRelationships(DisasterVictim personOne, String relationship, DisasterVictim personTwo) {
        // Check if a direct relationship already exists
        boolean exists = false;
        for (FamilyRelation relation : personOne.getFamilyConnections()) {
            if (relation.getPersonOne() == personOne && relation.getPersonTwo() == personTwo && relation.getRelationshipTo().equals(relationship)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            // Create and add the missing bidirectional relationship
            FamilyRelation reverseRelation = new FamilyRelation(personOne, relationship, personTwo);
            personOne.getFamilyConnections().add(reverseRelation);
        }
    }
    

    public void removeFamilyConnection(FamilyRelation familyConnection) throws IllegalArgumentException {
        if (familyConnection == null) {
            throw new IllegalArgumentException("You can't remove null family connections");
        } else {
            if (this.familyConnections.contains(familyConnection)) {
                this.familyConnections.remove(familyConnection);
            } else {
                throw new IllegalArgumentException("Family connection not found");
            }
        }

    }

    public void removeRelation(DisasterVictim victim) {
        if (familyConnections != null) {
            for (int i = 0; i < familyConnections.size(); i++) {
                FamilyRelation relation = familyConnections.get(i);
                if (relation.getPersonOne() == victim || relation.getPersonTwo() == victim) {
                    familyConnections.remove(i);
                    victim.removeRelation(this); // Remove the relationship from the other victim as well
                    return;
                }
            }
        }
    }

    public boolean hasRelation(DisasterVictim victim) {
        if (familyConnections != null) {
            for (FamilyRelation relation : familyConnections) {
                DisasterVictim personOne = relation.getPersonOne();
                DisasterVictim personTwo = relation.getPersonTwo();
                // Check if personOne or personTwo matches the victim by firstName and entryDate
                if (personOne.getFirstName().equals(victim.getFirstName())
                        || personTwo.getFirstName().equals(victim.getFirstName()) ) {
                    return true; // Relationship already exists
                }
            }
        }
        return false; // No matching relationship found
    }


    public void addDietaryRestriction(Diet restriction) {
        if (dietaryRestrictions == null) {
            dietaryRestrictions = new ArrayList<>();
        }
        dietaryRestrictions.add(restriction);
    }

    public boolean enterFirstName(String name, List<DisasterVictim> occupants) {
        if (name != null && !name.isEmpty()) {
            for (DisasterVictim victim : occupants) {
                if (victim.getFirstName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean enterLastName(String name, List<DisasterVictim> occupants) {
        if (name != null && !name.isEmpty()) {
            for (DisasterVictim victim : occupants) {
                if (victim.getLastName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    

}