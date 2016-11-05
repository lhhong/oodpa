package group1.reservation;

import group1.restaurant.Table;
import group1.storage.CacheService;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    public static int getIndex(LocalDateTime date) {
        LocalDate currentDate = getCache().getCurrentDay();
        LocalDate reservationDate = date.toLocalDate();
        int dayDifference = (int) DAYS.between(currentDate, reservationDate);
        if (dayDifference < 0 || dayDifference > 30) {
            return -1;
        }
        return dayDifference*2;



    }

    public static int getTable(int index, int pax){

        ArrayList<Table> tables = CacheService.getCache().getTables().getTables();

        ArrayList<Reservation> indexReservation;
        indexReservation = CacheService.getCache().getReservations().indexReservation(index);

    ArrayList<Integer> ReservedTables = new ArrayList<>();
    Iterator<Reservation> iter = indexReservation.iterator();
    while (iter.hasNext()) {
        Reservation current = iter.next();
        ReservedTables.add(current.getTableIndex());
    }
    int i = 1;
    for (Table t : tables) {
        if (t.getCapacity() >= pax && t.getCapacity() <= pax + 3 && t.getStatus() == 0) {
            if (!(ReservedTables.contains(i))) {
                System.out.println("TABLE alloc (delete this print later): " + i);
                return i;
            }


        }
        i++;
    }
        System.out.println("ERRORRRRRR  nothing reserved ast i:"+ i);
return -1;
    }


    public static void printIndexReservation(LocalDateTime date){
        ArrayList<Reservation> indexReservationAM, indexReservationPM;
        indexReservationAM = CacheService.getCache().getReservations().indexReservation(getIndex(date));
        System.out.println("looking at i:" + getIndex(date));
        ArrayList<String> ReservedTables = new ArrayList<>();
        Iterator<Reservation> iter = indexReservationAM.iterator();
        while (iter.hasNext()) {
            Reservation current = iter.next();
            ReservedTables.add(current.getName());
        }
        System.out.println("AM slot");
       System.out.println(ReservedTables);

        indexReservationPM = CacheService.getCache().getReservations().indexReservation(getIndex(date)+1);

        ReservedTables.clear();
        iter = indexReservationPM.iterator();
        while (iter.hasNext()) {
            Reservation current = iter.next();
            ReservedTables.add(current.getName());
        }
        System.out.println("PM slot");
        System.out.println(ReservedTables);


    }

   public static void removeIndexReservation(LocalDateTime specificDate,String name){
       int index = getIndex(specificDate);
       int i;
       for(i = index; i <= index+1;i++){
           ArrayList<Reservation> indexReservation = CacheService.getCache().getReservations().indexReservation(i);
           Iterator<Reservation> iter = indexReservation.iterator();
           int pos=0;
           System.out.println("looking at i:" + i);
           while (iter.hasNext()) {
               Reservation current = iter.next();
              if(current.getName()==name){
                  CacheService.getCache().getReservations().indexReservation(i).remove(pos);
                  System.out.println("removing reservation at pos:" + pos);

              };
              pos++;
           }

       }
       System.out.println("Could not find reservation");
    }

}
