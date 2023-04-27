package taxifyV4;

/**
 * Interface for User
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public interface IUser {

    /**
     * This method returns the id attribute.
     *
     * @return      integer representing the user's id
     */
    public int getId();
    
    /**
     * This method returns the firstName attribute.
     *
     * @return      String representing the user's first name
     */
    public String getFirstName();

    /**
     * This method returns the lastName attribute.
     * @return     String representing the user's last name
     */
    public String getLastName();
    
    /**
     * This method returns the service attribute.
     *
     * @return      boolean representing whether the user is currently being served
     */
    public boolean getService();
    
    /**
     *  This method sets the service attribute.
     */
    public void setService(boolean service);

    /**
     * This method sets the company attribute
     * @param company - ITaxiCompany object to set the attribute to
     */
    public void setCompany(ITaxiCompany company);

    /**
     * This method updates the user's location as it is walking from its location to the
     * pickup location of the micromobility vehicle or while in the ride. This attribute is only
     * helpful when using micromobility vehicles.
     *
     * @param      newLocation representing the where the user has moved
     */
    public void updateUserLocation(ILocation newLocation);

    /**
     * This method returns the user's location. This attribute is only helpful when using
     * micromobility vehicles
     *
     * @return      ILocation representing the current location of the user
     */
    public ILocation getUserLocation();

    /**
     * This method requests a service from the company.
     */
    public void requestService();

    /**
     * This method rates the service provided by the company.
     * @param service - IService object to rate for that specific ride
     */
    public void rateService(IService service);

    /**
     * This method returns a string representation of the User object.
     * @return - String representation of the User object (first and last name)
     */
    public String toString();

}
