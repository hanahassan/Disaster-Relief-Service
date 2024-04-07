/** 
@author: Alison Gartner, Caleb Bourbonnais, Hanna Cho, and Hana Hassan
@group number: 34
@version: 1.2
@since: 1.0

The Location class represents the location where disaster victims can held.
It encapsulates Location information such as names, address as well as a list of supplies and occupants. 
This class provides essential functionality to manage and maintain a disaster relief center.
It enabling the addition and removal of occupants and supplies as needed during a crisis situation.
*/

package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private String address;
    private ArrayList<DisasterVictim> occupants;
    private ArrayList<Supply> supplies;

    // constructor
    public Location(String name, String address) {
        this.name = name;
        this.address = address;
        this.occupants = new ArrayList<>();
        this.supplies = new ArrayList<>();
    }

    public Location(String name) {
        this.name = name;
        this.occupants = new ArrayList<>();
        this.supplies = new ArrayList<>();
    }

    // setters
    public void setName(String name) {
        this.name = name;
        return;
    }

    public void setAddress(String address) {
        this.address = address;
        return;
    }

    public void setOccupants(ArrayList<DisasterVictim> occupants) {
        this.occupants = occupants;
    }

    public void setSupplies(ArrayList<Supply> supplies) {
        this.supplies = supplies;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Supply> getSupplies() {
        return supplies;
    }

    public ArrayList<DisasterVictim> getOccupants() {
        return occupants;
    }

    // other member functions

    public void addOccupant(DisasterVictim occupant) {
        if (!this.occupants.contains(occupant)) {
            this.occupants.add(occupant);
        } else {
            System.out.println("Occupant already exists in the location.");
        }
    }

    public void removeOccupant(DisasterVictim occupant) {
        if (this.occupants.contains(occupant)) {
            this.occupants.remove(occupant);
        } else {
            System.out.println("Occupant doesn't exist in the location.");
        }
    }

    public void addSupply(Supply supply) {
        if (!this.supplies.contains(supply)) {
            this.supplies.add(supply);
        } else {
            System.out.println("Supply already exists in the location.");
        }
    }

    public void removeSupply(Supply supply) {
        if (this.supplies.contains(supply)) {
            this.supplies.remove(supply);
        } else {
            System.out.println("Supply doesn't exist in the location.");
        }
    }

    /**
     * Tracks the supply allocation to a DisasterVictim and decreases its quantity
     * by 1 at the Location.
     * 
     * @param supply The supply to be tracked and decreased.
     */
    public void supplyTracker(Supply supply) {
        if (this.supplies.contains(supply)) {
            Supply trackedSupply = this.supplies.get(this.supplies.indexOf(supply));
            trackedSupply.decreaseQuantity(1); // Decrease the quantity of the tracked supply by 1
        } else {
            System.out.println("Supply not found in the location.");
        }
    }
    
    public String getFirstName() {
        for (DisasterVictim victim : occupants) {
            if (victim.getFirstName() != null) {
                return victim.getFirstName();
            }
        }
        return null;
    }
    
    public String getLastName() {
        for (DisasterVictim victim : occupants) {
            if (victim.getLastName() != null) {
                return victim.getLastName();
            }
        }
        return null;
    }

    public ArrayList<DisasterVictim> searchVictims(String searchQuery) { // Changed return type to List<DisasterVictim>
        ArrayList<DisasterVictim> searchResults = new ArrayList<>();
        for (DisasterVictim victim : occupants) { // Changed reference to occupants
            if (victim.getFirstName().toLowerCase().contains(searchQuery.toLowerCase())
                    || victim.getLastName().toLowerCase().contains(searchQuery.toLowerCase())) {
                searchResults.add(victim);
            }
        }
        return searchResults;
    }

    public boolean locationExists(String name) {
        if (name == this.name) {
            return true;
        }

        else {
            return false;
        }
    }

}
