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

	Cache() {
		currentDay = LocalDate.now();
		staffs = new StaffList();
		reservations = new ReservationList();
		tables = new TableList();
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

	public TableList getTables() {
		return tables;
	}

	public StaffList getStaffs() {
		return staffs;
	}
}
