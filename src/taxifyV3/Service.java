// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This class keeps track of the data for each service (eg, the user, pickup and
// drop off locations, and the stars given)
package taxifyV3;

public class Service implements IService {
    private IUser user;
    private ILocation pickup;
    private ILocation dropoff;
    private int stars;

    /**
     * Class constructor
     */
    public Service(IUser user, ILocation pickup, ILocation dropoff) {
        this.user = user;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.stars = 0;
    }

    /**
     * This method returns the user attribute.
     *
     * @return      IUser object representing the rider
     */
    @Override
    public IUser getUser() {
        return this.user;
    }

    /**
     * This method returns the pickup attribute.
     *
     * @return      ILocation object representing the pickup location
     */
    @Override
    public ILocation getPickupLocation() {
        return this.pickup;
    }

    /**
     * This method returns the dropoff attribute.
     *
     * @return      ILocation object representing the dropoff location
     */
    @Override
    public ILocation getDropoffLocation() {
        return this.dropoff;
    }

    /**
     * This method returns the stars attribute.
     *
     * @return      integer representing the number of stars the service contains, 0 to 5
     */
    @Override
    public int getStars() {
        return this.stars;
    }

    /**
     * This method sets the stars attribute. It ensures the attribute is set to a valid integer
     *
     * @param  stars  value to set the stars attribute to, 0 to 5
     */
    @Override
    public void setStars(int stars) {
        if(stars > 5 || stars < 0) {
            System.out.println("ERROR: ratings must be between 0 and 5 stars");
            System.exit(0);
        }
        this.stars = stars;
    }

    /**
     * This method calculates the Manhattan distance between the pickup and drop off locations
     *
     * @return      integer representing the Manhattan distance between the two locations
     */
    @Override
    public int calculateDistance() {
        return Math.abs(this.pickup.getX() - this.dropoff.getX()) + Math.abs(this.pickup.getY() -
                this.dropoff.getY());
    }

    /**
     * This method returns the locations of the pickup and drop off points in a String
     *
     * @return      String representing the pickup and drop off locations
     */
    @Override
    public String toString() {
        return this.getPickupLocation().toString() + " to " +
                this.getDropoffLocation().toString();
    }
}
