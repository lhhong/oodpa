package group1.reservation;

import group1.storage.CacheService;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

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
    LocalDateTime date;
    /**
     * The time of the reservation
     */
    AMPM timeslot;
    /**
     * The name of the person making the reservation
     */
    String name;
    /**
     * The contact number of the person making the reservation
     */
    int contact;
    /**
     * The number of pax for the reservation
     */
    int pax;
    /**
     * The table number of the reservation
     */
    int tableIndex;

    /**
     * Creates a reservation along with all possible attributes
     * @param date Date of reservation
     * @param name Name of person making reservation
     * @param contact Contact number of person makinng reservation
     * @param pax Number of pax for reservation
     * @throws NotInMonthException
     * @throws NotInOperationException
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
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Changes date of reservation
     * @param date New date of reservation
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Gets the timeslot of the reservation (AM/PM)
     * @return timeslot of reservation
     */
    public AMPM getTimeslot() {
        return timeslot;
    }

    /**
     * Changes timeslot of reservation
     * @param timeslot new timeslot of reservation
     */
    public void setTimeslot(AMPM timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Gets the name of the person who made the reservation
     * @return name of person who made reservation
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the person who made the reservation
     * @param name New name of person who made reservation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact no. of person who made reservation
     * @return contact no. of person who made reservation
     */
    public int getContact() {
        return contact;
    }

    /**
     * Changes the contact number of person who made reservation
     * @param contact new contact number
     */
    public void setContact(int contact) {
        this.contact = contact;
    }

    /**
     * Gets the no. of pax for reservation
     * @return no. of pax
     */
    public int getPax() {
        return pax;
    }

    /**
     * Changes the no. of pax for reservation
     * @param pax new no. of pax
     */
    public void setPax(int pax) {
        this.pax = pax;
    }

    /**
     * Gets the table number of reservation
     * @return Table number
     */
    public int getTableIndex() {
        return tableIndex;
    }

    /**
     * Changes the table number of reservation
     * @param tableIndex new table number
     */
    public void setTableIndex(int tableIndex) {
        this.tableIndex = tableIndex;
    }
}