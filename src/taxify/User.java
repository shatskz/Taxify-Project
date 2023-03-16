// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This class represents an individual user using the Taxify application and
// keeps track of his or her first and last name, id, and whether the user is currently being
// serviced

package taxify;

public class User implements IUser {
    private int id;
    private String firstName;
    private String lastName;
    private ITaxiCompany company;
    private boolean service;
    
    /**
     * This constructor creates a new User object.
     *
     * @param  id          integer representing the user's id
     * @param  firstName   String representing the user's first name
     * @param  lastName    String representing the user's last name
     */
    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.service = false;
        this.company = null;
    }
    
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * This method returns the firstName attribute.
     *
     * @return      String representing the user's first name
     */
    @Override
    public String getFirstName() {
        return this.firstName;
    }


    /**
     * This method returns the lastName attribute.
     * @return     String representing the user's last name
     */
    @Override
    public String getLastName() {
        return this.lastName;
    }

    /**
     * This method returns the service attribute.
     *
     * @return      boolean representing whether the user is currently being served
     */
    @Override
    public boolean getService() {
        return this.service;
    }

    /**
     *  This method sets the service attribute.
     */
    @Override
    public void setService(boolean service) {
        this.service = service;
    }

    /**
     * This method sets the company attribute
     * @param company - ITaxiCompany object to set the attribute to
     */
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }

    /**
     * This method requests a service from the company.
     */
    @Override
    public void requestService() {
        this.company.requestService(this.id);
    }

    /**
     * This method rates the service provided by the company.
     * @param service - IService object to rate for that specific ride
     */
    @Override
    public void rateService(IService service) {
        // users rate around 50% of the services (1 to 5 stars)

        if (ApplicationLibrary.rand() % 2 == 0)
            service.setStars(ApplicationLibrary.rand(5) + 1);
    }

    /**
     * This method returns a string representation of the User object (first + last name).
     */
    @Override
    public String toString() {
        return this.getId() + " " + String.format("%-20s",this.getFirstName() + " " +
                this.getLastName());
    }
}
