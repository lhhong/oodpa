package group1.commons;

/**
 * Created by low on 4/11/16 1:38 PM.
 */
public class Money {
	public static String toString(int price) {
		return "$" + price/100 + "." + price%100;
	}
	public static int parseString(String price) throws MoneyFormatException {
		int priceInt;
		if (price.contains(".")) {
			String[] mixed = price.split(".");
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
}
