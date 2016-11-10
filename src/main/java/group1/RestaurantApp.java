package group1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import group1.commons.ReservationUpdateWorker;
import group1.commons.ShutDown;
import group1.invoice.Invoice;
import group1.menu.FoodItem;
import group1.menu.Menu;
import group1.reservation.NotInMonthException;
import group1.reservation.NotInOperationException;
import group1.reservation.Reservation;
import group1.reservation.ReservationFactory;
import group1.restaurant.*;
import group1.storage.CacheService;

/**
 * Main class that incorporates all functions of the Restaurant RRPSS
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class RestaurantApp {
    private static Scanner userInput = new Scanner(System.in);

    // Printing Functions
    private static void print() {
        System.out.println();
    }
    private static void print(Object obj) {
        System.out.println(obj);
    }
    private static void printnb(Object obj) {
        System.out.print(obj);
    }

    private static Menu menu = CacheService.getCache().getMenu();

    private static void changeMenu(){
        print("Please select one of the following options:");
        print("(1) Create Menu item");
        print("(2) Update Menu item");
        print("(3) Remove Menu item");
        int inputItem = userInput.nextInt();
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
        int inputPromo = userInput.nextInt();
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
    private static void createOrder(){
        int pax;
        print("Do you have a reservation? 1) Yes, 2)No :");
        int choice = userInput.nextInt();
        if (choice == 1){
            print("Please enter your contact number: ");
            int contact = userInput.nextInt();
            LocalDateTime specificDate = LocalDateTime.now();
            pax = ReservationFactory.removeIndexReservation(specificDate, contact);
        }
        else{
            print("Please enter number of pax: ");
            pax = userInput.nextInt();
        }
        //if pax=-1: break
        Table assigned = TableFactory.assignTable(pax);
	    if (assigned == null) {
            print("No tables available right now");
		    return;
        }
        ArrayList<Staff> staffs = CacheService.getCache().getStaffs();
        int j = 1;
        for (Staff s : staffs){
            print(j+") "+s.toString());
            j++;
        }
        print("Enter staff number: ");
        int staffno = userInput.nextInt();
        Order neworder = new Order(staffs.get(staffno-1));
        assigned.newOrder(neworder);
        editOrder(assigned.getTableNumber());
    }
    private static void viewOrder(){
        Order o;
        try {
            o = getOrder();
        } catch (InvalidTableException e) {
            return;
        }
        o.printOrder();
    }
    private static void editOrder() {
        Order o;
        try {
            o = getOrder();
        } catch (InvalidTableException e) {
            return;
        }
        editOrder(o);
    }
    private static void editOrder(int tableNumber) {
        Order o = getOrder(tableNumber);
	    editOrder(o);
    }
    private static void editOrder(Order o){
        int choice;
        int item_choice;
        do {
            print("Would you like to add(1) or remove(2) item, view order(3), quit(4)");
            choice = userInput.nextInt();
            switch(choice){
                case 1:
                    menu.printMenu();
                    print("Which item to add?: ");
                    item_choice = userInput.nextInt();
                    FoodItem f = menu.returnItem(item_choice);
                    o.addItem(f);
                    break;
                case 2:
                    o.printOrder();
                    print("Which item to remove?: ");
                    item_choice = userInput.nextInt();
                    o.removeItem(item_choice);
                    break;
                case 3:
                    o.printOrder();
                    break;
                default:
                    print("Order edited");
            }
        } while( choice < 4 );
    }
    private static Order getOrder(int tableNumber) {
        ArrayList<Table> tables = CacheService.getCache().getTables().getTables();
        Table t = tables.get(tableNumber-1);
        return t.getOrder();
    }
    private static Order getOrder() throws InvalidTableException {
        print("Please enter your table number: ");
        int tableno = userInput.nextInt();
        Table t;
        try {
            t = CacheService.getCache().getTables().getTables().get(tableno);
        } catch (IndexOutOfBoundsException e) {
            print("No such table exist");
            throw new InvalidTableException();
        }
        if (!t.isOccupied()) {
            print("table is currently unoccupied");
            throw new InvalidTableException();
        }
	    return getOrder(tableno);
    }
    private static void createReservation(){
        int year, month, day, hour, minute, contact, pax;
        print("Input Year Month Day Hour(0-24) Minute e.g. 2016 11 19 13 30");
        year = userInput.nextInt();
        month = userInput.nextInt();
        day = userInput.nextInt();
        hour = userInput.nextInt();
        minute = userInput.nextInt();
        print("Input Name");
        String name = userInput.next();
        print("Input Contact number");
        contact = userInput.nextInt();
        print("Input Pax");
        pax = userInput.nextInt();
        LocalDateTime specificDate = LocalDateTime.of(year, month, day, hour, minute);
        try {
            new Reservation(specificDate, name, contact, pax);
        } catch (NotInMonthException | NotInOperationException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void updateReservation(){
        print("Please select one of the following options:");
        print("(1) Check a next reservation");
        print("(2) Check a Reservation Booking for a date");
        print("(3) Remove a Reservation Booking");
        int reserveBook = userInput.nextInt();
        switch (reserveBook) {
            case 1:
                LocalDateTime specificDate = LocalDateTime.now();
                ReservationFactory.printIndexReservation(specificDate);
                break;
            case 2:
                print("Input Year Month Day e.g. 2016 11 19 ");
                int year = userInput.nextInt();
                int month = userInput.nextInt();
                int day = userInput.nextInt();
                specificDate = LocalDateTime.of(year, month, day, 0, 0);
                ReservationFactory.printIndexReservation(specificDate);
                break;

            case 3:
                print("Input Year Month Day e.g. 2016 11 19 ");
                year = userInput.nextInt();
                month = userInput.nextInt();
                day = userInput.nextInt();
                specificDate = LocalDateTime.of(year, month, day, 0, 0);
                print("Current reservation for: " + specificDate);
                ReservationFactory.printIndexReservation(specificDate);
                print("Input contact number to remove reservation");
                int contact = userInput.nextInt();
                ReservationFactory.removeIndexReservation(specificDate, contact);
                break;
            default:
                print("Please input a valid choice");
        }
    }


    private static void printInvoice(){
        print("Please enter your table number: ");
        int tableno = userInput.nextInt();
        ArrayList<Table> tables = CacheService.getCache().getTables().getTables();
        Table t = tables.get(tableno-1);
        Invoice i = new Invoice(t);
        print(i.toString());
        CacheService.getCache().getReports().addInvoice(i);

    }
    private static void printReport(){

        print("Would you like a daily(1) or monthly(2) report?:");
        int choice = userInput.nextInt();
        if (choice==1){
            print("Input date of report in yyyy-mm-dd");
            String day = userInput.next();
	        try {
                CacheService.getCache().getReports().printReport(LocalDate.parse(day));
            } catch (DateTimeParseException e) {
                print("invalid date input");
            }
        }
        else {
            print("Input Year in YYYY format:");
            int year = userInput.nextInt();
            print("Input Month in mm format:");
            int month = userInput.nextInt();
            CacheService.getCache().getReports().printReport(year,month);
        }
    }

    public static void main(String[] args) {

	    //initializes cache
        CacheService.getCache();
        Runtime.getRuntime().addShutdownHook(new ShutDown());
        Thread t = new Thread(new ReservationUpdateWorker());
        t.start();

        print("Welcome to the OOP Restaurant");
        int choice;
        Scanner userinput = new Scanner(System.in);
        menu.printMenu();
        do {
            print();
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
                    viewOrder();
                    break;
                case 5:
                    editOrder();
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
                    printInvoice();
                    break;
                case 10:
                    printReport();
                    break;
                default:
                    print("Program exited.");
            }

        } while (choice < 11);
        t.interrupt();
    }

}

