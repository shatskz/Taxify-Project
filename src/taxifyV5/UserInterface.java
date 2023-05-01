package taxifyV5;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.io.File;

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
    private JPanel panel;
    private JLabel[][] labels;
    private int ICON_SIZE = 35;

    // initialize the icons
    private ImageIcon greenCar = new ImageIcon("./Taxify-Project/src/taxifyV5/greenCar.png");
    private ImageIcon yellowCar = new ImageIcon("./Taxify-Project/src/taxifyV5/yellowCar.png");
    private ImageIcon redCar = new ImageIcon("./Taxify-Project/src/taxifyV5/redCar.png");
    private ImageIcon greenBike = new ImageIcon("./Taxify-Project/src/taxifyV5/greenBike.png");
    private ImageIcon yellowBike = new ImageIcon("./Taxify-Project/src/taxifyV5/yellowBike.png");
    private ImageIcon redBike = new ImageIcon("./Taxify-Project/src/taxifyV5/redBike.png");
    private ImageIcon greenScooter = new ImageIcon("./Taxify-Project/src/taxifyV5/greenScooter.png");
    private ImageIcon yellowScooter = new ImageIcon("./Taxify-Project/src/taxifyV5/yellowScooter.png");
    private ImageIcon redScooter = new ImageIcon("./Taxify-Project/src/taxifyV5/redScooter.png");
    private ImageIcon street = new ImageIcon("./Taxify-Project/src/taxifyV5/street.png");


    public UserInterface(ITaxiCompany taxiCompany, List<IUser> users, List<IVehicle> vehicles, DefaultListModel<String> vehicleListModel) {
        this.company = taxiCompany;
        this.users = users;
        this.vehicles = vehicles;
        this.vehicleListModel = vehicleListModel;

        // Set up the main JFrame
        setTitle("Taxify");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new JPanel with a grid layout

        this.panel = new JPanel(new GridLayout(10,10, 5, 5));
        this.panel.setBackground(Color.DARK_GRAY);
        // create a 2d 10 by 10 array of JLabels
        labels = new JLabel[10][10];

        // change the sizes of the icons
        greenCar = new ImageIcon(greenCar.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        yellowCar = new ImageIcon(yellowCar.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        redCar = new ImageIcon(redCar.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        greenBike = new ImageIcon(greenBike.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        yellowBike = new ImageIcon(yellowBike.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        redBike = new ImageIcon(redBike.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        greenScooter = new ImageIcon(greenScooter.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        yellowScooter = new ImageIcon(yellowScooter.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        redScooter = new ImageIcon(redScooter.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        street = new ImageIcon(street.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT));
        
        for(int y = 9; y >= 0; y--){
            for(int x = 0; x < 10; x++){
                labels[x][y] = new JLabel(" ");
                labels[x][y].setForeground(Color.WHITE);
                labels[x][y].setFont(new Font("Serif", Font.BOLD, 8));
                labels[x][y].setHorizontalTextPosition(SwingConstants.CENTER);
                labels[x][y].setVerticalTextPosition(SwingConstants.BOTTOM);
                this.panel.add(labels[x][y]);
            }
        }
        
        getContentPane().add(this.panel, BorderLayout.NORTH);

        // Create a JList with the default list model
        JList<String> vehicleList = new JList<String>(vehicleListModel);

        // Create a scroll pane to hold the vehicle list
        JScrollPane scrollPane = new JScrollPane(vehicleList);

        // Add the scroll pane to the main JFrame
        add(scrollPane);

        updateGUI();

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
            updateGUI();
        });
    }

    public void updateGUI() {
        for(int y = 9; y >= 0; y--){
            for(int x = 0; x < 10; x++){
                labels[x][y].setText(" ");
                labels[x][y].setIcon(street);
                for(IVehicle vehicle : vehicles) {
                    if(vehicle.getLocation().getX() == x && vehicle.getLocation().getY() == y){
                        labels[x][y].setText(vehicle.getClass().getSimpleName() + " " + vehicle.getId());
                        if(vehicle.getClass().getSimpleName().equals("Taxi") || vehicle.getClass().getSimpleName().equals("Shuttle")){
                            switch(vehicle.getVehicleStatus()){
                                case FREE:
                                    labels[x][y].setIcon(greenCar);
                                    break;
                                case PICKUP:
                                    labels[x][y].setIcon(yellowCar);
                                    break;
                                default : // SERVICE OR RIDESHARE
                                    labels[x][y].setIcon(redCar);
                                    break;
                            }
                        } if(vehicle.getClass().getSimpleName().equals("Bike")){
                            switch(vehicle.getMobilityStatus()){
                                case FREE:
                                    labels[x][y].setIcon(greenBike);
                                    break;
                                case BOOKED:
                                    labels[x][y].setIcon(yellowBike);
                                    break;
                                default : // SERVICE
                                    labels[x][y].setIcon(redBike);
                                    break;
                            }
                        } if(vehicle.getClass().getSimpleName().equals("Scooter")){
                            switch(vehicle.getMobilityStatus()){
                                case FREE:
                                    labels[x][y].setIcon(greenScooter);
                                    break;
                                case BOOKED:
                                    labels[x][y].setIcon(yellowScooter);
                                    break;
                                default : // SERVICE
                                    labels[x][y].setIcon(redScooter);
                                    break;
                            }
                        }
                    }
                }
            }
        }
        revalidate();
        repaint();
    }
    
    public void showStats(){
        this.reset();
        updateObserver("\n" + this.company.getName() + " statistics \n");

        // build a string with all of the statistics
        String stats = "";

        for (IVehicle vehicle : this.vehicles) {
            stats = String.format("%-7s %d    %d services     %d km.   %d eur.    %d reviews %.2f stars\n", 
            vehicle.getClass().getSimpleName(), vehicle.getId(), vehicle.getStatistics().getServices(), 
            vehicle.getStatistics().getDistance(), vehicle.getStatistics().getBilling(), vehicle.getStatistics().getReviews(), vehicle.getStatistics().getStars());
            updateObserver(stats);
        }

    }

}
