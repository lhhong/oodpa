package group1.commons;

import group1.storage.CacheService;
import group1.storage.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by low on 4/11/16 9:25 PM.
 */
public class ShutDown extends Thread{

	private static final Logger logger = LoggerFactory.getLogger(ShutDown.class);

	@Override
	public void run() {
		logger.info("running shutdown hook");

		//Database.flush();
	}
}
