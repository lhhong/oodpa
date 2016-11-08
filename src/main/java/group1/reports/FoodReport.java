package group1.reports;

/**
 * Created by low on 8/11/16 6:46 PM.
 */
public class FoodReport {
	int sales;
	int quantity;

	/**
	 * initialise FoodReport with single item
	 * @param price price of food item in concern
	 */
	public FoodReport(int price) {
		sales = price;
		quantity = 1;
	}

	public void increment(int price) {
		this.sales += price;
		quantity++;
	}

	/**
	 * Constructor solely for MockData generation
	 * @param sales
	 * @param quantity
	 */
	public FoodReport(int sales, int quantity) {
		this.sales = sales;
		this.quantity = quantity;
	}

	public int getSales(){return sales; }

	public int getQuantity() {return quantity; }
}
