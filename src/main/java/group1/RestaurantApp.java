package group1;
import java.time.LocalDateTime;
import java.util.Scanner;

import group1.commons.ReservationUpdateWorker;
import group1.commons.ShutDown;
import group1.menu.Menu;
import group1.reservation.Reservation;
import group1.storage.CacheService;


public class RestaurantApp {
	static Scanner userinput = new Scanner(System.in);
	static void print() {System.out.println();}
	static void print(Object obj) {System.out.println(obj);}
	static void printnb(Object obj) {System.out.print(obj);}

	private static Menu menu = new Menu();


	public static void main(String[] args){

		Runtime.getRuntime().addShutdownHook(new ShutDown());
		new Thread(new ReservationUpdateWorker()).start();

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
			switch(choice){
				case 1:
					print("Please select one of the following options:");
					print("(1) Create Menu item");
					print("(2) Update Menu item");
					print("(3) Remove Menu item");
					int inputItem = userinput.nextInt();
					switch(inputItem){
						case 1:
							menu.addItem();
							break;
						case 2:
							menu.updateItem();
							break;
						case 3:
							menu.removeItem();
							break;
						default: print("Please input a valid choice");
					}
					break;
				case 2:
					print("Please select one of the following options:");
					print("(1) Create a promotion");
					print("(2) Update a promotion");
					print("(3) Remove a promotion");
					int inputPromo = userinput.nextInt();
					switch(inputPromo){
						case 1:
							menu.addItem();
							break;
						case 2:
							menu.updateItem();
							break;
						case 3:
							menu.removeItem();
							break;
						default: print("Please input a valid choice");
					}
					break;
				case 3:
					//create new order
					break;
				case 4:
					//view order
					break;
				case 5:
					//edit order
					break;
				case 6:
					int year,month,day,hour,minute,contact,pax;
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
					LocalDateTime specificDate = LocalDateTime.of(year,month,day,hour,minute);

					Reservation reservation = new Reservation(specificDate, name, contact, pax);


					break;
				case 7:
					print("Please select one of the following options:");
					print("(1) Check a Reservation Booking");
					print("(2) Remove a Reservation Booking");
					int reserveBook = userinput.nextInt();
					switch(reserveBook) {
						case 1:
							//check booking
							break;
						case 2:
							//remove booking
							break;
						default:
							print("Please input a valid choice");
					}
					break;
				case 8:
					//table availability
					break;
				case 9:
					//print order invoice
					break;
				case 10:
					//print sales revenue report
					break;
				default: print("Program exited.");
			}

		} while (choice < 11);
	}
}

