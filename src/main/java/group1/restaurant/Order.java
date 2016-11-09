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
     * Creates a new order with the staff details and table number
     * @param s Staff details
     */
    public Order(Staff s){
        staff = s;
        food_order = new HashMap<>();
    }

    /**
     * Gets the name of the Staff who processed the order
     * @return Name of staff who processed the order
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * Get food order
     * @return food order
     */
    public HashMap<FoodItem, Integer> getFood_order() {
        return food_order;
    }

    /**
     * Prints order
     */
    public void printOrder(){
        int i = 1;
        for (FoodItem f:food_order.keySet()){
            System.out.print("Item "+i+") ");
            f.printDetails();
            System.out.println("quantity = " + food_order.get(f));
            i++;
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
        int i = 1;
        for (FoodItem f : food_order.keySet()) {
            if (i == choice) {
                food_order.remove(f);
                break;
            }
            i++;
        }
    }


}

