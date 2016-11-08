package group1.restaurant;
import group1.reservation.AMPM;
import group1.reservation.Reservation;
import group1.reservation.ReservationFactory;
import group1.storage.CacheService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import static group1.reservation.AMPM.AMSLOT;
import static group1.reservation.AMPM.PMSLOT;

/**
 * Created by low on 4/11/16 12:50 PM.
 */
public class TableFactory {



    public static void printTables(){
        int i = 1;
        ArrayList<Table> tables = CacheService.getCache().getTables().getTables();
        for (Table t:tables){
            System.out.println("Table " + i + ", size = "+ t.getCapacity());
            i++;
        }
    }
    public static Table assignTable(int pax){//1=walk in, 2= reserved
        // returns table assign, returns null if no available table
        int i = 1;

        ReservationFactory.updateReservation();
        ArrayList<Reservation> indexReservation;
        int index;
        if (LocalDateTime.now().toLocalTime().compareTo(LocalTime.NOON) == -1) {
            index = 0;
        } else {
            index = 1;
        }
        indexReservation = CacheService.getCache().getReservations().indexReservation(index);

        ArrayList<Integer> reservedTables = new ArrayList<>();
        Iterator<Reservation> iter = indexReservation.iterator();
        while(iter.hasNext())
        {
            Reservation current = iter.next();
            reservedTables.add(current.getTableIndex());
        }


        ArrayList<Table> tables = CacheService.getCache().getTables().getTables();
        for (Table t : tables) {
            if (reservedTables.contains(i)) {
                t.occupy();
            }
            if (t.getCapacity() >= pax && t.getCapacity() <= pax + 3 && t.isOccupied() == false && !reservedTables.contains(t.getTableNumber())) {
                t.occupy();

                System.out.println("Table " + i + " was assigned, size = " + t.getCapacity());
                return t;
            }
            i++;

        }

        //TODO deassign table ?
        //TODO when someone with reservation comes just removereservation and assigntable consecutively

        return null;
    }
    public static void printAvailableTables(){
        ReservationFactory.updateReservation();
        ArrayList<Reservation> indexReservation;
        int index;
        if (LocalDateTime.now().toLocalTime().compareTo(LocalTime.NOON) == -1) {
            index = 0;
        } else {
            index = 1;
        }
        indexReservation = CacheService.getCache().getReservations().indexReservation(index);

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
