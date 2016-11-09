package group1.reservation;

import group1.storage.SynchroLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Storage object to hold reservations for next 30 days
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class ReservationList implements Serializable{

	private static final Logger logger = LoggerFactory.getLogger(ReservationList.class);
	/**
	 * list of reservations stored by sessions
	 */
	private LinkedList<ArrayList<Reservation>> reservations;
	/**
	 * synchronization object
	 */
	private final SynchroLock reservationsChangeLock = new SynchroLock();

	/**
	 * Creates a new reservation list
	 */
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

	public SynchroLock getReservationsChangeLock() {
		return reservationsChangeLock;
	}

	public ArrayList<Reservation> indexReservation(int index){

		return reservations.get(index);
	}




}
