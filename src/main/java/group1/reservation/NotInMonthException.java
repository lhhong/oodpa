package group1.reservation;

/**
 * Created by jorda on 5/11/2016.
 */
public class NotInMonthException extends Exception{
    public NotInMonthException(){
        super("Reservation only up to 1 month in advance");
    }
}
