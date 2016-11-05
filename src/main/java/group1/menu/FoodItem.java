package group1.menu;

import java.io.Serializable;

/**
 * Created by tzeyangng on 4/11/16.
 */
public abstract class FoodItem implements Serializable{
    protected String name;
    protected String description;
    protected int price;

    public FoodItem(String name, String description, int price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public abstract void getDetails();
}