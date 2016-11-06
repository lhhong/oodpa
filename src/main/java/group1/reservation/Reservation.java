package group1.reservation;

import group1.storage.CacheService;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;



/**
 * Created by low on 4/11/16 12:35 PM.
 */
public class Reservation implements Serializable{

    LocalDateTime date;
    AMPM timeslot;
    String name;
    int contact;
    int pax;
    int tableIndex;

    public Reservation(LocalDateTime date, String name, int contact,int pax) {
        this.date = date;

        this.name = name;
        this.contact = contact;
        this.pax = pax;

        this.timeslot = ReservationFactory.getTimeSlot(date);
        this.tableIndex = ReservationFactory.getTable(ReservationFactory.getIndex(this), pax);
        CacheService.getCache().getReservations().addReservation(this);

    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AMPM getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(AMPM timeslot) {
        this.timeslot = timeslot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public int getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(int tableIndex) {
        this.tableIndex = tableIndex;
    }
}