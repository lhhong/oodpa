package group1.menu;

import java.io.Serializable;

/**
 * Created by tzeyangng on 4/11/16.
 */

public class AlaCarte extends FoodItem implements Serializable {
    protected char type;

    public AlaCarte(String name, char type, String description, int price){
        super(name,description,price);
        this.type = type;
    }

    @Override
    public void getDetails() {
        // TODO Auto-generated method stub
        System.out.println(this.name +"\t"+ this.description +"\t"+ this.price);
    }

    public char getType(){
        return type;
    }

	/*public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}*/
}
