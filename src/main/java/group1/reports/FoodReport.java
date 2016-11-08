package group1.reports;

/**
 * Created by low on 8/11/16 6:46 PM.
 */
public class FoodReport {
	int cost;
	int quatity;

	public FoodReport() {
		cost = 0;
		quatity = 0;
	}

	public void increment(int cost) {
		this.cost += cost;
		quatity++;
	}
}
