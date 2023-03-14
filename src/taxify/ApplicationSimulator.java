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

        for (int i=0; i<this.vehicles.size(); i++) {
            System.out.println(this.vehicles.get(i).toString());
        }
    }

    @Override
    public void showStatistics() {
        // shows the statistics of the company
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