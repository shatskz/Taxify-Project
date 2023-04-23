// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/8/23
// Description: This file provides the interface for the Statistics class

package taxifyV3;

public interface IStatistics {

    public int getServices();
    public int getReviews();
    public double getStars();
    public int getDistance();
    public int getBilling();
    public void updateServices();
    public void updateReviews();
    public void updateStars(int stars);
    public void updateDistance(int distance);
    public void updateBilling(double billing);
}
