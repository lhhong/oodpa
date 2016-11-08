package group1.reports;

import group1.invoice.Invoice;
import group1.menu.FoodItem;
import group1.restaurant.Order;

import java.util.HashMap;

/**
 * Created by low on 8/11/16 5:38 PM.
 */
public class DailyReport {
	private HashMap<FoodItem, FoodReport> itemQuantities;
	private int totalSales;

	public DailyReport() {
		totalSales = 0;
		itemQuantities = new HashMap<>();
	}

	public void addOrder(Order order) {
		HashMap<FoodItem, Integer> orderItems = order.getFood_order();
		for(FoodItem i : orderItems.keySet()) {
			if (itemQuantities.containsKey(i)) {
				itemQuantities.get(i).increment(10);
			}
		}
	}
}
