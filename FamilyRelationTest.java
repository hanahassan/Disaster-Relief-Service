/**
 @author: Hana Hassan
 @ucid: 30172447
 @version: 1.2
 @since: 1.0
 
 The FamilyRelationTest class contains unit tests for the FamilyRelation class, which represents relationships between disaster victims.
 It verifies the functionality of various methods in the FamilyRelation class.
 The FamilyRelation class encapsulates information about relationships between disaster victims, including the individuals involved and their relationship.
 It ensures accurate recording and retrieval of information about relationships between disaster victims.
 Unit tests in this class cover object creation, getter and setter methods for individuals involved and their relationship.
 **/
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {

    // Define test objects and values
    private DisasterVictim personOne = new DisasterVictim("John Dalan", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane Dalan", "2024-02-20");
    private String relationshipTo = "sibling";
    private FamilyRelation testFamilyRelationObject = new FamilyRelation(personOne, relationshipTo, personTwo);
    
    // Test Object Creation
    // Objective: Verify that a "FamilyRelation" object is successfully created.
    // Actual result: FamilyRelation object is not null.
    // Expected result: The test checks that the FamilyRelation object is not null,
    // confirming successful object creation.
    @Test
    public void testObjectCreation() {
        assertNotNull(testFamilyRelationObject);
    }
    
    // Test for setPersonOne() and getPersonOne() methods
    // Objective: Ensure the "setPersonOne()" and "getPersonOne()" methods correctly update and retrieve personOne.
    // Actual result: setPersonOne() updates personOne and getPersonOne() returns the correct value.
    // Expected result: The setPersonOne() method should update personOne to the new value, and getPersonOne() should return the updated value.
    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        testFamilyRelationObject.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne", newPersonOne, testFamilyRelationObject.getPersonOne());
    }

    // Test for setPersonTwo() and getPersonTwo() methods
    // Objective: Ensure the "setPersonTwo()" and "getPersonTwo()" methods correctly update and retrieve personTwo.
    // Actual result: setPersonTwo() updates personTwo and getPersonTwo() returns the correct value.
    // Expected result: The setPersonTwo() method should update personTwo to the new value, and getPersonTwo() should return the updated value.
    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        testFamilyRelationObject.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo", newPersonTwo, testFamilyRelationObject.getPersonTwo());
    }

    // Test for setRelationshipTo() and getRelationshipTo() methods
    // Objective: Ensure the "setRelationshipTo()" and "getRelationshipTo()" methods correctly update and retrieve the relationship.
    // Actual result: setRelationshipTo() updates the relationship and getRelationshipTo() returns the correct value.
    // Expected result: The setRelationshipTo() method should update the relationship to the new value, and getRelationshipTo() should return the updated value.
    @Test
    public void testSetAndGetRelationshipTo() {
        String newRelationship = "parent";
        testFamilyRelationObject.setRelationshipTo(newRelationship);
        assertEquals("setRelationshipTo should update the relationship", newRelationship, testFamilyRelationObject.getRelationshipTo());
    }

    // Test for constructor with duplicate relationship
    // Objective: Ensure that the FamilyRelation constructor throws an IllegalArgumentException when attempting to create a relationship with duplicate values.
    // Actual result: IllegalArgumentException is thrown when creating a FamilyRelation with duplicate relationship.
    // Expected result: The test checks that an IllegalArgumentException is thrown when attempting to create a FamilyRelation with duplicate relationship values.
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDuplicateRelationship() {
        // Attempt to create a FamilyRelation with duplicate relationship
        new FamilyRelation(personOne, relationshipTo, personTwo);
    }

    // Test for deleteRelationship() method
    // Objective: Ensure the "deleteRelationship()" method removes the specified relationship.
    // Actual result: The specified relationship is successfully removed.
    // Expected result: The deleteRelationship() method should remove the specified relationship between two persons.
    @Test
    public void testDeleteRelationship() {
        DisasterVictim personThree = new DisasterVictim("Alice", "2024-05-23");
        FamilyRelation relation = new FamilyRelation(personOne, "parent", personThree);
        relation.deleteRelationship(personOne, personThree);
        assertFalse("Delete relationship should remove the relationship", personOne.hasRelation(personThree));
    }

    // Test for checkExisting() method
    // Objective: Ensure the "checkExisting()" method correctly checks for an existing relationship.
    // Actual result: The checkExisting() method returns true for an existing relationship.
    // Expected result: The checkExisting() method should return true when there is an existing relationship between two persons.
    @Test
    public void testCheckExisting() {
        DisasterVictim personThree = new DisasterVictim("Alice", "2024-05-23");
        FamilyRelation relation = new FamilyRelation(personOne, "parent", personThree);
        assertTrue("Check existing should return true for existing relationship", relation.checkExisting(personOne, personThree));
    }

    // Test for checkSeriesOfRelationship() method
    // Objective: Ensure the "checkSeriesOfRelationship()" method correctly checks for a series of relationships.
    // Actual result: The checkSeriesOfRelationship() method returns false for a different relationship.
    // Expected result: The checkSeriesOfRelationship() method should return false when the relationship between two persons is different from the specified series.
    @Test
    public void testCheckSeriesOfRelationship() {
        DisasterVictim personThree = new DisasterVictim("Alice", "2024-05-23");
        FamilyRelation relation = new FamilyRelation(personOne, "parent", personTwo);
        assertFalse("Check series of relationship should return false for different relationship", relation.checkSeriesOfRelationship(personOne, personThree));
    }
}
