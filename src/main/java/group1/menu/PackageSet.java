package group1.menu;

import java.io.Serializable;

/**
 * Created by tzeyangng on 4/11/16.
 */

public class PackageSet extends FoodItem implements Serializable {
    protected AlaCarte[] set;

    public PackageSet(String name, AlaCarte[] set, String description, int price){
        super(name,description,price);
        this.set = set;
    }

    @Override
    public void getDetails() {
        // TODO Auto-generated method stub
        System.out.println(this.name);
        for(AlaCarte item:set){
            System.out.println(item.name +"\t"+ item.description);
        }
        System.out.println("Price =" + this.price);
    }

}
