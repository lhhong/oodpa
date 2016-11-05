package group1.storage;

import group1.menu.Menu;

import java.io.Serializable;
import java.time.LocalDate;

public class Cache implements Serializable{

	private LocalDate currentDay;

	private StaffList staffs;
	private TableList tables;
	private ReservationList reservations;
	private Menu menu;
	private int numEmptyTables;

	private boolean menuHasChanges = false;
	private final Object menuChangeLock = new Object();

	private boolean tablesHasChanges = false;
	private final Object tablesChangeLock = new Object();

	private boolean reservationsHasChanges = false;
	private final Object reservationsChangeLock = new Object();

	Cache() {
		currentDay = LocalDate.now();
		reservations = new ReservationList();
		menu = new Menu();
		numEmptyTables = 0;
	}

	public LocalDate getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(LocalDate currentDay) {
		this.currentDay = currentDay;
	}

	boolean menuNeedsFlush() {
		synchronized (menuChangeLock) {
			if (menuHasChanges) {
				menuHasChanges = false;
				return true;
			}
			else {
				return false;
			}
		}
	}

	boolean tablesNeedsFlush() {
		synchronized (tablesChangeLock) {
			if (tablesHasChanges) {
				tablesHasChanges = false;
				return true;
			}
			else {
				return false;
			}
		}
	}

	boolean reservationsNeedsFlush() {
		synchronized (reservationsChangeLock) {
			if (reservationsHasChanges) {
				reservationsHasChanges = false;
				return true;
			}
			else {
				return false;
			}
		}
	}

	//TODO: remove uneccessary setters
	public Menu getMenu() {
		return menu;
	}

	public int getNumEmptyTables() {
		return numEmptyTables;
	}

	public void setNumEmptyTables(int numEmptyTables) {
		this.numEmptyTables = numEmptyTables;
	}
}