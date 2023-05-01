package taxifyV5;

/**
 * Extends MicroMobility to calculate the cost and print the proper output for a bike
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public class Bike extends MicroMobility {

    /**
     * Class constructor for a Bike object
     * @param id the id of the vehicle
     * @param location the location of the vehicle
     */
    public Bike(int id, ILocation location) {
        super(id, location);
    }

    /**
     * This method calculates the cost of a service based on the distance between the pickup and
     * drop-off locations
     * @return the cost of the service
     */
    @Override
    public double calculateCost() {
        return super.calculateCost() * 0.5;
    }

    /**
     * This method returns a string representation of the Bike object
     * @return a string representation of the Bike object
     */
    @Override
    public String toString() {
        return "Bike    " + super.toString();
    }

    /*
     * The following method is not implemented because they are not needed for the Bike class
     */
    @Override
    public VehicleStatus getVehicleStatus() {
       throw new UnsupportedOperationException("Not supported yet.");
    }
}
