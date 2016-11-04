package group1.commons;

/**
 * Created by low on 4/11/16 9:56 PM.
 */
public class CacheService {

	private static Cache cache;

	public static Cache getCache() {
		if (cache == null) {
			Object o = Database.read();
			if (o != null) {
				cache = (Cache) o;
			}
			else {
				cache = new Cache();
			}
		}
		return cache;
	}
}
