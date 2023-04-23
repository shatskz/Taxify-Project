// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/8/23
// Description: This file provides the interface for the Service class

package taxifyV3;

public interface IService {

    public IUser getUser();
    public ILocation getPickupLocation();
    public ILocation getDropoffLocation();
    public int getStars();
    public ILocation getUserLocation();
    public void setStars(int stars);
    public int calculateDistance();
    public String toString();

}
