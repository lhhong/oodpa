package group1.commons;

/**
 * A class for the manipulation of money in our code
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class Money {

	/**
	 * converts a price in int to String
	 * @param price of the food item
	 * @return String of price in format $xx.xx
	 */
	public static String toString(int price) {
		int centsInt = price %100;
		String cents;
		if (centsInt == 0) {
			cents = "00";
		}
		else if (centsInt < 10) {
			cents = "0" + Integer.toString(centsInt);
		}
		else {
			cents = Integer.toString(centsInt);
		}
		return "$" + price/100 + "." + cents;
	}

	/**
	 * parses numerical string to money
	 * @param price string in format xx.xx
	 * @return money in cents. eg, $3.50 returns 300
	 * @throws MoneyFormatException if string is in a wrong format
	 */
	public static int parseString(String price) throws MoneyFormatException {
		int priceInt;
		if (price.contains(".")) {
			String[] mixed = price.split("\\.");
			if (mixed.length != 2) {
				throw new MoneyFormatException();
			}
			try {
				priceInt = Integer.parseInt(mixed[0]) * 100 + Integer.parseInt(mixed[1]);
			} catch (NumberFormatException e) {
				throw new MoneyFormatException();
			}
		}
		else {
			try {
				priceInt = Integer.parseInt(price) * 100;
			} catch (NumberFormatException e) {
				throw new MoneyFormatException();
			}

		}
		return priceInt;
	}

	/**
	 * parses float price into money format
	 * @param price actual price in dollar
	 * @return price in cents
	 */
	public static int parseFloat(float price) {
		return Math.round(price * 100);
	}
}
