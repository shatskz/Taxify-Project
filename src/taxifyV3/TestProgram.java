package taxifyV3;

import java.util.ArrayList;
import java.util.List;

public class TestProgram {

    public static void main(String[] args) {

        // 1. Declare a list of users. Instantiate at least 15 user

        List<IUser> users = new ArrayList<IUser>();
        User user1 = new User(0, "John", "Doe", ApplicationLibrary.randomLocation());
        User user2 = new User(1, "Jane", "Doe", ApplicationLibrary.randomLocation());
        User user3 = new User(2, "John", "Smith", ApplicationLibrary.randomLocation());
        User user4 = new User(3, "Jane", "Smith", ApplicationLibrary.randomLocation());
        User user5 = new User(4, "Benjy", "Kurcz", ApplicationLibrary.randomLocation());
        User user6 = new User(5, "Zack", "Shatsky", ApplicationLibrary.randomLocation());
        User user7 = new User(6, "Morgan", "Wallen", ApplicationLibrary.randomLocation());
        User user8 = new User(7, "Ben", "Dover", ApplicationLibrary.randomLocation());
        User user9 = new User(8, "David", "Krappenschitz", ApplicationLibrary.randomLocation());
        User user10 = new User(9, "Phil", "McKraken", ApplicationLibrary.randomLocation());
        User user11 = new User(10, "Hugh", "Janus", ApplicationLibrary.randomLocation());
        User user12 = new User(11, "Eileen", "Dover", ApplicationLibrary.randomLocation());
        User user13 = new User(12, "Babe", "Ruth", ApplicationLibrary.randomLocation());
        User user14 = new User(13, "Jack", "Goff", ApplicationLibrary.randomLocation());
        User user15 = new User(14, "Ronald", "McDonald", ApplicationLibrary.randomLocation());

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);
        users.add(user12);
        users.add(user13);
        users.add(user14);
        users.add(user15);

        //  2. Declare a list of vehicles. Instantiate at least 10 vehicles (Taxis, Shuttles,
        //  Bikes, and Scooters) and place them at random locations of the city map

        List<IVehicle> vehicles = new ArrayList<IVehicle>();
        Shuttle shuttle1 = new Shuttle(1, ApplicationLibrary.randomLocation());
        Shuttle shuttle2 = new Shuttle(2, ApplicationLibrary.randomLocation());
        Shuttle shuttle3 = new Shuttle(3, ApplicationLibrary.randomLocation());
        Shuttle shuttle4 = new Shuttle(4, ApplicationLibrary.randomLocation());
        Shuttle shuttle5 = new Shuttle(5, ApplicationLibrary.randomLocation());
        Taxi taxi1 = new Taxi(1, ApplicationLibrary.randomLocation());
        Taxi taxi2 = new Taxi(2, ApplicationLibrary.randomLocation());
        Taxi taxi3 = new Taxi(3, ApplicationLibrary.randomLocation());
        Taxi taxi4 = new Taxi(4, ApplicationLibrary.randomLocation());
        Taxi taxi5 = new Taxi(5, ApplicationLibrary.randomLocation());
        Bike bike1 = new Bike(1, ApplicationLibrary.randomLocation());
        Bike bike2 = new Bike(2, ApplicationLibrary.randomLocation());
        Scooter scooter1 = new Scooter(1, ApplicationLibrary.randomLocation());
        Scooter scooter2 = new Scooter(2, ApplicationLibrary.randomLocation());

        vehicles.add(taxi1);
        vehicles.add(shuttle1);
        vehicles.add(bike1);
        vehicles.add(scooter1);
        vehicles.add(taxi2);
        vehicles.add(shuttle2);
        vehicles.add(bike2);
        vehicles.add(scooter2);
        vehicles.add(taxi3);
        vehicles.add(shuttle3);
        vehicles.add(taxi4);
        vehicles.add(shuttle4);
        vehicles.add(taxi5);
        vehicles.add(shuttle5);


        // 3. Instantiate the taxi company and the application simulator. Add the application
        // simulator as an observer of the taxi company

        TaxiCompany taxify = new TaxiCompany("Taxify", users, vehicles);
        ApplicationSimulator application = new ApplicationSimulator(taxify, users, vehicles);

        taxify.addObserver(application);

        /*
            4. Start the simulation

            a. Show the status of the application
            b. Simulate at least 5 requests of service
            c. Run the simulation while there are vehicles in a service
         */

        application.show(); // Show the status of the application
        application.update(); // Update the state of the application
        application.requestService(); // Simulate a request of service (randomly, to avoid request a service each iteration)
        for(int i = 0; i < 100; i++) {
            if(i%5 == 0){ // only show the status of the application each 5 iterations so the output isn't too long
                application.show();
            }
            application.show();
            int random = ApplicationLibrary.rand(5);
            if(random == 1){ // requests a service 1 in 5 iterations
                application.requestService();
            }
            application.update(); // Update the state of the application
        }

        /*
            d. Finally, show the statistics of the simulation as shown below

            Taxify statistics

            Shuttle  1  8 services  57 km.  83 eur.  4 reviews 2.25 stars
            Taxi     2  6 services  59 km. 118 eur.  3 reviews 3.33 stars
            Shuttle  3  4 services  24 km.  35 eur.  1 reviews 3.0  stars
            Taxi     4  3 services  18 km.  36 eur.  1 reviews 4.0  stars
            Taxi     5  1 services   6 km.  12 eur.  0 reviews 0.0  stars
            Shuttle  6  1 services   7 km.  10 eur.  1 reviews 4.0  stars
            Taxi     7  1 services   3 km.   6 eur.  0 reviews 0.0  stars
            Shuttle  8  5 services  48 km.  71 eur.  3 reviews 3.33 stars
            Shuttle  9  5 services  35 km.  51 eur.  2 reviews 2.5  stars
            Shuttle 10  8 services  60 km.  87 eur.  4 reviews 3.5  stars

         */

        application.showStatistics();

    }

}