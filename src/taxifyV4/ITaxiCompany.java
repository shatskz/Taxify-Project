package taxifyV4;

/**
 * Interface for TaxiCompany
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public interface ITaxiCompany {

    public String getName();
    public int getTotalServices();
    public boolean requestService(int user);
    public void arrivedAtPickupLocation(IVehicle vehicle);
    public void arrivedAtDropoffLocation(IVehicle vehicle);
}
