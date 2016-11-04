package group1.restaurant;
import java.util.Scanner;
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
        print("Creating Restaurant...");
        int tableNumber = 0;
        int tablesize = 0;

        // get size of restaurant;
        for (int i:tableDistribution){
            numTables += i;
        }
        numEmptyTables = numTables;
        tables = new Table[numTables];

        // create correct number of tables
        for (int i = 0; i < tableDistribution.length; i++){
            tablesize = tableSizes[i];

            for (int j = 0; j < tableDistribution[i]; j++){
                tables[tableNumber] = new Table(tablesize);
                tableNumber++;
            }
        }
    }
    public void printTables(){
        int i = 1;
        for (Table t:tables){
            print("Table " + i + ", size = "+ t.getCapacity());
            i++;
        }
    }
    public int assignTable(int pax, int type){
        // returns table number assign, returns 0 if no available table
        int i = 1;
        for (Table t:tables){
            if (t.getCapacity() >= pax && t.getCapacity() <= pax+3 && t.getStatus() == 0){
                t.setStatus(type);
                numEmptyTables --;
                print("Table " + i + " was assigned, size = "+ t.getCapacity());
                return i;
            }
            i++;
        }
        print("No suitable table is available. Sorry!");
        return 0;
    }
    public void printAvailableTables(){
        int i = 1;
        for (Table t:tables){
            if (t.getStatus() == 0){
                print("Table " + i + ", size = "+ t.getCapacity());
            }
            i++;
        }
        print("Number of available tables = "+numEmptyTables);
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
