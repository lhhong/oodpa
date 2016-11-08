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
     * Gets the description of the Food item
     */
    public abstract void getDetails();

    public String getName(){return this.name;}
    public String getDescription(){return this.description;}
    public int getPrice(){return this.price;}

}