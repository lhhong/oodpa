package group1.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class for the manipulation of money in our code
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class Money {
	private static final Logger logger = LoggerFactory.getLogger(Money.class);

	/**
	 * converts a price in int to String
	 * @param price of the food item
	 * @return String of price in format $xx.xx
	 */
	public static String toString(int price) {
		return "$" + price/100 + "." + price%100;
	}
	public static int parseString(String price) throws MoneyFormatException {
		int priceInt;
		if (price.contains(".")) {
			String[] mixed = price.split("\\.");
			logger.debug(Integer.toString(mixed.length));
			if (mixed.length != 2) {
				logger.warn("money not in 2 parts");
				throw new MoneyFormatException();
			}
			try {
				priceInt = Integer.parseInt(mixed[0]) * 100 + Integer.parseInt(mixed[1]);
			} catch (NumberFormatException e) {
				logger.warn("cant parse to int");
				throw new MoneyFormatException();
			}
		}
		else {
			try {
				priceInt = Integer.parseInt(price) * 100;
			} catch (NumberFormatException e) {
				logger.warn("cant parse to int");
				throw new MoneyFormatException();
			}

		}
		return priceInt;
	}
	public static int parseFloat(float price) {
		return Math.round(price * 100);
	}
}
