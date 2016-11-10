package group1.commons;

import group1.storage.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * shutdown hook to flush data into a file
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class ShutDown extends Thread{

	private static final Logger logger = LoggerFactory.getLogger(ShutDown.class);

	/**
	 * flushes data into a file
	 */
	@Override
	public void run() {
		logger.info("flushing cache to server database");

		Database.flush();
		System.out.println("Data saved!");
	}
}
