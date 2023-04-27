package taxifyV4;

/**
 * Extends Vehicle to calculate the cost and print the proper output for a shuttle
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
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
    public double calculateCost() {
        return super.calculateCost() * 1.5;
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