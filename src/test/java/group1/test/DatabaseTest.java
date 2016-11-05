package group1.test;

import group1.commons.Cache;
import group1.commons.CacheService;
import group1.commons.Database;
import group1.menu.FoodItem;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;

/**
 * Created by low on 4/11/16 12:55 PM.
 */
public class DatabaseTest {
	@Ignore
	@Test
	public void upload() {
		Object item = null;
		Database.save(item);
	}

	@Ignore
	@Test
	public void read() {
		Object o = Database.read();
		Assert.assertNull(o);

	}




}
