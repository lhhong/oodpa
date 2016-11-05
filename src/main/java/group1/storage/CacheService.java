package group1.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by low on 4/11/16 9:56 PM.
 * Initialize a singleton instance of cache either by retrieving from database or from mock data initialization
 */
public class CacheService {

	private static Cache cache;
	private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

	/**
	 * get singleton instance of cache
	 * @return cache containing restaurant data
	 */
	public static Cache getCache() {
		if (cache == null) {
			logger.info("initializing cache. This may take some time, please wait a moment...");
			//cache = Database.read();
			if (cache == null) {
				cache = new Cache();
				logger.info("Cache initialized from Mock Data");
			}
			else {
				logger.info("Cache initialized from ftp server");
			}

		}
		return cache;
	}
}
