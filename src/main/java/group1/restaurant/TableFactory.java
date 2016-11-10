package group1.restaurant;
import group1.reservation.ReservationList;

import java.util.ArrayList;

/**
 * factory class to manage functions to do with tables
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

class TableFactory {


    /**
     * Returns an empty, non reserved table according to pax size, returns null if no table available
     * @param pax number of people a table
     * @return Table assigned, null if no table available
     */
    static Table assignTable(int pax, ArrayList<Table> tables, ReservationList reservationList){
        // returns table assign, returns null if no available table
        int i = 1;

        ArrayList<Integer> reservedTables = reservationList.getReservedTablesInThisSession();

        /*
         * Checks through each table, assigns table if the capacity is within pax and pax+3, is unoccupied and is not reserved
         */
        for (Table t : tables) {
            if (t.getCapacity() >= pax && t.getCapacity() <= pax + 3 && !t.isOccupied() && !reservedTables.contains(t.getTableNumber())) {
                t.occupy();

                System.out.println("Table " + i + " was assigned, size = " + t.getCapacity());
                return t;
            }
            i++;

        }
        //Returns null if no table is available
        return null;
    }

    /**
     * Prints all the tables and shows their current status
     */
    static void printAvailableTables(ArrayList<Table> tables, ReservationList reservationList){

        ArrayList<Integer> reservedTables = reservationList.getReservedTablesInThisSession();

        int i = 1;
        int emptyTables = 0;
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
