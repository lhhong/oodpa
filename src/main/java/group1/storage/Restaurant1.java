package group1.storage;


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

	/**
	 * get singleton instance of cache
	 * @return cache containing restaurant data
	 */
	public static Cache getCache() {
		if (cache == null) {
			cache = Database.read();
			if (cache == null) {
				cache = new Cache();
				cache.setMockReports();
			}
			else {
			}

		}
		return cache;
	}
}
