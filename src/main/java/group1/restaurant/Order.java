package group1.restaurant;

/**
 * Created by signapoop on 4/11/16.
 */
import group1.menu.FoodItem;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable {
    private Staff staff;
    private HashMap<FoodItem, Integer> food_order;
    private int tablenumber;

    Order(Staff s, int t){
        setStaff(s);
        tablenumber = t;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public HashMap<FoodItem, Integer> getFood_order() {
        return food_order;
    }

    public void setFood_order(HashMap<FoodItem, Integer> food_order) {
        this.food_order = food_order;
    }

    public void printOrder(){
        for (FoodItem f:food_order.keySet()){
            f.getDetails();
            System.out.println("quantity = " + food_order.get(f));
        }
    }

    public void addItem(FoodItem f){
        if (food_order.keySet().contains(f)) {
            food_order.put(f, food_order.get(f) +1);
        }
        else {
            food_order.put(f, 1);
        }
    }

    public void removeItem(int choice){
        //TODO recode this part
    }

}

