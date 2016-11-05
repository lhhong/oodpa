package group1.commons;

import group1.reservation.Reservation;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by low on 5/11/16 10:09 AM.
 */
public class ReservationList implements Serializable{
	private LinkedList<ArrayList<Reservation>> reservations;

	private int getIndex(Reservation reservation) {
		LocalDate currentDay = CacheService.getCache().getCurrentDay();
		return 0;
	}

	public void addReservation(Reservation reservation) {

	}
}
