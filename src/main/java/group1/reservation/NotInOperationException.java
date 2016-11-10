package group1.reservation;

/**
 * Exception when restaurant is not in operation i.e. time not within 11am-3pm or 6pm to 10 pm
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class NotInOperationException extends Exception{
    NotInOperationException(){
        super("Only open from 11am-3pm or 6pm to 10 pm");
    }

}
