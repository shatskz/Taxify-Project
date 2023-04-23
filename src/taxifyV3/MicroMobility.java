package taxifyV3;

import taxify.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

public class MicroMobility implements IVehicle {

    private int id;
    private ITaxiCompany company;
    private MobilityStatus status;
    private IService service;
    private ILocation location;
    private ILocation destination;
    private IStatistics statistics;
    private List<ILocation> route;

    /**
     * This method calculates the cost of a service based on the distance between the pickup and drop-off locations
     * @param id the id of the vehicle
     * @param location the location of the vehicle
     */
    public MicroMobility(int id, ILocation location) {
        this.id = id;
        this.service = null;
        this.status = MobilityStatus.FREE;
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
     * This method returns the services of the vehicle, but should not be used for
     * micromobility vehicles
     * @return the services of the vehicle (always null for micromobility vehicles)
     */
    @Override
    public List<IService> getServices() {
        return null;
    }


    /**
     * This method picks a service, set destination to the service pickup location, and status
     * to "booked"
     * @param service the service that the user is booking
     * @return boolean to represent if service is accepted (always true for micromobility)
     */
    @Override
    public boolean pickService(IService service) {
        // pick a service, set destination to the vehicle's current location (user will walk
        // from their location to vehicle's location, and status to "booked"

        this.service = service;
        this.destination = service.getPickupLocation();
        this.route = setDrivingRouteToDestination(this.service.getUserLocation(), this.location);
        this.status = MobilityStatus.BOOKED;

        return true;
    }

    @Override
    public void startService() {

    }

    @Override
    public void endService() {

    }

    @Override
    public void notifyArrivalAtPickupLocation() {

    }

    @Override
    public void notifyArrivalAtDropoffLocation() {

    }

    @Override
    public boolean isFreeOrInService() {
        return false;
    }

    @Override
    public void move() {

    }

    @Override
    public double calculateCost() {
        return 0;
    }

    @Override
    public String showDrivingRoute() {
        return null;
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
