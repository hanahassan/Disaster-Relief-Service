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
    Location newExpectedLocation = new Location("HospitalX", "123 Main Street");
    private String newExpectedTreatmentDetails = "Fractured leg treated";
    private String newExpectedDateOfTreatment = "2024-02-20";
    private String newValidDateOfTreatment = "2024-03-15";
    private String newInvalidDateOfTreatment = "2024/03/15";
    MedicalRecord medicalRecord = new MedicalRecord(newExpectedLocation, newExpectedTreatmentDetails,
            newExpectedDateOfTreatment);

    // Test case to ensure that MedicalRecord object is successfully created
    @Test
    public void testObjectCreation() {
        assertNotNull(medicalRecord);
    }

    // Test case to check the functionality of getLocation method
    @Test
    public void testGetLocation() {
        assertEquals("getLocation should return the correct Location", newExpectedLocation, medicalRecord.getLocation());
    }

    // Test case to check the functionality of setLocation method
    @Test
    public void testSetLocation() {
        Location newerExpectedLocation = new Location("HospitalY", "456 Oak Street");
        medicalRecord.setLocation(newerExpectedLocation);
        assertEquals("setLocation should update the Location", newerExpectedLocation.getName(),
                medicalRecord.getLocation().getName());
    }

    // Test case to check the functionality of getTreatmentDetails method
    @Test
    public void testGetTreatmentDetails() {
        assertEquals("getTreatmentDetails should return the correct treatment details", newExpectedTreatmentDetails,
                medicalRecord.getTreatmentDetails());
    }

    // Test case to check the functionality of setTreatmentDetails method
    @Test
    public void testSetTreatmentDetails() {
        String newerExpectedTreatment = "No surgery required";
        medicalRecord.setTreatmentDetails(newerExpectedTreatment);
        assertEquals("setTreatmentDetails should update the treatment details", newerExpectedTreatment,
                medicalRecord.getTreatmentDetails());
    }

    // Test case to check the functionality of getDateOfTreatment method
    @Test
    public void testGetDateOfTreatment() {
        assertEquals("getDateOfTreatment should return the correct date of treatment", newExpectedDateOfTreatment,
                medicalRecord.getDateOfTreatment());
    }

    // Test case to check the functionality of setDateOfTreatment method
    @Test
    public void testSetDateOfTreatment() {
        String newerExpectedDateOfTreatment = "2024-02-21";
        medicalRecord.setDateOfTreatment(newerExpectedDateOfTreatment);
        assertEquals("setDateOfTreatment should update date of treatment", newerExpectedDateOfTreatment,
                medicalRecord.getDateOfTreatment());
    }

    // Test case to check if setDateOfTreatment method accepts a valid date format
    @Test
    public void testSetDateOfTreatmentWithValidFormat() {
        medicalRecord.setDateOfTreatment(newValidDateOfTreatment); // Should not throw an exception
    }

    // Test case to check if setDateOfTreatment method throws an exception with an invalid date format
    @Test
    public void testSetDateOfBirthWithInvalidFormat() {
        boolean correctValue = false;
        String failureReason = "no exception was thrown";

        try {
            medicalRecord.setDateOfTreatment(newInvalidDateOfTreatment); // Should throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            correctValue = true;
        } catch (Exception e) {
            failureReason = "the wrong type of exception was thrown";
        }

        String message = "setDateOfTreatment() should throw an IllegalArgumentException with invalid date format '"
                + newInvalidDateOfTreatment + "' but " + failureReason + ".";
        assertTrue(message, correctValue);
    }

    // Test case to check if setDateOfTreatment method throws an exception with invalid non-date input
    @Test
    public void testSetDateOfBirthWithNotADate() {
        boolean correctValue = false;
        String failureReason = "no exception was thrown";

        try {
            medicalRecord.setDateOfTreatment(newExpectedTreatmentDetails); // Should throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            correctValue = true;
        } catch (Exception e) {
            failureReason = "the wrong type of exception was thrown";
        }

        String message = "setDateOfTreatment() should throw an IllegalArgumentException with invalid non-date input '"
                + newInvalidDateOfTreatment + "' but " + failureReason + ".";
        assertTrue(message, correctValue);
    }
}