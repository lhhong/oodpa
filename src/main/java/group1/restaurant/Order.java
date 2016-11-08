package group1.restaurant;

import group1.menu.FoodItem;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Orders created for the Restaurant
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class Order implements Serializable {
    /**
     * Staff for the order
     */
    private Staff staff;
    /**
     * Hashmap that contains the food items within an order
     */
    private HashMap<FoodItem, Integer> food_order;
    /**
     * Table number of the order
     */
    private int tablenumber;

    /**
     * Creates a new order with the staff details and table number
     * @param s Staff details
     * @param t Table Number
     */
    Order(Staff s, int t){
        setStaff(s);
        tablenumber = t;
    }

    /**
     * Gets the name of the Staff who processed the order
     * @return Name of staff who processed the order
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * Changes the name of the Staff who processed the order
     * @param staff Name of new staff who processed the order
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     * Get food order
     * @return food order
     */
    public HashMap<FoodItem, Integer> getFood_order() {
        return food_order;
    }

    /**
     * Changes food order
     * @param food_order New food order
     */
    public void setFood_order(HashMap<FoodItem, Integer> food_order) {
        this.food_order = food_order;
    }

    /**
     * Prints order
     */
    public void printOrder(){
        for (FoodItem f:food_order.keySet()){
            f.printDetails();
            System.out.println("quantity = " + food_order.get(f));
        }
    }

    /**
     * Adds new items into the food order
     * @param f food item
     */
    public void addItem(FoodItem f){
        if (food_order.keySet().contains(f)) {
            food_order.put(f, food_order.get(f) +1);
        }
        else {
            food_order.put(f, 1);
        }
    }

    /**
     * Remove items from the food order
     * @param choice choice of food item
     */
    public void removeItem(int choice){
        //TODO recode this part
    }


}

