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
		staffs.add(new Staff("Joeline", Gender.Female, 2, "waiter"));
		staffs.add(new Staff("Timothy", Gender.Male, 3, "waiter"));

		return staffs;
	}

	static ArrayList<FoodItem> getFoodItems() {
		ArrayList<FoodItem> items = new ArrayList<>();
		items.add(new AlaCarte("Chicken Rice", 'M', "spicy and tender", 400));
		items.add(new AlaCarte("Ice Kachang", 'D', "cooling and sweet", 250));
		items.add(new AlaCarte("Ice Milo", 'R', "Cold Chocolate", 100));
		items.add(new AlaCarte("Fish Ball Noodles", 'M', "Noodles", 350));
		items.add(new AlaCarte("Red Ruby", 'D', "Thai Coconut Dessert", 300));
		items.add(new AlaCarte("Iced Tea", 'R', "Cold Milk Tea", 150));

		return items;
	}
}
