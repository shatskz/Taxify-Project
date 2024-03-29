// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This file provides the interface for the User class

package taxify;

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
