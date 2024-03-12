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
    @Test
    public void testObjectCreation() {
        // Verify that an "Inquirer" object is successfully created
        assertNotNull(inquirer);
    }

    // Test for getFirstName() method
    @Test
    public void testGetFirstName() {
        // Ensure getFirstName() method returns the correct first name
        assertEquals("getFirstName() should return the inquirer's first name", expectedFirstName, inquirer.getFirstName());
    }

    // Test for getLastName() method
    @Test
    public void testGetLastName() {
        // Ensure getLastName() method returns the correct last name
        assertEquals("getLastName() should return the inquirer's last name", expectedLastName, inquirer.getLastName());
    }

    // Test for setFirstName() method
    @Test
    public void testSetFirstName() {
        // Set a new first name
        inquirer.setFirstName("Jane");
        // Verify that the first name has been updated
        assertEquals("setFirstName() should update the inquirer's first name", "Jane", inquirer.getFirstName());
    }

    // Test for setLastName() method
    @Test
    public void testSetLastName() {
        // Set a new last name
        inquirer.setLastName("Smith");
        // Verify that the last name has been updated
        assertEquals("setLastName() should update the inquirer's last name", "Smith", inquirer.getLastName());
    }

    // Test for getServicesPhoneNum() method
    @Test
    public void testGetServicesPhoneNum() {
        // Ensure getServicesPhoneNum() method returns the correct services phone number
        assertEquals("getServicesPhoneNum() should return the correct services phone number", expectedPhoneNumber, inquirer.getServicesPhoneNum());
    }

    // Test for getInfo() method
    @Test
    public void testGetInfo() {
        // Ensure getInfo() method returns the correct information message
        assertEquals("getInfo() should return the inquirer's message", expectedMessage, inquirer.getInfo());
    }
}
