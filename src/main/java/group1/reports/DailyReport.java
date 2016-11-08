package group1.reports;

import group1.invoice.Invoice;
import group1.menu.FoodItem;

import java.util.HashMap;

/**
 * Created by low on 8/11/16 5:38 PM.
 */
public class DailyReport {
	private HashMap<FoodItem, Integer> itemQuantities;
	private int totalSales;

	public DailyReport() {
		totalSales = 0;
		itemQuantities = new HashMap<>();
	}

	public void addInvoice(Invoice invoice) {

	}
}
