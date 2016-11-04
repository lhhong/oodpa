package group1.menu;

/**
 * Created by tzeyangng on 4/11/16.
 */
public abstract class FoodItem {
    protected String name;
    protected String description;
    protected float price;

    public FoodItem(String name, String description, float price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public abstract void getDetails();
}