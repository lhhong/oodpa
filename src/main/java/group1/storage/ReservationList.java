package group1.storage;

import group1.reservation.Reservation;
import group1.reservation.ReservationFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by low on 5/11/16 10:09 AM.
 */
public class ReservationList implements Serializable{
	private LinkedList<ArrayList<Reservation>> reservations;

	public ReservationList() {
		reservations = new LinkedList<>();
		for (int i=0; i<31; i++) {
			reservations.add(new ArrayList<>());
		}
	}

	public void addReservation(Reservation reservation) {
		int index = ReservationFactory.getIndex(reservation);
		reservations.get(index).add(reservation);
	}

	public void oneDayPassed() {
		reservations.removeFirst();
		reservations.addLast(new ArrayList<Reservation>());
	}

	public ArrayList<Reservation> indexReservation(int index){
		return reservations.get(index);
	}




}
