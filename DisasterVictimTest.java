/** 
@author: Hana Hassan
@ucid: 30172447
@version: 1.3
@since: 1.0

DisasterVictimTest class contains unit tests for the DisasterVictim class, which represents victims of a disaster.
It verifies the functionality of various methods in the DisasterVictim class.
The DisasterVictim class encapsulates information about disaster victims including their personal details, medical records, family connections, etc.
Unit tests in this class cover object creation, setter and getter methods for various attributes, as well as methods related to family connections, personal belongings, etc.
 */

 package edu.ucalgary.oop;

 import org.junit.Before;
 import org.junit.Test;
 
 import java.util.ArrayList;
 import java.util.List;
 
 import static org.junit.Assert.*;
 
 public class DisasterVictimTest {
     private DisasterVictim victim;
     private List<Supply> suppliesToSet;
     private List<FamilyRelation> familyRelations;
     private String expectedFirstName = "Freda";
     private final String EXPECTED_ENTRY_DATE = "2024-01-18";
     private String validDate = "2024-01-15";
     private String invalidDate = "15/13/2024";
     private String expectedGender = "female";
     private String expectedComments = "Needs medical attention and speaks 2 languages";
 
     @Before
     public void setUp() {
         victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE, 30);
         suppliesToSet = new ArrayList<>();
         suppliesToSet.add(new Supply("Water Bottle", 10));
         suppliesToSet.add(new Supply("Blanket", 5));
 
         DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20", "1990-01-01");
         DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22", "1995-05-15");
     }
 
     /**
      * Test the constructor with a valid entry date.
      * Objective: To verify that a DisasterVictim object is successfully created
      * with a valid entry date.
      * Expected result: The entry date of the victim should be set correctly.
      */
     @Test
     public void testConstructorWithValidEntryDate() {
         String validEntryDate = "2024-01-18";
         DisasterVictim victim = new DisasterVictim("Freda", validEntryDate, 25);
         assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
         assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
     }
 
     /**
      * Test the constructor with an invalid entry date format.
      * Objective: To verify that the constructor throws an IllegalArgumentException
      * when provided with an invalid date format.
      * Expected result: An IllegalArgumentException should be thrown due to the
      * invalid date format.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testConstructorWithInvalidEntryDateFormat() {
         String invalidEntryDate = "18/01/2024"; // Incorrect format according to your specifications
         new DisasterVictim("Freda", invalidEntryDate, 25);
         // Expecting IllegalArgumentException due to invalid date format
     }
 
     /**
      * Test the setter and getter methods for the date of birth attribute.
      * Objective: To ensure that the setDateOfBirth method correctly updates the
      * date of birth attribute,
      * and the getDateOfBirth method correctly retrieves it.
      * Expected result: The date of birth should be updated and retrieved
      * successfully.
      */
     @Test
     public void testSetDateOfBirth() {
         String newDateOfBirth = "1987-05-21";
         victim.setDateOfBirth(newDateOfBirth);
         assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth,
                 victim.getDateOfBirth());
     }
 
     /**
      * Test setting the date of birth with an invalid format.
      * Objective: To ensure that setDateOfBirth throws an IllegalArgumentException
      * for an invalid date format.
      * Expected result: IllegalArgumentException should be thrown.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testSetDateOfBirthWithInvalidFormat() {
         victim.setDateOfBirth(invalidDate); // This format should cause an exception
     }
 
     /**
      * Test the setter and getter methods for the first name attribute.
      * Objective: To ensure that the setFirstName method correctly updates the first
      * name attribute,
      * and the getFirstName method correctly retrieves it.
      * Expected result: The first name should be updated and retrieved successfully.
      */
     @Test
     public void testSetAndGetFirstName() {
         String newFirstName = "Alice";
         victim.setFirstName(newFirstName);
         assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName,
                 victim.getFirstName());
     }
 
     /**
      * Test the setter and getter methods for the last name attribute.
      * Objective: To ensure that the setLastName method correctly updates the last
      * name attribute,
      * and the getLastName method correctly retrieves it.
      * Expected result: The last name should be updated and retrieved successfully.
      */
     @Test
     public void testSetAndGetLastName() {
         String newLastName = "Smith";
         victim.setLastName(newLastName);
         assertEquals("setLastName should update and getLastName should return the new last name", newLastName,
                 victim.getLastName());
     }
 
     /**
      * Test the getter and setter methods for the comments attribute.
      * Objective: To ensure that the setComments method correctly updates the
      * comments attribute,
      * and the getComments method correctly retrieves it.
      * Expected result: The comments should be updated and retrieved successfully.
      */
     @Test
     public void testGetComments() {
         victim.setComments(expectedComments);
         assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
     }
 
     /**
      * Test the setter and getter methods for the comments attribute.
      * Objective: To ensure that the setComments method correctly updates the
      * comments attribute,
      * and the getComments method correctly retrieves it.
      * Expected result: The comments should be updated and retrieved successfully.
      */
     @Test
     public void testSetComments() {
         victim.setComments(expectedComments);
         String newComments = "Has a minor injury on the left arm";
         victim.setComments(newComments);
         assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
     }
 
     /**
      * Test the getter method for the assigned social ID attribute.
      * Objective: To ensure that the getAssignedSocialID method returns the expected
      * social ID.
      * Expected result: The assigned social ID should be retrieved successfully.
      */
     @Test
     public void testGetAssignedSocialID() {
         // The next victim should have an ID one higher than the previous victim
         // Tests can be run in any order so two victims will be created
         DisasterVictim newVictim = new DisasterVictim("Kash", "2024-01-21", 35);
         int expectedSocialId = newVictim.getAssignedSocialID() + 1;
         DisasterVictim actualVictim = new DisasterVictim("Adeleke", "2024-01-22", 40);
 
         assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId,
                 actualVictim.getAssignedSocialID());
     }
 
     /**
      * Test the getter method for the entry date attribute.
      * Objective: To ensure that the getEntryDate method returns the expected entry
      * date.
      * Expected result: The entry date should be retrieved successfully.
      */
     @Test
     public void testGetEntryDate() {
         assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
     }
 
     /**
      * Test the setter and getter methods for the gender attribute.
      * Objective: To ensure that the setGender method correctly updates the gender
      * attribute,
      * and the getGender method correctly retrieves it.
      * Expected result: The gender should be updated and retrieved successfully.
      */
     @Test
     public void testSetAndGetGender() {
         String newGender = "girl";
         victim.setGender(newGender);
         assertEquals("setGender should update and getGender should return the new gender", newGender.toLowerCase(),
                 victim.getGender());
     }
 
     /**
      * Test the addition of a family connection.
      * Objective: To ensure that the addFamilyConnection method correctly adds a
      * family relationship.
      * Expected result: The family relationship should be added successfully.
      */
     @Test
     public void testAddFamilyConnection() {
         DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20", "1990-01-01");
         DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22", "1995-05-15");
 
         FamilyRelation relation = new FamilyRelation(victim2, "parent", victim1);
         FamilyRelation[] expectedRelations = { relation };
         victim2.setFamilyConnections(new ArrayList<>(List.of(expectedRelations)));
 
         FamilyRelation[] testFamily = victim2.getFamilyConnections().toArray(new FamilyRelation[0]);
         boolean correct = false;
 
         if ((testFamily != null) && (testFamily[0] == expectedRelations[0])) {
             correct = true;
         }
         assertTrue("addFamilyConnection should add a family relationship", correct);
     }
 
     /**
      * Test the addition of a personal belonging.
      * Objective: To ensure that the addPersonalBelonging method correctly adds a
      * personal belonging to the victim.
      * Expected result: The personal belonging should be added successfully, and
      * then removed successfully.
      */
     @Test
     public void testAddPersonalBelonging() {
         Supply supply = new Supply("Food", 5); // Create a supply
         victim.setPersonalBelongings(new ArrayList<>()); // Ensure that personalBelongings list is initialized
 
         // Add the personal belonging to the victim
         victim.addPersonalBelonging(supply, null); // No need to specify location for this test case
 
         // Remove the personal belonging
         victim.removePersonalBelonging(supply);
 
         // Assert that the personal belonging was successfully removed
         assertTrue("removePersonalBelonging should remove the supply from personal belongings",
                 victim.getPersonalBelongings().isEmpty());
     }
 
     /**
      * Test the removal of a family connection.
      * Objective: To ensure that the removeFamilyConnection method correctly removes
      * a family connection.
      * Expected result: The family connection should be removed successfully.
      */
     @Test
     public void testRemoveFamilyConnection() {
         DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20", "1990-01-01");
         DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22", "1995-05-15");
         FamilyRelation relation1 = new FamilyRelation(victim, "sibling", victim1);
         FamilyRelation relation2 = new FamilyRelation(victim, "sibling", victim2);
         FamilyRelation[] expectedRelations = { relation2 };
         FamilyRelation[] originalRelations = { relation1, relation2 };
         victim.setFamilyConnections(new ArrayList<>(List.of(originalRelations)));
 
         DisasterVictim victim = new DisasterVictim("Freda", "2024-01-23", "1980-01-01");
         victim.addFamilyConnection(relation1);
         victim.addFamilyConnection(relation2);
         victim.removeFamilyConnection(relation1);
 
         FamilyRelation[] testFamily = victim.getFamilyConnections().toArray(new FamilyRelation[0]);
         boolean correct = true;
 
         int i;
         for (i = 0; i < testFamily.length; i++) {
             if (testFamily[i] == relation1) {
                 correct = false;
             }
         }
         assertTrue("removeFamilyConnection should remove the family member", true);
     }
 
     /**
      * Test the removal of a personal belonging.
      * Objective: To ensure that the removePersonalBelonging method correctly
      * removes a personal belonging from the victim.
      * Expected result: The personal belonging should be removed successfully.
      */
     @Test
     public void testRemovePersonalBelonging() {
         Supply supplyToRemove = suppliesToSet.get(0);
         Location location = new Location("Shelter A", "123 Main St"); // Or null if location is not significant
         victim.addPersonalBelonging(supplyToRemove, location);
         victim.removePersonalBelonging(supplyToRemove);
 
         Supply[] testSupplies = victim.getPersonalBelongings().toArray(new Supply[0]);
         boolean correct = true;
 
         for (int i = 0; i < testSupplies.length; i++) {
             if (testSupplies[i] == supplyToRemove) {
                 correct = false;
             }
         }
         assertTrue("removePersonalBelonging should remove the supply from personal belongings", correct);
     }
 
     /**
      * Test setting a family connection.
      * Objective: To ensure that the setFamilyConnection method correctly sets a
      * family connection for a victim.
      * Expected result: The family connection should be set successfully.
      */
     @Test
     public void testSetFamilyConnection() {
         // Create two victim instances
         DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20", "1990-01-01");
         DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22", "1995-05-15");
 
         // Create a family relation between the two victims
         FamilyRelation relation = new FamilyRelation(victim1, "sibling", victim2);
         FamilyRelation[] expectedRelations = { relation };
         victim1.setFamilyConnections(new ArrayList<>(List.of(expectedRelations)));
 
         boolean correct = true;
 
         // Manually check if the expected and actual relations are equal
         FamilyRelation[] actualRecords = victim1.getFamilyConnections().toArray(new FamilyRelation[0]);
         if (expectedRelations.length != actualRecords.length) {
             correct = false;
         } else {
             for (int i = 0; i < actualRecords.length; i++) {
                 if (expectedRelations[i] != actualRecords[i]) {
                     correct = false;
                     break;
                 }
             }
         }
         assertTrue("Family relation should be set", correct);
     }
 
     /**
      * Test setting medical records.
      * Objective: To ensure that the setMedicalRecords method correctly sets medical
      * records for a victim.
      * Expected result: The medical records should be set successfully.
      */
     @Test
     public void testSetMedicalRecords() {
         // Create a test medical record
         Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
         MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");
         boolean correct = true;
 
         // Set the medical records for the victim
         MedicalRecord[] newRecords = { testRecord };
         victim.setMedicalRecords(new ArrayList<>(List.of(newRecords)));
         MedicalRecord[] actualRecords = victim.getMedicalRecords().toArray(new MedicalRecord[0]);
 
         // Manually check if the expected and actual records are equal
         if (newRecords.length != actualRecords.length) {
             correct = false;
         } else {
             for (int i = 0; i < newRecords.length; i++) {
                 if (actualRecords[i] != newRecords[i]) {
                     correct = false;
                     break;
                 }
             }
         }
         assertTrue("setMedicalRecords should correctly update medical records", correct);
     }
 
     /**
      * Test setting personal belongings.
      * Objective: To ensure that the setPersonalBelongings method correctly sets
      * personal belongings for a victim.
      * Expected result: The personal belongings should be set successfully.
      */
     @Test
     public void testSetPersonalBelongings() {
         // Create two test supplies
         Supply one = new Supply("Tent", 1);
         Supply two = new Supply("Jug", 3);
         Supply[] newSupplies = { one, two };
         boolean correct = true;
 
         // Set the personal belongings for the victim
         victim.setPersonalBelongings(new ArrayList<>(List.of(newSupplies)));
         Supply[] actualSupplies = victim.getPersonalBelongings().toArray(new Supply[0]);
 
         // Manually check if the expected and actual supplies are equal
         if (newSupplies.length != actualSupplies.length) {
             correct = false;
         } else {
             for (int i = 0; i < newSupplies.length; i++) {
                 if (actualSupplies[i] != newSupplies[i]) {
                     correct = false;
                     break;
                 }
             }
         }
         assertTrue("setPersonalBelongings should correctly update personal belongings", correct);
     }
 
     /**
      * Test getting the date of birth.
      * Objective: To ensure that the getDateOfBirth method returns the correct date
      * of birth for a victim.
      * Expected result: The correct date of birth should be returned.
      */
     @Test
     public void testGetDateOfBirth() {
         String expectedDateOfBirth = "1990-01-01";
         DisasterVictim victim = new DisasterVictim("Jane", "2024-01-20", expectedDateOfBirth);
         assertEquals("getDateOfBirth should return the expected date of birth", expectedDateOfBirth,
                 victim.getDateOfBirth());
     }
 
     /**
      * Test getting the approximate age.
      * Objective: To ensure that the getApproximateAge method returns the correct
      * approximate age for a victim.
      * Expected result: The correct approximate age should be returned.
      */
     @Test
     public void testGetApproximateAge() {
         int expectedAge = 30;
         assertEquals("getApproximateAge should return the expected approximate age", expectedAge,
                 victim.getApproximateAge());
     }
 
     /**
      * Test getting medical records.
      * Objective: To ensure that the getMedicalRecords method returns a non-null
      * ArrayList.
      * Expected result: The method should return a non-null ArrayList.
      */
     @Test
     public void testGetMedicalRecords() {
         assertNotNull("getMedicalRecords should return a non-null ArrayList", victim.getMedicalRecords());
     }
 
     /**
      * Test getting personal belongings.
      * Objective: To ensure that the getPersonalBelongings method returns a non-null
      * ArrayList.
      * Expected result: The method should return a non-null ArrayList.
      */
     @Test
     public void testGetPersonalBelongings() {
         assertNotNull("getPersonalBelongings should return a non-null ArrayList", victim.getPersonalBelongings());
     }
 
     /**
      * Test getting family connections.
      * Objective: To ensure that the getFamilyConnections method returns a non-null
      * ArrayList.
      * Expected result: The method should return a non-null ArrayList.
      */
     @Test
     public void testGetFamilyConnections() {
         assertNotNull("getFamilyConnections should return a non-null ArrayList", victim.getFamilyConnections());
     }
 
     /**
      * Test setting the approximate age with an invalid age.
      * Objective: To ensure that setting an invalid approximate age throws an
      * IllegalArgumentException.
      * Expected result: IllegalArgumentException should be thrown.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testSetApproximateAgeWithInvalidAge() {
         victim.setApproximateAge(-5);
     }
 
     /**
      * Test setting the approximate age with a valid age.
      * Objective: To ensure that setting a valid approximate age updates the
      * victim's age correctly.
      * Expected result: The victim's age should be updated to the valid value.
      */
     @Test
     public void testSetApproximateAgeWithValidAge() {
         int validAge = 25;
         victim.setApproximateAge(validAge);
         assertEquals("setApproximateAge should set the approximate age to the valid value", validAge,
                 victim.getApproximateAge());
     }
 
     /**
      * Test setting gender with an invalid gender.
      * Objective: To ensure that setting an invalid gender throws an
      * IllegalArgumentException.
      * Expected result: IllegalArgumentException should be thrown.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testSetGenderWithInvalidGender() {
         victim.setGender("invalid");
     }
 
     /**
      * Test adding a medical record.
      * Objective: To ensure that adding a medical record updates the victim's
      * medical records correctly.
      * Expected result: The medical record should be added to the victim's medical
      * records.
      */
     @Test
     public void testAddMedicalRecord() {
         MedicalRecord medicalRecord = new MedicalRecord(new Location("Test Location", "Test Address"), "Test diagnosis",
                 "2024-03-13");
         victim.addMedicalRecord(medicalRecord);
         assertTrue("addMedicalRecord should add a medical record to the victim",
                 victim.getMedicalRecords().contains(medicalRecord));
     }
 
     /**
      * Test adding a dietary restriction.
      * Objective: To ensure that adding a dietary restriction updates the victim's
      * dietary restrictions correctly.
      * Expected result: The dietary restriction should be added to the victim's
      * dietary restrictions.
      */
     @Test
     public void testAddDietaryRestriction() {
         DisasterVictim.Diet restriction = DisasterVictim.Diet.AVML;
         victim.addDietaryRestriction(restriction);
         assertTrue("addDietaryRestriction should add a dietary restriction",
                 victim.getDietaryRestrictions().contains(restriction));
     }
 
     /**
      * Test getting dietary restrictions.
      * Objective: To ensure that the getDietaryRestrictions method returns a
      * non-null ArrayList.
      * Expected result: The method should return a non-null ArrayList.
      */
     @Test
     public void testGetDietaryRestrictions() {
         DisasterVictim.Diet restriction = DisasterVictim.Diet.MOML;
         victim.addDietaryRestriction(restriction);
         assertNotNull("getDietaryRestrictions should return a non-null ArrayList", victim.getDietaryRestrictions());
     }
 
     /**
      * Test checking duplicate relationship with no existing relation.
      * Objective: To ensure that checkDuplicate returns false if no existing
      * relation is found.
      * Expected result: The method should return false.
      */
     @Test
     public void testCheckDuplicateWithNoExistingRelation() {
         FamilyRelation newRelation = new FamilyRelation(new DisasterVictim("Test1", "2024-03-13", "1990-01-01"),
                 "sibling", new DisasterVictim("Test2", "2024-03-13", "1995-05-15"));
         assertFalse("checkDuplicate should return false if no existing relation found",
                 victim.checkDuplicate(newRelation));
     }
 
     /**
      * Test checking a series of relationships.
      * Objective: To ensure that checkSeriesOfRelationships adds the inverse
      * relationship.
      * Expected result: The method should add the inverse relationship.
      */
     @Test
     public void testCheckSeriesOfRelationships() {
         FamilyRelation relation1 = new FamilyRelation(new DisasterVictim("Test1", "2024-03-13", "1990-01-01"),
                 "sibling", new DisasterVictim("Test2", "2024-03-13", "1995-05-15"));
         FamilyRelation relation2 = new FamilyRelation(new DisasterVictim("Test2", "2024-03-13", "1995-05-15"),
                 "sibling", new DisasterVictim("Test3", "2024-03-13", "1992-03-20"));
         victim.addFamilyConnection(relation1);
         victim.addFamilyConnection(relation2);
         assertTrue("checkSeriesOfRelationships should add inverse relationship",
                 victim.hasRelation(relation2.getPersonTwo()));
     }
 
     /**
      * Test removing a relation.
      * Objective: To ensure that removeRelation removes the relationship between two
      * victims.
      * Expected result: The relationship between two victims should be removed.
      */
     @Test
     public void testRemoveRelation() {
         DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20", "1990-01-01");
         FamilyRelation relation = new FamilyRelation(victim, "sibling", victim1);
         victim.addFamilyConnection(relation);
         victim.removeRelation(victim1);
         assertFalse("removeRelation should remove the relationship between two victims", victim.hasRelation(victim1));
     }
 
     /**
      * Test checking if a relation exists.
      * Objective: To ensure that hasRelation returns true if there is a relationship
      * between two victims.
      * Expected result: The method should return true if there is a relationship
      * between two victims.
      */
     @Test
     public void testHasRelation() {
         DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20", "1990-01-01");
         FamilyRelation relation = new FamilyRelation(victim, "sibling", victim1);
         victim.addFamilyConnection(relation);
         assertTrue("hasRelation should return true if there is a relationship between two victims",
                 victim.hasRelation(victim1));
     }
 
     /**
      * Test entering a first name.
      * Objective: To ensure that enterFirstName returns true for an existing first
      * name and false for a non-existing one.
      * Expected result: The method should return true for an existing first name and
      * false for a non-existing one.
      */
     @Test
     public void testEnterFirstName() {
         // Initialize occupants list
         List<DisasterVictim> occupants = new ArrayList<>();
 
         // Create location and victims
         Location location = new Location("Shelter A", "123 Main St");
         DisasterVictim victim1 = new DisasterVictim("John", "2024-01-20", "1990-01-01");
         DisasterVictim victim2 = new DisasterVictim("Jane", "2024-01-22", "1995-05-15");
 
         // Add victims to location occupants
         location.addOccupant(victim1);
         location.addOccupant(victim2);
 
         // Add victims to the occupants list
         occupants.add(victim1);
         occupants.add(victim2);
 
         // Test enterFirstName method
         assertTrue("enterFirstName should return true for existing first name",
                 victim1.enterFirstName("John", occupants));
         assertFalse("enterFirstName should return false for non-existing first name",
                 victim1.enterFirstName("Alice", occupants));
     }
 
     /**
      * Test entering a last name.
      * Objective: To ensure that enterLastName returns true for an existing last
      * name and false for a non-existing one.
      * Expected result: The method should return true for an existing last name and
      * false for a non-existing one.
      */
     @Test
     public void testEnterLastName() {
         // Initialize occupants list
         List<DisasterVictim> occupants = new ArrayList<>();
 
         // Create location and victims
         Location location = new Location("Shelter A", "123 Main St");
         DisasterVictim victim1 = new DisasterVictim("John", "2024-01-20", "1990-01-01");
         victim1.setLastName("Hassan");
 
         // Add victims to location occupants
         location.addOccupant(victim1);
 
         // Add victims to the occupants list
         occupants.add(victim1);
 
         // Test enterLastName method
         assertTrue("enterLastName should return true for existing last name",
                 victim1.enterLastName("Hassan", occupants));
         assertFalse("enterLastName should return false for non-existing last name",
                 victim1.enterLastName("Smith", occupants));
     }
 
 }
 