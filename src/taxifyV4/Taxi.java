package taxifyV4;

/**
 * Extends Vehicle to calculate the cost and print the proper output for a taxi
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
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