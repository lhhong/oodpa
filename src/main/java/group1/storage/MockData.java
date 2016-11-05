package group1.storage;

import group1.restaurant.Gender;
import group1.restaurant.Staff;

import java.util.ArrayList;

/**
 * Created by low on 4/11/16 11:47 PM.
 */
public class MockData {
	static ArrayList<Staff> getStaffs() {
		ArrayList<Staff> staffs = new ArrayList<>();
		staffs.add(new Staff("Robert", Gender.Male, 1, "waiter"));

		return staffs;
	}
}
