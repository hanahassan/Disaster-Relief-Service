/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class ReliefServiceTest {
    private ReliefService reliefService;
    private Inquirer inquirer;
    private DisasterVictim missingPerson; //neeeded?
    private Location lastKnownLocation;
    private String newValidDate = "2024-03-15";
    private String newInvalidDate = "2024/03/15";
    private String newExpectedInfoProvided = "Urgent situation";
    private String newExpectedLogDetails = "Inquirer: Mary, Missing Person: David Smith, Date of Inquiry: 2024-03-15, Info Provided: Urgent situation, Last Known Location: Downtown";

    @Before
    public void setUp() {
        // Initialize objects before each test
        inquirer = new Inquirer("Mary", "Smith", "9876543210", "Urgent situation");
        missingPerson = new DisasterVictim("David Smith", "2024-02-20");
        lastKnownLocation = new Location("Downtown", "123 Main St");
        reliefService = new ReliefService(inquirer, missingPerson, newValidDate, newExpectedInfoProvided,
                lastKnownLocation);
    }

    /*
     * testObjectCreation:
     * - Purpose: To verify that a "ReliefService" object is successfully created.
     * - Actual result: ReliefService object is not null.
     * - Expected Result: The test checks that the ReliefService object is not null,
     * confirming successful object creation.
     */
    @Test
    public void testObjectCreation() {
        assertNotNull("ReliefService object should not be null", reliefService);
    }

    /*
     * testGetInquirer:
     * - Purpose: To ensure the "getInquirer()" method correctly returns the
     * Inquirer.
     * - Actual result: Inquirer matches the one set in setup.
     * - Expected result: The returned Inquirer should match the one set during
     * setup.
     */
    @Test
    public void testGetInquirer() {
        assertEquals("Inquirer should match the one set in setup", inquirer, reliefService.getInquirer());
    }

    /*
     * testGetMissingPerson:
     * - Purpose: To ensure the "getMissingPerson()" method correctly returns the
     * missing person.
     * - Actual result: Missing person matches the one set in setup.
     * - Expected result: The returned missing person should match the one set
     * during setup.
     */
    @Test
    public void testGetMissingPerson() {
        assertEquals("Missing person should match the one set in setup", missingPerson,
                reliefService.getMissingPerson());
    }

    /*
     * testGetDateOfInquiry:
     * - Purpose: To ensure the "getDateOfInquiry()" method correctly returns the
     * date of inquiry.
     * - Actual result: Date of inquiry matches the one set in setup.
     * - Expected result: The returned date of inquiry should match the one set
     * during setup.
     */
    @Test
    public void testGetDateOfInquiry() {
        assertEquals("Date of inquiry should match the one set in setup", newValidDate,
                reliefService.getDateOfInquiry());
    }

    /*
     * testGetInfoProvided:
     * - Purpose: To ensure the "getInfoProvided()" method correctly returns the
     * provided information.
     * - Actual result: Provided information matches the one set in setup.
     * - Expected result: The returned information should match the one set during
     * setup.
     */
    @Test
    public void testGetInfoProvided() {
        assertEquals("Info provided should match the one set in setup", newExpectedInfoProvided,
                reliefService.getInfoProvided());
    }

    /*
     * testGetLastKnownLocation:
     * - Purpose: To ensure the "getLastKnownLocation()" method correctly returns
     * the last known location.
     * - Actual result: Last known location matches the one set in setup.
     * - Expected result: The returned last known location should match the one set
     * during setup.
     */
    @Test
    public void testGetLastKnownLocation() {
        assertEquals("Last known location should match the one set in setup", lastKnownLocation,
                reliefService.getLastKnownLocation());
    }

    /*
     * testSetDateOfInquiryWithValidDate:
     * - Purpose: To ensure the "setDateOfInquiry()" method correctly updates the
     * date of inquiry with a valid date.
     * - Actual result: Date of inquiry is updated to a valid date.
     * - Expected result: The date of inquiry should be updated to the new valid
     * date.
     */
    @Test
    public void testSetDateOfInquiryWithValidDate() {
        reliefService.setDateOfInquiry(newValidDate);
        assertEquals("Setting a valid date should update the date of inquiry", newValidDate,
                reliefService.getDateOfInquiry());
    }

    /*
     * testSetDateOfInquiryWithInvalidDate:
     * - Purpose: To ensure the "setDateOfInquiry()" method throws an
     * IllegalArgumentException with an invalid date.
     * - Actual result: IllegalArgumentException is thrown.
     * - Expected result: An IllegalArgumentException should be thrown due to an
     * invalid date format.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfInquiryWithInvalidDate() {
        reliefService.setDateOfInquiry(newInvalidDate); // This should throw IllegalArgumentException due to invalid
                                                        // format
    }

    /*
     * testGetLogDetails:
     * - Purpose: To ensure the "getLogDetails()" method returns log details in the
     * expected format.
     * - Actual result: Log details match the expected format.
     * - Expected result: The returned log details should match the expected format.
     */
    @Test
    public void testGetLogDetails() {
        assertEquals("Log details should match the expected format", newExpectedLogDetails,
                reliefService.getLogDetails());
    }

    @Test
    public void testAddInteractionLog() {
        String interactionLog = "Interaction log details...";
        reliefService.addInteractionLog(interactionLog);
        assertTrue("Interaction log should be added", reliefService.showInteractionLog().contains(interactionLog));
    }

    @Test
    public void testSearchDisasterVictims() {
        // Add a DisasterVictim to the location for testing
        DisasterVictim praveen = new DisasterVictim("Praveen Kumar", "2024-03-10");
        lastKnownLocation.addOccupant(praveen);

        String searchName = "Pra"; // Part of the name
        List<DisasterVictim> searchResult = reliefService.searchDisasterVictims(searchName);
        
        assertEquals("Search should return matching DisasterVictims", 1, searchResult.size());
        assertEquals("Search result should contain the added victim", praveen, searchResult.get(0));
    }

    @Test
    public void testDetermineWorkerMode() {
        // Test for central relief worker mode
        reliefService.determineWorkerMode(true);
        // Assertion or additional logic for central relief worker mode

        // Test for location-based relief worker mode
        reliefService.determineWorkerMode(false);
        // Assertion or additional logic for location-based relief worker mode
    }
}
