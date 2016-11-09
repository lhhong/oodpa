package group1.restaurant;

/**
 * Created by low on 9/11/16 10:09 PM.
 */
public class InvalidTableException extends Exception{
	public InvalidTableException() {
		super("invalid table selected");
	}
}
