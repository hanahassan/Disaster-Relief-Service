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
    private String comments;
    private static int counter = 0;
    private final int ASSIGNED_SOCIAL_ID;
    private ArrayList<MedicalRecord> medicalRecords;
    private ArrayList<FamilyRelation> familyConnections;
    private final String ENTRY_DATE;
    private ArrayList<Supply> personalBelongings;
    private String gender;
    private ArrayList<Diet> dietaryRestrictions;

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

    }

    // Getters

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public int getApproximateAge() {
        return this.approximateAge;
    }

    public String getComments() {
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

    public void setComments(String comments) {
        if (comments instanceof String) {
            this.comments = comments;
            return;
        }
        System.out.println("Comments cannot be null");
    }

    public void setGender(String gender) throws IllegalArgumentException {
        try {
            List<String> genderOptions = new FileManager().readConfigurationFile();
            if (genderOptions.contains(gender.toLowerCase())) {
                this.gender = gender.toLowerCase();
                return;
            }
            throw new IllegalArgumentException("Gender not found in the options file.");
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading gender options file.");
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

    // Mecical Recods
    public void setMedicalRecords(ArrayList<MedicalRecord> medicalRecords) {
        if (medicalRecords == null) {
            System.out.println("Medical records cannot be null");
        } else {
            this.medicalRecords = new ArrayList<>(medicalRecords);
        }
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
    public void setPersonalBelongings(ArrayList<Supply> supplies) {
        if (supplies == null) {
            System.out.println("Supplies records cannot be null");
        }
        this.personalBelongings = new ArrayList<>(supplies);
    }

    public void addPersonalBelonging(Supply supply, Location location) {
        if (supply != null) {
            if (this.personalBelongings == null) {
                this.personalBelongings = new ArrayList<>();
            }
            this.personalBelongings.add(supply);
            if (location != null) {
                location.supplyTracker(supply);
            } else {
                System.out.println("Location is null. Cannot track supply.");
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

    public void addFamilyConnection(FamilyRelation familyConnection) throws IllegalArgumentException {
        if (familyConnection == null) {
            System.out.println("You can't add null family connections");
        } else {
            if (this.familyConnections == null) {
                this.familyConnections = new ArrayList<>();
            }
            // Check for duplicate relationship
            if (!checkDuplicate(familyConnection)) {
                this.familyConnections.add(familyConnection);
                // Check series of relationships and add extra if needed
                checkSeriesOfRelationships();
            } else {
                throw new IllegalArgumentException("Duplicate relationship already exists.");
            }
        }
    }

    public boolean checkDuplicate(FamilyRelation newRelation) {
        if (familyConnections != null) {
            for (FamilyRelation relation : familyConnections) {
                if (relation.checkExisting(newRelation.getPersonOne(), newRelation.getPersonTwo())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkSeriesOfRelationships() {
        if (familyConnections != null) {
            for (int i = 0; i < familyConnections.size(); i++) {
                FamilyRelation newRelation = familyConnections.get(i);
                for (int j = i + 1; j < familyConnections.size(); j++) {
                    FamilyRelation existingRelation = familyConnections.get(j);
                    if (existingRelation.checkSeriesOfRelationship(newRelation.getPersonOne(), newRelation.getRelationshipTo(), newRelation.getPersonTwo())) {
                        // If series of relationships detected, add the inverse relationship
                        DisasterVictim personOne = newRelation.getPersonOne();
                        DisasterVictim personTwo = existingRelation.getPersonTwo();
                        addFamilyConnection(new FamilyRelation(personOne, newRelation.getRelationshipTo(), personTwo));
                        return;
                    }
                }
            }
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

    // Method to check if a relationship exists between this instance and another
    // DisasterVictim
    public boolean hasRelation(DisasterVictim victim) {
        if (familyConnections != null) {
            for (FamilyRelation relation : familyConnections) {
                if (relation.getPersonOne() == victim || relation.getPersonTwo() == victim) {
                    return true;
                }
            }
        }
        return false;
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