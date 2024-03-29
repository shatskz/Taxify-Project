// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This file provides the interface for the Vehicle class

package taxify;

public interface IVehicle {

    public int getId();
    public ILocation getLocation();
    public ILocation getDestination();
    public IService getService();
    public IStatistics getStatistics();
    public void setCompany(ITaxiCompany company);
    public void pickService(IService service);
    public void startService();
    public void endService();
    public void notifyArrivalAtPickupLocation();
    public void notifyArrivalAtDropoffLocation();
    public boolean isFree();
    public void move();
    public int calculateCost();
    public String showDrivingRoute();
    public String toString();

}