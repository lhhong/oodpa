package group1.reservation;

/**
 * Exception when restaurant is not in operation i.e. time not within 9am to 5 pm
 */
public class NotInOperationException extends Exception{
    public NotInOperationException(){
        super("Only open from 9am to 5pm");
    }

}
