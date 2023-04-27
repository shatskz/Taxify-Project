package taxifyV4;

import javax.swing.*;
import java.util.List;

/**
 * Provides the infrastructure for the user interface
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public class UserInterface extends JFrame implements IObserver {

    private ITaxiCompany company;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private DefaultListModel<String> vehicleListModel;

    public UserInterface(ITaxiCompany taxiCompany, List<IUser> users, List<IVehicle> vehicles, DefaultListModel<String> vehicleListModel) {
        this.company = taxiCompany;
        this.users = users;
        this.vehicles = vehicles;
        this.vehicleListModel = vehicleListModel;

        // Set up the main JFrame
        setTitle("Taxify");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JList with the default list model
        JList<String> vehicleList = new JList<String>(vehicleListModel);

        // Create a scroll pane to hold the vehicle list
        JScrollPane scrollPane = new JScrollPane(vehicleList);

        // Add the scroll pane to the main JFrame
        add(scrollPane);

        // Make the JFrame visible
        setVisible(true);
    }

     /**
     * Updates observer by printing the message to the User Interface
     */
    @Override
    public void updateObserver(String message) {
                // Update the GUI with new data
                SwingUtilities.invokeLater(() -> {
                    vehicleListModel.addElement(message);
                });
    }

    public void reset(){
        SwingUtilities.invokeLater(() -> {
            vehicleListModel.clear();
        });
    }

    public void updateVehicles(List<IVehicle> vehicles) {
        this.vehicles = vehicles;
        SwingUtilities.invokeLater(() -> {
            vehicleListModel.clear();
            for(IVehicle vehicle : vehicles){
                vehicleListModel.addElement(vehicle.toString());
            }
        });
    }

    /*
    public void gridLayout(){
        frame = new JFrame();
        frame.setTitle("Grid Layout");

        mainFrame.setLayout(new GridLayout(3, 2));
    }
    */

    /*
    public void showStats(){
        this.reset();
        updateObserver("\n" + this.company.getName() + " statistics \n");

        // build a string with all of the statistics

        for (IVehicle vehicle : this.vehicles) {
            updateObserver("%-7s %d    %d services     %d km.   %d eur.    %d reviews %.2f stars\n", 
            vehicle.getClass().getSimpleName(), vehicle.getId(), vehicle.getStatistics().getServices(), 
            vehicle.getStatistics().getDistance(), vehicle.getStatistics().getBilling(), vehicle.getStatistics().getReviews(), vehicle.getStatistics().getStars());
        }
    }
    */
}
