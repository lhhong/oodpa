package group1.test;

import group1.commons.Money;
import group1.commons.MoneyFormatException;
import group1.storage.Cache;
import group1.storage.Database;
import org.junit.Ignore;
import org.junit.Test;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by low on 4/11/16 12:55 PM.
 */
public class DatabaseTest {

	@Ignore
	@Test
	public void testMoney() {
		try {
			Assert.assertEquals(Money.parseString("12.34"), 1234);
			Assert.assertEquals(Money.parseString("12"), 1200);
		} catch (MoneyFormatException e) {
			e.printStackTrace();
		}
		float money = (float) 14.233;
		Assert.assertEquals(Money.parseFloat(money), 1423);
	}

	@Ignore
	@Test
	public void time() {
		System.out.println(LocalDate.now());
	}

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
