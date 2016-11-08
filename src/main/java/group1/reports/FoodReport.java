package group1.reports;

import java.io.Serializable;

/**
 * Created by low on 8/11/16 6:46 PM.
 */
public class FoodReport implements Cloneable, Serializable{
	int sales;
	int quantity;

	@Override
	public FoodReport clone() {
		return new FoodReport(sales, quantity);
	}

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

	void collapse(FoodReport foodReport) {
		this.sales += foodReport.getSales();
		this.quantity += foodReport.getQuantity();
	}

	public int getSales() {
		return sales;
	}

	public int getQuantity() {
		return quantity;
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
