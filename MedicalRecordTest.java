/** 
@author: Hana Hassan
@ucid: 30172447
@version: 1.4
@since: 1.0

The MedicalRecordTest class contains unit tests for the MedicalRecord class, which represents medical records for disaster victims. 
It verifies the functionality of various methods in the MedicalRecord class.
MedicalRecord class encapsulates information about the treatment of a disaster victim at a specific location. 
It includes details such as the location where treatment occurred, treatment details, and the date of treatment. 
The class ensures accurate recording and retrieval of medical treatment information for disaster victims.
Unit tests in this class cover object creation, getter and setter methods for location, treatment details, and date of treatment. 
Additionally, it tests the validation of date formats in the setDateOfTreatment method, ensuring proper exception handling for both valid and invalid date formats.

*/
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class MedicalRecordTest {

    // Define new expected values for testing
    Location testExpectedLocation = new Location("HospitalX", "123 Main Street");
    private String testExpectedTreatmentDetails = "Fractured leg treated";
    private String testExpectedDateOfTreatment = "2024-02-20";
    private String testValidDateOfTreatment = "2024-03-15";
    private String testInvalidDateOfTreatment = "2024/03/15";
    MedicalRecord medicalRecord = new MedicalRecord(testExpectedLocation, testExpectedTreatmentDetails,
            testExpectedDateOfTreatment);

    /*
     * testObjectCreation:
     * - Objective: To verify that a "MedicalRecord" object is successfully created.
     * - Actual result: MedicalRecord object is not null.
     * - Expected result: The test checks that the MedicalRecord object is not null,
     * confirming successful object creation.
     */
    @Test
    public void testObjectCreation() {
        assertNotNull(medicalRecord);
    }

    /*
     * testGetLocation:
     * - Objective: To ensure the "getLocation()" method correctly returns the actual Location.
     * - Actual result: getLocation should return the correct Location.
     * - Expected result: The returned Location should match the one set during setup.
     */
    @Test
    public void testGetLocation() {
        assertEquals("getLocation should return the correct Location", testExpectedLocation, medicalRecord.getLocation());
    }

    /*
     * testSetLocation:
     * - Objective: To ensure the "setLocation()" method correctly updates the MedicalRecord location.
     * - Actual result: setLocation should update the Location.
     * - Expected result: The setLocation should update the Location to the new expected Location.
     */
    @Test
    public void testSetLocation() {
        Location newExpectedLocation = new Location("HospitalY", "456 Oak Street");
        medicalRecord.setLocation(newExpectedLocation);
        assertEquals("setLocation should update the Location", newExpectedLocation.getName(),
                medicalRecord.getLocation().getName());
    }

    /*
     * testGetTreatmentDetails:
     * - Objective: To ensure the "getTreatmentDetails()" method correctly returns the actual treatment details.
     * - Actual result: getTreatmentDetails should return the correct treatment details.
     * - Expected result: The returned treatment details should match the one set during setup.
     */
    @Test
    public void testGetTreatmentDetails() {
        assertEquals("getTreatmentDetails should return the correct treatment details", testExpectedTreatmentDetails,
                medicalRecord.getTreatmentDetails());
    }

    /*
     * testSetTreatmentDetails:
     * - Objective: To ensure the "setTreatmentDetails()" method correctly updates the treatment details.
     * - Actual result: setTreatmentDetails should update the treatment details.
     * - Expected result: The setTreatmentDetails should update the treatment details to the new expected treatment details.
     */
    @Test
    public void testSetTreatmentDetails() {
        String newExpectedTreatment = "No surgery required";
        medicalRecord.setTreatmentDetails(newExpectedTreatment);
        assertEquals("setTreatmentDetails should update the treatment details", newExpectedTreatment,
                medicalRecord.getTreatmentDetails());
    }

    /*
     * testGetDateOfTreatment:
     * - Objective: To ensure the "getDateOfTreatment()" method correctly returns the actual date of treatment.
     * - Actual result: getDateOfTreatment should return the correct date of treatment.
     * - Expected result: The returned date of treatment should match the one set during setup.
     */
    @Test
    public void testGetDateOfTreatment() {
        assertEquals("getDateOfTreatment should return the correct date of treatment", testExpectedDateOfTreatment,
                medicalRecord.getDateOfTreatment());
    }

    /*
     * testSetDateOfTreatment:
     * - Objective: To ensure the "setDateOfTreatment()" method correctly updates the date of treatment.
     * - Actual result: setDateOfTreatment should update date of treatment.
     * - Expected result: The setDateOfTreatment should update the date of treatment to the new expected date of treatment.
     */
    @Test
    public void testSetDateOfTreatment() {
        String newExpectedDateOfTreatment = "2024-02-21";
        medicalRecord.setDateOfTreatment(newExpectedDateOfTreatment);
        assertEquals("setDateOfTreatment should update date of treatment", newExpectedDateOfTreatment,
                medicalRecord.getDateOfTreatment());
    }

    /*
     * testSetDateOfTreatmentWithValidFormat:
     * - Objective: To check if setDateOfTreatment method accepts a valid date format.
     * - Actual result: setDateOfTreatment accepts valid date format.
     * - Expected result: setDateOfTreatment should not throw an exception with valid date format.
     */
    @Test
    public void testSetDateOfTreatmentWithValidFormat() {
        medicalRecord.setDateOfTreatment(testValidDateOfTreatment); // Should not throw an exception
    }

    /*
     * testSetDateOfBirthWithInvalidFormat:
     * - Objective: To check if setDateOfTreatment method throws an exception with an invalid date format.
     * - Actual result: IllegalArgumentException thrown.
     * - Expected result: setDateOfTreatment should throw an IllegalArgumentException with invalid date format.
     */
    @Test
    public void testSetDateOfBirthWithInvalidFormat() {
        boolean correctValue = false;
        String failureReason = "no exception was thrown";

        try {
            medicalRecord.setDateOfTreatment(testInvalidDateOfTreatment); // Should throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            correctValue = true;
        } catch (Exception e) {
            failureReason = "the wrong type of exception was thrown";
        }

        String message = "setDateOfTreatment() should throw an IllegalArgumentException with invalid date format '"
                + testInvalidDateOfTreatment + "' but " + failureReason + ".";
        assertTrue(message, correctValue);
    }

    /*
     * testSetDateOfBirthWithNotADate:
     * - Objective: To check if setDateOfTreatment method throws an exception with invalid non-date input.
     * - Actual result: IllegalArgumentException thrown.
     * - Expected result: setDateOfTreatment should throw an IllegalArgumentException with invalid non-date input.
     */
    @Test
    public void testSetDateOfBirthWithNotADate() {
        boolean correctValue = false;
        String failureReason = "no exception was thrown";

        try {
            medicalRecord.setDateOfTreatment(testExpectedTreatmentDetails); // Should throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            correctValue = true;
        } catch (Exception e) {
            failureReason = "the wrong type of exception was thrown";
        }

        String message = "setDateOfTreatment() should throw an IllegalArgumentException with invalid non-date input '"
                + testInvalidDateOfTreatment + "' but " + failureReason + ".";
        assertTrue(message, correctValue);
    }
}