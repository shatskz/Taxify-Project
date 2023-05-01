package taxifyV5;

/**
 * Extends MicroMobility to calculate the cost and print the proper output for a scooter
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public class Scooter extends MicroMobility {
    /**
     * Class constructor for a Scooter object
     * @param id the id of the vehicle
     * @param location the location of the vehicle
     */
    public Scooter(int id, ILocation location) {
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
     * This method returns a string representation of the Scooter object
     * @return a string representation of the Scooter object
     */
    @Override
    public String toString() {
        return "Scooter " + super.toString();
    }

    /**
     * The following method is not implemented because they are not needed for the Scooter class
     */
    @Override
    public VehicleStatus getVehicleStatus() {
        throw new UnsupportedOperationException("Unimplemented method 'getVehicleStatus'");
    }
}
