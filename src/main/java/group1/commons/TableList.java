package group1.commons;

import group1.restaurant.Staff;
import group1.restaurant.Table;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by low on 5/11/16 10:46 AM.
 */
public class TableList implements Serializable{
	private ArrayList<Table> tables;

	TableList() {
        int tablesize = 0;
		int tableSizes[] = {2,4,8,10};
		int tableDistribution[] = {10,10,5,5};

        tables = new ArrayList<>(30);

        // create correct number of tables
        for (int i = 0; i < tableDistribution.length; i++){
            tablesize = tableSizes[i];

            for (int j = 0; j < tableDistribution[i]; j++){
                tables.add(new Table(tablesize));
            }
        }

	}
}
