package group1.storage;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by low on 4/11/16 8:45 PM.
 */
public class FileWorker implements Runnable {

	@Override
	public void run() {
		while (true) {
			if (DAYS.between(LocalDate.now(), CacheService.getCache().getCurrentDay()) == 1) {

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
