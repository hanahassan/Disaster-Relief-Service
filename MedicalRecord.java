/*
@author: Alison Gartner, Caleb Bourbonnais, Hanna Cho, and Hana Hassan
@group number: 34
@version: 1.3
@since: 1.0

The MedicalRecord class represents a medical record of a disaster victim.
It encapsulates the location of treatment, treatment details, and the date of treatment.
The class provides validation for inputs, ensuring accurate representation of medical records,
making it a comprehensive model for disaster management scenarios.
*/
package edu.ucalgary.oop;

public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;

    // Constructor, takes in a Location, treatment details, and date of treatment
    public MedicalRecord(Location location, 
    String treatmentDetails, String dateOfTreatment) throws IllegalArgumentException {
        setLocation(location);
        setTreatmentDetails(treatmentDetails);
        setDateOfTreatment(dateOfTreatment);
    }
    
    // Setters for MedicalRecord, takes in a Location, treatment details, and date of treatment
    public void setLocation(Location place) throws IllegalArgumentException {
        if (place instanceof Location) {
            this.location = place;
            return;
        }
        else {
            throw new IllegalArgumentException("Invalid location entered: " +place);
        }
    }
    public void setTreatmentDetails(String details) {
        if (details instanceof String) {
            this.treatmentDetails = details;
            return;
        }
        else {
            throw new IllegalArgumentException("Invalid treatmentDetails entered: " +details);
        }
    }
    // Validates the date of treatment, takes in a string, throws an exception if the date is invalid using regex and parsing the numbers
    public void setDateOfTreatment(String dateOfTreatment) throws IllegalArgumentException {
        if (dateOfTreatment.matches("\\d{4}-\\d{2}-\\d{2}")) {

            String[] dateParts = dateOfTreatment.split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            
            // Validates the date of treatment, throws an exception if the date is invalid
            if (year >= 2000 && year <= 2050 && month >= 1 && month <= 12 && day >= 1 && day <= 31) {
                this.dateOfTreatment = dateOfTreatment;
                return;
            }
        }
        throw new IllegalArgumentException("Invalid date of treatment entered: " + dateOfTreatment);
    }

    // Getters for MedicalRecord, returns a Location, treatment details, and date of treatment
    public Location getLocation() { return location; }
    public String getTreatmentDetails() { return treatmentDetails; }
    public String getDateOfTreatment() { return dateOfTreatment; }

}