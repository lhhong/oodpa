package group1.reservation;

import group1.restaurant.Table;
import group1.storage.Restaurant1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;

import static group1.reservation.AMPM.AMSLOT;
import static group1.reservation.AMPM.PMSLOT;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Handles all functions related to Reservations
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

class ReservationFactory {

    /**
     * Checks if the time slot is within 9am - 5pm and within a month, if not, throw their respective exceptions
     * Returns AM/PM depending on the local time
     * @param date date and time of reservation
     * @return session of reservation
     * @throws NotInOperationException when time out of range
     */
    static AMPM getTimeSlot(LocalDateTime date, ReservationList reservationList) throws NotInOperationException {
        /*
         * Update reservation list and checks for any expired reservations
         */
        ReservationFactory.updateReservation(reservationList);

        //check if reservation between 9.00 - 17.00
/*
 * ensures the time is within 9 am to 5 pm,if not, throw NotInOperationException
 */
        if (date.toLocalTime().compareTo(LocalTime.of(23, 0)) == 1) {
            throw new NotInOperationException();
        } else if (date.toLocalTime().compareTo(LocalTime.of(9, 0)) == -1) {
            throw new NotInOperationException();
        }
/*
 * Return time in AM/PM
 */
        if (date.toLocalTime().compareTo(LocalTime.NOON) == -1) {
            return AMSLOT;
        } else {
            return PMSLOT;
        }

    }

    /**
     * Gets the specific reservation array (at position index of reservation list) in the reservation list
     * @param reservation reservation object to get index for
     * @return index of the reservation in reservation list
     */
    static int getIndex(Reservation reservation, ReservationList reservationList) {
        ReservationFactory.updateReservation(reservationList);
        LocalDate currentDate = reservationList.getCurrentDay();
        LocalDate reservationDate = reservation.getDate().toLocalDate();

        /*
         * Ensure reservation within 30 days
         */
        int dayDifference = (int) DAYS.between(currentDate, reservationDate);
        if (dayDifference < 0 || dayDifference > 30) {
            return -1;
        }

        /*
         * Calculates the index based on the number of day difference and compensates for AM/PM
         */
        switch (reservation.getTimeslot()) {
            case AMSLOT:
                return dayDifference * 2;
            case PMSLOT:
                return dayDifference * 2 + 1;
        }
        return -1;
    }

    /**
     * Gets the specific reservation array (at position index of reservation list) in the reservation list
     * @param date date and time of reservation
     * @return index of the reservation in reservation list
     */
    private static int getIndex(LocalDateTime date, ReservationList reservationList) {
        /*
         * Update reservation list and checks for any expired reservations
         */
        ReservationFactory.updateReservation(reservationList);
        LocalDate currentDate = reservationList.getCurrentDay();
        LocalDate reservationDate = date.toLocalDate();
        int dayDifference = (int) DAYS.between(currentDate, reservationDate);
        if (dayDifference < 0 || dayDifference > 30) {
            return -1;
        }
        return dayDifference * 2;


    }

    /**
     * Assigns a table to the reservation
     * @param index index of reservation
     * @param pax number of people
     * @return table number
     */
    static int getTable(int index, int pax, ReservationList reservationList) {
        /*
         * Update reservation list and checks for any expired reservations
         */
        ReservationFactory.updateReservation(reservationList);
        /*
         * Get the arraylist of tables
         */
        ArrayList<Table> tables = Restaurant1.getCache().getTables().getTables();

        /*
         * Get the arraylist of reservations
         */
        ArrayList<Reservation> indexReservation;
        indexReservation = Restaurant1.getCache().getTables().getReservationList().indexReservation(index);

        ArrayList<Integer> reservedTables = new ArrayList<>();
        for (Reservation current : indexReservation) {
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
        /*
         * Return -1 if no free tables are present
         */
        System.out.println("No free tables for pax: " + pax);
        return -1;
    }

    /**
     * Prints the reservation at a particular date where index is the position where the reservation was stored in the reservation list
     * @param date date and time of reservation
     */
    static void printIndexReservation(LocalDateTime date, ReservationList reservationList) {
        /*
         * Update reservation list and checks for any expired reservations
         */
        ReservationFactory.updateReservation(reservationList);
        ArrayList<Reservation> indexReservationAM, indexReservationPM;
        indexReservationAM = reservationList.indexReservation(getIndex(date, reservationList));

        Iterator<Reservation> iter = indexReservationAM.iterator();
        int count = 1;
        /*
         * Boolean to check if there are reservation existing
         */
        boolean hasReservation = false;
        System.out.println("AM slot");

        /*
         * Prints out reservation detail for each reservation for AM slot
         */
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

        /*
         * Notify if there is no reservation found
         */
        if (!hasReservation) {
            System.out.println("No Reservations found");
            System.out.println("");
        }

/*
 * Repeats for the PM slot
 */
        indexReservationPM = reservationList.indexReservation(getIndex(date, reservationList) + 1);

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

    /**
     * removes a particular reservation at a date using their contact number
     * returns pax if reservation found and deleted, returns -1 if reservation not found;
     * @param specificDate date of reservation to be removed
     * @param contact contact number of the person
     * @return pax of reservation
     */
    static int removeIndexReservation(LocalDateTime specificDate, int contact, ReservationList reservationList) {
        ReservationFactory.updateReservation(reservationList);

        int index = getIndex(specificDate, reservationList);
        int i;
        int pax;


        /*
         * For AM and PM (index and index +1), checks for the reservation with the corresponding contact number and deletes the reservation
         */
        for (i = index; i <= index + 1; i++) {
            ArrayList<Reservation> indexReservation = reservationList.indexReservation(i);
            Iterator<Reservation> iter = indexReservation.iterator();
            int pos = 0;

            while (iter.hasNext()) {
                Reservation current = iter.next();
                if (current.getContact() == contact) {
                    pax = indexReservation.get(pos).getPax();
                    indexReservation.remove(pos);
                    System.out.println("Reservation with contact " + contact + " has been removed.");
                    return pax;
                }
                pos++;
            }
        }
        System.out.println("Could not find reservation");

        /*
         * Returns -1 if no reservation found
         */
        return -1;
    }


    /**
     * Update reservation list and checks for any expired reservations
     */
    static void updateReservation(ReservationList reservationList) {
        /*
         * expiryTime is the time where all reservations before this time is considered expired
         */
        LocalDate currentDate = LocalDate.now();
        long dayDiff = DAYS.between(reservationList.getCurrentDay(), currentDate);
        if (dayDiff == 1) {
            reservationList.oneDayPassed();
            reservationList.setCurrentDay(currentDate);
        }
        else if (dayDiff !=0) {
            for (int i = 0; i < dayDiff; i++) {
                reservationList.oneDayPassed();
            }
            reservationList.setCurrentDay(currentDate);
        }

        LocalDateTime expiryTime = LocalDateTime.now().minusMinutes(30);
        /*
         * For AM and PM (pos 0 and 1), remove any expired reservations
         */
        for (int i = 0; i <= 1 ; i++) {
            ArrayList<Reservation> indexReservation = reservationList.indexReservation(i);
	        for (int j = 0; j < indexReservation.size(); j++) {
                Reservation current;
                try {
                    current = indexReservation.get(j);
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
                if (current.getDate().compareTo(expiryTime) == -1) {
                    indexReservation.remove(j);
                    j--;
                }
            }

            /*
            Iterator<Reservation> iter = indexReservation.iterator();
            int pos = 0;


            while (iter.hasNext()) {
                Reservation current = iter.next();
                if (current.getDate().compareTo(expiryTime) == -1) {
                    CacheService.getCache().getReservations().indexReservation(i).remove(pos);
                    iter = indexReservation.iterator();
                    if(iter.hasNext()){ iter.next();}
                    pos = -1;
                }

                pos++;


            }
            */

        }
    }
}
