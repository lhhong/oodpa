package group1.storage;

import group1.reservation.Reservation;
import group1.reservation.ReservationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by low on 5/11/16 10:09 AM.
 * storage object to hold reservations for next 30 days
 */
public class ReservationList implements Serializable{

	private static final Logger logger = LoggerFactory.getLogger(ReservationList.class);
	private LinkedList<ArrayList<Reservation>> reservations;
	private final SynchroLock reservationsChangeLock = new SynchroLock();

	public ReservationList() {
		reservations = new LinkedList<>();
		for (int i=0; i<31; i++) {
			reservations.add(new ArrayList<>());
		}
	}

	/**
	 * Adds a reservation to the list
	 * @param reservation new reservation object to be added
	 */
	public void addReservation(Reservation reservation) {
		int index = ReservationFactory.getIndex(reservation);
		synchronized (reservationsChangeLock) {
			reservations.get(index).add(reservation);
		}
	}

	/**
	 * Removes a reservation from the list
	 * @param reservation existing reservation to be removed, should be of the same instance retrieved from cache
	 */
	public void removeReservation(Reservation reservation) {
		synchronized (reservationsChangeLock) {
			int index = ReservationFactory.getIndex(reservation);
			if (reservations.get(index).remove(reservation)) {
				return;
			}
			logger.error("tried to remove a reservation that is not present or index calculated wrongly");
		}
	}

	/**
	 * dequeue reservation list of the day and add a new reservation for the 30th day
	 */
	public void oneDayPassed() {
		synchronized (reservationsChangeLock) {
			reservations.removeFirst();
			reservations.removeFirst();
			reservations.addLast(new ArrayList<Reservation>());
			reservations.addLast(new ArrayList<Reservation>());
		}
	}

	public ArrayList<Reservation> indexReservation(int index){

		return reservations.get(index);
	}




}
