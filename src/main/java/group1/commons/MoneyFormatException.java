package group1.commons;

/**
 * Created by low on 4/11/16 1:42 PM.
 */
public class MoneyFormatException extends Exception{

	public MoneyFormatException() {
			super("string not in proper format to convert to money");
	}
}
