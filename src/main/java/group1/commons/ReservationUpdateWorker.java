package group1.commons;

import group1.storage.Cache;
import group1.storage.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by low on 4/11/16 8:45 PM.
 */
public class ReservationUpdateWorker implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ReservationUpdateWorker.class);

	@Override
	public void run() {
		Cache cache = CacheService.getCache();

		while (true) {
			LocalDate currentDate = LocalDate.now();
			long dayDiff = DAYS.between(currentDate, cache.getCurrentDay());
			if (dayDiff == 1) {
				logger.info("one day have passed, updating reservations list");
				cache.getReservations().oneDayPassed();
				cache.setCurrentDay(currentDate);
			}
			else if (dayDiff !=0) {
				logger.warn("more than 1 day have passed");
				for (int i = 0; i < dayDiff; i++) {
					cache.getReservations().oneDayPassed();
				}
				cache.setCurrentDay(currentDate);
			}
			try {
				Thread.sleep(100000L);
			} catch (InterruptedException e) {
				logger.info("service interrupted, ending it now");
				break;
			}
		}
	}
}
