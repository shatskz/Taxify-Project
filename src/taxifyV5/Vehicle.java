package taxifyV5;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements IVehicle to provide the superclass for Taxi and Shuttle
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public abstract class Vehicle implements IVehicle {

    private int id;
    private ITaxiCompany company;
    private List<IService> service; // List of services in vehicle
    private VehicleStatus status;
    private ILocation location;
    private ILocation destination;
    private IStatistics statistics;
    private List<ILocation> route;
    private IService currentService; // The service whose destination we are currently driving to
    private int CAR_CAPACITY = 4; // Maximum number of services in each vehicle

    /**
     * Class constructor
     *
     * @param id the id of the vehicle
     * @param location the location of the vehicle
     */
    public Vehicle(int id, ILocation location) {
        this.id = id;
        this.service = new ArrayList<>();
        this.status = VehicleStatus.FREE;
        this.location = location;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.statistics = new Statistics();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
    }

    /**
     * This method returns the id of the vehicle
     * @return the id of the vehicle
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * This method returns the location of the vehicle
     * @return the location of the vehicle
     */
    @Override
    public ILocation getLocation() {
        return this.location;
    }

    /**
     * This method returns the destination of the vehicle
     * @return the destination of the vehicle
     */
    @Override
    public ILocation getDestination() {
        return this.destination;
    }

    /**
     * This method returns the list of services that the vehicle is currently servicing
     * @return list of services that the vehicle is currently servicing with the data on the ride
     */
    @Override
    public List<IService> getServices() {
        return this.service;
    }

    /**
     * This method returns the service that the vehicle is currently servicing
     * @return service that the vehicle is currently servicing with the data on the ride
     */
    @Override
    public IService getService() {
        return this.currentService;
    }

    /**
     * This method returns the statistics of the vehicle
     * @return the statistics of the vehicle
     */
    @Override
    public IStatistics getStatistics() {
        return this.statistics;
    }

    /**
     * This method returns the status of the vehicle
     * 
     * @return the status of the vehicle - FREE, PICKUP, SERVICE, OR RIDESHARE
     */
    public VehicleStatus getVehicleStatus(){
        return this.status;
    }

    /**
     * This method sets the company that owns the vehicle
     * @param company the company that owns the vehicle
     */
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }

    /**
     * This method picks a service, set destination to the service pickup location, and status to "pickup"
     * @param service the service that the vehicle is picking up
     * @return boolean true if ride is accepted by vehicle and false if not
     */
    @Override
    public boolean pickService(IService service) {
        // pick a service, set destination to the service pickup location, and status to "pickup"
        if(this.service.size() == 0) {
            this.service.add(service);
            this.destination = service.getPickupLocation();
            this.route = setDrivingRouteToDestination(this.location, this.destination);
            this.currentService = service;
            this.status = VehicleStatus.PICKUP;
            return true;
        }
        else if (this.service.size() < CAR_CAPACITY) {
            // If there is more than one service in the car, we will randomly decide if it
            // becomes a ride share vehicle
            if(ApplicationLibrary.rand() % 2 == 0) {
                // Ride share option is accepted
                this.service.add(service);
                this.destination = service.getPickupLocation();
                this.route = setDrivingRouteToDestination(this.location, this.destination);
                this.currentService = service;
                this.status = VehicleStatus.PICKUP;
                return true;
            }
            else {
                // Ride share option is rejected by either service
                return false;
            }
        }
        else {
            // If the capacity of the vehicle is reached, we will not accept the ride
            return false;
        }
    }

    /**
     * This method sets destination to the service drop-off location, update the driving route, set status to "service"
     */
    @Override
    public void startService() {
        this.destination = this.service.get(0).getDropoffLocation();
        this.currentService = this.service.get(0);

        for(IService s : this.service) {
            // Change drop off location to nearest one in the services list or to the only
            // service still in the vehicle
            if(ApplicationLibrary.distance(this.location, s.getDropoffLocation()) <
                    ApplicationLibrary.distance(this.location, this.destination)) {
                this.destination = s.getDropoffLocation();
                this.currentService = s;
            }
        }
        this.route = setDrivingRouteToDestination(this.location, this.destination);

        // a vehicle will be in service with one
        if (this.service.size() > 1) {
            this.status = VehicleStatus.RIDESHARE;
        } else {
            this.status = VehicleStatus.SERVICE;
        }
    }

    /**
     * This method concludes the ride
     * updates vehicle statistics, sets service to null, and status to "free" only when all
     * people have been dropped off
     */
    @Override
    public void endService() {
        // update vehicle statistics

        // The cost of the ride is only based on the origin and destination of the user that
        // requests it. This is different from the distance traveled by the vehicle in total
        // (see move())
        this.statistics.updateBilling(this.calculateCost());
        this.statistics.updateServices();

        // if the service is rated by the user, update statistics

        if (this.currentService.getStars() != 0) {
            this.statistics.updateStars(this.currentService.getStars());
            this.statistics.updateReviews();
        }

        // this service ends, so remove it from service list
        this.service.remove(currentService);

        // set service to null, and status to "free" if the vehicle is empty
        if(this.service.size() == 0) {
            this.destination = ApplicationLibrary.randomLocation(this.location);
            this.route = setDrivingRouteToDestination(this.location, this.destination);
            this.status = VehicleStatus.FREE;
        }
        else {
            // Choose the next service to drop off if the vehicle has people in it
            startService();
            // Make sure that once a vehicle has become a ride share, it remains a ride share
            // vehicle until there are no more people in it again
            this.status = VehicleStatus.RIDESHARE;
        }
    }

    /**
     * This method notifies the company that the vehicle is at the pickup location and starts the service
     */
    @Override
    public void notifyArrivalAtPickupLocation() {
        this.company.arrivedAtPickupLocation(this);
        this.startService();
    }

    /**
     * This method notifies the company that the vehicle is at the drop-off location and ends the service
     */
    @Override
    public void notifyArrivalAtDropoffLocation() {
        this.company.arrivedAtDropoffLocation(this);
        this.endService();
    }

    /**
     * Whether the vehicle's status is free or in service or in ride share or not
     * @return true if the vehicle's status is free or in service or in ride share, false
     * otherwise
     */
    @Override
    public boolean isAvailable() {
        return this.status == VehicleStatus.FREE || this.status == VehicleStatus.SERVICE ||
                this.status == VehicleStatus.RIDESHARE;
    }

    /**
     * This method moves the vehicle from one location to another
     */
    @Override
    public void move() {
        // get the next location from the driving route

        this.location = this.route.get(0);
        this.route.remove(0);

        // We must differentiate between the service's distance and the vehicle's distance.
        // Here we are calculating the vehicle's distance which is incremented by 1 for each
        // unit the vehicle moves while the vehicle is in ride share or service status or when
        // the vehicle is picking someone up while there is another service in the vehicle
        if(this.status == VehicleStatus.RIDESHARE || this.status == VehicleStatus.SERVICE ||
                (this.status == VehicleStatus.PICKUP && this.service.size() > 1)){
            this.statistics.updateDistance(1);
        }

        if (this.route.isEmpty()) {
            if (this.service == null || this.service.size() == 0) {
                // the vehicle continues its random route

                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = setDrivingRouteToDestination(this.location, this.destination);
            }
            else {
                // checks if the vehicle has arrived to a pickup or drop off location

                ILocation origin = this.currentService.getPickupLocation();
                ILocation destination = this.currentService.getDropoffLocation();

                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {

                    notifyArrivalAtPickupLocation();

                } else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {

                    notifyArrivalAtDropoffLocation();
                }
            }
        }
    }

    /**
     * This method calculates the cost of the current service based on the distance between the
     * pickup and drop-off locations
     * @return the cost of the current service
     */
    @Override
    public double calculateCost() {
        if(this.status == VehicleStatus.SERVICE)
            return this.currentService.calculateDistance();
        else {
            // 20% discount if vehicle is a ride share
            return this.currentService.calculateDistance() * 0.8;
        }
    }

    /**
     * This method returns the driving route of the vehicle
     * @return the driving route of the vehicle
     */
    @Override
    public String showDrivingRoute() {
        String s = "";

        for (ILocation l : this.route)
            s = s + l.toString() + " ";

        return s;
    }

    /**
     * This method returns the string representation of the vehicle
     * @return the string representation of the vehicle
     */
    @Override
    public String toString() {
        StringBuilder statusString = new StringBuilder();

        switch (this.status) {
            case FREE:
                statusString.append(" is free with path ").append(showDrivingRoute());
                break;
            case PICKUP:
                statusString.append(" to pickup user ").append(this.currentService.getUser().getId());

                // Even if a vehicle is going to pick someone up, we want to print that the
                // vehicle is in ride share
                if(this.service.size() > 1)
                    statusString.append(" in ride share ");

                break;
            case SERVICE:
                statusString.append(" in service ");
                break;
            default:
                statusString.append(" in ride share dropping off user ").append(
                        this.currentService.getUser().getId()).append(". ");

                for(IService s : this.service)
                    statusString.append("User ").append(s.getUser().getId()).append(" ");

                statusString.append("is/are in the vehicle");
                break;
        }

        return this.id + " at " + this.location + " driving to " + this.destination + statusString;
    }

    /**
     * This method sets the driving to the destination from the location parameter
     * @param location initial location where vehicle is driving from
     * @param destination location where vehicle is driving to
     * @return list of ILocations to represent driving route
     */
    private List<ILocation> setDrivingRouteToDestination(ILocation location, ILocation destination) {
        List<ILocation> route = new ArrayList<ILocation>();

        int x1 = location.getX();
        int y1 = location.getY();

        int x2 = destination.getX();
        int y2 = destination.getY();

        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);

        for (int i=1; i<=dx; i++) {
            x1 = (x1 < x2) ? x1 + 1 : x1 - 1;

            route.add(new Location(x1, y1));
        }

        for (int i=1; i<=dy; i++) {
            y1 = (y1 < y2) ? y1 + 1 : y1 - 1;

            route.add(new Location(x1, y1));
        }

        return route;
    }
}