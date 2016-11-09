package group1.storage;

import group1.menu.Menu;
import group1.reports.Reports;
import group1.reservation.ReservationList;
import group1.restaurant.Staff;
import group1.restaurant.TableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * singleton storage class containing states of the restaurant
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class Cache implements Serializable{

	/**
	 * stores the date today
	 */
	private LocalDate currentDay;
	/**
	 * stores state of tables
	 */
	private TableList tables;
	/**
	 * stores the staff of the restaurant
	 */
	private ArrayList<Staff> staffs;
	/**
	 * stores the reservations
	 */
	private ReservationList reservations;
	/**
	 * stores the menu
	 */
	private Menu menu;
	/**
	 * stores the reports
	 */
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

	/**
	 * seperate method to initialize reports due to need for menu to be set up first
	 */
	public void setMockReports() {
		reports = new Reports(MockData.getReportList(this));
	}

	/**
	 * gets the current date
	 * @return today's date
	 */
	public LocalDate getCurrentDay() {
		return currentDay;
	}

	/**
	 * change today's date
	 * @param currentDay date to change to
	 */
	public void setCurrentDay(LocalDate currentDay) {
		this.currentDay = currentDay;
	}

	/**
	 * gets the menu storage element
	 * @return menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * gets the reservation storage element
	 * @return reservations
	 */
	public ReservationList getReservations() {
		return reservations;
	}

	/**
	 * gets the table storage element
	 * @return tables
	 */
	public TableList getTables() {
		return tables;
	}

	/**
	 * gets the staffs in the restaurants
	 * @return staffs
	 */
	public ArrayList<Staff> getStaffs() {
		return staffs;
	}

	/**
	 * gets the reports storage element
	 * @return reports
	 */
	public Reports getReports() {
		return reports;
	}
}
