package group1.restaurant;

/**
 * Created by signapoop on 4/11/16.
 */
import java.util.ArrayList;

public class Order {
    private Staff staff;
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


}

