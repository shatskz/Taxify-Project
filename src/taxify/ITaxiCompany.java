// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This file provides the interface for the TaxiCompany class

package taxify;

public interface ITaxiCompany {

    public String getName();
    public int getTotalServices();
    public boolean requestService(int user);
    public void arrivedAtPickupLocation(IVehicle vehicle);
    public void arrivedAtDropoffLocation(IVehicle vehicle);

    // more methods will be declared in upcoming sprints

}
