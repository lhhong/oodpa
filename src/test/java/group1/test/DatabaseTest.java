package group1.test;

import group1.commons.Database;
import group1.menu.FoodItem;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * Created by low on 4/11/16 12:55 PM.
 */
public class DatabaseTest {
	@Ignore
	@Test
	public void upload() {
		FoodItem item = new FoodItem("names", "desc", 148) {
			@Override
			public void getDetails() {

			}
		};
		Database.save(item);
	}

	@Ignore
	@Test
	public void read() {
		FoodItem food = (FoodItem) Database.read();
		assert food != null;
		System.out.println(food.toString());
	}
}
