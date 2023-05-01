package taxifyV5;

import java.util.List;

/**
 * Provides functions used in the test program to run the simulation
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public class ApplicationSimulator implements IApplicationSimulator, IObserver {
    private ITaxiCompany company;
    private List<IUser> users;
    private List<IVehicle> vehicles;

    /**
     * This constructor creates a new ApplicationSimulator object.
     *
     * @param  company  ITaxiCompany object representing the company
     * @param  users    List of IUser objects representing the users
     * @param  vehicles List of IVehicle objects representing the vehicles
     */
    public ApplicationSimulator(ITaxiCompany company, List<IUser> users, List<IVehicle> vehicles) {
        this.company = company;
        this.users = users;
        this.vehicles = vehicles;
    }

    /**
     * Shows status of vehicles (whether they're free, picking up a user, or servicing a user)
     */
    @Override
    public void show() {
        // shows the status of the vehicles

        System.out.println("\n" + this.company.getName() + " status \n");

        for (IVehicle vehicle : this.vehicles) {
            System.out.println(vehicle.toString());
        }
    }

    /**
     * Shows statistics of vehicles (how many users they've serviced, how many kilometers they've driven, etc.)
     */
    @Override
    public void showStatistics() {
        // shows the statistics of the company

        System.out.println("\n" + this.company.getName() + " statistics \n");

        for (IVehicle vehicle : this.vehicles) {
            System.out.printf("%-7s %d    %d services     %d km.   %d eur.    %d reviews %.2f stars\n", 
            vehicle.getClass().getSimpleName(), vehicle.getId(), vehicle.getStatistics().getServices(), 
            vehicle.getStatistics().getDistance(), vehicle.getStatistics().getBilling(), vehicle.getStatistics().getReviews(), vehicle.getStatistics().getStars());
        }
    }

    /**
     * Moves vehicles to their next location
     */
    @Override
    public void update() {
        // moves the vehicles to their next location

        for (int i=0; i<this.vehicles.size(); i++) {
            this.vehicles.get(i).move();
        }
    }

    /**
     * Finds a "free" user and requests a service to the Taxi Company
     */
    @Override
    public void requestService() {
        // finds a "free" user and requests a service to the Taxi Company

        for (int i=0; i<this.users.size(); i++) {
            if(!this.users.get(i).getService()) {
                this.company.requestService(i);
                return; // Exits method if we find a "free" user
            }
        }
    }

    /**
     * Returns the total number of services
     * @return int totalServices - the total number of services
     */
    @Override
    public int getTotalServices() {
        return this.company.getTotalServices();
    }

    /**
     * Updates observer by printing the parameter message to the console
     */
    @Override
    public void updateObserver(String message) {
        System.out.println(message);
    }
}