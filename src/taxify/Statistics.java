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
     * @return      integer representing the number services provided
     */
    @Override
    public int getServices() {
        return this.services;
    }

    /**
     * This method returns the reviews attribute.
     *
     * @return      integer representing the number reviews provided
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
     * @return      integer representing the total billing
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
     * This method updates the stars attribute by setting it equal to the method's argument
     *
     * @param  stars  integer that will be the new value of the stars field
     */
    @Override
    public void updateStars(int stars) {
        this.stars = stars;
    }

    /**
     * This method updates the distance attribute by setting it equal to the method's argument
     *
     * @param  distance  integer that will be the new value of the distance field
     */
    @Override
    public void updateDistance(int distance) {
        this.distance = distance;
    }

    /**
     * This method updates the billing attribute by setting it equal to the method's argument
     *
     * @param  billing  integer that will be the new value of the billing field
     */
    @Override
    public void updateBilling(int billing) {
        this.billing = billing;
    }
}
