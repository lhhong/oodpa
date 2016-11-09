package group1.menu;

import group1.commons.Money;
import group1.commons.MoneyFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Restaurant Menu containing Food Items
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class Menu implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(Menu.class);

    /**
     * Creates the Food Item Array lists for different types
     */
    private ArrayList<FoodItem> mains = new ArrayList<>();;
    private ArrayList<FoodItem> drinks = new ArrayList<>();;
    private ArrayList<FoodItem> desserts = new ArrayList<>();;
    private ArrayList<FoodItem> packages = new ArrayList<>();;

    /**
     * Constructor for empty menu
     */
    public Menu (){
    }

    /**
     * Construtor for pre-saved menu loaded from file
     * loaded from database
     * @param savedMenu loaded from file
     */
    public Menu(ArrayList<FoodItem> savedMenu){
        for (int i = 0; i<savedMenu.size(); i++) {
            createItem(savedMenu.get(i));
        }
    }

    /**
     * Prints out the menu
     */
    public void printMenu(){
        int count = 1;
        System.out.println("========================   MAINS   ========================");
        for(FoodItem item:mains){
            System.out.printf("("+count+") ");
            item.printDetails();
            count ++;
        }
        System.out.println("\n========================   Drinks   ========================");
        for(FoodItem item:drinks){
            System.out.printf("("+count+") ");
            item.printDetails();
            count ++;
        }
        System.out.println("\n========================   Desserts   ========================");
        for(FoodItem item:desserts){
            System.out.printf("("+count+") ");
            item.printDetails();
            count ++;
        }
        System.out.println("\n========================   Packages   ========================");
        for(FoodItem item:packages){
            System.out.printf("("+count+") ");
            item.printDetails();
            count ++;
        }
    }

    /**
     * Adds new items to the menu
     */
    public void addItem(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to add a \n(1) Main\n(2) Dessert\n(3) Drink");
        int input = sc.nextInt(); //userinput
        char type= '\u0000';
        String cases = null;

        switch(input){
            case 1:
                type = 'M';
                cases = "Main Course";
                break;
            case 2:
                type = 'D';
                cases = "Dessert";
                break;
            case 3:
                type = 'R';
                cases = "Drink";
                break;
            default:
                System.out.println("Invalid Input");
	            return;
        }
        sc.nextLine();//clear buffer
        System.out.println("What is the name of the new " + cases + "?");
        String name = sc.nextLine();
        System.out.println("What is the description of the new " + cases + "?");
        String description = sc.nextLine();
        System.out.println("What is the price of the new " + cases + "?");
        int price = -1;
        try {
            price = Money.parseString(sc.next());
        } catch (MoneyFormatException e) {
            e.getMessage();
        }
        createItem(new AlaCarte(name,type,description,price));
        //sc.close();
    }

    /**
     * Adds new promotions to the menu
     */
    public void addPromotion() {
        Scanner sc = new Scanner(System.in);

        AlaCarte[] temp = new AlaCarte[3];

        for (int i = 1; i < 4; i++) {
            printMenu();
            System.out.println("Choose an AlaCarte number " + i + " for the package");
            int j = sc.nextInt();
            AlaCarte a = (AlaCarte) returnItem(j);
            temp[i - 1] = a;
        }
        sc.nextLine();//clear buffer
        System.out.println("What is the name of the new promotion?");
        String name = sc.nextLine();
        System.out.println("What is the description of the new promotion?");
        String description = sc.nextLine();
        System.out.println("What is the price of the new promotion?");
        int price = -1;
        try {
            price = Money.parseString(sc.next());
        } catch (MoneyFormatException e) {
            e.getMessage();
        }
        createItem(new PackageSet(name, temp, description, price));
    }

    /**
     * Creates food items
     * @param i item to be created
     */
    private void createItem(FoodItem i){
        returnArray(i).add(i);
    }

    /**
     * Removes items from the menu
     */
    public void removeItem(){
        printMenu();
        System.out.println("Which item would you like to remove?");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        FoodItem temp =returnItem(i);
        returnArray(temp).remove(temp);
    }

    /**
     * Updates menu items from the menu
     */
    public void updateItem(){
        printMenu();
        System.out.println("Select which item you would like to update");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        AlaCarte temp = (AlaCarte)returnItem(input);
        returnArray(temp).remove(temp);

        char type = temp.getType();
        sc.nextLine();//clear buffer
        System.out.println("What is the name of the updated item?");
        String name = sc.nextLine();
        System.out.println("What is the description of the new item?");
        String description = sc.nextLine();
        System.out.println("What is the price of the new item?");
        int price = -1;
        try {
            price = Money.parseString(sc.next());
        } catch (MoneyFormatException e) {
            e.getMessage();
        }
        createItem(new AlaCarte(name,type,description,price));
    }

    /**
     * Update Promotions from the menu
     */
    public void updatePromotion(){
        printMenu();
        System.out.println("Select which item you would like to update");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        FoodItem temp = returnItem(input);
        returnArray(temp).remove(temp);

        AlaCarte[] temps = new AlaCarte[3];
        for (int i=1;i<4;i++){
            printMenu();
            System.out.println("Choose an AlaCarte number " + i +" for the package");
            int j = sc.nextInt();
            AlaCarte a =(AlaCarte) returnItem(j);
            temps[i-1] = a;
        }

        sc.nextLine();//clear buffer
        System.out.println("What is the name of the updated promotion?");
        String name = sc.nextLine();
        System.out.println("What is the description of the new promotion?");
        String description = sc.nextLine();
        System.out.println("What is the price of the new promotion?");
        int price = -1;
        try {
            price = Money.parseString(sc.next());
        } catch (MoneyFormatException e) {
            e.getMessage();
        }
        createItem(new PackageSet(name,temps,description,price));

    }

    //usually preceded by printMenu(). input index on menu, return FoodItem;

    /**
     * Takes in an integer and locates the index that corresponds to the location of the Food Item
     * @param i of the food item
     * @return food item
     */
    public FoodItem returnItem(int i){
        i -= 1;
        if (i<mains.size())
            return mains.get(i);
        i -= mains.size();
        if (i<drinks.size())
            return drinks.get(i);
        i -= drinks.size();
        if (i<desserts.size())
            return desserts.get(i);
        i -= desserts.size();
        return packages.get(i);
    }

    /**
     * returns the number of food items in menu
     */
    public int size(){
        return (mains.size()+drinks.size()+desserts.size()+packages.size());
    }

    /**
     * Input food item i, returns the Array List which is located in
     * @param i index of food item
     * @return the Array List food item is located in
     */
    private ArrayList<FoodItem> returnArray(FoodItem i){
        if (i instanceof PackageSet)
            return packages;
        else if (((AlaCarte) i).getType() == 'M')
            return mains;
        else if (((AlaCarte) i).getType() == 'R')
            return drinks;
        else if (((AlaCarte) i).getType() == 'D')
            return desserts;
        else
            return null;
    }
}