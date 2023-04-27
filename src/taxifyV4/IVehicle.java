package taxifyV4;

import java.util.List;

/**
 * Interface for Vehicle
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public interface IVehicle {

    public int getId();
    public ILocation getLocation();
    public ILocation getDestination();
    public List<IService> getServices();
    public IService getService();
    public IStatistics getStatistics();
    public void setCompany(ITaxiCompany company);
    public boolean pickService(IService service);
    public void startService();
    public void endService();
    public void notifyArrivalAtPickupLocation();
    public void notifyArrivalAtDropoffLocation();
    public boolean isAvailable();
    public void move();
    public double calculateCost();
    public String showDrivingRoute();
    public String toString();

}