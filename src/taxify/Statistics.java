// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/8/23
// Description: This class keeps track of the statistics for each vehicle (eg, the number of
// services provided, reviews received, rating, distance traveled, and billing generated)

package taxify;

public class Statistics implements IStatistics {
    private int services;
    private int reviews;
    private double stars;
    private int distance;
    private int billing;

    /**
     * Class constructor
     */
    public Statistics(int services, int reviews, double stars, int distance, int billing) {
        this.services = services;
        this.reviews = reviews;
        this.stars = stars;
        this.distance = distance;
        this.billing = billing;
    }

    /**
     * This method returns the services attribute.
     *
     * @return      integer representing the number of services provided by this vehicle
     */
    @Override
    public int getServices() {
        return this.services;
    }

    /**
     * This method returns the reviews attribute.
     *
     * @return      integer representing the number reviews provided for this vehicle
     */
    @Override
    public int getReviews() {
        return this.reviews;
    }

    /**
     * This method returns the stars attribute.
     *
     * @return      double representing the rating of a driver
     */
    @Override
    public double getStars() {
        return this.stars;
    }

    /**
     * This method returns the distance attribute.
     *
     * @return      integer representing the distance traveled
     */
    @Override
    public int getDistance() {
        return this.distance;
    }

    /**
     * This method returns the billing attribute.
     *
     * @return      integer representing the total billing in euros
     */
    @Override
    public int getBilling() {
        return this.billing;
    }

    /**
     * This method increments the services attribute by 1.
     */
    @Override
    public void updateServices() {
        services++;
    }

    /**
     * This method increments the reviews attribute by 1.
     */
    @Override
    public void updateReviews() {
        reviews++;
    }

    /**
     * This method updates the stars attribute by adding the method's argument to the total
     * number of stars given and dividing it by the number of reviews to get the average
     * number of stars
     *
     * @param  stars int that will be the new value of the stars field
     */
    @Override
    public void updateStars(int stars) {
        this.stars = ((this.stars * this.reviews) + stars) / (this.reviews + 1);
    }

    /**
     * This method updates the distance attribute by adding an amount equal to the
     * method's argument
     *
     * @param  distance  integer that will be the new value of the distance field
     */
    @Override
    public void updateDistance(int distance) {
        this.distance += distance;
    }

    /**
     * This method updates the billing attribute by adding an amount equal to the method's
     * argument
     *
     * @param  billing  integer that will be the new value of the billing field
     */
    @Override
    public void updateBilling(int billing) {
        this.billing += billing;
    }
}
