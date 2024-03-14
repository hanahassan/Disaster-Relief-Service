/** 
@author: Hana Hassan
@ucid: 30172447
@version: 1.9
@since: 1.0

 The ReliefServiceTest class contains unit tests for the ReliefService class, which manages relief efforts
for disaster victims. It verifies the functionality of various methods in the ReliefService class.
The ReliefService class facilitates the coordination between inquirers, missing persons, and relevant
information regarding inquiries and last known locations. This class ensures organized and effective
relief service operations in response to disasters and emergencies.
 
**/
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class ReliefServiceTest {

    // Define test data
    private ReliefService reliefService;
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private Location lastKnownLocation;
    private String newValidDate = "2024-03-15";
    private String newInvalidDate = "2024/03/15";
    private String newExpectedInfoProvided = "Urgent situation";
    private String newExpectedLogDetails = "Inquirer: Mary, Missing Person: David Smith, Date of Inquiry: 2024-03-15, Info Provided: Urgent situation, Last Known Location: Downtown";

    @Before
    public void setUp() {
        // Initialize objects before each test
        inquirer = new Inquirer("Mary", "Smith", "9876543210", "Urgent situation");
        missingPerson = new DisasterVictim("David Smith", "2024-02-20", "2004-05-23");
        lastKnownLocation = new Location("Downtown", "123 Main St");
        reliefService = new ReliefService(inquirer, missingPerson, newValidDate, newExpectedInfoProvided,
                lastKnownLocation);
    }

    /*
     * testObjectCreation:
     * - Objective: Verify that a ReliefService object is successfully created.
     * - Actual result: ReliefService object is not null.
     * - Expected result: The ReliefService object should not be null, confirming
     * successful object creation.
     */
    @Test
    public void testObjectCreation() {
        assertNotNull("ReliefService object should not be null", reliefService);
    }

    /*
     * testGetInquirer:
     * - Objective: Ensure the "getInquirer()" method correctly returns the
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
     * - Objective: Ensure the "getMissingPerson()" method correctly returns the
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
     * - Objective: Ensure the "getDateOfInquiry()" method correctly returns the
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
     * - Objective: Ensure the "getInfoProvided()" method correctly returns the
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
     * - Objective: Ensure the "getLastKnownLocation()" method correctly returns the
     * last known location.
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
     * - Objective: Ensure the "setDateOfInquiry()" method correctly updates the
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
     * - Objective: Ensure the "setDateOfInquiry()" method throws an
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
     * - Objective: Ensure the "getLogDetails()" method returns log details in the
     * expected format.
     * - Actual result: Log details match the expected format.
     * - Expected result: The returned log details should match the expected format.
     */
    @Test
    public void testGetLogDetails() {
        assertEquals("Log details should match the expected format", newExpectedLogDetails,
                reliefService.getLogDetails());
    }

    /*
     * testAddInteractionLog:
     * - Objective: Ensure the "addInteractionLog()" method correctly adds
     * interaction logs.
     * - Actual result: Interaction log is added.
     * - Expected result: The interaction log should be added to the interaction
     * history.
     */
    @Test
    public void testAddInteractionLog() {
        String interactionLog = "Interaction log details...";
        reliefService.addInteractionLog(interactionLog);
        assertTrue("Interaction log should be added", reliefService.showInteractionLog().contains(interactionLog));
    }

    /*
     * testShowInteractionLog:
     * - Objective: Ensure the "showInteractionLog()" method correctly returns all
     * added interaction logs.
     * - Actual result: Interaction logs are returned as expected.
     * - Expected result: The showInteractionLog should return all added interaction
     * logs concatenated.
     */
    @Test
    public void testShowInteractionLog() {
        // Add interaction logs
        String log1 = "Interaction log 1 details...";
        String log2 = "Interaction log 2 details...";
        reliefService.addInteractionLog(log1);
        reliefService.addInteractionLog(log2);

        // Concatenate expected interaction log
        String expectedLog = log1 + "\n" + log2;

        assertEquals("showInteractionLog should return all added interaction logs", expectedLog,
                reliefService.showInteractionLog());
    }

    /*
     * testSearchDisasterVictims:
     * - Objective: Ensure the "searchDisasterVictims()" method correctly searches
     * for disaster victims.
     * - Actual result: Search returns matching disaster victims.
     * - Expected result: The search should return matching disaster victims based
     * on a part of their name.
     */
    @Test
    public void testSearchDisasterVictims() {
        // Add disaster victims to the location for testing
        DisasterVictim praveen = new DisasterVictim("Praveen Kumar", "2024-03-10", 12);
        DisasterVictim oprah = new DisasterVictim("Oprah Winfrey", "2024-03-11", 34);
        DisasterVictim john = new DisasterVictim("John Doe", "2024-03-12", 34);
        lastKnownLocation.addOccupant(praveen);
        lastKnownLocation.addOccupant(oprah);
        lastKnownLocation.addOccupant(john);

        // Search for disaster victims by a part of their name
        List<DisasterVictim> searchResult = reliefService.searchDisasterVictims("Pra");

        // Assert that the search result contains Praveen Kumar and Oprah Winfrey
        assertEquals("Search should return 2 matching DisasterVictims", 2, searchResult.size());
        assertTrue("Search result should contain Praveen Kumar", searchResult.contains(praveen));
        assertTrue("Search result should contain Oprah Winfrey", searchResult.contains(oprah));
    }

    /*
     * testDetermineWorkerMode:
     * - Objective: Ensure the "determineWorkerMode()" method correctly determines
     * the mode of the relief worker.
     * - Actual result: Mode of the relief worker is determined.
     * - Expected result: The method should correctly determine whether the relief
     * worker is in central or location-based mode.
     */
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
