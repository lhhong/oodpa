package group1.reports;

import group1.menu.FoodItem;
import group1.restaurant.Order;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Contains compiled reports for a day or a month
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class CompiledReport implements Serializable {
	/**
	 * HashMap containing a map of food items and their quantities and sales
	 */
	private HashMap<FoodItem, FoodReport> itemQuantities;
	/**
	 * total sales of this collection
	 */
	private int totalSales;

	/**
	 * initialize the compilation
	 */
	CompiledReport() {
		totalSales = 0;
		itemQuantities = new HashMap<>();
	}

	/**
	 * adds and order to the collection, incrementing quantity if the FoodItem exist
	 * @param order order containing items to add
	 */
	void addOrder(Order order) {
		HashMap<FoodItem, Integer> orderItems = order.getFoodOrder();
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

	/**
	 * get Map of item quantities
	 * @return food - quantity map
	 */
	HashMap<FoodItem, FoodReport> getItemQuantities() {
		return itemQuantities;
	}

	/**
	 * get total sales
	 * @return total sales
	 */
	int getTotalSales() {
		return totalSales;
	}

	/**
	 * combines 2 reports together
	 * @param compiledReport report to be combined
	 */
	void collapse(CompiledReport compiledReport) {
		this.totalSales += compiledReport.getTotalSales();
		for (FoodItem i : compiledReport.getItemQuantities().keySet()) {
			if (itemQuantities.keySet().contains(i)) {
				itemQuantities.get(i).collapse(compiledReport.getItemQuantities().get(i));
			}
			else {
				itemQuantities.put(i, compiledReport.getItemQuantities().get(i).clone());
			}
		}
	}

	/**
	 * constructor solely for MockData generation
	 * @param itemQuantities quantity
	 * @param totalSales sales revenue
	 */
	public CompiledReport(HashMap<FoodItem, FoodReport> itemQuantities, int totalSales) {
		this.itemQuantities = itemQuantities;
		this.totalSales = totalSales;
	}
}
