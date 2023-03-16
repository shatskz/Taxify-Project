package taxify;

import java.util.List;

public class TaxiCompany implements ITaxiCompany, ISubject {
    private String name;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private int totalServices;
    private IObserver observer;

    /**
     * The constructor of the class TaxiCompany
     * @param name the name of the company
     * @param users the list of users
     * @param vehicles the list of vehicles
     */
    public TaxiCompany(String name, List<IUser> users, List<IVehicle> vehicles) {
        this.name = name;
        this.users = users;
        this.vehicles = vehicles;
        this.totalServices = 0;

        // set the taxi company for users and vehicles

        for (int i=0; i<this.users.size(); i++)
            this.users.get(i).setCompany(this);

        for (int i=0; i<this.vehicles.size(); i++)
            this.vehicles.get(i).setCompany(this);
    }

    /**
     * The method getName returns the name of the company
     * @return the name of the company
     */
    @Override
    public String getName() {
        // returns the name of the company
        return this.name;
    }

    /**
     * Gets the number of total services/rides
     * @return the number of total services/rides
     */
    @Override
    public int getTotalServices() {
        // returns the total services
        return this.totalServices;
    }

    /**
     * The method requestService requests a service to the company
     * @param user the user that requests the service
     * @return true if the service is assigned to a vehicle, false otherwise
     */
    @Override
    public boolean requestService(int user) {
        int userIndex = indexOfUserId(user);
        int vehicleIndex = findFreeVehicle();

        // if there is a free vehicle, assign a random pickup and drop-off location to the new service
        // the distance between the pickup and the drop-off location should be at least 3 blocks

        if (vehicleIndex != -1) {
            ILocation origin, destination;

            do {

                origin = ApplicationLibrary.randomLocation();
                destination = ApplicationLibrary.randomLocation(origin);

            } while (ApplicationLibrary.distance(origin, this.vehicles.get(vehicleIndex).getLocation()) < 3);

            // update the user status

            this.users.get(userIndex).setService(true);

            // create a service with the user, the pickup and the drop-off location

            Service service = new Service(this.users.get(userIndex), origin, destination);

            // assign the new service to the vehicle

            this.vehicles.get(vehicleIndex).pickService(service);

            notifyObserver("User " + this.users.get(userIndex).getId() + " requests a service from " + service.toString() + ", the ride is assigned to " +
                           this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                           this.vehicles.get(vehicleIndex).getLocation().toString());

            // update the counter of services

            this.totalServices++;

            return true;
        }

        return false;
    }

    /**
     * The method arrivedAtPickupLocation is called when a vehicle arrives at the pickup location
     * The observer is notified
     * @param vehicle the vehicle that arrives at the pickup location
     */
    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        // a vehicle arrives at the pickup location

        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " loads user " + vehicle.getService().getUser().getId());
    }

    /**
     * The method arrivedAtDropoffLocation is called when a vehicle arrives at the drop-off location
     * The company asks the user for a rating, the status is updated, and observer is notified
     * @param vehicle
     */
    @Override
    public void arrivedAtDropoffLocation(IVehicle vehicle) {
        // a vehicle arrives at the drop-off location

        IService service = vehicle.getService();
        int user = service.getUser().getId();
        int userIndex = indexOfUserId(user);

        // the taxi company requests the user to rate the service, and updates its status

        this.users.get(userIndex).rateService(service);
        this.users.get(userIndex).setService(false);

        // update the counter of services

        this.totalServices--;

        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off user " + user);
    }

    /**
     * The method addObserver adds an observer to the list of observers
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(IObserver observer) {
        this.observer = observer;
    }

    /**
     * The method removeObserver removes an observer from the list of observers
     * @param message the message to be sent to the observer
     */
    @Override
    public void notifyObserver(String message) {
        this.observer.updateObserver(message);
    }

    /**
     * The method findFreeVehicle finds a free vehicle
     * @return the index of the vehicle in the list, otherwise it returns -1
     */
    private int findFreeVehicle() {
        // finds a free vehicle and returns the index of the vehicle in the list, otherwise it returns -1

        for(int i = 0; i < this.vehicles.size(); i++) {
            if(this.vehicles.get(i).isFree())
                return i;
        }

        return -1; // If no free vehicles are found
    }

    /**
     * The method indexOfUserId finds the index of a user with the input id in the list
     * @param id the id of the user
     * @return the index of the user in the list, otherwise it returns -1
     */
    private int indexOfUserId(int id) {
        // finds the index of a user with the input id in the list, otherwise it returns -1

        for(int i = 0; i < this.users.size(); i++) {
            if(this.users.get(i).getId() == id)
                return i;
        }

        return -1; // If no users with the input id are found
    }
}