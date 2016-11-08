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
		for (FoodItem i:order.getFood_order().keySet()) {
			subtotal = order.getFood_order().get(i) * i.getPrice();
		}
	}

	public int calcGst() {
		return Money.parseFloat(subtotal*1.07f);
	}

	public String toString() {
		//TODO convert into string of full invoice
		String stringOutput = "The OODA Restaurant\n" +
				"=====================\n" +
				"Block 50A, Tanjong Hall, #04-120 \n" +
				"Nanyang Technological University \n" +
				"Tel: +65 9876 5432 \n" +
				"Date: " + dateTime + "\n"+
				"Server: " + order.getStaff().getName() + "\n"+
				"Table: " + tableNumber + "\n" +
				"\n====================\n" +
				"";
		for (FoodItem i:order.getFood_order().keySet()) {
			stringOutput += order.getFood_order().get(i) + " " + i.getName() +
					"\t" + Money.toString(order.getFood_order().get(i)*i.getPrice()) + "\n";
		}
		stringOutput += "\n Subtotal:" + Money.toString(subtotal)+ "\n" +
				"GST: " + Money.toString(calcGst()) + "\n" +
				"Total: " + Money.toString(subtotal+calcGst());
		return stringOutput;
	}

	public Order getOrder() {
		return order;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}
}
