package group1.storage;

import group1.reservation.Reservation;

import java.io.Serializable;
import java.time.LocalDate;
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

	public void addReservation(Reservation reservation,int index) {

reservations.get(index).add(reservation);

	}

	public ArrayList<Reservation> indexReservation(int index){
		try{
		if(reservations.get(index)==null){

		}}catch(NullPointerException e){
			return null;
			}
		return reservations.get(index);
	}




}
