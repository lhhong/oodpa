package group1.storage;

import group1.menu.Menu;
import group1.reports.Reports;
import group1.reservation.ReservationList;
import group1.restaurant.Staff;
import group1.restaurant.Table;
import group1.restaurant.TableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cache implements Serializable{

	private LocalDate currentDay;

	private TableList tables;
	//private ArrayList<Table> tables;
	private ArrayList<Staff> staffs;
	private ReservationList reservations;
	private Menu menu;
	private Reports reports;

	/**
	 * constructor to initialize Cache with MockData
	 */
	Cache() {
		tables = new TableList();
		currentDay = LocalDate.now();
		staffs = MockData.getStaffs();
		reservations = new ReservationList();
		menu = new Menu(MockData.getFoodItems());
	}

	public void setMockReports() {
		reports = new Reports(MockData.getReportList(this));
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

	public ArrayList<Staff> getStaffs() {
		return staffs;
	}

	public Reports getReports() {
		return reports;
	}
}
