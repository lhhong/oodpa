package group1.commons;

/**
 * Exception when string is not in the required format to parse to money
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class MoneyFormatException extends Exception{

	/**
	 * constructs new exception
	 */
	MoneyFormatException() {
			super("string not in proper format to convert to money");
	}
}
