/** 
@author: Hana Hassan
@ucid: 30172447
@version: 1.3
@since: 1.0

The PersonTest class contains unit tests for the Person class, which represents individuals.
It verifies the functionality of various methods in the Person class.
The Person class encapsulates information about individuals, including their first name and last name.
It ensures accurate recording and retrieval of individual information.
Unit tests in this class cover setter and getter methods for first name and last name.
*/
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {

    private Person personWithFirstNameAndLastName;
    private Person personWithOnlyFirstName;
    private String expectedFirstName = "Hana";
    private String expectedLastName = "Hassan";

    // Initialize a Person object with both first name and last name before each
    // test
    @Before
    public void setUp() {
        personWithFirstNameAndLastName = new Person(expectedFirstName, expectedLastName);
    }

    // Test for setFirstName() and getFirstName() methods
    // Objective: Ensure the "setFirstName()" and "getFirstName()" methods correctly
    // update and retrieve the first name.
    // Actual result: setFirstName() updates the first name and getFirstName()
    // returns the correct value.
    // Expected result: The setFirstName() method should update the first name to
    // the new value, and getFirstName() should return the updated value.
    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Mena";
        personWithFirstNameAndLastName.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName,
                personWithFirstNameAndLastName.getFirstName());
    }

    // Test for setLastName() and getLastName() methods when using the first
    // constructor
    // Objective: Ensure the "setLastName()" and "getLastName()" methods correctly
    // update and retrieve the last name.
    // Actual result: setLastName() updates the last name and getLastName() returns
    // the correct value.
    // Expected result: The setLastName() method should update the last name to the
    // new value, and getLastName() should return the updated value.
    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Bobby";z
        personWithFirstNameAndLastName.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName, personWithFirstNameAndLastName.getLastName());
    }

    // Test for getting the first name when using the second constructor
    // Objective: Ensure the "getFirstName()" method correctly returns the first
    // name when using the second constructor.
    // Actual result: The method returns the first name set in the constructor.
    // Expected result: The method should return the first name specified in the
    // constructor, which is "Hana".
    @Test
    public void testGetFirstNameWithOnlyFirstNameConstructor() {
        personWithOnlyFirstName = new Person(expectedFirstName);
        assertEquals("getFirstName should return the first name specified in the constructor", expectedFirstName,
                personWithOnlyFirstName.getFirstName());
    }

    // Test for getting the last name when using the second constructor
    // Objective: Ensure the "getLastName()" method returns null when using the
    // second constructor.
    // Actual result: The method returns null for the last name when using the
    // second constructor.
    // Expected result: The method should return null as the last name is not
    // specified in the constructor.
    @Test
    public void testGetLastNameWithOnlyFirstNameConstructor() {
        personWithOnlyFirstName = new Person(expectedFirstName);
        assertNull("getLastName should return null when using the second constructor",
                personWithOnlyFirstName.getLastName());
    }
}
