/*
@author: Alison Gartner, Caleb Bourbonnais, Hanna Cho, and Hana Hassan
@group number: 34
@version: 1.2
@since: 1.0

The Supply class represents the supplies kept at a distaer reflief shelter 
that is then given to disater victims on a as-needed-baisis. 
It encapsulates supply information such as type and quantity. 
It provides methods to set and retrieve the type and quantity of a particular supply. 
This class is designed to facilitate the management and tracking of supply resources.
*/

package edu.ucalgary.oop;

public class Supply {
    private String type;
    private int quantity;

    // constructor
    public Supply(String type, int quantity) {
        setType(type);
        setQuantity(quantity);
    }

    // setters
    public void setType(String type) {
        this.type = type;
        return;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        return;
    }

    // getters
    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Decreases the quantity of the supply by the specified amount.
     * 
     * @param amount The amount by which to decrease the quantity.
     */
    public void decreaseQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
        } else {
            System.out.println("Cannot decrease quantity. Insufficient quantity available.");
        }
    }

}
