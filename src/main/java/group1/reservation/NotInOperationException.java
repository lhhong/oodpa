package group1.reservation;

/**
 * Created by jorda on 5/11/2016.
 */
public class NotInOperationException extends Exception{
    public NotInOperationException(){
        super("Only open from 9am to 5pm");
    }

}
