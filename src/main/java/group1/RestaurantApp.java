package group1;
import java.util.Scanner;


/**
 * Created by low on 4/11/16 12:08 PM.
 */
public class RestaurantApp {
	static Scanner userinput = new Scanner(System.in);
	static void print() {System.out.println();}
	static void print(Object obj) {System.out.println(obj);}
	static void printnb(Object obj) {System.out.print(obj);}


	public static void main(String[] args){
		print("Welcome to the OOP Restaurant");
		int choice;
		Scanner userinput = new Scanner(System.in);

		do {
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
					//menu.updateMenu();
					break;
				case 2:
					//remove promotion
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
					//create reserve booking
					break;
				case 7:
					//edit reservation booking
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

