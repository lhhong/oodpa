package group1.reservation;

import group1.restaurant.Table;
import group1.storage.CacheService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;

import static group1.reservation.AMPM.AMSLOT;
import static group1.reservation.AMPM.PMSLOT;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by jorda on 4/11/2016.
 */
public class ReservationFactory {


    public static AMPM getTimeSlot(LocalDateTime date) throws NotInOperationException {
        ReservationFactory.updateReservation();
        LocalDate currentDate = CacheService.getCache().getCurrentDay();


        LocalDate specifiedDate = date.toLocalDate();

        int dayDifference = (int) DAYS.between(currentDate, specifiedDate);



        //check if reservation between 9.00 - 17.00
        // CHANGED!!!

            if (date.toLocalTime().compareTo(LocalTime.of(23, 0)) == 1) {
                throw new NotInOperationException();
            } else if (date.toLocalTime().compareTo(LocalTime.of(9, 0)) == -1) {
                throw new NotInOperationException();
            }


        //return AM or PM
        if (date.toLocalTime().compareTo(LocalTime.NOON) == -1) {
            return AMSLOT;
        } else {
            return PMSLOT;
        }

    }

    public static int getIndex(Reservation reservation) {
        ReservationFactory.updateReservation();
        LocalDate currentDate = CacheService.getCache().getCurrentDay();
        LocalDate reservationDate = reservation.getDate().toLocalDate();
        int dayDifference = (int) DAYS.between(currentDate, reservationDate);
        if (dayDifference < 0 || dayDifference > 30) {
            return -1;
        }

        switch (reservation.getTimeslot()) {
            case AMSLOT:
                return dayDifference * 2;
            case PMSLOT:
                return dayDifference * 2 + 1;
        }
        return -1;
    }

    public static int getIndex(LocalDateTime date) {
        ReservationFactory.updateReservation();
        LocalDate currentDate = CacheService.getCache().getCurrentDay();
        LocalDate reservationDate = date.toLocalDate();
        int dayDifference = (int) DAYS.between(currentDate, reservationDate);
        if (dayDifference < 0 || dayDifference > 30) {
            return -1;
        }
        return dayDifference * 2;


    }

    public static int getTable(int index, int pax) {
        ReservationFactory.updateReservation();
        ArrayList<Table> tables = CacheService.getCache().getTables().getTables();

        ArrayList<Reservation> indexReservation;
        indexReservation = CacheService.getCache().getReservations().indexReservation(index);

        ArrayList<Integer> reservedTables = new ArrayList<>();
        Iterator<Reservation> iter = indexReservation.iterator();
        while (iter.hasNext()) {
            Reservation current = iter.next();
            reservedTables.add(current.getTableIndex());
        }
        int i = 1;
        for (Table t : tables) {
            if (t.getCapacity() >= pax && t.getCapacity() <= pax + 3 && !t.isOccupied()) {
                if (!(reservedTables.contains(i))) {
                    System.out.println("Reservation Success at table: " + i);
                    return i;
                }


            }
            i++;
        }
        System.out.println("No free tables for pax: " + pax);
        return -1;
    }


    public static void printIndexReservation(LocalDateTime date) {
        ReservationFactory.updateReservation();
        ArrayList<Reservation> indexReservationAM, indexReservationPM;
        indexReservationAM = CacheService.getCache().getReservations().indexReservation(getIndex(date));

        Iterator<Reservation> iter = indexReservationAM.iterator();
        int count = 1;
        boolean hasReservation = false;
        System.out.println("AM slot");
        while (iter.hasNext()) {
            Reservation current = iter.next();

            System.out.println("Reservation number: " + count);
            System.out.println("Name: " + current.getName());
            System.out.println("Contact: " + current.getContact());
            System.out.println("Time: " + current.getDate().toLocalTime());
            System.out.println("Table: " + current.getTableIndex());
            System.out.println("Pax: " + current.getPax());
            System.out.println("");
            hasReservation = true;
            count++;

        }

        if (!hasReservation) {
            System.out.println("No Reservations found");
            System.out.println("");
        }


        indexReservationPM = CacheService.getCache().getReservations().indexReservation(getIndex(date) + 1);

        count = 1;
        hasReservation = false;

        iter = indexReservationPM.iterator();

        System.out.println("PM slot");
        while (iter.hasNext()) {
            Reservation current = iter.next();

            System.out.println("Reservation number: " + count);
            System.out.println("Name: " + current.getName());
            System.out.println("Contact: " + current.getContact());
            System.out.println("Time: " + current.getDate().toLocalTime());
            System.out.println("Table: " + current.getTableIndex());
            System.out.println("Pax: " + current.getPax());
            System.out.println("");
            hasReservation = true;
            count++;
        }

        if (!hasReservation) {
            System.out.println("No Reservations found");
            System.out.println("");
        }

    }
// removes a particular reservation at a date using their contact number and returns pax if reservation found and deleted, returns -1 if reservation not found;
    public static int removeIndexReservation(LocalDateTime specificDate, int contact) {
        ReservationFactory.updateReservation();

        int index = getIndex(specificDate);
        int i;
        int pax;
        boolean removed = false;

        for (i = index; i <= index + 1; i++) {
            ArrayList<Reservation> indexReservation = CacheService.getCache().getReservations().indexReservation(i);
            Iterator<Reservation> iter = indexReservation.iterator();
            int pos = 0;


            while (iter.hasNext()) {
                Reservation current = iter.next();
                if (current.getContact() == contact) {
                   pax = indexReservation.get(pos).getPax();

                   indexReservation.remove(pos);

                    removed = true;
                    System.out.println("Reservation with contact " + contact + " has been removed.");
                    return pax;

                }
                pos++;
            }

        }
        if (removed == false) {
            System.out.println("Could not find reservation");
        }

        return -1;

    }


    public static void updateReservation() {
        LocalDateTime expiryTime = LocalDateTime.now().minusMinutes(30);

        for (int i = 1; i <= 2 + 1; i++) {
            ArrayList<Reservation> indexReservation = CacheService.getCache().getReservations().indexReservation(i);
            Iterator<Reservation> iter = indexReservation.iterator();
            int pos = 0;


            while (iter.hasNext()) {
                Reservation current = iter.next();
                if (current.getDate().compareTo(expiryTime) == -1) {
                    CacheService.getCache().getReservations().indexReservation(i).remove(pos);
                    iter = indexReservation.iterator();
                   if(iter.hasNext()){ current = iter.next();}
                    pos = -1;
                }

                pos++;


            }

        }
    }
}
