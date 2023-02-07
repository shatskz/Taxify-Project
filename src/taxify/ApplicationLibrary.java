package taxify;

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
     * This method generates a random number up to (but not including) the parameter max
     *
     * @param  max  integer that is 1 above the maximum random number that will be returned
     * @return      random integer
     */
    public static int distance(ILocation a, ILocation b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    public static ILocation randomLocation() {
        return new Location(rand(width), rand(height));
    }

    public static ILocation randomLocation(ILocation location) {
        ILocation destination;

        do {

            destination = new Location(rand(width), rand(height));

        } while (distance(location, destination) < 3);

        return destination;
    }
}
