package group1.restaurant;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by low on 4/11/16 12:50 PM.
 */
public class Table {
    private int status;




    private int capacity; // 0=empty, 1=occupied, 2= reserved

    public Table(int c){
        setCapacity(c);
        setStatus(0);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}