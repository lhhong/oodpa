package group1.storage;

import group1.menu.AlaCarte;
import group1.menu.FoodItem;
import group1.menu.Menu;
import group1.menu.PackageSet;
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
		AlaCarte item1 = new AlaCarte("Chicken Rice", 'M', "spicy and tender", 400);
		AlaCarte item2 = new AlaCarte("Ice Kachang", 'D', "cooling and sweet", 250);
		AlaCarte item3 = new AlaCarte("Ice Milo", 'R', "Cold Chocolate", 100);
		AlaCarte item4 = new AlaCarte("Fish Ball Noodles", 'M', "Noodles", 350);
		AlaCarte item5 = new AlaCarte("Red Ruby", 'D', "Thai Coconut Dessert", 300);
		AlaCarte item6 = new AlaCarte("Iced Tea", 'R', "Cold Milk Tea", 150);
		AlaCarte[] set1 = {item1,item2,item3};
		AlaCarte[] set2 = {item4,item5,item6};
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		items.add(new PackageSet("Chicken Rice Set",set1, "With Ice Milo and Ice Kachang", 1500));
		items.add(new PackageSet("Thai Food Set",set2, "With Red Ruby and Milk Tea", 1650));

		return items;
	}
}
