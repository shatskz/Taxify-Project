package taxifyV4;

/**
 * Interface for Statistics
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
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
