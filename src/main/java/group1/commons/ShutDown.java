package group1.commons;

import group1.storage.Cache;
import group1.storage.Database;


/**
 * shutdown hook to flush data into a file
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class ShutDown extends Thread{

	private Cache cache;

	public ShutDown(Cache cache) {
		this.cache = cache;
	}
	/**
	 * flushes data into a file
	 */
	@Override
	public void run() {

		Database.flush(cache);
		System.out.println("Data saved!");
	}
}
