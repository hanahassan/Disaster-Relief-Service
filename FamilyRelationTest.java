/** 
@author: Hana Hassan
@ucid: 30172447
@version: 1.2
@since: 1.0

The InquirerTest class contains unit tests for the Inquirer class, which represents individuals making inquiries during disasters. 
It verifies the functionality of various methods in the Inquirer class.
The Inquirer class encapsulates information about individuals making inquiries, including their first name, last name, phone number, and message.
It ensures accurate recording and retrieval of information about the individuals making inquiries.
Unit tests in this class cover object creation, getter and setter methods for first name, last name, phone number, and message. 
**/
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {

    private DisasterVictim personOne = new DisasterVictim("John Dalan", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane Dalan", "2024-02-20");
    private String relationshipTo = "sibling";
    private FamilyRelation testFamilyRelationObject = new FamilyRelation(personOne, relationshipTo, personTwo);
    
    @Test
    public void testObjectCreation() {
        assertNotNull(testFamilyRelationObject);
    }
    
    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        testFamilyRelationObject.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne", newPersonOne, testFamilyRelationObject.getPersonOne());
    }

    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        testFamilyRelationObject.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo", newPersonTwo, testFamilyRelationObject.getPersonTwo());
    }

    @Test
    public void testSetAndGetRelationshipTo() {
        String newRelationship = "parent";
        testFamilyRelationObject.setRelationshipTo(newRelationship);
        assertEquals("setRelationshipTo should update the relationship", newRelationship, testFamilyRelationObject.getRelationshipTo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDuplicateRelationship() {
        // Attempt to create a FamilyRelation with duplicate relationship
        new FamilyRelation(personOne, relationshipTo, personTwo);
    }

    @Test
    public void testDeleteRelationship() {
        DisasterVictim personThree = new DisasterVictim("Alice", "2024-05-23");
        FamilyRelation relation = new FamilyRelation(personOne, "parent", personThree);
        relation.deleteRelationship(personOne, personThree);
        assertFalse("Delete relationship should remove the relationship", personOne.hasRelation(personThree));
    }

    @Test
    public void testCheckExisting() {
        DisasterVictim personThree = new DisasterVictim("Alice", "2024-05-23");
        FamilyRelation relation = new FamilyRelation(personOne, "parent", personThree);
        assertTrue("Check existing should return true for existing relationship", relation.checkExisting(personOne, personThree));
    }

    @Test
    public void testCheckSeriesOfRelationship() {
        DisasterVictim personThree = new DisasterVictim("Alice", "2024-05-23");
        FamilyRelation relation = new FamilyRelation(personOne, "parent", personTwo);
        assertFalse("Check series of relationship should return false for different relationship", relation.checkSeriesOfRelationship(personOne, personThree));
    }
}
