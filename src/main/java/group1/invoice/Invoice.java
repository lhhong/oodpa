package group1.invoice;

import group1.restaurant.Order;
import group1.restaurant.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by low on 6/11/16 12:06 PM.
 */
public class Invoice implements Serializable {
	private Order order;
	private int tableNumber;
	private LocalDateTime dateTime;

	public Invoice(Table table) {
		this.order = table.getOrder();
		this.tableNumber = table.getTableNumber();
		table.vacate();
		dateTime = LocalDateTime.now();
	}

	public int calcGst() {
		//TODO calc GST
		return -1;
	}

	public String toString() {
		//TODO calc GST
		return "toString";
	}
}
