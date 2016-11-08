package group1.restaurant;
import java.io.Serializable;

/**
 * Created by low on 4/11/16 12:50 PM.
 */
public class Table implements Serializable{
    private boolean occupied;
    private Order order;
    private int capacity; // 0=empty, 1=occupied, 2= reserved
    private int tableNumber;

    public Table(int c, int id){
        tableNumber = id;
	    order = null;
        setCapacity(c);
	    occupied = false;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        this.occupied = true;
    }

    public void vacate() {
        this.occupied = false;
        order = null;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}