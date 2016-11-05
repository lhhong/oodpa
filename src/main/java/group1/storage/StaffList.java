package group1.storage;

import group1.restaurant.Staff;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by low on 5/11/16 10:57 AM.
 */
public class StaffList implements Serializable{
	ArrayList<Staff> staffs;

	StaffList() {
		staffs = MockData.getStaffs();
	}

	public ArrayList<Staff> getStaffs() {
		return staffs;
	}

}
