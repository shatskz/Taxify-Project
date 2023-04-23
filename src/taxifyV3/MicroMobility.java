package taxifyV3;

import taxify.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class MicroMobility implements IVehicle {

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
     * This method picks a service, has the user walk to the vehicle, and sets status to "booked"
     * @param service the service that the user is booking
     * @return boolean to represent if service is accepted (always true for micromobility)
     */
    @Override
    public boolean pickService(IService service) {
        // pick a service, set destination to the vehicle's current location (user will walk
        // from their location to vehicle's location, and status to "booked"

        this.service = service;
        this.destination = service.getPickupLocation();
        // Since a micromobility can't move itself, the user has to walk to it and this time
        // delay will be given by setting the driving route from the user's current location
        // to the current location of the vehicle
        this.route = setDrivingRouteToDestination(this.service.getUser().getUserLocation(),
                this.location);
        this.status = MobilityStatus.BOOKED;

        // If the micromobility vehicle is free, the vehicle can't reject the service
        return true;
    }

    /**
     * This method sets destination to the service drop-off location, update the driving route,
     * set status to "service"
     */
    @Override
    public void startService() {
        this.destination = this.service.getDropoffLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = MobilityStatus.SERVICE;
    }

    /**
     * This method concludes the ride updates vehicle statistics; sets service, destination,
     * and route to null; and status to "free"
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
        // Micromobility vehicle does not have the ability to "wander" around without a person
        // riding it, so destination and route are set to null
        this.destination = null;
        this.route = null;
        this.status = MobilityStatus.FREE;
    }

    /**
     * This method notifies the company that the user is at the pickup location and starts the
     * service
     */
    @Override
    public void notifyArrivalAtPickupLocation() {
        this.company.arrivedAtPickupLocation(this);
        this.startService();
    }

    /**
     * This method notifies the company that the user rode the vehicle to the drop-off location
     * and ends the service
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
    public boolean isAvailable() {
        return this.status == MobilityStatus.FREE;
    }

    @Override
    public void move() {
        if(this.service != null) {
            // get the next location from the driving route only if there's

            // Only update vehicle's location when ride is in service (vehicle doesn't move when
            // booked)
            if (this.status == MobilityStatus.SERVICE)
                this.location = this.route.get(0);

            // Always update user's location (either when walking to pickup location or when ride
            // is in service)
            this.service.getUser().updateUserLocation(this.route.get(0));


            this.route.remove(0);

            if (this.route.isEmpty()) {
                    // checks if the user has arrived at pickup or drop off location

                    ILocation origin = this.service.getPickupLocation();
                    ILocation destination = this.service.getDropoffLocation();

                    // Notify arrival at pickup location if user arrives at pickup location of
                    // service (wherever the micromobility vehicle is located)
                    if (this.service.getUser().getUserLocation().getX() == origin.getX() &&
                            this.service.getUser().getUserLocation().getY() == origin.getY()) {

                        notifyArrivalAtPickupLocation();

                    } else if (this.location.getX() == destination.getX() && this.location.getY()
                            == destination.getY()) {

                        notifyArrivalAtDropoffLocation();

                    }
            }
        }
    }

    /**
     * This method calculates the cost of a service based on the distance between the pickup
     * and drop-off locations. We will multiply the cost by 0.75 and 0.5 for scooters and
     * bikes, respectively, in the subclasses
     * @return the cost of the service
     */
    @Override
    public double calculateCost() { return this.service.calculateDistance(); }

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
                statusString.append(" is free ");
                return this.id + " at " + this.location + statusString;
            case BOOKED:
                statusString.append(" has been booked by user ")
                        .append(this.service.getUser().getId()).append(" who is at ")
                        .append(this.service.getUser().getUserLocation());
                return this.id + " at " + this.location + statusString;
            default:
                statusString.append(" in service with user ").append(this.service.getUser().getId());
                return this.id + " at " + this.location + " driving to " + this.destination + statusString;
        }
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
