package group1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import group1.commons.ReservationUpdateWorker;
import group1.commons.ShutDown;
import group1.menu.Menu;
import group1.reservation.NotInMonthException;
import group1.reservation.NotInOperationException;
import group1.reservation.Reservation;
import group1.reservation.ReservationFactory;
import group1.restaurant.Staff;
import group1.restaurant.Table;
import group1.restaurant.TableFactory;
import group1.restaurant.Order;
import group1.restaurant.Staff;
import group1.storage.CacheService;

/**
 * Main class that incorporates all functions of the Restaurant RRPSS
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class RestaurantApp {
    static Scanner userinput = new Scanner(System.in);

    // Printing Functions
    static void print() {
        System.out.println();
    }
    static void print(Object obj) {
        System.out.println(obj);
    }
    static void printnb(Object obj) {
        System.out.print(obj);
    }

    private static Menu menu = new Menu();

    private static void changeMenu(){
        print("Please select one of the following options:");
        print("(1) Create Menu item");
        print("(2) Update Menu item");
        print("(3) Remove Menu item");
        int inputItem = userinput.nextInt();
        switch (inputItem) {
            case 1:
                menu.addItem();
                break;
            case 2:
                menu.updateItem();
                break;
            case 3:
                menu.removeItem();
                break;
            default:
                print("Please input a valid choice");
        }
    }
    private static void changePromotion(){
        print("Please select one of the following options:");
        print("(1) Create a promotion");
        print("(2) Update a promotion");
        print("(3) Remove a promotion");
        int inputPromo = userinput.nextInt();
        switch (inputPromo) {
            case 1:
                menu.addPromotion();
                break;
            case 2:
                menu.updatePromotion();
                break;
            case 3:
                menu.removeItem();
                break;
            default:
                print("Please input a valid choice");
        }

    }
    private static void createReservation(){
        int year, month, day, hour, minute, contact, pax;
        print("Input Year Month Day Hour(0-24) Minute e.g. 2016 11 19 13 30");
        year = userinput.nextInt();
        month = userinput.nextInt();
        day = userinput.nextInt();
        hour = userinput.nextInt();
        minute = userinput.nextInt();
        print("Input Name");
        String name = userinput.next();
        print("Input Contact number");
        contact = userinput.nextInt();
        print("Input Pax");
        pax = userinput.nextInt();
        LocalDateTime specificDate = LocalDateTime.of(year, month, day, hour, minute);
        try {
            Reservation reservation = new Reservation(specificDate, name, contact, pax);
        } catch (NotInMonthException e) {
            System.out.println(e.getMessage());

        } catch(NotInOperationException e){
            System.out.println(e.getMessage());

        }
    }
    private static void updateReservation(){
        print("Please select one of the following options:");
        print("(1) Check a next reservation");
        print("(2) Check a Reservation Booking for a date");
        print("(3) Remove a Reservation Booking");
        int reserveBook = userinput.nextInt();
        switch (reserveBook) {
            case 1:
                LocalDateTime specificDate = LocalDateTime.now();
                ReservationFactory.printIndexReservation(specificDate);
                break;
            case 2:
                print("Input Year Month Day e.g. 2016 11 19 ");
                int year = userinput.nextInt();
                int month = userinput.nextInt();
                int day = userinput.nextInt();
                specificDate = LocalDateTime.of(year, month, day, 0, 0);
                ReservationFactory.printIndexReservation(specificDate);
                break;

            case 3:
                print("Input Year Month Day e.g. 2016 11 19 ");
                year = userinput.nextInt();
                month = userinput.nextInt();
                day = userinput.nextInt();
                specificDate = LocalDateTime.of(year, month, day, 0, 0);
                print("Current reservation for: " + specificDate);
                ReservationFactory.printIndexReservation(specificDate);
                print("Input contact number to remove reservation");
                int contact = userinput.nextInt();
                ReservationFactory.removeIndexReservation(specificDate, contact);
                break;
            default:
                print("Please input a valid choice");
        }
    }
    private static void createOrder(){
        int pax = 0;
        print("Do you have a reservation? 1) Yes, 2)No :");
        int choice = userinput.nextInt();
        if (choice == 1){
            print("Please enter your contact number: ");
            int contact = userinput.nextInt();
            LocalDateTime specificDate = LocalDateTime.now();
            pax = ReservationFactory.removeIndexReservation(specificDate, contact);
            }
        else{
            print("Please enter number of pax: ");
            pax = userinput.nextInt();
        }
        //if pax=-1: break
        Table assigned = TableFactory.assignTable(pax);
        ArrayList<Staff> staffs = CacheService.getCache().getStaffs();
        int j = 1;
        for (Staff s : staffs){
            print(j+") "+s.toString());
            j++;
        }
        print("Enter staff number: ");
        int staffno = userinput.nextInt();
        Order neworder = new Order(staffs.get(staffno-1));
        assigned.newOrder(neworder);
    }
    private static void viewOrder(){
        print("Please enter your table number: ");
        int tableno = userinput.nextInt();
        ArrayList<Table> tables = CacheService.getCache().getTables().getTables();
        Table t = tables.get(tableno-1);
        t.getOrder().printOrder();
    }
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new ShutDown());
        Thread t = new Thread(new ReservationUpdateWorker());
        t.start();

        print("Welcome to the OOP Restaurant");
        int choice;
        Scanner userinput = new Scanner(System.in);

        do {
            print();
            menu.printMenu();
            print("\nPlease select one of the following options:");
            print("\n(1) Create/Update/Remove menu item");
            print("(2) Create/Update/Remove promotion");
            print("(3) Create new order");
            print("(4) View order");
            print("(5) Edit order");
            print("(6) Create reservation booking");
            print("(7) Check/Remove reservation booking");
            print("(8) Check table availability");
            print("(9) Print order invoice");
            print("(10) Print sale revenue report");
            print("(11) Exit");
            printnb("\nEnter your choice: ");
            choice = userinput.nextInt();
            switch (choice) {
                case 1:
                    changeMenu();
                    break;
                case 2:
                    changePromotion();
                    break;
                case 3:
                    createOrder();
                    break;
                case 4:
                    //view order
                    break;
                case 5:
                    //edit order
                    break;
                case 6:
                    createReservation();
                    break;
                case 7:
                    updateReservation();
                    break;
                case 8:
                    TableFactory.printAvailableTables();
                    break;
                case 9:
                    print("Which table number would you like to print an invoice for?");
                    choice = userinput.nextInt();

                    break;
                case 10:
                    //print sales revenue report
                    break;
                default:
                    print("Program exited.");
            }

        } while (choice < 11);
        t.interrupt();
    }

}

