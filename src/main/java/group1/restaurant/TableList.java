package group1.restaurant;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by low on 8/11/16 5:08 PM.
 */
public class TableList implements Serializable {
	private ArrayList<Table> tables;
	private int numEmptyTables;

	public TableList() {
		int tablesize = 0;
		int tableSizes[] = {2,4,8,10};
		int tableDistribution[] = {10,10,5,5};

        tables = new ArrayList<>(30);

		int id = 0;
        // create correct number of tables
        for (int i = 0; i < tableDistribution.length; i++){
            tablesize = tableSizes[i];

            for (int j = 0; j < tableDistribution[i]; j++){
                tables.add(new Table(tablesize, id++));
            }
        }
        numEmptyTables = 30;
	}

	public void assignTable(/*some arguements*/) {
		//tables.get(TableFactory.assignTable(tables, //other arguements needed)).occupy;
		//numEmptyTables++;
	}
}
