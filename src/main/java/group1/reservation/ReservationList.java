package group1.reservation;

import group1.storage.CacheService;
import group1.storage.SynchroLock;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Storage object to hold reservations for next 30 days
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class ReservationList implements Serializable{

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
	void addReservation(Reservation reservation) {
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
			reservations.addLast(new ArrayList<>());
			reservations.addLast(new ArrayList<>());
		}
	}

	/**
	 * gets change lock instance
	 * @return synchronization lock instance
	 */
	SynchroLock getReservationsChangeLock() {
		return reservationsChangeLock;
	}

	/**
	 * gets reservaion at an index
	 * @param index index of reservation
	 * @return reservation list of the session
	 */
	ArrayList<Reservation> indexReservation(int index){

		return reservations.get(index);
	}

	public ArrayList<Integer> getReservedTablesInThisSession() {
		ReservationFactory.updateReservation();
		ArrayList<Reservation> indexReservation;
		int index;
        /*
         * Determine if it is AM or PM and set index to 0 or 1 respectively
         */
		if (LocalDateTime.now().toLocalTime().compareTo(LocalTime.NOON) == -1) {
			index = 0;
		} else {
			index = 1;
		}
        /*
         * Obtains the AM/PM reservation for the day
         */
		indexReservation = indexReservation(index);

		ArrayList<Integer> reservedTables = new ArrayList<>();
		for (Reservation current : indexReservation) {
			reservedTables.add(current.getTableIndex());
		}
		return reservedTables;
	}

	public void printIndexReservation(LocalDateTime date) {
		ReservationFactory.printIndexReservation(date, this);
	}



}
