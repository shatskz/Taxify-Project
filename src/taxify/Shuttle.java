// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This file defines the methods for the Shuttle class. It extends Vehicle because
// it alters the behavior of some of its methods

package taxify;

public class Shuttle extends Vehicle {

    public Shuttle(int id, ILocation location) {
        super(id, location);
    }

    @Override
    public int calculateCost() {
        return (int) (super.calculateCost() * 1.5);
    }

    @Override
    public String toString() {
        return "Shuttle " + super.toString();
    }
}