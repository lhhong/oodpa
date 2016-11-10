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
     * HashMap that contains the food items within an order
     */
    private HashMap<FoodItem, Integer> foodOrder;

    /**
     * Creates a new order with the staff details and table number
     * @param s Staff details
     */
    public Order(Staff s){
        staff = s;
        foodOrder = new HashMap<>();
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
    public HashMap<FoodItem, Integer> getFoodOrder() {
        return foodOrder;
    }

    /**
     * Prints order
     */
    public void printOrder(){
        int i = 1;
        for (FoodItem f: foodOrder.keySet()){
            System.out.print("Item "+i+") ");
            f.printDetails();
            System.out.println("quantity = " + foodOrder.get(f));
            i++;
        }
    }

    /**
     * Adds new items into the food order
     * @param f food item
     */
    public void addItem(FoodItem f){
        if (foodOrder.keySet().contains(f)) {
            foodOrder.put(f, foodOrder.get(f) +1);
        }
        else {
            foodOrder.put(f, 1);
        }
    }

    /**
     * Remove items from the food order
     * @param choice choice of food item
     */
    public void removeItem(int choice){
        int i = 1;
        for (FoodItem f : foodOrder.keySet()) {
            if (i == choice) {
                foodOrder.remove(f);
                break;
            }
            i++;
        }
    }


}

