package group1.reports;

import group1.menu.FoodItem;
import group1.restaurant.Order;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Contains compiled reports
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 * Created by low on 8/11/16 5:38 PM.
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
	public CompiledReport() {
		totalSales = 0;
		itemQuantities = new HashMap<>();
	}

	/**
	 * adds and order to the collection, incrementing quantity if the FoodItem exist
	 * @param order order containing items to add
	 */
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

	public void collapse(CompiledReport compiledReport) {
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
	 * @param itemQuantities
	 * @param totalSales
	 */
	public CompiledReport(HashMap<FoodItem, FoodReport> itemQuantities, int totalSales) {
		this.itemQuantities = itemQuantities;
		this.totalSales = totalSales;
	}
}
