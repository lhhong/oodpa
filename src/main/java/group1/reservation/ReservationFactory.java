package group1.reservation;

import group1.storage.CacheService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDateTime;
import static group1.reservation.AMPM.AMSLOT;
import static group1.reservation.AMPM.PMSLOT;
import static group1.storage.CacheService.getCache;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by jorda on 4/11/2016.
 */
public class ReservationFactory extends Exception{



    public static AMPM getTimeSlot(LocalDateTime date){

        LocalDate currentDate = getCache().getCurrentDay();


       LocalDate specifiedDate = date.toLocalDate();

        int dayDifference = (int) DAYS.between(currentDate, specifiedDate);

        //check if reservation is within 1 month
try{
        if (dayDifference < 0 || dayDifference > 30) {
       throw new NotInMonthException();
        }}catch(NotInMonthException e){
    System.out.println(e.getMessage());

        }

//check if reservation between 9.00 - 17.00
        try {
            if (date.toLocalTime().compareTo(LocalTime.of(17,0))== 1) {
                throw new NotInOperationException();
            } else if (date.toLocalTime().compareTo(LocalTime.of(9,0))== -1) {
                throw new NotInOperationException();
            }
        }catch(NotInOperationException e){
            System.out.println(e.getMessage());
        }

        //return AM or PM
        if(date.toLocalTime().compareTo(LocalTime.NOON)==-1){
            return AMSLOT;
        }else{
            return PMSLOT;
        }

    }

    public static int getIndex(Reservation reservation) {
        LocalDate currentDate = getCache().getCurrentDay();
        LocalDate reservationDate = reservation.getDate().toLocalDate();
        int dayDifference = (int) DAYS.between(currentDate, reservationDate);
        if (dayDifference < 0 || dayDifference > 30) {
            return -1;
        }

        switch (reservation.getTimeslot()) {
            case AMSLOT:
                return dayDifference*2;
            case PMSLOT:
                return dayDifference*2 +1;
        }
        return -1;
    }

    public static int getTable(int index, int pax){
        ArrayList<Reservation> indexReservation;
        indexReservation = CacheService.getCache().getReservations().indexReservation(index);



return -1;
    }
}
