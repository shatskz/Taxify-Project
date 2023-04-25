// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This file defines the methods for the Taxi class. It extends Vehicle because
// it alters the behavior of some of its methods

package taxifyV4;

public class Taxi extends Vehicle {

    /**
     * Class constructor for a Taxi object
     * @param id the id of the vehicle
     * @param location the location of the vehicle
     */
    public Taxi(int id, ILocation location) {
        super(id, location);
    }

    /**
     * This method calculates the cost of a service based on the distance between the pickup and drop-off locations
     * @return the cost of the service
     */
    @Override
    public double calculateCost() {
        return super.calculateCost() * 2;
    }

    /**
     * This method returns a string representation of the Taxi object
     * @return a string representation of the Taxi object
     */
    @Override
    public String toString() {
        return "Taxi    " + super.toString();
    }
}