package group1.commons;

import group1.storage.Cache;
import group1.storage.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Updates reservation list every midnight
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class ReservationUpdateWorker implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ReservationUpdateWorker.class);

	/**
	 * runs worker to update reservation list pass midnight
	 */
	@Override
	public void run() {
		Cache cache = CacheService.getCache();

		while (true) {
			LocalDate currentDate = LocalDate.now();
			long dayDiff = DAYS.between(cache.getCurrentDay(), currentDate);
			logger.debug("Day difference: " + Long.toString(dayDiff));
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
				Thread.sleep(60000L);
			} catch (InterruptedException e) {
				logger.info("service interrupted, ending it now");
				break;
			}
		}
	}
}
