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

import org.junit.*;
import static org.junit.Assert.*;

public class InquirerTest {

    // Define the values which will be used for tests
    private String expectedFirstName = "John";
    private String expectedLastName = "Doe";
    private String expectedPhoneNumber = "+1-987-654-3210";
    private String expectedMessage = "Looking for information";

    // Initialize Inquirer object with predefined values for testing
    private Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);

    // Test Object Creation
    // Objective: Verify that an "Inquirer" object is successfully created.
    // Actual result: The Inquirer object is not null.
    // Expected result: The test checks that the Inquirer object is not null,
    // confirming successful object creation.
    @Test
    public void testObjectCreation() {
        assertNotNull(inquirer);
    }

    // Test for getFirstName() method
    // Objective: Ensure the "getFirstName()" method correctly returns the
    // inquirer's first name.
    // Actual result: getFirstName() returns the correct first name.
    // Expected result: The returned first name should match the one set during
    // setup.
    @Test
    public void testGetFirstName() {
        assertEquals("getFirstName() should return the inquirer's first name", expectedFirstName,
                inquirer.getFirstName());
    }

    // Test for getLastName() method
    // Objective: Ensure the "getLastName()" method correctly returns the inquirer's
    // last name.
    // Actual result: getLastName() returns the correct last name.
    // Expected result: The returned last name should match the one set during
    // setup.
    @Test
    public void testGetLastName() {
        assertEquals("getLastName() should return the inquirer's last name", expectedLastName, inquirer.getLastName());
    }

    // Test for setFirstName() method
    // Objective: Ensure the "setFirstName()" method correctly updates the
    // inquirer's first name.
    // Actual result: setFirstName() updates the inquirer's first name.
    // Expected result: The setFirstName() method should update the inquirer's first
    // name to the new expected first name.
    // Test setup includes using the method getFirstName() to verify the change.
    // The new expected first name is "Jane".
    @Test
    public void testSetFirstName() {
        inquirer.setFirstName("Jane");
        assertEquals("setFirstName() should update the inquirer's first name", "Jane", inquirer.getFirstName());
    }

    // Test for setLastName() method
    // Objective: Ensure the "setLastName()" method correctly updates the inquirer's
    // last name.
    // Actual result: setLastName() updates the inquirer's last name.
    // Expected result: The setLastName() method should update the inquirer's last
    // name to the new expected last name.
    // Test setup includes using the method getLastName() to verify the change.
    // The new expected last name is "Smith".
    @Test
    public void testSetLastName() {
        inquirer.setLastName("Smith");
        assertEquals("setLastName() should update the inquirer's last name", "Smith", inquirer.getLastName());
    }

    // Test for getServicesPhoneNum() method
    // Objective: Ensure the "getServicesPhoneNum()" method correctly returns the
    // inquirer's services phone number.
    // Actual result: getServicesPhoneNum() returns the correct services phone
    // number.
    // Expected result: The returned services phone number should match the one set
    // during setup.
    @Test
    public void testGetServicesPhoneNum() {
        assertEquals("getServicesPhoneNum() should return the correct services phone number", expectedPhoneNumber,
                inquirer.getServicesPhoneNum());
    }

    // Test for getInfo() method
    // Objective: Ensure the "getInfo()" method correctly returns the inquirer's
    // information message.
    // Actual result: getInfo() returns the correct information message.
    // Expected result: The returned information message should match the one set
    // during setup.
    @Test
    public void testGetInfo() {
        assertEquals("getInfo() should return the inquirer's message", expectedMessage, inquirer.getInfo());
    }
}
