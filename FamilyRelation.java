/*
 * @author: Alison Gartner, Caleb Bourbonnais, Hanna Cho, and Hana Hassan
 * @group number: 34
 * @version: 1.1
 * @since: 1.0
 * 
 * The FamilyRelation class represents a relationship between two DisasterVictims.
 * It encapsulates the two DisasterVictims and the relationship between them.
 * The class provides validation for inputs, ensuring accurate representation of family relations,
 * making it a comprehensive model for disaster management scenarios.
 */
package edu.ucalgary.oop;

import java.util.Objects;

public class FamilyRelation {

    private DisasterVictim personOne;
    private String relationshipTo;
    private DisasterVictim personTwo;

    // Constructor for FamilyRelation, takes in two DisasterVictims and a
    // relationship
    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        if (checkExisting(personOne, personTwo)) {
            throw new IllegalArgumentException("Duplicate relationship already exists.");
        }
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
    }

    // Setters for FamilyRelation, takes in a DisasterVictim and a relationship
    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }

    // Getters for FamilyRelation, returns DisasterVictims and a relationship
    public DisasterVictim getPersonOne() {
        return personOne;
    }

    public String getRelationshipTo() {
        return relationshipTo;
    }

    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    // Method to delete a relationship between two DisasterVictims
    public void deleteRelationship(DisasterVictim personOne, DisasterVictim personTwo) {
        if (checkExisting(personOne, personTwo)) {
            personOne.removeRelation(personTwo);
            personTwo.removeRelation(personOne);
        }
    }

    // Method to check if a relationship between two DisasterVictims already exists
    public boolean checkExisting(DisasterVictim personOne, DisasterVictim personTwo) {
        // We should check if both persons are not null and they have relations
        return personOne != null && personTwo != null && personOne.hasRelation(personTwo)
                && personTwo.hasRelation(personOne);
    }

    // Method to check if adding a relationship would create a duplicate
    public boolean checkSeriesOfRelationship(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        return Objects.equals(this.personOne, personOne)
                && Objects.equals(this.personTwo, personTwo)
                && Objects.equals(this.relationshipTo, relationshipTo);
    }

}
