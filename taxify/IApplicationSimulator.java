package taxify;

public interface IApplicationSimulator {

    /**
     * Shows status of vehicles (whether they're free, picking up a user, or servicing a user)
     */
    public void show();

    /**
     * Shows statistics of vehicles (how many users they've serviced, how many kilometers they've driven, etc.)
     */
    public void showStatistics();
    
    /**
     * Moves vehicles to their next location
     */
    public void update();

    /**
     * Finds a "free" user and requests a service to the Taxi Company
     */
    public void requestService();

    /**
     * Returns the total number of services
     * @return int totalServices - the total number of services
     */
    public int getTotalServices();

}