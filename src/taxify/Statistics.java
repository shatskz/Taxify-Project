package taxify;

public class Statistics implements IStatistics {
    private int services;
    private int reviews;
    private double stars;
    private int distance;
    private int billing;

    public Statistics(int services, int reviews, double stars, int distance, int billing) {
        this.services = services;
        this.reviews = reviews;
        this.stars = stars;
        this.distance = distance;
        this.billing = billing;
    }

    @Override
    public int getServices() {
        return this.services;
    }

    @Override
    public int getReviews() {
        return this.reviews;
    }

    @Override
    public double getStars() {
        return this.stars;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    @Override
    public int getBilling() {
        return this.billing;
    }

    @Override
    public void updateServices() {
        services++;
    }

    @Override
    public void updateReviews() {
        reviews++;
    }

    @Override
    public void updateStars(int stars) {
        this.stars = stars;
    }

    @Override
    public void updateDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public void updateBilling(int billing) {
        this.billing = billing;
    }
}
