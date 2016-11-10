package group1.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Initialize a singleton instance of cache either by retrieving from database or from mock data initialization
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class Restaurant1 {

	/**
	 * singleton object of cache
	 */
	private static Cache cache;
	private static final Logger logger = LoggerFactory.getLogger(Restaurant1.class);

	/**
	 * get singleton instance of cache
	 * @return cache containing restaurant data
	 */
	public static synchronized Cache getCache() {
		if (cache == null) {
			logger.info("initializing cache. This may take some time, please wait a moment...");
			//cache = Database.read();
			if (cache == null) {
				cache = new Cache();
				cache.setMockReports();
				logger.info("Cache initialized from Mock Data");
			}
			else {
				logger.info("Cache initialized from ftp server");
			}

		}
		return cache;
	}
}
