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

	/**
	 * gets the reservation list for this set of tables
	 * @return reservations for next 30 days
	 */
	public ReservationList getReservationList() {
		return reservationList;
	}

	/**
	 * gets the array of tables
	 * @return list of table objects
	 */
	public ArrayList<Table> getTables() {
		return tables;
	}

	/**
	 * assigns tables for pax
	 * @param pax number of persons
	 * @return table assigned, null if no tables available
	 */
	public Table assignTable(int pax) {
		return TableFactory.assignTable(pax, tables, reservationList);
	}

	/**
	 * prints the availability of all tables and show their current status
	 */
	public void printAvailableTables() {
		TableFactory.printAvailableTables(tables, reservationList);
	}

}
