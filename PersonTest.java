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

    private Person person;
    private String expectedFirstName = "Hana";
    private String expectedLastName = "Hassan";

    // Initialize a Person object with predefined first name and last name before each test
    @Before
    public void setUp() {
        person = new Person(expectedFirstName, expectedLastName);
    }

    // Test for setFirstName() and getFirstName() methods
    // Objective: Ensure the "setFirstName()" and "getFirstName()" methods correctly update and retrieve the first name.
    // Actual result: setFirstName() updates the first name and getFirstName() returns the correct value.
    // Expected result: The setFirstName() method should update the first name to the new value, and getFirstName() should return the updated value.
    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Mena";
        person.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName, person.getFirstName());
    }

    // Test for setLastName() and getLastName() methods
    // Objective: Ensure the "setLastName()" and "getLastName()" methods correctly update and retrieve the last name.
    // Actual result: setLastName() updates the last name and getLastName() returns the correct value.
    // Expected result: The setLastName() method should update the last name to the new value, and getLastName() should return the updated value.
    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Bobby";
        person.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName, person.getLastName());
    }
}
