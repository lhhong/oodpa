package group1.invoice;

import group1.menu.FoodItem;
import group1.commons.Money;
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
	private int subtotal;
	private LocalDateTime dateTime;

	public Invoice(Table table) {
		this.order = table.getOrder();
		this.tableNumber = table.getTableNumber();
		table.vacate();
		dateTime = LocalDateTime.now();
		for (FoodItem i:order.getFood_order())
			subtotal += i.getPrice();
	}

	public int calcGst() {
		return Money.parseFloat(subtotal*1.07f);
	}

	public String toString() {
		//TODO convert into string of full invoice
		String stringOutput = "The OODA Restaurant\n" +
				"=====================\n" +
				"Block 50A, Tanjong Hall, #04-120" +
				"Nanyang Technological University" +
				"Tel: +65 9876 5432" +
				"Date: " + dateTime +
				"Server: " + order.getStaff().getName() +
				"Table: " + tableNumber +
				"\n====================\n" +
				"";
		for (FoodItem i:order.getFood_order())
			stringOutput += i.getName() + " " + i.getPrice() + "\n";
		stringOutput += "\n Subtotal:" + subtotal +
				"GST: " + calcGst() +
				"Total: " + (subtotal+calcGst());
		return stringOutput;
	}

	public Order getOrder() {
		return order;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}
}
