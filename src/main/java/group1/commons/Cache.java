package group1.commons;

import group1.menu.Menu;
import group1.reservation.Reservation;
import group1.restaurant.Staff;
import group1.restaurant.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by low on 4/11/16 8:44 PM.
 */
public class Cache implements Serializable{

	private ArrayList<Table> tables;
	private ArrayList<Staff> staffs;
	private ArrayList<ArrayList<Reservation>> reservations;
	private Menu menu;
	private int numEmptyTables;

	private boolean hasChanges = false;
	private final Object changeLock = new Object();

	Cache() {
		tables = MockData.getTables();
		staffs = new ArrayList<>();
		menu = new Menu();
		numEmptyTables = 0;
	}

	boolean needsFlush() {
		synchronized (changeLock) {
			if (hasChanges) {
				hasChanges = false;
				return true;
			}
			else {
				return false;
			}
		}
	}

	//TODO: remove uneccessary setters
	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

	public ArrayList<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(ArrayList<Staff> staffs) {
		this.staffs = staffs;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getNumEmptyTables() {
		return numEmptyTables;
	}

	public void setNumEmptyTables(int numEmptyTables) {
		this.numEmptyTables = numEmptyTables;
	}
}
