package group1.storage;

import group1.menu.AlaCarte;
import group1.menu.FoodItem;
import group1.menu.Menu;
import group1.restaurant.Gender;
import group1.restaurant.Staff;

import java.util.ArrayList;

/**
 * Created by low on 4/11/16 11:47 PM.
 * Class containing initialization of mock datas when app is ran for the first time
 */
public class MockData {

	//TODO: populate the mock datas

	static ArrayList<Staff> getStaffs() {
		ArrayList<Staff> staffs = new ArrayList<>();
		staffs.add(new Staff("Robert", Gender.Male, 1, "waiter"));

		return staffs;
	}

	static FoodItem[] getFoodItems() {
		FoodItem[] items = new FoodItem[1];
		items[0] = new AlaCarte("name", 'M', "so nice", 300);

		return items;
	}
}
