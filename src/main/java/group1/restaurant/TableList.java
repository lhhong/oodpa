package group1.restaurant;

import group1.reservation.ReservationList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates a list of tables
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class TableList implements Serializable {
	/**
	 * Array list of tables
	 */
	private ArrayList<Table> tables;
	private ReservationList reservationList;

	/**
	 * initialize restaurant layout of the tables
	 */
	public TableList() {

		int tableSize;
		int tableSizes[] = {2,4,8,10};
		int tableDistribution[] = {10,10,5,5};

        tables = new ArrayList<>(30);

		int id = 1;
        // create correct number of tables
        for (int i = 0; i < tableDistribution.length; i++){
            tableSize = tableSizes[i];

            for (int j = 0; j < tableDistribution[i]; j++){
                tables.add(new Table(tableSize, id++));
            }
        }

        reservationList = new ReservationList();
	}

	public ReservationList getReservationList() {
		return reservationList;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public Table assignTable(int pax) {
		return TableFactory.assignTable(pax, tables, reservationList);
	}

	public void printAvailableTables() {
		TableFactory.printAvailableTables(tables, reservationList);
	}

}
