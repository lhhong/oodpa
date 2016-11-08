package group1.reservation;

/**
 * Exception message when date is not within 1 month of current date
 */
public class NotInMonthException extends Exception{
    public NotInMonthException(){
        super("Reservation only up to 1 month in advance");
    }
}
