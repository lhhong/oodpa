package group1.restaurant;
import group1.reservation.Reservation;
import group1.reservation.ReservationFactory;
import group1.storage.CacheService;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by low on 4/11/16 12:50 PM.
 */
public class Restaurant {
    private static Table tables[];
    private int tableSizes[] = {2,4,8,10};
    private int tableDistribution[] = {10,10,5,5};
    private int numTables = 0;
    private int numEmptyTables;

    Restaurant(){
        System.out.println("Creating Restaurant...");
        ReservationFactory.updateReservation();
        int tableNumber = 0;
        int tablesize = 0;

        // get size of restaurant;
        for (int i:tableDistribution){
            numTables += i;
        }
        numEmptyTables = numTables;
        tables = new Table[numTables];

        // create correct number of tables
	    int id = 0;
        for (int i = 0; i < tableDistribution.length; i++){
            tablesize = tableSizes[i];

            for (int j = 0; j < tableDistribution[i]; j++){
                tables[tableNumber] = new Table(tablesize, id++);
                tableNumber++;
            }
        }
    }
    public void printTables(){
        int i = 1;
        for (Table t:tables){
            System.out.println("Table " + i + ", size = "+ t.getCapacity());
            i++;
        }
    }
    public int assignTable(int pax, int type){//0=empty, 1=occupied, 2= reserved
        // returns table number assign, returns 0 if no available table
        int i = 1;

        ReservationFactory.updateReservation();
        ArrayList<Reservation> indexReservation;
        indexReservation = CacheService.getCache().getReservations().indexReservation(0);

        ArrayList<Integer> reservedTables = new ArrayList<>();
        Iterator<Reservation> iter = indexReservation.iterator();
        while(iter.hasNext())
        {
            Reservation current = iter.next();
            reservedTables.add(current.getTableIndex());
        }

        //TODO make this work with only boolean isOccupied
        /*
        if(type == 1 || type ==0) {
            for (Table t : tables) {
                if(reservedTables.contains(i)){
                    t.setOccupied(2);
                }
                if (t.getCapacity() >= pax && t.getCapacity() <= pax + 3 && t.getOccupied() == 0) {
                    t.setOccupied(type);
                    numEmptyTables--;
                    System.out.println("Table " + i + " was assigned, size = " + t.getCapacity());
                    return i;
                }
                i++;
            }
        }else if(type ==2){


        }
        */


        System.out.println("No suitable table is available. Sorry!");
        return 0;
    }
    public void printAvailableTables(){
        int i = 1;
        for (Table t:tables){
            if (!t.isOccupied()){
                System.out.println("Table " + i + ", size = "+ t.getCapacity());
            }
            i++;
        }
        System.out.println("Number of available tables = "+numEmptyTables);
    }


    public static void main(String[] args){
        Restaurant r = new Restaurant();
        r.assignTable(5,2);
        r.assignTable(7,1);
        r.assignTable(8,1);
        r.assignTable(7,1);
        r.assignTable(8,1);
        r.assignTable(7,1);
        r.assignTable(8,1);

        r.printAvailableTables();
    }
}
