package group1.reservation;

import java.io.Serializable;
import java.time.LocalDate;
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
	 * stores the date today
	 */
	private LocalDate currentDay;
	/**
	 * Creates a new reservation list
	 */
	public ReservationList() {
		currentDay = LocalDate.now();
		reservations = new LinkedList<>();
		for (int i=0; i<31; i++) {
			reservations.add(new ArrayList<>());
		}
	}

	public void addReservation(LocalDateTime date, String name, int contact,int pax) throws NotInMonthException, NotInOperationException {
		Reservation reservation = new Reservation(date, name, contact, pax, this);
		addReservation(reservation);
	}

	/**
	 * Adds a reservation to the list
	 * @param reservation new reservation object to be added
	 */
	private void addReservation(Reservation reservation) {
		int index = ReservationFactory.getIndex(reservation, this);
		reservations.get(index).add(reservation);
	}

	/**
	 * dequeue reservation list of the day and add a new reservation for the 30th day
	 */
	void oneDayPassed() {
		reservations.removeFirst();
		reservations.removeFirst();
		reservations.addLast(new ArrayList<>());
		reservations.addLast(new ArrayList<>());
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
		ReservationFactory.updateReservation(this);
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

	public int removeIndexReservation(LocalDateTime specificDate, int contact) {
			return ReservationFactory.removeIndexReservation(specificDate, contact, this);
	}

	/**
	 * gets the current date
	 * @return today's date
	 */
	LocalDate getCurrentDay() {
		return currentDay;
	}

	/**
	 * change today's date
	 * @param currentDay date to change to
	 */
	void setCurrentDay(LocalDate currentDay) {
		this.currentDay = currentDay;
	}

}
