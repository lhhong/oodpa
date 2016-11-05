package group1.storage;

/**
 * Created by low on 4/11/16 8:45 PM.
 */
public class FileWorker implements Runnable {

	@Override
	public void run() {
		while (true) {
			if (CacheService.getCache().menuNeedsFlush()) {
				Database.save(CacheService.getCache());
			}
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
