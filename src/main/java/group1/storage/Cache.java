package group1.storage;

import group1.menu.Menu;
import group1.reservation.ReservationList;
import group1.restaurant.Staff;
import group1.restaurant.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cache implements Serializable{

	private LocalDate currentDay;

	private ArrayList<Table> tables;
	int numEmptyTables;
	private ArrayList<Staff> staffs;
	private ReservationList reservations;
	private Menu menu;

	Cache() {
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
		currentDay = LocalDate.now();
		staffs = MockData.getStaffs();
		reservations = new ReservationList();
		menu = new Menu(MockData.getFoodItems());
	}

	public LocalDate getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(LocalDate currentDay) {
		this.currentDay = currentDay;
	}

	public Menu getMenu() {
		return menu;
	}

	public ReservationList getReservations() {
		return reservations;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public ArrayList<Staff> getStaffs() {
		return staffs;
	}
}
