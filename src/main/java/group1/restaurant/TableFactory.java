package group1.restaurant;
import group1.reservation.Reservation;
import group1.reservation.ReservationFactory;
import group1.storage.CacheService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * factory class to manage functions to do with tables
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class TableFactory {


    /**
     * Returns an empty, non reserved table according to pax size, returns null if no table available
     * @param pax number of people a table
     * @return Table object
     */
    public static Table assignTable(int pax){
        // returns table assign, returns null if no available table
        int i = 1;

        ReservationFactory.updateReservation();
        ArrayList<Reservation> indexReservation;
        int index;
        /*
         * Determine if it is AM or PM and set index to 0 or 1 respectively
         */
        if (LocalDateTime.now().toLocalTime().compareTo(LocalTime.NOON) == -1) {
            index = 0;
        } else {
            index = 1;
        }
        /*
         * Obtains the AM/PM reservation for the day
         */
        indexReservation = CacheService.getCache().getReservations().indexReservation(index);

        ArrayList<Integer> reservedTables = new ArrayList<>();
        Iterator<Reservation> iter = indexReservation.iterator();
        while(iter.hasNext()) {
            Reservation current = iter.next();
            reservedTables.add(current.getTableIndex());
        }

/*
 * Gets the list of tables
 */
        ArrayList<Table> tables = CacheService.getCache().getTables().getTables();

        /*
         * Checks through each table, assigns table if the capacity is within pax and pax+3, is unoccupied and is not reserved
         */
        for (Table t : tables) {
            if (t.getCapacity() >= pax && t.getCapacity() <= pax + 3 && t.isOccupied() == false && !reservedTables.contains(t.getTableNumber())) {
                t.occupy();

                System.out.println("Table " + i + " was assigned, size = " + t.getCapacity());
                return t;
            }
            i++;

        }


/*
 * Returns null if no table is available
 */

        return null;
    }

    /**
     * Prints all the tables and shows their current status
     */
    public static void printAvailableTables(){
        ReservationFactory.updateReservation();
        ArrayList<Reservation> indexReservation;
        int index;
        /*
         * Checks if the time is AM or PM and looks in the AM or PM reservation respectively
         */
        if (LocalDateTime.now().toLocalTime().compareTo(LocalTime.NOON) == -1) {
            index = 0;
        } else {
            index = 1;
        }
        indexReservation = CacheService.getCache().getReservations().indexReservation(index);

        /*
         * Gets a list of reserved tables called reservedTables
         */
        ArrayList<Integer> reservedTables = new ArrayList<>();
        Iterator<Reservation> iter = indexReservation.iterator();
        while(iter.hasNext())
        {
            Reservation current = iter.next();
            reservedTables.add(current.getTableIndex());
        }

        int i = 1;
        int emptyTables = 0;
        ArrayList<Table> tables = CacheService.getCache().getTables().getTables();
        for (Table t:tables){
            if (!t.isOccupied()){
                if(reservedTables.contains(i)){
                System.out.println("Table " + i + ", size = "+ t.getCapacity() + ", status = Reserved" );
                }else{
                    System.out.println("Table " + i + ", size = "+ t.getCapacity() + ", status = Available" );
                    emptyTables++;
                }
            }else{
                System.out.println("Table " + i + ", size = "+ t.getCapacity() + ", status = Occupied" );
            }
            i++;
        }
        System.out.println("Number of available tables = " + emptyTables);



    }


}
