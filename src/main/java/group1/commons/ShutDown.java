package group1.commons;

/**
 * Created by low on 4/11/16 9:25 PM.
 */
public class ShutDown extends Thread{

	@Override
	public void run() {
		Database.save(CacheService.getCache());
	}
}
