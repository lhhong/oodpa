package group1.menu;

import java.io.Serializable;

/**
 * A food item that people will consume in their meal.
 * Food items are contained in Menu
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public abstract class FoodItem implements Serializable{
    /**
     * The name of the food item.
     */
    protected String name;
    /**
     * The description of the food item
     */
    protected String description;
    /**
     * The price of the food item
     */
    protected int price;

    /**
     * Creates a new Food Item with all possible attributes
     * @param name of the food item
     * @param description of the food item
     * @param price of the food item
     */
    public FoodItem(String name, String description, int price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Prints the details of the Food item
     */
    public abstract void printDetails();

    /**
     * Gets the name of the Food item
     * @return name
     */
    public String getName(){return this.name;}

    /**
     * Gets the description of the Food item
     * @return description
     */
    public String getDescription(){return this.description;}

    /**
     * Gets the price of the Food item
     * @return price
     */
    public int getPrice(){return this.price;}

}