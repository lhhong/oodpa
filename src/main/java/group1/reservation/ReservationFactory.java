package group1.reservation;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static group1.reservation.AMPM.AMSLOT;
import static group1.reservation.AMPM.PMSLOT;

/**
 * Created by jorda on 4/11/2016.
 */
public class ReservationFactory extends Exception{
    /* main could use this

           Scanner sc = new Scanner(System.in);
        System.out.println("Enter Reservation Date (dd.MM.yyyy.h.m)");
        inputDate = sc.next();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy.h.m");
        Date specifiedDate = null;
        try {
            specifiedDate = dateFormatter.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
     */


    public static AMPM getTimeSlot(Date date){

        Date now = new Date();//current date
        String inputDate;
        int day, month, curMonth, curDay, session,hour;

        Scanner sc = new Scanner(System.in);
       Date specifiedDate = date;

        //check that reservation is within a month
       SimpleDateFormat dateFormatter = new SimpleDateFormat("MM");
        month = Integer.parseInt(dateFormatter.format(specifiedDate));
        curMonth = Integer.parseInt(dateFormatter.format(now));

        dateFormatter = new SimpleDateFormat("dd");
        day = Integer.parseInt(dateFormatter.format(specifiedDate));
        curDay = Integer.parseInt(dateFormatter.format(now));


        int nextMonth;
        if (curMonth == 12) {
            nextMonth = 1;
        } else {
            nextMonth = curMonth + 1;
        }


        if (!((month == curMonth && day >= curDay) || (month == nextMonth && day <= curDay))) {

        } else {
       // throw new NotInMonthException();

        }


        dateFormatter = new SimpleDateFormat("h");
        hour = Integer.parseInt(dateFormatter.format(specifiedDate));
        if(hour<9){
          //  throw new NotInOperationException()
        }else if(hour>17){
            //  throw new otInOperationException()
        }else if(hour<12){
            return AMSLOT;
        }else{
            return PMSLOT;
        }

        return AMSLOT;//need remove

    }


    public static void getTable(Date date, int pax){


    }
}
