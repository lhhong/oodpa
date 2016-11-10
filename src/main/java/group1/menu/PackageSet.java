package group1.menu;

import group1.commons.Money;

import java.io.Serializable;

/**
 * An Package is a set of 3 AlaCarte Food Items
 * Package items are contained in Menu
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class PackageSet extends FoodItem implements Serializable {
    /**
     * Stores an array of AlaCarte objects
     */
    private AlaCarte[] set;

    /**
     * Creates an PackageSet item and initializes its name, set, description and price
     * @param name of PackageSet item
     * @param set of PackageSet item
     * @param description of PackageSet item
     * @param price of PackageSet item
     */
    public PackageSet(String name, AlaCarte[] set, String description, int price){
        super(name,description,price);
        this.set = set;
    }

    /**
     * Prints the details of the package item
     */
    public void printDetails() {
        System.out.println(this.name + "\t-\t" + this.description);
        for(AlaCarte item:set){
            System.out.println(item.name +"\t-\t"+ item.description);
        }
        System.out.println("Price =" + Money.toString(this.price));
    }

}
