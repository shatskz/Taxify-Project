package taxify;

import java.util.ArrayList;
import java.util.List;

public class TestProgram {

    public static void main(String[] args) {

        /*

         1. Declare a list of users. Instantiate at least 15 users

            List<IUser> users = new ArrayList<IUser>()

         2. Declare a list of vehicles. Instantiate at least 10 vehicles (Taxis and Shuttles) and place them at random locations of the city map

            List<IVehicle> vehicles = new ArrayList<IVehicle>();

         3. Instantiate the taxi company and the application simulator. Ad the application simulator as an observer of the taxy company

            TaxiCompany taxify = new TaxiCompany("Taxify", users, vehicles);
            ApplicationSimulator application = new ApplicationSimulator(taxify, users, vehicles);

            taxify.addObserver(application);

         4. Start the simulation

            a. Show the status of the application
            b. Simulate at least 5 requests of service
            c. Run the simulation while there are vehicles in a service

               Show the status of the application
               Update the state of the application
               Simulate a request of service (randomly, to avoid request a service each iteration)

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

    }

}