package group1.menu;

import group1.commons.Money;

import java.io.Serializable;

/**
 * An Ala Carte item that people will consume in their meal.
 * Ala Carte items are contained in Menu
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class AlaCarte extends FoodItem implements Serializable {
    /**
     * The AlaCarte type
     */
    private char type;

    /**
     * Creates an AlaCarte item and initializes its name, type, description and price
     * @param name of AlaCarte item
     * @param type of AlaCarte item
     * @param description of AlaCarte item
     * @param price of AlaCarte item
     */
    public AlaCarte(String name, char type, String description, int price){
        super(name,description,price);
        this.type = type;
    }

    /**
     * Prints the details of the Food item
     */
    public void printDetails() {
        System.out.println(this.name +"\t"+ this.description +"\t"+ Money.toString(this.price));
    }

    /**
     * returns the type
     * @return type
     */
    char getType(){
        return type;
    }

}
