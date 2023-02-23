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
     * Class constructor
     */
    public User(int id, String firstName, String lastName, ITaxiCompany company) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.service = false;
    }

    /**
     * This method returns the id attribute.
     *
     * @return      integer representing the user's id
     */
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
     *
     * @return      String representing the user's last name
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
     * This method sets the service attribute.
     *
     * @param  service  value to set the service attribute to
     */
    @Override
    public void setService(boolean service) {
        this.service = service;
    }

    /**
     * This method sets the company attribute
     *
     * @param  company  ITaxiCompany object to set the attribute to
     */
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }


    /**
     * This method calls on ITaxiCompany's requestService() method to indicate that this user
     * wants a ride
     *
     */
    @Override
    public void requestService() {
        this.company.requestService(this.id);
    }

    /**
     * This method gives a random rating to the service received between 1 and 5. This method
     * will only rate about 50% of the rides a user takes
     *
     * @param  service  IService object which will be rated
     */
    @Override
    public void rateService(IService service) {
        // users rate around 50% of the services (1 to 5 stars)

        if (ApplicationLibrary.rand() % 2 == 0)
            service.setStars(ApplicationLibrary.rand(5) + 1);
    }

    /**
     * This method returns the user's id, first name, and last name in a String
     *
     * @return      String representing the user's information
     */
    @Override
    public String toString() {
        return this.getId() + " " + String.format("%-20s",this.getFirstName() + " " +
                this.getLastName());
    }
}
