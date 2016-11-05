package group1.reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by low on 4/11/16 12:35 PM.
 */
public class Reservation {

    LocalDateTime date;
    AMPM timeslot;
    String name;
    String contact;
    int pax;
    public Reservation(LocalDateTime date, AMPM timeslot, String name, String contact,int pax) {
        this.date = date;
        this.timeslot = timeslot;
        this.name = name;
        this.contact = contact;
        this.pax = pax;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }
}