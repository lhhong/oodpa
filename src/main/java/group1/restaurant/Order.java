package group1.restaurant;

/**
 * Created by signapoop on 4/11/16.
 */
import group1.menu.FoodItem;

import java.util.ArrayList;

public class Order {
    private Staff staff;
    private ArrayList<FoodItem> food_order;
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

    public ArrayList<FoodItem> getFood_order() {
        return food_order;
    }

    public void setFood_order(ArrayList<FoodItem> food_order) {
        this.food_order = food_order;
    }

    public void printOrder(){
        for (FoodItem f:food_order){
            f.getDetails();
        }
    }

    public void addItem(FoodItem f){
        food_order.add(f);
    }

    public void removeItem(int choice){
        food_order.remove(choice);
    }

}

