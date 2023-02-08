// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/8/23
// Description: This class represents the location of a pickup or drop off spot by
// holding its x and y coordinates

package taxify;

public class Location implements ILocation {
    private int x;
    private int y;

    /**
     * Class constructor
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns the x attribute.
     *
     * @return      integer representing the horizontal coordinate of the location
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * This method returns the y attribute.
     *
     * @return      integer representing the vertical coordinate of the location
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * This method returns the location's coordinates in a String in the form of '(x,y)'
     *
     * @return      String representing the location's coordinates
     */
    @Override
    public String toString() {
        return String.format("(%d,%d)", this.x, this.y);
    }
}
