package group1.reservation;

/** Exception message when date is not within 1 month of current date
 * Description of Class
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class NotInMonthException extends Exception{
    public NotInMonthException(){
        super("Reservation only up to 1 month in advance");
    }
}
