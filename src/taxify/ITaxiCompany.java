// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/8/23
// Description: This file provides the interface for the TaxiCompany class

package taxify;

public interface ITaxiCompany {

    public String getName();
    public int getTotalServices();
    public boolean requestService(int user);

    // more methods will be declared in upcoming sprints

}
