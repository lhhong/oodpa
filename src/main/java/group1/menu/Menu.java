package group1.menu;

/**
 * Created by low on 4/11/16 12:35 PM.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    protected ArrayList<FoodItem> mains = new ArrayList<>();
    protected ArrayList<FoodItem> drinks = new ArrayList<>();
    protected ArrayList<FoodItem> desserts = new ArrayList<>();
    protected ArrayList<FoodItem> packages = new ArrayList<>();

    //constructor for empty menu
    public Menu (){
    }

    //constructor for a pre-saved menu loaded from file
    public Menu(FoodItem[] savedMenu){
        for (int i = 0; i<savedMenu.length; i++)
            createItem(savedMenu[i]);
    }

    //prints out the menu
    public void printMenu(){
        int count = 1;
        System.out.println("========================   MAINS   ========================");
        for(FoodItem item:mains){
            System.out.printf("("+count+") ");
            item.getDetails();
            count ++;
        }
        System.out.println("\n========================   Drinks   ========================");
        for(FoodItem item:drinks){
            System.out.printf("("+count+") ");
            item.getDetails();
            count ++;
        }
        System.out.println("\n========================   Desserts   ========================");
        for(FoodItem item:desserts){
            System.out.printf("("+count+") ");
            item.getDetails();
            count ++;
        }
        System.out.println("\n========================   Packages   ========================");
        for(FoodItem item:packages){
            System.out.printf("("+count+") ");
            item.getDetails();
            count ++;
        }
    }

    public void addItem(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to add a \n(1) Main\n(2) Dessert\n(3) Drink\n(4) Package Set");
        //sc.nextLine();
        int input = sc.nextInt(); //userinput
        AlaCarte[] temp = new AlaCarte[3];
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
            case 4:
                cases = "Package";
                //AlaCarte[] temp = new AlaCarte[3];
                for (int i=1;i<4;i++){
                    printMenu();
                    System.out.println("Choose an AlaCarte number " + i +" for the package");
                    int j = sc.nextInt();
                    AlaCarte a =(AlaCarte) returnItem(j);
                    temp[i-1] = a;
                }
                break;
            default:
                //throw new ArrayIndexOutOfBoundsException();
        }
        sc.nextLine();//clear buffer
        System.out.println("What is the name of the new " + cases + "?");
        String name = sc.nextLine();
        System.out.println("What is the description of the new " + cases + "?");
        String description = sc.nextLine();
        System.out.println("What is the price of the new " + cases + "?");
        float price = sc.nextFloat();
        if (input == 4)
            createItem(new PackageSet(name,temp,description,price));
        else
            createItem(new AlaCarte(name,type,description,price));
        //sc.close();
    }

    public void addItem(FoodItem i){
        if (i instanceof AlaCarte)
            returnArray(i).add(i);
        else
            packages.add(i);
    }

    public void createItem(FoodItem i){
        returnArray(i).add(i);
    }

    public void removeItem(){
        printMenu();
        System.out.println("Which item would you like to remove?");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        FoodItem temp =returnItem(i);
        returnArray(temp).remove(temp);
    }

    public void updateItem(){
        printMenu();
        System.out.println("Select which item you would like to update");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        FoodItem temp = returnItem(input);
        input = returnTypeint(temp);
        returnArray(temp).remove(temp);

        AlaCarte[] temps = new AlaCarte[3];
        String cases = null;
        char type =' ';
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
            case 4:
                cases = "Package";
                for (int i=1;i<4;i++){
                    printMenu();
                    System.out.println("Choose an AlaCarte number " + i +" for the package");
                    int j = sc.nextInt();
                    AlaCarte a =(AlaCarte) returnItem(j);
                    temps[i-1] = a;
                }
                break;
            default:
                //throw new ArrayIndexOutOfBoundsException();
        }
        sc.nextLine();//clear buffer
        System.out.println("What is the name of the updated " + cases + "?");
        String name = sc.nextLine();
        System.out.println("What is the description of the new " + cases + "?");
        String description = sc.nextLine();
        System.out.println("What is the price of the new " + cases + "?");
        float price = sc.nextFloat();
        if (input == 4)
            createItem(new PackageSet(name,temps,description,price));
        else
            createItem(new AlaCarte(name,type,description,price));
    }

    //usually preceded by printMenu(). input index on menu, return FoodItem;
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

    //input FoodItem i, returns the ArrayList it is in
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

    //What does this do?
    private int returnTypeint(FoodItem i){
        if (i instanceof PackageSet)
            return 4;
        else if (((AlaCarte) i).getType() == 'M')
            return 1;
        else if (((AlaCarte) i).getType() == 'R')
            return 3;
        else if (((AlaCarte) i).getType() == 'D')
            return 2;
        else
            return 0;
    }

}