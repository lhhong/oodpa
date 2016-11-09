package group1.restaurant;
import java.io.Serializable;

/**
 * Represents a table in the restaurant
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */


public class Table implements Serializable {
    /**
     * Status of the table
     */
    private boolean occupied;
    /**
     * A table has an order
     */
    private Order order;
    /**
     * Capacity of the table
     */
    private int capacity; // 0=empty, 1=occupied, 2= reserved
    /**
     * Table number
     */
    private int tableNumber;

    /**
     * Creates a new table with table capacity and table number
     * @param c table capacity
     * @param id table number
     */
    Table(int c, int id) {
        tableNumber = id;
        order = null;
        capacity = c;
        occupied = false;
    }

    /**
     * Gets the Table number
     * @return Table number
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * Gets the status of the table
     * @return Table is occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Occupies table
     * Sets boolean of table occupied to true
     */
    void occupy() {
        this.occupied = true;
    }

    /**
     * Vacates table
     * Sets boolean of table occupied to false
     */
    public void vacate() {
        this.occupied = false;
        order = null;
    }

    /**
     * Gets capacity of table
     * @return Capacity of table
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets order of table
     * @return order of table
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Creates new order of table
     * @param order order to add
     */
    public void newOrder(Order order) {
        this.order = order;
    }

}