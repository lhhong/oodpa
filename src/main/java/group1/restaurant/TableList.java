package group1.restaurant;

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

	/**
	 * initialize restaurant layout of the tables
	 */
	public TableList() {
		int tablesize = 0;
		int tableSizes[] = {2,4,8,10};
		int tableDistribution[] = {10,10,5,5};

        tables = new ArrayList<>(30);

		int id = 1;
        // create correct number of tables
        for (int i = 0; i < tableDistribution.length; i++){
            tablesize = tableSizes[i];

            for (int j = 0; j < tableDistribution[i]; j++){
                tables.add(new Table(tablesize, id++));
            }
        }
	}

	public ArrayList<Table> getTables() {
		return tables;
	}


}
