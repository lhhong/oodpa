package group1.reservation;

import group1.storage.CacheService;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The reservation entity that holds the customer name, contact and reservation details with the assigned table
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class Reservation implements Serializable{

    /**
     * The date of the reservation
     */
    private LocalDateTime date;
    /**
     * The time of the reservation
     */
    private AMPM timeslot;
    /**
     * The name of the person making the reservation
     */
    private String name;
    /**
     * The contact number of the person making the reservation
     */
    private int contact;
    /**
     * The number of pax for the reservation
     */
    private int pax;
    /**
     * The table number of the reservation
     */
    private int tableIndex;

    /**
     * Creates a reservation along with all possible attributes
     * @param date Date of reservation
     * @param name Name of person making reservation
     * @param contact Contact number of person makinng reservation
     * @param pax Number of pax for reservation
     * @throws NotInMonthException when reservation more than 30 days in advance
     * @throws NotInOperationException when reservation made out of operating hours
     */
    public Reservation(LocalDateTime date, String name, int contact,int pax) throws NotInMonthException, NotInOperationException {
        this.date = date;

        this.name = name;
        this.contact = contact;
        this.pax = pax;

        this.timeslot = ReservationFactory.getTimeSlot(date);

        int index = ReservationFactory.getIndex(this);

        if(index == -1){
            throw new NotInMonthException();
        }
        this.tableIndex = ReservationFactory.getTable(index, pax);
        CacheService.getCache().getReservations().addReservation(this);

    }

    /**
     * Gets the date of reservation
     * @return date of reservation
     */
    LocalDateTime getDate() {
        return date;
    }

    /**
     * Gets the timeslot of the reservation (AM/PM)
     * @return timeslot of reservation
     */
    AMPM getTimeslot() {
        return timeslot;
    }

    /**
     * Gets the name of the person who made the reservation
     * @return name of person who made reservation
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the contact no. of person who made reservation
     * @return contact no. of person who made reservation
     */
    int getContact() {
        return contact;
    }

    /**
     * Gets the no. of pax for reservation
     * @return no. of pax
     */
    int getPax() {
        return pax;
    }

    /**
     * Gets the table number of reservation
     * @return Table number
     */
    public int getTableIndex() {
        return tableIndex;
    }
}