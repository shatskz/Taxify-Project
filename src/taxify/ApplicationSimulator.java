package taxify;

import java.util.List;

public class ApplicationSimulator implements IApplicationSimulator, IObserver {
    private ITaxiCompany company;
    private List<IUser> users;
    private List<IVehicle> vehicles;

    public ApplicationSimulator(ITaxiCompany company, List<IUser> users, List<IVehicle> vehicles) {
        this.company = company;
        this.users = users;
        this.vehicles = vehicles;
    }

    @Override
    public void show() {
        // shows the status of the vehicles

        System.out.println("\n" + this.company.getName() + " status \n");

        for (IVehicle vehicle : this.vehicles) {
            System.out.println(vehicle.toString());
        }
    }

    @Override
    public void showStatistics() {
        // shows the statistics of the company

        System.out.println("\n" + this.company.getName() + " statistics \n");

        for (IVehicle vehicle : this.vehicles) {
            System.out.printf("%-7s %d    %d services     %d km.   %d eur.    %d reviews %.2f stars\n", 
            vehicle.getClass().getSimpleName(), vehicle.getId(), vehicle.getStatistics().getServices(), 
            vehicle.getStatistics().getDistance(), vehicle.getStatistics().getBilling(), vehicle.getStatistics().getReviews(), vehicle.getStatistics().getStars());
        }
    }

    @Override
    public void update() {
        // moves the vehicles to their next location

        for (int i=0; i<this.vehicles.size(); i++) {
            this.vehicles.get(i).move();
        }
    }

    @Override
    public void requestService() {
        // finds a "free" user and requests a service to the Taxi Company

        for (int i=0; i<this.users.size(); i++) {
            if(!this.users.get(i).getService()) {
                this.company.requestService(i);
                return; // Exits method if we find a "free" user
            }
        }
    }

    @Override
    public int getTotalServices() {
        return this.company.getTotalServices();
    }

    @Override
    public void updateObserver(String message) {
        System.out.println(message);
    }
}