/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class SupplyTest {
    String testExpectedType = "Food";
    int testExpectedQuantity = 20;
    private Supply supply = new Supply(newExpectedType, newExpectedQuantity);

    /*
     * testObjectCreation:
     * - Objective: To verify that a "Supply" object is successfully created.
     * - Actual result: Supply type is "Food" and the quantity is 20.
     * - Expected result: The test checks that the Supply object is not null,
     * confirming successful object creation.
     */
    @Test
    public void testObjectCreation() {
        assertNotNull(supply);
    }

    /*
     * testGetType:
     * - Objective: To ensure the "getType()" method correctly returns the actual
     * Supply type.
     * - Actual result: "Food".
     * - Expected result: "supply.getType()" should return "Food".
     */
    @Test
    public void testGetType() {
        assertEquals("getType should return the correct type", newExpectedType, supply.getType());
    }

    /*
     * testSetType:
     * - Objective: To ensure the "setType()" method correctly updates the Supply
     * type.
     * - Actual result: "Food".
     * - Expected result: "setType" updates the Supply type to "Medical".
     */
    @Test
    public void testSetType() {
        supply.setType("Medical");
        assertEquals("setType should update the type", "Medical", supply.getType());
    }

    /*
     * testGetQuantity:
     * - Objective: To ensure the "getQuantity()" method correctly returns the
     * actual Supply quantity.
     * - Actual result: 20.
     * - Expected result: "supply.getType()" should return 20.
     */
    @Test
    public void testGetQuantity() {
        assertEquals("getQuantity should return the correct quantity", newExpectedQuantity, supply.getQuantity());
    }

    /*
     * testSetQuantity:
     * - Objective: To ensure the "setQuantity" method correctly updates the
     * Supply quantity.
     * - Actual result: 20.
     * - Expected result: "setQuantity" updates the Supply quantity to 10.
     */
    @Test
    public void testSetQuantity() {
        supply.setQuantity(10);
        assertEquals("setQuantity should update the quantity", 10, supply.getQuantity());
    }

    /*
     * testDecreaseQuantity:
     * - Objective: To verify that the "decreaseQuantity()" method correctly reduces
     * the supply quantity.
     * - Steps:
     * 1. Create a new Supply object with an initial quantity of 20.
     * 2. Call the decreaseQuantity method with an amount of 5.
     * 3. Assert that the quantity after decreasing is 15.
     * - Expected result: The quantity of the supply should decrease by the
     * specified amount.
     */
    @Test
    public void testDecreaseQuantity() {
        supply.decreaseQuantity(5);
        assertEquals("decreaseQuantity should reduce the quantity by 5", newExpectedQuantity - 5, supply.getQuantity());
    }

}
