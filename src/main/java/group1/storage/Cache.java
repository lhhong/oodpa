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
	 * stores state of tables
	 */
	private TableList tables;
	/**
	 * stores the staff of the restaurant
	 */
	private ArrayList<Staff> staffs;
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
		staffs = MockData.getStaffs();
		menu = new Menu(MockData.getFoodItems());
	}

	/**
	 * seperate method to initialize reports due to need for menu to be set up first
	 */
	void setMockReports() {
		reports = new Reports(MockData.getReportList(this));
	}


	/**
	 * gets the menu storage element
	 * @return menu
	 */
	public Menu getMenu() {
		return menu;
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
