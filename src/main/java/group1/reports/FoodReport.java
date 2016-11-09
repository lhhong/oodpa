package group1.reports;

import java.io.Serializable;

/**
 * contains quantity and sales for a particular food item
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class FoodReport implements Cloneable, Serializable{
	/**
	 * total sales revenue for a food item
	 */
	private int sales;

	/**
	 * quantity of the food item sold
	 */
	private int quantity;

	@Override
	public FoodReport clone() {
		try {
			return (FoodReport) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * initialise FoodReport with single item
	 * @param price price of food item in concern
	 */
	FoodReport(int price) {
		sales = price;
		quantity = 1;
	}

	/**
	 * increase quantity by one, increments price at the same time
	 * @param price price of a single food item
	 */
	void increment(int price) {
		this.sales += price;
		quantity++;
	}

	/**
	 * combines 2 FoodReport
	 * @param foodReport foodreport to be combined
	 */
	void collapse(FoodReport foodReport) {
		this.sales += foodReport.getSales();
		this.quantity += foodReport.getQuantity();
	}

	/**
	 * get the sales revenue
	 * @return total sales revenue
	 */
	int getSales() {
		return sales;
	}

	/**
	 * get the sales quantity
	 * @return quantity of the food item sold
	 */
	int getQuantity() {
		return quantity;
	}

	/**
	 * Constructor solely for MockData generation
	 * @param sales sales revenue
	 * @param quantity quantity sold
	 */
	public FoodReport(int sales, int quantity) {
		this.sales = sales;
		this.quantity = quantity;
	}
}
