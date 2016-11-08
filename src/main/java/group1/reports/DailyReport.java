package group1.reports;

import group1.invoice.Invoice;
import group1.menu.FoodItem;
import group1.restaurant.Order;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by low on 8/11/16 5:38 PM.
 */
public class DailyReport implements Serializable {
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
				itemQuantities.get(i).increment(i.getPrice());
			}
			else {
				itemQuantities.put(i, new FoodReport(i.getPrice()));
			}
			totalSales += i.getPrice();
		}
	}

	public HashMap<FoodItem, FoodReport> getItemQuantities() {
		return itemQuantities;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void collapse(DailyReport dailyReport) {
		this.totalSales += dailyReport.getTotalSales();
		for (FoodItem i : dailyReport.getItemQuantities().keySet()) {
			if (itemQuantities.keySet().contains(i)) {
				itemQuantities.get(i).collapse(dailyReport.getItemQuantities().get(i));
			}
			else {
				itemQuantities.put(i, dailyReport.getItemQuantities().get(i).clone());
			}
		}
	}

	/**
	 * constructor solely for MockData generation
	 * @param itemQuantities
	 * @param totalSales
	 */
	public DailyReport(HashMap<FoodItem, FoodReport> itemQuantities, int totalSales) {
		this.itemQuantities = itemQuantities;
		this.totalSales = totalSales;
	}
}
