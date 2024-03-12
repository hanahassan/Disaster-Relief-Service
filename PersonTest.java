package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {

    private Person person;
    private String expectedFirstName = "Hana";
    private String expectedLastName = "Hassan";

    @Before
    public void setUp() {
        person = new Person(expectedFirstName, expectedLastName);
    }

    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Mena";
        person.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName, person.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Bobby";
        person.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName, person.getLastName());
    }
}
