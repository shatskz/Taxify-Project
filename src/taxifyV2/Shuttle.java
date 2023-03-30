// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This file defines the methods for the Shuttle class. It extends Vehicle because
// it alters the behavior of some of its methods

package taxifyV2;

public class Shuttle extends Vehicle {

    /**
     * Class constructor for a Shuttle object
     * @param id the id of the vehicle
     * @param location the location of the vehicle
     */
    public Shuttle(int id, ILocation location) {
        super(id, location);
    }

    /**
     * This method calculates the cost of a service based on the distance between the pickup and drop-off locations
     * @return the cost of the service
     */
    @Override
    public int calculateCost() {
        return (int) (super.calculateCost() * 1.5);
    }

    /**
     * This method returns a string representation of the Shuttle object
     * @return a string representation of the Shuttle object
     */
    @Override
    public String toString() {
        return "Shuttle " + super.toString();
    }
}