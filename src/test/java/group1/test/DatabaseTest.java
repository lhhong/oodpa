package group1.test;

import group1.storage.Cache;
import group1.storage.Database;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by low on 4/11/16 12:55 PM.
 */
public class DatabaseTest {
	@Ignore
	@Test
	public void upload() {
		Cache item = null;
		Database.flush();
	}

	@Ignore
	@Test
	public void read() {
		Object o = Database.read();
		Assert.assertNull(o);

	}




}
