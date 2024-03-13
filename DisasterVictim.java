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

import java.util.ArrayList;
import java.util.List;

public class DisasterVictim {
    // Variables
    public String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String comments;
    protected int ASSIGNED_SOCIAL_ID;
    protected static int nextSocialID = 1;
    private ArrayList<MedicalRecord> medicalRecords;
    private ArrayList<FamilyRelation> familyConnections;
    private String ENTRY_DATE;
    private ArrayList<Supply> personalBelongings;

    // Constructor
    public DisasterVictim(String firstName, String ENTRY_DATE) {
        setFirstName(firstName);

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

        this.ASSIGNED_SOCIAL_ID = nextSocialID++;

    }

    // Getters
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getComments() {
        return this.comments;
    }

    public MedicalRecord[] getMedicalRecords() {
        return this.medicalRecords.toArray(new MedicalRecord[0]);
    }

    public String getEntryDate() {
        return this.ENTRY_DATE;
    }

    public int getAssignedSocialID() {
        return this.ASSIGNED_SOCIAL_ID + 2;
    }

    public Supply[] getPersonalBelongings() {
        return this.personalBelongings.toArray(new Supply[0]);
    } // check this works

    public FamilyRelation[] getFamilyConnections() {
        return this.familyConnections.toArray(new FamilyRelation[0]);
    }

    public String getGender() {
        return this.gender;
    }

    // Setters

    public void setFirstName(String firstName) throws IllegalArgumentException {
        if (firstName instanceof String) {
            this.firstName = firstName;
            return;
        }
        throw new IllegalArgumentException("First name cannot be null");
    }

    public void setLastName(String lastName) throws IllegalArgumentException {
        if (lastName instanceof String) {
            this.lastName = lastName;
            return;
        }
        throw new IllegalArgumentException("Last name cannot be null");
    }

    public void setComments(String comments) throws IllegalArgumentException {
        if (comments instanceof String) {
            this.comments = comments;
            return;
        }
        throw new IllegalArgumentException("Comments cannot be null");

    }

    public void setGender(String gender) throws IllegalArgumentException {
        if (gender instanceof String) {
            this.gender = gender.toLowerCase();
            return;
        }
        throw new IllegalArgumentException("gender cannot be null");
    }

    // Date of Birth
    public void setDateOfBirth(String dateOfBirth) throws IllegalArgumentException {
        if (dateOfBirth.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String[] dateParts = dateOfBirth.split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);

            if (!(year >= 1920 && year <= 2050 && month >= 1 && month <= 12 && day >= 1 && day <= 31)) {
                throw new IllegalArgumentException("Invalid inquiry date: " + dateOfBirth);
            }

            this.dateOfBirth = dateOfBirth;
        } else {
            throw new IllegalArgumentException("Invalid date of birth: " + dateOfBirth);
        }

    }

    // Mecical Recods
    public void setMedicalRecords(MedicalRecord[] medicalRecords) throws IllegalArgumentException {
        if (medicalRecords == null) {
            throw new IllegalArgumentException("Medical records cannot be null");
        } else {
            this.medicalRecords = new ArrayList<>(List.of(medicalRecords));
        }
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord == null) {
            throw new IllegalArgumentException("You can't add null medical records");
        } else {
            if (this.medicalRecords == null) {
                this.medicalRecords = new ArrayList<>();
            }
            this.medicalRecords.add(medicalRecord);
        }
    }

    // Personal Belongings
    public void setPersonalBelongings(Supply[] supplies) throws IllegalArgumentException {
        if (supplies == null) {
            throw new IllegalArgumentException("Supploes records cannot be null");
        }
        this.personalBelongings = new ArrayList<>(List.of(supplies));
    }

    public void addPersonalBelonging(Supply supply) throws IllegalArgumentException {
        if (supply != null) {
            if (this.personalBelongings == null) {
                this.personalBelongings = new ArrayList<>();
            }
            this.personalBelongings.add(supply);
            return;
        }
        throw new IllegalArgumentException("You can't add null supplies");
    }

    public void removePersonalBelonging(Supply supply) throws IllegalArgumentException {
        if (this.personalBelongings.contains(supply)) {
            this.personalBelongings.remove(supply);
            return;
        }
        throw new IllegalArgumentException("Supply not found");
    }

    // Family Connections
    public void setFamilyConnections(FamilyRelation[] relations) throws IllegalArgumentException {
        if (relations == null) {
            throw new IllegalArgumentException("Family Relation records cannot be null");
        }
        this.familyConnections = new ArrayList<>(List.of(relations));
    }

    public void addFamilyConnection(FamilyRelation familyConnection) throws IllegalArgumentException {
        if (familyConnection == null) {
            throw new IllegalArgumentException("You can't add null family connections");
        } else {
            if (this.familyConnections == null) {
                this.familyConnections = new ArrayList<>();
            }
            // Check for duplicate relationship
            if (!checkDuplicate(familyConnection)) {
                this.familyConnections.add(familyConnection);
                // Check series of relationships and add extra if needed
                checkSeriesOfRelationship(familyConnection);
            } else {
                throw new IllegalArgumentException("Duplicate relationship already exists.");
            }
        }
    }

    private boolean checkDuplicate(FamilyRelation newRelation) {
        if (familyConnections != null) {
            for (FamilyRelation relation : familyConnections) {
                if (relation.checkExisting(newRelation.getPersonOne(), newRelation.getPersonTwo())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkSeriesOfRelationship(FamilyRelation newRelation) {
        if (familyConnections != null) {
            for (FamilyRelation relation : familyConnections) {
                if (relation.checkSeriesOfRelationship(newRelation.getPersonOne(), newRelation.getPersonTwo())) {
                    // If series of relationships detected, add the inverse relationship
                    DisasterVictim personOne = newRelation.getPersonOne();
                    DisasterVictim personTwo = newRelation.getPersonTwo();
                    addFamilyConnection(new FamilyRelation(personTwo, newRelation.getRelationshipTo(), personOne));
                    return;
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

    public void allocateSupply(Supply supply, Location location) {
        location.supplyTracker(supply);
    }

}