/** 
@author: Hana Hassan
@ucid: 30172447
@version: 1.3
@since: 1.0

The LocationTest class contains unit tests for the Location class, which represents physical locations in disaster scenarios.
It verifies the functionality of various methods in the Location class.
The Location class encapsulates information about physical locations, including their name, address, occupants, and available supplies.
It ensures accurate recording and retrieval of information about physical locations.
Unit tests in this class cover object creation, setter and getter methods for name, address, occupants, and supplies, 
as well as methods for adding and removing occupants and supplies.
 */
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class LocationTest {
    private Location location;
    private DisasterVictim victim;
    private Supply supply;

    // Initializing test objects before each test method
    @Before
    public void setUp() {
        location = new Location("Shelter A", "1234 Shelter Ave");
        victim = new DisasterVictim("John Doe", "2024-01-01");
        supply = new Supply("Water Bottle", 10);
    }

    // Helper method to check if a supply is in the list
    private boolean containsSupply(ArrayList<Supply> supplies, Supply supplyToCheck) {
        return supplies.contains(supplyToCheck);
    }

    // Test for constructor
    // Objective: Ensure that the constructor initializes a Location object correctly.
    // Actual result: A non-null Location object is created with correct name and address.
    // Expected result: The constructor should create a non-null Location object with the specified name and address.
    @Test
    public void testConstructor() {
        assertNotNull("Constructor should create a non-null Location object", location);
        assertEquals("Constructor should set the name correctly", "Shelter A", location.getName());
        assertEquals("Constructor should set the address correctly", "1234 Shelter Ave", location.getAddress());
    }

    // Test for setName() method
    // Objective: Ensure the setName() method correctly updates the name of the location.
    // Actual result: The name of the location is updated as expected.
    // Expected result: The setName() method should update the name of the location to the specified value.
    @Test
    public void testSetName() {
        String newName = "Shelter B";
        location.setName(newName);
        assertEquals("setName should update the name of the location", newName, location.getName());
    }

    // Test for setAddress() method
    // Objective: Ensure the setAddress() method correctly updates the address of the location.
    // Actual result: The address of the location is updated as expected.
    // Expected result: The setAddress() method should update the address of the location to the specified value.
    @Test
    public void testSetAddress() {
        String newAddress = "4321 Shelter Blvd";
        location.setAddress(newAddress);
        assertEquals("setAddress should update the address of the location", newAddress, location.getAddress());
    }

    // Test for addOccupant() method
    // Objective: Ensure the addOccupant() method correctly adds a disaster victim to the occupants list.
    // Actual result: The disaster victim is successfully added to the occupants list.
    // Expected result: The addOccupant() method should add the specified disaster victim to the occupants list.
    @Test
    public void testAddOccupant() {
        location.addOccupant(victim);
        assertTrue("addOccupant should add a disaster victim to the occupants list",
                location.getOccupants().contains(victim));
    }

    // Test for removeOccupant() method
    // Objective: Ensure the removeOccupant() method correctly removes a disaster victim from the occupants list.
    // Actual result: The disaster victim is successfully removed from the occupants list.
    // Expected result: The removeOccupant() method should remove the specified disaster victim from the occupants list.
    @Test
    public void testRemoveOccupant() {
        location.addOccupant(victim); // Ensure the victim is added first
        location.removeOccupant(victim);
        assertFalse("removeOccupant should remove the disaster victim from the occupants list",
                location.getOccupants().contains(victim));
    }

    // Test for setOccupants() and getOccupants() methods
    // Objective: Ensure the setOccupants() method correctly replaces the occupants list and getOccupants() method correctly retrieves the occupants list.
    // Actual result: The setOccupants() method replaces the occupants list and getOccupants() method retrieves the updated list.
    // Expected result: The setOccupants() method should replace the occupants list with the specified list, and getOccupants() method should retrieve the updated list.
    @Test
    public void testSetAndGetOccupants() {
        ArrayList<DisasterVictim> newOccupants = new ArrayList<>();
        newOccupants.add(victim);
        location.setOccupants(newOccupants);
        assertTrue("setOccupants should replace the occupants list with the new list",
                location.getOccupants().containsAll(newOccupants));
    }

    // Test for addSupply() method
    // Objective: Ensure the addSupply() method correctly adds a supply to the supplies list.
    // Actual result: The supply is successfully added to the supplies list.
    // Expected result: The addSupply() method should add the specified supply to the supplies list.
    @Test
    public void testAddSupply() {
        location.addSupply(supply);
        assertTrue("addSupply should add a supply to the supplies list",
                containsSupply(location.getSupplies(), supply));
    }

    // Test for removeSupply() method
    // Objective: Ensure the removeSupply() method correctly removes a supply from the supplies list.
    // Actual result: The supply is successfully removed from the supplies list.
    // Expected result: The removeSupply() method should remove the specified supply from the supplies list.
    @Test
    public void testRemoveSupply() {
        location.addSupply(supply); // Ensure the supply is added first
        location.removeSupply(supply);
        assertFalse("removeSupply should remove the supply from the supplies list",
                containsSupply(location.getSupplies(), supply));
    }

    // Test for setSupplies() and getSupplies() methods
    // Objective: Ensure the setSupplies() method correctly replaces the supplies list and getSupplies() method correctly retrieves the supplies list.
    // Actual result: The setSupplies() method replaces the supplies list and getSupplies() method retrieves the updated list.
    // Expected result: The setSupplies() method should replace the supplies list with the specified list, and getSupplies() method should retrieve the updated list.
    @Test
    public void testSetAndGetSupplies() {
        ArrayList<Supply> newSupplies = new ArrayList<>();
        newSupplies.add(supply);
        location.setSupplies(newSupplies);
        assertTrue("setSupplies should replace the supplies list with the new list",
                containsSupply(location.getSupplies(), supply));
    }

    // Test for supplyTracker() method
    // Objective: Ensure the supplyTracker() method correctly decreases the quantity of a tracked supply.
    // Actual result: The quantity of the tracked supply is decreased by 1.
    // Expected result: The supplyTracker() method should decrease the quantity of the tracked supply by 1.
    @Test
    public void testSupplyTracker() {
        // Add the supply to the location
        location.addSupply(supply);
        
        // Call supplyTracker method
        location.supplyTracker(supply);
        
        // Check if the quantity of the supply is decreased by 1
        assertEquals("Supply quantity should decrease by 1 after tracking", 9, supply.getQuantity());
    }
}