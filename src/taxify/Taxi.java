// Zack Shatsky and Benjy Kurcz
// Taxify Project
// Last Modified: 2/23/23
// Description: This file defines the methods for the Taxi class. It extends Vehicle because
// it alters the behavior of some of its methods

package taxify;

public class Taxi extends Vehicle {

    public Taxi(int id, ILocation location) {
        super(id, location);
    }

    @Override
    public int calculateCost() {
        return super.calculateCost() * 2;
    }

    @Override
    public String toString() {
        return "Taxi    " + super.toString();
    }
}