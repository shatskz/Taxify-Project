// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This file defines some of the methods and attributes for the Vehicle class. It
// is an abstract class because the Taxi and Shuttle classes inherit from Vehicle and
// implement the remaining methods

package taxify;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle implements IVehicle {

    private int id;
    private ITaxiCompany company;
    private IService service;
    private VehicleStatus status;
    private ILocation location;
    private ILocation destination;
    private IStatistics statistics;
    private List<ILocation> route;

    /**
     * This method calculates the cost of a service based on the distance between the pickup and drop-off locations
     * @param id the id of the vehicle
     * @param location the location of the vehicle
     */
    public Vehicle(int id, ILocation location) {
        this.id = id;
        this.service = null;
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
     * This method returns the service that the vehicle is currently servicing
     * @return service that the vehicle is currently servicing with the data on the ride
     */
    @Override
    public IService getService() {
        return this.service;
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
     */
    @Override
    public void pickService(IService service) {
        // pick a service, set destination to the service pickup location, and status to "pickup"

        this.service = service;
        this.destination = service.getPickupLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.PICKUP;
    }

    /**
     * This method sets destination to the service drop-off location, update the driving route, set status to "service"
     */
    @Override
    public void startService() {
        this.destination = this.service.getDropoffLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    }

    /**
     * This method concludes the ride
     * updates vehicle statistics, sets service to null, and status to "free"
     */
    @Override
    public void endService() {
        // update vehicle statistics

        this.statistics.updateBilling(this.calculateCost());
        this.statistics.updateDistance(this.service.calculateDistance());
        this.statistics.updateServices();

        // if the service is rated by the user, update statistics

        if (this.service.getStars() != 0) {
            this.statistics.updateStars(this.service.getStars());
            this.statistics.updateReviews();
        }

        // set service to null, and status to "free"

        this.service = null;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.FREE;
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
     * Whether the vehicle's status is free or not
     * @return true if the vehicle's status is free, false otherwise
     */
    @Override
    public boolean isFree() {
        return this.status == VehicleStatus.FREE;
    }

    /**
     * This method moves the vehicle from one location to another
     */
    @Override
    public void move() {
        // get the next location from the driving route

        this.location = this.route.get(0);
        this.route.remove(0);

        if (this.route.isEmpty()) {
            if (this.service == null) {
                // the vehicle continues its random route

                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = setDrivingRouteToDestination(this.location, this.destination);
            }
            else {
                // checks if the vehicle has arrived to a pickup or drop off location

                ILocation origin = this.service.getPickupLocation();
                ILocation destination = this.service.getDropoffLocation();

                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {

                    notifyArrivalAtPickupLocation();

                } else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {

                    notifyArrivalAtDropoffLocation();

                }
            }
        }
    }

    /**
     * This method calculates the cost of a service based on the distance between the pickup and drop-off locations
     * @return the cost of the service
     */
    @Override
    public int calculateCost() {
        return this.service.calculateDistance();
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
        return this.id + " at " + this.location + " driving to " + this.destination +
                ((this.status == VehicleStatus.FREE) ? " is free with path " + showDrivingRoute(): ((this.status == VehicleStatus.PICKUP) ? " to pickup user " +
                        this.service.getUser().getId() : " in service "));
    }

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