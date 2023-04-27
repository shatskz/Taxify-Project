package taxifyV4;

/**
 * Interface for Service
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public interface IService {

    public IUser getUser();
    public ILocation getPickupLocation();
    public ILocation getDropoffLocation();
    public int getStars();
    public void setStars(int stars);
    public int calculateDistance();
    public String toString();

}
