// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/8/23
// Description: This file provides the interface for the User class

package taxify;

public interface IUser {

    public int getId();
    public String getFirstName();
    public String getLastName();
    public boolean getService();
    public void setService(boolean service);
    public void requestService();
    public void rateService(IService service);
    public String toString();

}
