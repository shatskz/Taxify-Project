// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/8/23
// Description: This class provides functions to generate random locations and calculate
// distances between locations

package taxifyV4;

import java.util.Random;

public class ApplicationLibrary {
    private static final int width = 10;
    private static final int height = 10;

    /**
     * This method generates a random number.
     *
     * @return      random integer
     */
    public static int rand() {
        Random random = new Random();

        return random.nextInt(9767);
    }

    /**
     * This method generates a random number up to (but not including) the parameter max
     *
     * @param  max  integer that is 1 above the maximum random number that will be returned
     * @return      random integer
     */
    public static int rand(int max) {
        Random random = new Random();

        return random.nextInt(9767) % max;
    }

    /**
     * This method calculates the Manhattan distance between two locations
     *
     * @param  a  ILocation object whose distance we will calculate from b
     * @param  b  ILocation object whose distance we will calculate from a
     * @return      integer that represents the distance between the points
     */
    public static int distance(ILocation a, ILocation b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    /**
     * This overloaded method returns a random location within the bounds of the grid
     *
     * @return      random ILocation object
     */
    public static ILocation randomLocation() {
        return new Location(rand(width), rand(height));
    }

    /**
     * This overloaded method returns a random location that is greater than or equal to 3
     * units away the location parameter
     *
     * @param location  ILocation object that will be used to calculate a random point far
     *                  enough away (greater than or equal to 3 units away)
     * @return      random ILocation object
     */
    public static ILocation randomLocation(ILocation location) {
        ILocation destination;

        do {

            destination = new Location(rand(width), rand(height));

        } while (distance(location, destination) < 3);

        return destination;
    }
}
