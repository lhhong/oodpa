package group1.restaurant;

/**
 * exception to indicate an invalid table is selected
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class InvalidTableException extends Exception{
	public InvalidTableException() {
		super("invalid table selected");
	}
}
